package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CombinedShipTest {
  @Test
  public void test_constructor() {
    CombinedShip<Character> ship = new CombinedShip<Character>("Battleship", 's', '*');
    assertFalse(ship.getCoordinates().iterator().hasNext());
    assertEquals("Battleship", ship.getName());
  }

  @Test
  public void test_add_composition_ship() {
    CombinedShip<Character> ship = new CombinedShip<Character>("Battleship", 's', '*');
    V1ShipFactory rectangleShipFactory = new V1ShipFactory();
    Ship<Character> battleshipComposition1 = rectangleShipFactory.createShip(new Placement("B4V"), 1, 1, 'b', "Battleship");
    Ship<Character> battleshipComposition2 = rectangleShipFactory.createShip(new Placement("C3H"), 3, 1, 'b', "Battleship");
    ship.addCompositionShip(battleshipComposition1);
    ship.addCompositionShip(battleshipComposition2);
    for (Coordinate c: battleshipComposition1.getCoordinates()) {
      assertTrue(ship.occupiesCoordinates(c));
    }
    for (Coordinate c: battleshipComposition2.getCoordinates()) {
      assertTrue(ship.occupiesCoordinates(c));
    }
  }

}
