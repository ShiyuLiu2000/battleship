package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_get_width_and_height() {
    BattleShipBoard testBoard = new BattleShipBoard(10, 20);
    assertEquals(10, testBoard.getWidth());
    assertEquals(20, testBoard.getHeight());
  }
}
