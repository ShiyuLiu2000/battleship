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
}
