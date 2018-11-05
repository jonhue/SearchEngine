import org.testng.annotations.*;
import org.testng.Assert;

public class StringulinaTest {
  @Test
  public void substringPos_noNeedleFound() {
    final int expected = -1;
    final int actual = Stringulina.substringPos("abc", "d");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void substringPos_needleFound() {
    final int expected = 3;
    final int actual = Stringulina.substringPos("abcd", "d");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void countSubstring_noOccurrences() {
    final int expected = 0;
    final int actual = Stringulina.countSubstring("aaaa", "b");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void countSubstring_someOccurrences() {
    final int expected = 3;
    final int actual = Stringulina.countSubstring("aaaa", "aa");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void correctlyBracketed_exampleOne() {
    final boolean expected = true;
    final boolean actual = Stringulina.correctlyBracketed("a(xx(]))");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void correctlyBracketed_exampleTwo() {
    final boolean expected = false;
    final boolean actual = Stringulina.correctlyBracketed("a(xx))");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void correctlyBracketed_exampleThree() {
    final boolean expected = false;
    final boolean actual = Stringulina.correctlyBracketed("a(xx)(");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void correctlyBracketed_exampleFour() {
    final boolean expected = false;
    final boolean actual = Stringulina.correctlyBracketed("a)xx()(");

    Assert.assertEquals(actual, expected);
  }
}
