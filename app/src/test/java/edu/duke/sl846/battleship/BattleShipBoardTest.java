package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_get_valid_width_and_height() {
    Board<Character> testBoard = new BattleShipBoard<Character>(10, 20, 'X');
    assertEquals(10, testBoard.getWidth());
    assertEquals(20, testBoard.getHeight());
  }

  @Test
  public void test_invalid_dimensions() {
    // JUnit will execute the lambda (second parameter) inside a try-catch, and make
    // sure it catches the exception class we specify
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-3, 5, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(5, -9, 'X'));
  }

  public <T> void checkWhatIsAtBoard(BattleShipBoard<T> board, T[][] expected) {
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        assertEquals(board.whatIsAtForSelf(new Coordinate(i, j)), expected[i][j]);
      }
    }
  }

  @Test
  public void test_tryAddShip_and_whatIsAt() {
    BattleShipBoard<Character> board = new BattleShipBoard<>(1, 3, 'X');
    Character[][] expected = new Character[3][1];
    // the board should start empty
    checkWhatIsAtBoard(board, expected);
    // add a ship
    RectangleShip<Character> s1 = new RectangleShip<Character>(new Coordinate(2, 0), 's', '*');
    assertNull(board.tryAddShip(s1));
    expected[2][0] = 's';
    checkWhatIsAtBoard(board, expected);
  }

  @Test
  public void test_fire_at() {
    Board<Character> board = new BattleShipBoard<>(10, 20, 'X');
    V1ShipFactory factory = new V1ShipFactory();
    Ship<Character> ship = factory.makeDestroyer(new Placement("A5V"));
    assertNull(board.tryAddShip(ship));
    assertFalse(board.isAllShipSunk());
    Ship<Character> nullShip = board.fireAt(new Coordinate("A0"));
    assertEquals(null, nullShip);
    assertEquals('X', board.whatIsAtForEnemy(new Coordinate("A0")));
    assertFalse(board.isAllShipSunk());
    Ship<Character> ship1 = board.fireAt(new Coordinate("A5"));
    assertSame(ship, ship1);
    assertFalse(ship.isSunk());
    assertFalse(board.isAllShipSunk());
    Ship<Character> ship2 = board.fireAt(new Coordinate("B5"));
    assertSame(ship, ship2);
    assertFalse(ship.isSunk());
    assertFalse(board.isAllShipSunk());
    Ship<Character> ship3 = board.fireAt(new Coordinate("C5"));
    assertSame(ship, ship3);
    assertTrue(ship.isSunk());
    assertTrue(board.isAllShipSunk());
  }
}
