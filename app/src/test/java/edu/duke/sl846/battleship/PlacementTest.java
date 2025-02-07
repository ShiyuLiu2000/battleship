package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementTest {
  @Test
  public void test_invalid_placement() {
    Coordinate c1 = new Coordinate(2, 4);
    assertThrows(IllegalArgumentException.class, () -> new Placement(c1, 'a'));
    assertThrows(IllegalArgumentException.class, () -> new Placement(c1, '!'));
    assertThrows(IllegalArgumentException.class, () -> new Placement(c1, '0'));
    assertThrows(IllegalArgumentException.class, () -> new Placement(c1, ' '));
  }

  @Test
  public void test_get_where_and_orientation() {
    Coordinate c1 = new Coordinate(2, 4);
    Placement p1 = new Placement(c1, 'v');
    Placement p2 = new Placement(c1, 'H');
    assertEquals("(2, 4)", p1.getWhere().toString());
    assertEquals("(2, 4)", p2.getWhere().toString());
    assertEquals('V', p1.getOrientation());
    assertEquals('H', p2.getOrientation());
  }

  @Test
  public void test_equals_and_hashcode_and_toString() {
    Coordinate c1 = new Coordinate(2, 4);
    Coordinate c2 = new Coordinate(5, 3);
    Placement p1 = new Placement(c1, 'h');
    Placement p2 = new Placement(c1, 'H');
    Placement p3 = new Placement(c2, 'v');
    Placement p4 = new Placement(c2, 'h');
    // equals
    assertEquals(p1, p2);
    assertEquals(p1, p1);
    assertNotEquals(p1, p4);
    assertNotEquals(p3, p4);
    assertNotEquals(p2, p4);
    assertNotEquals(p4, "(5, 3), H");
    // hashCode
    assertEquals(p1.hashCode(), p2.hashCode());
    assertNotEquals(p1.hashCode(), p4.hashCode());
    assertNotEquals(p3.hashCode(), p4.hashCode());
    // toString
    assertEquals("(2, 4), H", p1.toString());
    assertEquals("(2, 4), H", p2.toString());
    assertEquals("(5, 3), V", p3.toString());
    assertEquals("(5, 3), H", p4.toString());
  }
}
