package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CoordinateTest {
  @Test
  public void test_get_row_and_column() {
    Coordinate testCoordinate = new Coordinate(3, 4);
    assertEquals(3, testCoordinate.getRow());
    assertEquals(4, testCoordinate.getColumn());
  }

  @Test
  public void test_equals() {
    Coordinate c1 = new Coordinate(0, 0);
    Coordinate c2 = new Coordinate(0, 0);
    Coordinate c3 = new Coordinate(1, 5);
    Coordinate c4 = new Coordinate(1, 5);
    Coordinate c5 = new Coordinate(5, 1);
    assertTrue(c1.equals(c2));
    assertTrue(c3.equals(c4));
    assertFalse(c2.equals(c5));
    assertFalse(c5.equals(c4));
    Board b = new BattleShipBoard(3, 4);
    assertFalse(c4.equals(b));
  }
}
