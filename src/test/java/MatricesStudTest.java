import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.Arrays;

public class MatricesStudTest {
  @Test
  public void multiply() {
    final double[][] matrix = {{1.5, 2.0, 1.0}, {2.0, 4.0, -1.0}, {3.2, -5.4, 1.6}};
    final double[] vector = {1.0, -2.0, 1.0};
    final double[] expected = {-1.5, -7.0, 15.6};
    final double[] actual = MatrixVectorOperations.multiply(matrix, vector);

    assertTrue(Arrays.equals(expected, actual));
  }

  @Test
  public void cosineSimilarity() {
    final double[] vector1 = {1.5, 2.0, 1.0};
    final double[] vector2 = {1.0, -2.0, 1.0};
    final double expected = -0.22742941307;
    final double actual = MatrixVectorOperations.cosineSimilarity(vector1, vector2);

    assertEquals(expected, actual, 0.00000000001);
  }

  @Test
  public void transpose() {
    final double[][] matrix = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};
    final double[][] expected = {{1.0, 4.0, 7.0}, {2.0, 5.0, 8.0}, {3.0, 6.0, 9.0}};
    final double[][] actual = MatrixVectorOperations.transpose(matrix);

    assertTrue(Arrays.deepEquals(expected, actual));
  }

  @Test
  public void euclideanDistance() {
    final double[] vector1 = {1.5, 2.0, 1.0};
    final double[] vector2 = {1.0, -2.0, 1.0};
    final double expected = 4.03112887415;
    final double actual = MatrixVectorOperations.euclideanDistance(vector1, vector2);

    assertEquals(expected, actual, 0.00000000001);
  }

  @Test
  public void permutations() {
    final int[][] expected = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
    final int[][] actual = MatrixVectorOperations.permutations(3);

    assertTrue(Arrays.deepEquals(expected, actual));
  }

  @Test
  public void sgn_example1() {
    final int[] permutation = {0, 1, 2};
    final int expected = 1;
    final int actual = MatrixVectorOperations.sgn(permutation);

    assertEquals(expected, actual);
  }

  @Test
  public void sgn_example2() {
    final int[] permutation = {3, 0, 1, 2};
    final int expected = -1;
    final int actual = MatrixVectorOperations.sgn(permutation);

    assertEquals(expected, actual);
  }

  @Test
  public void determinant() {
    final int[][] matrix = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
    final int expected = -3;
    final int actual = MatrixVectorOperations.determinant(matrix);

    assertEquals(expected, actual);
  }
}
