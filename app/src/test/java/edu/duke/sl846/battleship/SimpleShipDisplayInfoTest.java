package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleShipDisplayInfoTest {
  @Test
  public void test_getInfo() {
    ShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', 'h');
    assertEquals('s', display.getInfo(null, false));
    assertEquals('h', display.getInfo(null, true));
  }

}
