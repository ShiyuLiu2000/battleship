package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V1ShipFactoryTest {
  @Test
  public void test_make_ships() {
    V1ShipFactory factory = new V1ShipFactory();
    Placement vertical = new Placement("d5v");
    Placement horizontal = new Placement("d5H");
    Ship<Character> submarine = factory.makeSubmarine(horizontal);
    Ship<Character> destroyer = factory.makeDestroyer(horizontal);
    Ship<Character> battleship = factory.makeBattleship(vertical);
    Ship<Character> carrier = factory.makeCarrier(vertical);

    Coordinate c1 = new Coordinate("d5");
    Coordinate c2 = new Coordinate("d6");
    Coordinate c3 = new Coordinate("d7");
    Coordinate c4 = new Coordinate("e5");
    Coordinate c5 = new Coordinate("f5");
    Coordinate c6 = new Coordinate("g5");
    Coordinate c7 = new Coordinate("h5");
    Coordinate c8 = new Coordinate("i5");

    checkShip(submarine, "Submarine", 's', c1, c2);
    checkShip(destroyer, "Destroyer", 'd', c1, c2, c3);
    checkShip(battleship, "Battleship", 'b', c1, c4, c5, c6);
    checkShip(carrier, "Carrier", 'c', c1, c4, c5, c6, c7, c8);
  }

  private void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter,
      Coordinate... expectedLocs) {
    assertEquals(expectedName, testShip.getName());
    for (Coordinate c : expectedLocs) {
      assertTrue(testShip.occupiesCoordinates(c));
      assertEquals(expectedLetter, testShip.getDisplayInfoAt(c));
    }
  }
}
