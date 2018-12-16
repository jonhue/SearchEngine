import java.util.Arrays;
import java.util.Comparator;

public class LinkedDocumentCollection extends DocumentCollection {
  public String toString() {
    return "<LinkedDocumentCollection size=" + numDocuments() + ">";
  }

  public void prependDocument(Document document) {
    if (!(document instanceof LinkedDocument) || contains(document)) return;

    super.prependDocument(document);
  }

  public void appendDocument(Document document) {
    if (!(document instanceof LinkedDocument) || contains(document)) return;

    super.appendDocument(document);
  }

  public double getQueryRelevance(int index) {
    DocumentCollectionCell documentCollectionCell = getCell(index);
    if (documentCollectionCell == null) return -1.0;

    return documentCollectionCell.getRelevance();
  }

  public void match(String query) {
    prependDocument(new LinkedDocument("query", "query", "", "", new Date(), new Author("", "", new Date(), "", "a@b.de"), query));

    super.match();

    sortByRelevance(0.85, 0.6);
  }

  private double[] sortByRelevance(double dampingFactor, final double weightingFactor) {
    // Compute PageRank
    double[] pageRank = pageRank(dampingFactor);

    // Build array from list & reset list elements
    DocumentCollectionCell[] arr = toArray();
    for (int i = 0; i < arr.length; i++)
      arr[i].setRelevance(computeRelevance(arr[i], pageRank[i], weightingFactor));

    // Sort array
    Arrays.sort(arr, new Comparator<DocumentCollectionCell>() {
      public int compare(DocumentCollectionCell o1, DocumentCollectionCell o2) {
        return -Double.compare(o1.getRelevance(), o2.getRelevance());
      }
    });

    // Build new list
    fromArray(arr);

    // Return a sorted array with relevance values
    return relevanceArray(arr);
  }

  private double[] relevanceArray(DocumentCollectionCell[] arr) {
    double[] result = new double[arr.length];
    for (int i = 0; i < arr.length; i++)
      result[i] = arr[i].getRelevance();

    return result;
  }

  private double computeRelevance(DocumentCollectionCell documentCollectionCell, double pageRank, double weightingFactor) {
    return weightingFactor * documentCollectionCell.getSimilarity() + (1 - weightingFactor) * pageRank;
  }

  public void calculateIncomingLinks() {
    for (int i = 0; i < numDocuments(); ++i) {
      LinkedDocumentCollection outgoingLinks = ((LinkedDocument) get(i)).getOutgoingLinks();

      for (int j = 0; j < outgoingLinks.numDocuments(); ++j) {
        LinkedDocument linkedDocument = ((LinkedDocument) get(indexOf(outgoingLinks.get(j))));
        if (linkedDocument != null)
          linkedDocument.addIncomingLink(((LinkedDocument) get(i)));
      }
    }
  }

  public LinkedDocumentCollection crawl() {
    LinkedDocumentCollection resultCollection = new LinkedDocumentCollection();
    for (int i = 0; i < numDocuments(); ++i)
      resultCollection.appendDocument(get(i));

    crawl(resultCollection);

    return resultCollection;
  }

  private void crawl(LinkedDocumentCollection resultCollection) {
    boolean unknownDocuments = false;
    for (int i = resultCollection.numDocuments() - 1; i >= 0; --i) {
      LinkedDocumentCollection outgoingLinks = ((LinkedDocument) resultCollection.get(i)).getOutgoingLinks();

      for (int j = 0; j < outgoingLinks.numDocuments(); ++j) {
        if (resultCollection.indexOf(outgoingLinks.get(j)) == -1) {
          unknownDocuments = true;
          resultCollection.appendDocument(outgoingLinks.get(j));
        }
      }
    }

    if (unknownDocuments) crawl(resultCollection);
  }

