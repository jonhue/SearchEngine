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
    String dividend = strToBinaryString(str) + repeat("0", getDegree());
    String divisor = Integer.toBinaryString(poly);

    return Integer.parseInt(polynomialDivision(dividend, divisor), 2);
  }

  public int getDegree() {
    return Integer.toBinaryString(poly).length() - 1;
  }

  /* Recursive implementation of polynomial division */
  private String polynomialDivision(String dividend, String divisor) {
    if (divisor.length() > dividend.length())
      return dividend;
    else
      return polynomialDivision(calculateNewDividend(dividend, divisor), divisor);
  }

  /* Calculates dividend for next iteration of polynomialDivision */
  private String calculateNewDividend(String dividend, String divisor) {
    return xorBinary(dividend, divisor) + Stringulina.substring(dividend, divisor.length(), dividend.length());
  }

  /* Takes two binary strings and returns the result of an XOR operation */
  private String xorBinary(String dividend, String divisor) {
    return Integer.toBinaryString(Integer.parseInt(Stringulina.substring(dividend, 0, divisor.length()), 2) ^ Integer.parseInt(divisor, 2));
  }

  /* Takes an ASCII string and returns a string representing its binary encoding */
  private static String strToBinaryString(String str) {
    String binary = "";
    for (int i = 0; i < str.length(); ++i) {
      binary = binary + Integer.toBinaryString((int) str.charAt(i));
    }
    return binary;
  }

  private static String repeat(String str, int n) {
    if (n == 1)
      return str;
    else
      return str + repeat(str, n - 1);
  }
}
