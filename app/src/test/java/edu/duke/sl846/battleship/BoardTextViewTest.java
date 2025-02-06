package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_display_empty_2by2() {
    Board testBoard = new BattleShipBoard(2, 2);
    BoardTextView testView = new BoardTextView(testBoard);
    String expected =
      "  0|1\n" +
      "A  |  A\n" +
      "B  |  B\n" +
      "  0|1\n";
    assertEquals(expected, testView.displayMyOwnBoard());
  }

}
