package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_makeBodyLine() {
    Board<Character> testBoard = new BattleShipBoard<Character>(3, 3);
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
    Board<Character> testBoard = new BattleShipBoard<Character>(2, 2);
    BoardTextView testView = new BoardTextView(testBoard);
    String expectedBody = "A  |  A\n" + "B  |  B\n";
    assertEquals(expectedBody, testView.makeEmptyBody());
  }

  @Test
  public void test_invalid_board_size() {
    Board<Character> wideBoard = new BattleShipBoard<Character>(11, 20);
    Board<Character> tallBoard = new BattleShipBoard<Character>(5, 27);
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));
  }

  private void emptyBoardHelper(int width, int height, String expectedHeader, String expectedBody) {
    Board<Character> testBoard = new BattleShipBoard<Character>(width, height);
    BoardTextView testView = new BoardTextView(testBoard);
    assertEquals(expectedHeader, testView.makeHeader());
    assertEquals(expectedBody, testView.makeEmptyBody());
    String expectedBoard = expectedHeader + expectedBody + expectedHeader;
    assertEquals(expectedBoard, testView.displayMyOwnBoard());
  }

  @Test
  public void test_display_empty_2by2() {
    String expectedHeader = "  0|1\n";
    String expectedBody = "A  |  A\n" + "B  |  B\n";
    emptyBoardHelper(2, 2, expectedHeader, expectedBody);
  }

  @Test
  public void test_display_empty_3by2() {
    String expectedHeader = "  0|1|2\n";
    String expectedBody = "A  | |  A\n" + "B  | |  B\n";
    emptyBoardHelper(3, 2, expectedHeader, expectedBody);
  }
  
  @Test
  public void test_display_empty_3by5() {
    String expectedHeader = "  0|1|2\n";
    String expectedBody =
      "A  | |  A\n" +
      "B  | |  B\n" +
      "C  | |  C\n" +
      "D  | |  D\n" +
      "E  | |  E\n";
    emptyBoardHelper(3, 5, expectedHeader, expectedBody);
  }
}
