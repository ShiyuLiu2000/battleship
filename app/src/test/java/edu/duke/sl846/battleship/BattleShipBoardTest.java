package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_get_valid_width_and_height() {
    Board<Character> testBoard = new BattleShipBoard<Character>(10, 20);
    assertEquals(10, testBoard.getWidth());
    assertEquals(20, testBoard.getHeight());
  }

  @Test
  public void test_invalid_dimensions() {
    // JUnit will execute the lambda (second parameter) inside a try-catch, and make
    // sure it catches the exception class we specify
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-3, 5));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(5, -9));
  }

  public <T> void checkWhatIsAtBoard(BattleShipBoard<T> board, T[][] expected) {
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        assertEquals(board.whatIsAt(new Coordinate(i, j)), expected[i][j]);
      }
    }
  }

  @Test
  public void test_tryAddShip_and_whatIsAt() {
    BattleShipBoard<Character> board = new BattleShipBoard<>(1, 3);
    Character[][] expected = new Character[3][1];
    // the board should start empty
    checkWhatIsAtBoard(board, expected);
    // add a ship
    RectangleShip<Character> s1 = new RectangleShip<Character>(new Coordinate(2, 0), 's', '*');
    assertTrue(board.tryAddShip(s1));
    expected[2][0] = 's';
    checkWhatIsAtBoard(board, expected);
  }
}
