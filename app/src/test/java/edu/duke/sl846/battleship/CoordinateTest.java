package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CoordinateTest {
  Coordinate c1 = new Coordinate(0, 0);
  Coordinate c2 = new Coordinate(0, 0);
  Coordinate c3 = new Coordinate(1, 5);
  Coordinate c4 = new Coordinate(1, 5);
  Coordinate c5 = new Coordinate(5, 1);

  @Test
  public void test_get_row_and_column() {
    Coordinate testCoordinate = new Coordinate(3, 4);
    assertEquals(3, testCoordinate.getRow());
    assertEquals(4, testCoordinate.getColumn());
  }

  @Test
  public void test_equals() {
    assertTrue(c1.equals(c1)); // should be reflexive
    assertTrue(c1.equals(c2));
    assertTrue(c3.equals(c4));
    assertFalse(c2.equals(c5));
    assertFalse(c5.equals(c4));
    assertNotEquals(c4, "(1, 5)");
    assertEquals(c2, c1);
  }

  @Test
  public void test_toString() {
    assertEquals("(0, 0)", c2.toString());
    assertEquals(c3.toString(), "(1, 5)");
  }

  @Test
  public void test_hashCode() {
    assertEquals(c1.hashCode(), c2.hashCode());
    assertEquals(c3.hashCode(), c4.hashCode());
    assertNotEquals(c1.hashCode(), c3.hashCode());
    assertNotEquals(c5.hashCode(), c4.hashCode());
  }

  @Test
  void test_string_constructor_valid_cases() {
    Coordinate c1 = new Coordinate("B3");
    assertEquals(1, c1.getRow());
    assertEquals(3, c1.getColumn());
    Coordinate c2 = new Coordinate("D5");
    assertEquals(3, c2.getRow());
    assertEquals(5, c2.getColumn());
    Coordinate c3 = new Coordinate("A9");
    assertEquals(0, c3.getRow());
    assertEquals(9, c3.getColumn());
    Coordinate c4 = new Coordinate("Z0");
    assertEquals(25, c4.getRow());
    assertEquals(0, c4.getColumn());
  }

  @Test
  public void test_string_constructor_error_cases() {
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("00"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("AA"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("@0"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("[0"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A/"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A:"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A12"));
  }
}
