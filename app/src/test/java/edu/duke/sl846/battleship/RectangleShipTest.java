package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  @Test
  public void test_makeCoordinates() {
    Coordinate upperLeft = new Coordinate(1, 2);
    HashSet<Coordinate> coordinates = RectangleShip.makeCoordinates(upperLeft, 2, 3);
    assertNotNull(coordinates);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        Coordinate c = new Coordinate(1 + i, 2 + j);
        assertTrue(coordinates.contains(c));
        coordinates.remove(c);
      }
    }
    assertTrue(coordinates.isEmpty());
  }

  @Test
  public void test_constructor() {
    Coordinate upperLeft = new Coordinate(3, 2);
    RectangleShip<Character> ship = new RectangleShip<Character>(upperLeft, 4, 5, 's', '*');
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 4; j++) {
        assertTrue(ship.occupiesCoordinates(new Coordinate(3 + i, 2 + j)));
      }
    }
  }
}
