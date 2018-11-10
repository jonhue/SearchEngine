public class CRC {
  private int poly;

  public static void main(String[] args) {
    CRC crc = new CRC(38);
    System.out.println("" + crc.crcASCIIString("az"));
  }

  public CRC(int poly) {
    this.poly = poly;
  }

  public int crcASCIIString(String str) {
    return Integer.parseInt(polynomialDivision(toBinary(strBinary(str)), toBinary(poly)), 2);
  }

  public int getDegree() {
    return toBinary(poly).length() - 1;
  }

  private String polynomialDivision(String dividend, String divisor) {
    if (divisor.length() > dividend.length()) {
      return dividend;
    }
    else {
      String xor = toBinary(Integer.parseInt(substring(dividend, 0, divisor.length()), 2) ^ Integer.parseInt(divisor, 2));
      dividend = xor + substring(dividend, divisor.length(), dividend.length());
      return polynomialDivision(dividend, divisor);
    }
  }

  private int strBinary(String str) {
    String binary = "";
    for (int i = 0; i < str.length(); ++i) {
      binary = binary + toBinary((int) str.charAt(i));
    }
    return Integer.parseInt(binary + strNTimes("0", getDegree()), 2);
  }

  private static int powerOf(int num, int n) {
    if (n == 0)
      return 1;
    else
      return num * powerOf(num, n - 1);
  }

  private static String toBinary(int n) {
    if (n == 1)
      return "1";
    else if (n == 0)
      return "0";
    else
      return toBinary(n / 2) + n % 2;
  }

  private static String strNTimes(String str, int n) {
    if (n == 1)
      return str;
    else
      return str + strNTimes(str, n - 1);
  }

  private static String substring(String str, int startPos, int endPos) {
    String newStr = "";
    for (int i = startPos; i < endPos; ++i)
      newStr = newStr + str.charAt(i);

    return newStr;
  }
}
