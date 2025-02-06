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
    String expectedBodyLineZ = "Z  | |  Z\n";
    assertEquals(expectedBodyLineZ, testView.makeBodyLine('Z'));
    assertThrows(IllegalArgumentException.class, () -> testView.makeBodyLine('a'));
    assertThrows(IllegalArgumentException.class, () -> testView.makeBodyLine('3'));
  }

  @Test
  public void test_makeBody() {
    Board testBoard = new BattleShipBoard(2, 2);
    BoardTextView testView = new BoardTextView(testBoard);
    String expectedBody = "A  |  A\n" + "B  |  B\n";
    assertEquals(expectedBody, testView.makeEmptyBody());
  }

  @Test
  public void test_invalid_board_size() {
    Board wideBoard = new BattleShipBoard(11, 20);
    Board tallBoard = new BattleShipBoard(5, 27);
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));
  }
  
  private void emptyBoardHelper(int width, int height, String expectedHeader, String expectedBody) {
    Board testBoard = new BattleShipBoard(width, height);
    BoardTextView testView = new BoardTextView(testBoard);
    assertEquals(expectedHeader, testView.makeHeader());
    assertEquals(expectedBody, testView.makeEmptyBody());
    String expectedBoard = expectedHeader + expectedBody + expectedHeader;
    assertEquals(expectedBoard, testView.displayMyOwnBoard());
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