  public double[] pageRankRec(double dampingFactor) {
    int[][] transitionMatrix = transitionMatrixRec();
    double[] result = new double[numDocuments()];

    for (int i = 0; i < numDocuments(); i++)
      result[i] = pageRankRec(transitionMatrix, i, dampingFactor, 0);

    return result;
  }

  public double pageRankRec(int[][] transitionMatrix, int i, double dampingFactor, int recDepth) {
    if (i >= numDocuments() || i < 0) return 0.0;
    if (recDepth >= 5) return 1.0 / numDocuments();

    double sum = 0;
    for (int j = 0; j < numDocuments(); j++) {
      if (transitionMatrix[i][j] == 0) continue;

      sum += pageRankRec(transitionMatrix, j, dampingFactor, recDepth + 1) / numOutgoingLinks(transitionMatrix, j);
    }

    return pageRankRecFactor(dampingFactor) * sum;
  }

  private int[][] transitionMatrixRec() {
    int[][] result = new int[numDocuments()][numDocuments()];

    for (int i = 0; i < numDocuments(); i++) {
      LinkedDocumentCollection outgoingLinks = ((LinkedDocument) get(i)).getOutgoingLinks();
      for (int j = 0; j < numDocuments(); j++)
        if (outgoingLinks.isEmpty() || outgoingLinks.contains(get(j)))
          result[j][i] = 1;
        else
          result[j][i] = 0;
    }

    return result;
  }

  private double pageRankRecFactor(double dampingFactor) {
    return (1 - dampingFactor) / numDocuments() + dampingFactor;
  }

  private int numOutgoingLinks(int[][] C, int i) {
    int result = 0;
    for (int j = 0; j < numDocuments(); j++)
      result += C[j][i];

    return result;
  }

  public double[] pageRank(double dampingFactor) {
    double[] result = new double[numDocuments()];
    for (int i = 0; i < numDocuments(); i++)
      result[i] = 0.25;
    double[][] pageRankMatrix = pageRankMatrix(transitionMatrix(), dampingFactor);

    for (int i = 0; i < Math.pow(numDocuments(), 3); i++)
      result = multiply(multiply(pageRankMatrix, pageRankMatrix), result);

    return result;
  }

  private double[][] transitionMatrix() {
    double[][] result = new double[numDocuments()][numDocuments()];

    for (int i = 0; i < numDocuments(); i++) {
      LinkedDocumentCollection outgoingLinks = ((LinkedDocument) get(i)).getOutgoingLinks();
      for (int j = 0; j < numDocuments(); j++)
        if (outgoingLinks.isEmpty())
          result[j][i] = 1.0 / numDocuments();
        else if (outgoingLinks.contains(get(j)))
          result[j][i] = 1.0 / outgoingLinks.numDocuments();
        else
          result[j][i] = 0.0;
    }

    return result;
  }

  private double[][] pageRankMatrix(double[][] transitionMatrix, double dampingFactor) {
    double[][] result = new double[numDocuments()][numDocuments()];

    for (int i = 0; i < numDocuments(); i++)
      for (int j = 0; j < numDocuments(); j++)
        result[i][j] = dampingFactor * transitionMatrix[i][j] + (1 - dampingFactor) / numDocuments();

    return result;
  }

  private static double[][] multiply(double[][] m1, double[][] m2) {
    double[][] result = new double[m1.length][m2.length];

    for (int i = 0; i < m1.length; i++)
      for (int j = 0; j < m2[0].length; j++)
        for (int k = 0; k < m1[0].length; k++)
          result[i][j] += m1[i][k] * m2[k][j];

    return result;
  }

  private static double[] multiply(double[][] m, double[] v) {
    double[] result = new double[v.length];

    for (int i = 0; i < m.length; ++i)
      result[i] = scalarProduct(m[i], v);

    return result;
  }

  private static double scalarProduct(double[] v1, double[] v2) {
    double result = 0;

    for (int i = 0; i < v1.length; ++i)
      result += v1[i] * v2[i];

    return result;
  }
}
