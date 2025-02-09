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

  @Test
  public void test_hit_and_sunk() {
    Coordinate upperLeft = new Coordinate(2, 5);
    RectangleShip<Character> ship = new RectangleShip<Character>(upperLeft, 4, 4, 's', '*');
    assertThrows(IllegalArgumentException.class, () -> ship.recordHitAt(new Coordinate(1, 1)));
    assertThrows(IllegalArgumentException.class, () -> ship.wasHitAt(new Coordinate(1, 1)));
    Coordinate c = new Coordinate(3, 7);
    ship.recordHitAt(c);
    assertTrue(ship.wasHitAt(c));
    assertFalse(ship.wasHitAt(upperLeft));
    assertFalse(ship.isSunk());
    RectangleShip<Character> ship1by1 = new RectangleShip<Character>(upperLeft, 's', '*');
    ship.recordHitAt(upperLeft);
    assertFalse(ship1by1.isSunk());
    ship1by1.recordHitAt(upperLeft);
    assertTrue(ship1by1.isSunk());
  }
}
