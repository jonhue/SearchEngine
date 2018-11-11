import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CRCTest {
  private CRC crc;

  @Before
  public void setUp() {
    crc = new CRC(Integer.parseInt("100110", 2));
  }

  @Test
  public void getDegree() {
    final int expected = 5;
    final int actual = crc.getDegree();

    assertEquals(expected, actual);
  }

  @Test
  public void crcASCIIString_exampleOne() {
    final int expected = 26;
    final int actual = new CRC(Integer.parseInt("100110", 2)).crcASCIIString("az");

    assertEquals(expected, actual);
  }

  @Test
  public void crcASCIIString_exampleTwo() {
    final int expected = 12;
    final int actual = new CRC(Integer.parseInt("100110", 2)).crcASCIIString("abc");

    assertEquals(expected, actual);
  }

  @Test
  public void crcASCIIString_exampleThree() {
    final int expected = 6;
    final int actual = new CRC(Integer.parseInt("11010", 2)).crcASCIIString("a");

    assertEquals(expected, actual);
  }

  @Test
  public void crcASCIIString_exampleFour() {
    final int expected = 1;
    final int actual = new CRC(Integer.parseInt("1101", 2)).crcASCIIString("L");

    assertEquals(expected, actual);
  }

  @Test
  public void crcASCIIString_exampleFive() {
    final int expected = 5;
    final int actual = new CRC(Integer.parseInt("10001", 2)).crcASCIIString("A");

    assertEquals(expected, actual);
  }

  @Test
  public void crcASCIIString_exampleSix() {
    final int expected = 3;
    final int actual = new CRC(Integer.parseInt("1001", 2)).crcASCIIString(".");

    assertEquals(expected, actual);
  }

  @Test
  public void crcASCIIString_exampleSeven() {
    final int expected = 34;
    final int actual = new CRC(Integer.parseInt("1001101", 2)).crcASCIIString("Hell");

    assertEquals(expected, actual);
  }

  @Test
  public void crcASCIIString_exampleEight() {
    final int expected = 160;
    final int actual = new CRC(Integer.parseInt("1001111110", 2)).crcASCIIString("yes!");

    assertEquals(expected, actual);
  }
}
