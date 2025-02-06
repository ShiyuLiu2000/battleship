package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_makeBodyLine() {
    Board testBoard = new BattleShipBoard(3, 3);
    BoardTextView testView = new BoardTextView(testBoard);
    String expectedBodyLineA = "A  | |  A\n";
    assertEquals(expectedBodyLineA, testView.makeBodyLine('A'));
    assertThrows(IllegalArgumentException.class, () -> testView.makeBodyLine('a'));
    assertThrows(IllegalArgumentException.class, () -> testView.makeBodyLine('3'));
  }
  
  @Test
  public void test_display_empty_2by2() {
    Board testBoard = new BattleShipBoard(2, 2);
    BoardTextView testView = new BoardTextView(testBoard);
    String expectedHeader = "  0|1\n";
    assertEquals(expectedHeader, testView.makeHeader());
    String expectedBodyLineA = "A  |  A\n";
    assertEquals(expectedBodyLineA, testView.makeBodyLine('A'));
    String expected =
      expectedHeader +
      "A  |  A\n" +
      "B  |  B\n" +
      expectedHeader;
    assertEquals(expected, testView.displayMyOwnBoard());
  }
}
