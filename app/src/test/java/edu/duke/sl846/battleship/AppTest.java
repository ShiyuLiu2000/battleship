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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

class AppTest {

  @Test
  void test_read_placement() throws IOException {
    // read from String like an input stream
    StringReader stringReader = new StringReader("B2V\nC8H\na4v\n");
    // use ByteArrayOutputStream to collect the output
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    // use PrintStream to write data into bytes instead of to screen
    // set autoflush to true to ensure data becomes immediately available in bytes
    PrintStream printStream = new PrintStream(bytes, true);
    // create a board and an App
    Board<Character> board = new BattleShipBoard<>(10, 20);
    App app = new App(board, stringReader, printStream);

    String prompt = "Please enter a location for a Ship: ";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');
    for (int i = 0; i < expected.length; i++) {
      Placement placement = app.readPlacement(prompt);
      assertEquals(placement, expected[i]); // did we get the right Placement back
      assertEquals(prompt + "\n", bytes.toString()); // should have printed prompt and newline
      bytes.reset(); // clear out bytes for next time around
    }
  }

  @Test
  public void test_doOnePlacement() throws IOException {
    StringReader stringReader = new StringReader("B2V\nC8H\na4v\n");
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<>(10, 20);
    App app = new App(board, stringReader, printStream);

    String prompt = "Where would you like to put your ship? ";
    for (int i = 0; i < 3; i++) {
      app.doOnePlacement();
      checkBoardOutputWithPrompt(bytes, board, prompt);
    }
  }

  private void checkBoardOutputWithPrompt(ByteArrayOutputStream bytes, Board<Character> board, String prompt) {
    BoardTextView view = new BoardTextView(board);
    assertEquals(prompt + "\n" + view.displayMyOwnBoard(), bytes.toString());
    bytes.reset();
  }

  @Test
  @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
  public void test_main() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes, true);
    // get an InputStream for input.txt file
    InputStream input = getClass().getClassLoader().getResourceAsStream("input1.txt");
    assertNotNull(input);
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output1.txt");
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
