import java.util.Arrays;

public class RecursiveDeterminant {
  public static void main(String[] args) {
    int[][] matrix1 = {{1, 2}, {3, 4}};
    System.out.println("det2x2(" + Arrays.deepToString(matrix1) + ") = " + det2x2(matrix1));

    int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    System.out.println("det3x3(" + Arrays.deepToString(matrix2) + ") = " + det3x3(matrix2));

    int[][] matrix3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    System.out.println("detNxN(" + Arrays.deepToString(matrix3) + ") = " + detNxN(matrix3));

    int[][] matrix4 = {{1, 3, 5, 9}, {1, 3, 1, 7}, {4, 3, 9, 7}, {5, 2, 0, 9}};
    System.out.println("detNxN(" + Arrays.deepToString(matrix4) + ") = " + detNxN(matrix4));
  }

  public static int det2x2(int[][] matrix) {
    if (matrix.length != 2 || matrix[0].length != 2) return -1;

    return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
  }

  public static int det3x3(int[][] matrix) {
    if (matrix.length != 3 || matrix[0].length != 3) return -1;

    return matrix[0][0] * det2x2(removeColumn(removeRow(matrix, 0), 0)) - matrix[0][1] * det2x2(removeColumn(removeRow(matrix, 0), 1)) + matrix[0][2] * det2x2(removeColumn(removeRow(matrix, 0), 2));
  }

  public static int detNxN(int[][] matrix) {
    if (matrix.length < 2) {
      return -1;
    } else if (matrix.length == 2) {
      return det2x2(matrix);
    } else {
      int sum = 0;
      for (int i = 0; i < matrix.length; i++) {
        int determinant = matrix[0][i] * detNxN(removeColumn(removeRow(matrix, 0), i));
        if (i % 2 == 0) {
          sum += determinant;
        } else {
          sum -= determinant;
        }
      }

      return sum;
    }
  }

  public static int[][] removeRow(int[][] matrix, int index) {
    int[][] result = new int[matrix.length - 1][matrix[0].length];

    int j = 0;
    for (int i = 0; i < result.length; i++) {
      if (index == j) j++;
      result[i] = matrix[j++];
    }

    return result;
  }

  public static int[][] removeColumn(int[][] matrix, int index) {
    int[][] result = new int[matrix.length][matrix[0].length - 1];

    for (int i = 0; i < result.length; i++) {
      int j = 0;
      for (int k = 0; k < matrix[0].length; k++) {
        if (index == k) continue;
        result[i][j++] = matrix[i][k];
      }
    }

    return result;
  }
}
