import java.util.Arrays;

public class MergeSort {
  public static void main(String[] args) {
    int[] arr1 = {3, 4, 2, 7, 1, 0, 5};
    System.out.println("Unsorted: " + Arrays.toString(arr1));
    System.out.println("Sorted: " + Arrays.toString(mergeSortIt(arr1)));
    int[] arr2 = {250, 345, 34, 123, 2};
    System.out.println("Unsorted: " + Arrays.toString(arr2));
    System.out.println("Sorted: " + Arrays.toString(mergeSortIt(arr2)));
    int[] arr3 = {16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    System.out.println("Unsorted: " + Arrays.toString(arr3));
    System.out.println("Sorted: " + Arrays.toString(mergeSortIt(arr3)));
  }

  public static int[] mergeSortIt(int[] arr) {
    for (int maxSortedPartLength = 1; maxSortedPartLength < arr.length; maxSortedPartLength *= 2) {
      int startPos = 0;
      while (startPos < arr.length) {
        int endPos = endPos(startPos, maxSortedPartLength, arr.length);
        java.lang.System.arraycopy(merge(arr, startPos, dividePos(startPos, maxSortedPartLength, arr.length), endPos), 0, arr, startPos, endPos - startPos);
        startPos = endPos;
      }
    }

    return arr;
  }

  private static int[] merge(int[] arr, int startPos, int dividePos, int endPos) {
    int[] b = new int[endPos - startPos];
    int k = 0;

    int i = startPos;
    int j = dividePos;
    while (k < endPos) {
      if (i == dividePos) {
        java.lang.System.arraycopy(arr, j, b, k, endPos - j);
        k = endPos;
      } else if (j == endPos) {
        java.lang.System.arraycopy(arr, i, b, k, dividePos - i);
        k = endPos;
      } else {
        if (arr[i] < arr[j]) {
          b[k++] = arr[i++];
        } else {
          b[k++] = arr[j++];
        }
      }
    }

    return b;
  }

  private static int dividePos(int startPos, int maxSortedPartLength, int arrLength) {
    if (startPos + maxSortedPartLength >= arrLength)
      return arrLength;
    else if (startPos + 2 * maxSortedPartLength >= arrLength)
      return startPos + maxSortedPartLength;
    else
      return startPos + maxSortedPartLength;
  }

  private static int endPos(int startPos, int maxSortedPartLength, int arrLength) {
    if (startPos + 2 * maxSortedPartLength >= arrLength)
      return arrLength;
    else
      return startPos + 2 * maxSortedPartLength;
  }
}
