package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

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
    RectangleShip<Character> ship = new RectangleShip<Character>("submarine", upperLeft, 4, 5, 's', '*');
    assertEquals("submarine", ship.getName());
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 4; j++) {
        assertTrue(ship.occupiesCoordinates(new Coordinate(3 + i, 2 + j)));
      }
    }
  }

  @Test
  public void test_hit_and_sunk_and_display() {
    Coordinate upperLeft = new Coordinate(2, 5);
    RectangleShip<Character> ship = new RectangleShip<Character>("submarine", upperLeft, 4, 4, 's', '*');
    assertThrows(IllegalArgumentException.class, () -> ship.recordHitAt(new Coordinate(1, 1)));
    assertThrows(IllegalArgumentException.class, () -> ship.wasHitAt(new Coordinate(1, 1)));
    Coordinate c = new Coordinate(3, 7);
    ship.recordHitAt(c);
    assertTrue(ship.wasHitAt(c));
    assertFalse(ship.wasHitAt(upperLeft));
    assertEquals('s', ship.getDisplayInfoAt(upperLeft, true));
    assertEquals('*', ship.getDisplayInfoAt(c, true));
    assertFalse(ship.isSunk());
    RectangleShip<Character> ship1by1 = new RectangleShip<Character>(upperLeft, 's', '*');
    ship.recordHitAt(upperLeft);
    assertFalse(ship1by1.isSunk());
    ship1by1.recordHitAt(upperLeft);
    assertTrue(ship1by1.isSunk());
  }

  @Test
  public void test_getCoordinates() {
    Coordinate c1 = new Coordinate(1, 2);
    RectangleShip<Character> ship = new RectangleShip<Character>("submarine", c1, 1, 5, 's', '*');
    Iterable<Coordinate> coordinates = ship.getCoordinates();
    assertTrue(coordinates.iterator().hasNext());
    int length = 0;
    for (Coordinate c: coordinates) {
      assertTrue(ship.occupiesCoordinates(c));
      length += 1;
    }
    assertEquals(5, length);
  }
}
