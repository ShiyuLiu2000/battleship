package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementTest {
  Coordinate c1 = new Coordinate(2, 4);

  @Test
  public void test_invalid_placement() {
    assertThrows(IllegalArgumentException.class, () -> new Placement(c1, 'a'));
    assertThrows(IllegalArgumentException.class, () -> new Placement(c1, '!'));
    assertThrows(IllegalArgumentException.class, () -> new Placement(c1, '0'));
    assertThrows(IllegalArgumentException.class, () -> new Placement(c1, ' '));
  }

  @Test
  public void test_get_where_and_orientation() {
    Placement p1 = new Placement(c1, 'v');
    Placement p2 = new Placement(c1, 'H');
    assertEquals("(2, 4)", p1.getWhere().toString());
    assertEquals("(2, 4)", p2.getWhere().toString());
    assertEquals('V', p1.getOrientation());
    assertEquals('H', p2.getOrientation());
  }
}
