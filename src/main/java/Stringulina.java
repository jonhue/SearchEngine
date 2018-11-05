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

  public static boolean matches(String str, String pattern) {
    return new Matches(str, pattern).matches();
  }
}

class Matches {
  private String str;
  private String pattern;
  private int strPos;
  private int patternPos;
  private boolean matches;

  public Matches(String str, String pattern) {
    this.str = str;
    this.pattern = pattern;
    this.strPos = 0;
    this.patternPos = 0;
    this.matches = true;
  }

  public boolean matches() {
    while (patternPos < pattern.length()) {
      if (pattern.charAt(patternPos) == '.' || str.charAt(strPos) == pattern.charAt(patternPos)) {
        ++patternPos;
        ++strPos;
      } else if (pattern.charAt(patternPos) == '{') {
        if (!matchesMultiplicity()) {
          matches = false;
          break;
        }
      } else {
        matches = false;
        break;
      }
    }

    return matches;
  }

  private boolean matchesMultiplicity() {
    boolean matches = true;
    int multiplicity = parseMultiplicity();
    if (pattern.charAt(patternPos - 1) != '.') {
      for (int i = strPos; i < strPos + multiplicity - 1; ++i) { // -1 as the first letter of multiplicity has already been checked
        if (str.charAt(i) != pattern.charAt(patternPos - 1)) {
          matches = false;
          break;
        }
      }
    }

    strPos = strPos + multiplicity - 1; // points to next char after multiplicity
    patternPos = patternPos + 2 + String.valueOf(multiplicity).length(); // skips declaration of multiplicity (e.g.: {1})
    return matches;
  }

  private int parseMultiplicity() {
    int endPos = patternPos + 2; // minimum endPos (one digit multiplicity)
    while (pattern.charAt(endPos) != '}') {
      ++endPos;
    }

    return Integer.parseInt(pattern.substring(patternPos + 1, endPos));
  }
}