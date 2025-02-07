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

}
