public class Primverquerung {
  public static void main(String args[]) {
    System.out.println("querPrim(1) -> 0: " + querPrim(0));
    System.out.println("querPrim(7) -> 2: " + querPrim(7));
    System.out.println("querPrim(8) -> 2: " + querPrim(8));
    System.out.println("querPrim(24) -> 2 + 11 + 13 + 17 + 19 = 62: " + querPrim(24));
  }

  public static int querPrim(int n) {
    if (n < 0) return 0;

    int sum = 0;
    for (int i = 1; i < n; ++i) {
      if (isPrime(i) && isDigitSumEven(i)) {
        sum = sum + i;
      }
    }

    return sum;
  }

  private static boolean isPrime(int n) {
    boolean isPrime = true;

    for (int i = 2; i < n; ++i) {
      if (n % i == 0) {
        isPrime = false;
        break;
      }
    }

    return isPrime;
  }

  private static boolean isDigitSumEven(int n) {
    int digitSum = calculateDigitSum(n);

    return digitSum % 2 == 0;
  }

  private static int calculateDigitSum(int n) {
    if (n < 10) {
      return n;
    } else {
      int lastDigit = n % 10;
      int firstDigits = n / 10;
      return lastDigit + calculateDigitSum(firstDigits);
    }
  }
}
