/*
 * This source file was generated by the Gradle 'init' task
 */
package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

class AppTest {
  @Disabled
  @Test
  @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
  public void test_main() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes, true);
    // get an InputStream for input.txt file
    InputStream input = getClass().getClassLoader().getResourceAsStream("input11.txt");
    assertNotNull(input);
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output11.txt");
    assertNotNull(expectedStream);
    InputStream oldIn = System.in;
    PrintStream oldOut = System.out;
    try {
      System.setIn(input);
      System.setOut(out);
      App.main(new String[0]);
    } finally {
      System.setIn(oldIn);
      System.setOut(oldOut);
    }
    // read all the data from expectedStream (output.txt)
    String expected = new String(expectedStream.readAllBytes());
    // get the String out of bytes
    String actual = bytes.toString();
    // finally, compare them
    assertEquals(expected, actual);
  }
}
