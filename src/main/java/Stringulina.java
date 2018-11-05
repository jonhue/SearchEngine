public class Stringulina {
  public static int substringPos(String haystack, String needle) {
    int pos = -1;
    for (int i = 0; i < haystack.length(); ++i) {
      if (isSubstringAtPos(haystack, needle, i)) {
        pos = i;
        break;
      }
    }

    return pos;
  }

  public static int countSubstring(String haystack, String needle) {
    int count = 0;
    for (int i = 0; i < haystack.length(); ++i) {
      if (isSubstringAtPos(haystack, needle, i)) {
        count = count + 1;
      }
    }

    return count;
  }

  private static boolean isSubstringAtPos(String haystack, String needle, int pos) {
    boolean isSubstringAtPos = true;
    for (int i = 0; i < needle.length(); ++i) {
      if (haystack.length() - 1 < pos + i || haystack.charAt(pos + i) != needle.charAt(i)) {
        isSubstringAtPos = false;
        break;
      }
    }

    return isSubstringAtPos;
  }

  public static boolean correctlyBracketed(String str) {
    int bracketsCount = 0;

    for (int i = 0; i < str.length(); ++i) {
      if (str.charAt(i) == '(') {
        bracketsCount = bracketsCount + 1;
      } else if (str.charAt(i) == ')') {
        bracketsCount = bracketsCount - 1;
      }

      if (bracketsCount < 0) break;
    }

    return bracketsCount == 0;
  }
}
