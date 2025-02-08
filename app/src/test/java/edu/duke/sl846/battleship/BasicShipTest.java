package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BasicShipTest {
  @Test
  public void test_occupiesCoordinates() {
    Coordinate c1 = new Coordinate(1, 3);
    Coordinate c2 = new Coordinate(2, 4);
    BasicShip s1 = new BasicShip(c1);
    assertTrue(s1.occupiesCoordinates(c1));
    assertFalse(s1.occupiesCoordinates(c2));
  }

}
