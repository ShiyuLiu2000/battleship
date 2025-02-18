package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CombinedShipTest {
  @Test
  public void test_constructor() {
    V2ShipFactory factory = new V2ShipFactory();
    Ship<Character> ship = factory.makeBattleship(new Placement("b2d"));
    ArrayList<Coordinate> orderedCoordinates = new ArrayList<>();
    for (Coordinate c: ship.getCoordinates()) {
      orderedCoordinates.add(c);
    }
    assertEquals(4, orderedCoordinates.size());
    assertEquals(1, orderedCoordinates.get(2).getRow());
    assertEquals(3, orderedCoordinates.get(2).getColumn());
  }
}
