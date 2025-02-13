package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_makeBodyLine() {
    Board<Character> testBoard = new BattleShipBoard<Character>(3, 3, 'X');
    BoardTextView testView = new BoardTextView(testBoard);
    String expectedBodyLine = "  | |  ";
    assertEquals(expectedBodyLine, testView.makeBodyLineForSelf(0));
  }

  @Test
  public void test_makeEmptyBody() {
    Board<Character> testBoard = new BattleShipBoard<Character>(2, 2, 'X');
    BoardTextView testView = new BoardTextView(testBoard);
    String expectedBody = "A  |  A\n" + "B  |  B\n";
    assertEquals(expectedBody, testView.makeEmptyBody());
  }

  @Test
  public void test_invalid_board_size() {
    Board<Character> wideBoard = new BattleShipBoard<Character>(11, 20, 'X');
    Board<Character> tallBoard = new BattleShipBoard<Character>(5, 27, 'X');
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));
  }

  private void emptyBoardHelper(int width, int height, String expectedHeader, String expectedBody) {
    Board<Character> testBoard = new BattleShipBoard<Character>(width, height, 'X');
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
    String expectedBody = "A  | |  A\n" +
        "B  | |  B\n" +
        "C  | |  C\n" +
        "D  | |  D\n" +
        "E  | |  E\n";
    emptyBoardHelper(3, 5, expectedHeader, expectedBody);
  }

  private void nonEmptyBoardHelper(int width, int height, String expectedHeader, String expectedBody,
      Coordinate... coordinates) {
    Board<Character> testBoard = new BattleShipBoard<Character>(width, height, 'X');
    BoardTextView testView = new BoardTextView(testBoard);
    assertEquals(expectedHeader, testView.makeHeader());
    // add ships
    for (Coordinate c : coordinates) {
      RectangleShip<Character> s = new RectangleShip<Character>(c, 's', '*');
      assertNull(testBoard.tryAddShip(s));
    }
    assertEquals(expectedBody, testView.makeBodyForSelf());
    String expectedBoard = expectedHeader + expectedBody + expectedHeader;
    assertEquals(expectedBoard, testView.displayMyOwnBoard());
  }

  @Test
  public void test_display_3by5() {
    String expectedHeader = "  0|1|2\n";
    String expectedBody = "A  | |  A\n" +
        "B s| |  B\n" +
        "C  |s|  C\n" +
        "D  | |  D\n" +
        "E  | |s E\n";
    Coordinate c1 = new Coordinate("B0");
    Coordinate c2 = new Coordinate("C1");
    Coordinate c3 = new Coordinate("E2");
    nonEmptyBoardHelper(3, 5, expectedHeader, expectedBody, c1, c2, c3);
  }

  @Test
  public void test_display_3by5_enemy() {
    String expectedHeader = "  0|1|2\n";
    String expectedBody = "A  | |  A\n" +
        "B s| |  B\n" +
        "C  |X|  C\n" +
        "D  | |  D\n" +
        "E  | |  E\n";
    Coordinate c1 = new Coordinate("B0");
    Coordinate c2 = new Coordinate("C1");
    Board<Character> testBoard = new BattleShipBoard<Character>(3, 5, 'X');
    BoardTextView testView = new BoardTextView(testBoard);
    V1ShipFactory factory = new V1ShipFactory();
    testBoard.tryAddShip(factory.makeSubmarine(new Placement("b0v")));
    testBoard.fireAt(c1);
    testBoard.fireAt(c2);
    String expectedBoard = expectedHeader + expectedBody + expectedHeader;
    assertEquals(expectedBoard, testView.displayEnemyBoard());
  }
}
