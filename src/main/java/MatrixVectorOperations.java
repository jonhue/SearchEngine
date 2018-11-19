public class MatrixVectorOperations {
  public static double[] multiply(double[][] m, double[] v) {
    double[] result = new double[v.length];

    for (int i = 0; i < m.length; ++i)
      result[i] = scalarProduct(m[i], v);

    return result;
  }

  public static double cosineSimilarity(double[] v1, double[] v2) {
    return scalarProduct(v1, v2) / Math.sqrt(scalarProduct(v1, v1) * scalarProduct(v2, v2));
  }

  public static double[][] transpose(double[][] m) {
    double[][] result = new double[m[0].length][m.length];

    for (int i = 0; i < m.length; ++i)
      for (int j = 0; j < m[0].length; ++j)
        result[j][i] = m[i][j];

    return result;
  }

  public static double euclideanDistance(double[] v1, double[] v2) {
    double result = 0;

    for (int i = 0; i < v1.length; ++i)
      result += Math.pow(v1[i] - v2[i], 2);

    return Math.sqrt(result);
  }

  public static int[][] permutations(int n) {
    int[][] result = new int[factorial(n)][n];

    for (int i = 0; i < n; ++i)
      result[0][i] = i;
    java.lang.System.arraycopy(result[0], 0, result[1], 0, n);

    for (int i = 1; i < factorial(n); ++i) {
      // find the position of the last pair of numbers where the latter is larger
      int pos = -1;
      for (int j = n - 2; j >= 0 && pos == -1; --j)
        if (result[i][j] < result[i][j + 1])
          pos = j;

      // increment the number at the found position so that there exists no duplicate at smaller indices
      do {
        result[i][pos] = result[i][pos] + 1;
      } while (isInArray(result[i][pos], result[i], 0, pos));

      // add the remaining numbers (in ascending order)
      for (int j = pos + 1; j < n; ++j)
        for (int k = 0; k < n; ++k)
          if (!isInArray(k, result[i], 0, j)) {
            result[i][j] = k;
            break;
          }

      // create a copy of the current permutation to serve as a basis for the following
      if (i < factorial(n) - 1)
        java.lang.System.arraycopy(result[i], 0, result[i + 1], 0, n);
    }

    return result;
  }

  public static int sgn(int[] permutation) {
    int result = 1;
    int base = 1;

    for (int i = 0; i < permutation.length - 1; ++i)
      for (int j = permutation.length - 1; j > 0 && i < j; --j) {
        result *= (int) (((permutation[j] - permutation[i]) * 1.0 / (j - i)) * 1000);
        base *= 1000;
        if (result % 1000 == 0) {
          result /= 1000;
          base /= 1000;
        }
      }

    return (int) Math.round(result * 1.0 / base);
  }

  public static int determinant(int[][] m) {
    int result = 0;
    final int[][] permutations = permutations(m[0].length);

    for (int[] permutation : permutations)
      result += sgn(permutation) * something(m, permutation);

    return result;
  }

  private static double scalarProduct(double[] v1, double[] v2) {
    double result = 0;

    for (int i = 0; i < v1.length; ++i)
      result += v1[i] * v2[i];

    return result;
  }

  private static int factorial(int n) {
    if (n == 1)
      return 1;
    else
      return n * factorial(n - 1);
  }

  private static boolean isInArray(int n, int[] arr, int startPos, int endPos) {
    boolean result = false;

    for (int i = startPos; i < endPos && !result; ++i)
      if (arr[i] == n)
        result = true;

    return result;
  }

  private static int something(int[][] m, int[] permutation) {
    int result = 1;

    for (int i = 0; i < m[0].length; ++i)
      result *= m[i][permutation[i]];

    return result;
  }
}
