package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class V2ShipFactoryTest {
  @Test
  public void test_make_battleship() {
    V2ShipFactory factory = new V2ShipFactory();
    Placement up = new Placement("d5U");
    Placement right = new Placement("d5r");
    Placement down = new Placement("d5D");
    Placement left = new Placement("d5l");
    Ship<Character> battleshipUp = factory.makeBattleship(up);
    Ship<Character> battleshipRight = factory.makeBattleship(right);
    Ship<Character> battleshipDown = factory.makeBattleship(down);
    Ship<Character> battleshipLeft = factory.makeBattleship(left);

    Coordinate c1 = new Coordinate("d5");
    Coordinate c2 = new Coordinate("d6");
    Coordinate c3 = new Coordinate("d7");
    Coordinate c4 = new Coordinate("e5");
    Coordinate c5 = new Coordinate("e6");
    Coordinate c6 = new Coordinate("e7");
    Coordinate c7 = new Coordinate("f5");
    Coordinate c8 = new Coordinate("f6");
    checkShip(battleshipUp, "Battleship", 'b', c2, c4, c5, c6);
    checkShip(battleshipRight, "Battleship", 'b', c1, c4, c7, c5);
    checkShip(battleshipDown, "Battleship", 'b', c1, c2, c3, c5);
    checkShip(battleshipLeft, "Battleship", 'b', c2, c4, c5, c8);

    Placement invalid = new Placement("d5v");
    assertThrows(IllegalArgumentException.class, () -> factory.makeBattleship(invalid));
  }

  @Test
  public void test_make_carrier() {
    V2ShipFactory factory = new V2ShipFactory();
    Placement up = new Placement("d5U");
    Placement right = new Placement("d5r");
    Placement down = new Placement("d5D");
    Placement left = new Placement("d5l");
    Ship<Character> carrierUp = factory.makeCarrier(up);
    Ship<Character> carrierRight = factory.makeCarrier(right);
    Ship<Character> carrierDown = factory.makeCarrier(down);
    Ship<Character> carrierLeft = factory.makeCarrier(left);

    Coordinate c1 = new Coordinate("d5");
    Coordinate c2 = new Coordinate("e5");
    Coordinate c3 = new Coordinate("f5");
    Coordinate c4 = new Coordinate("g5");
    Coordinate c5 = new Coordinate("f6");
    Coordinate c6 = new Coordinate("g6");
    Coordinate c7 = new Coordinate("h6");
    Coordinate c8 = new Coordinate("d6");
    Coordinate c9 = new Coordinate("d7");
    Coordinate c10 = new Coordinate("d8");
    Coordinate c11 = new Coordinate("d9");
    Coordinate c12 = new Coordinate("e6");
    Coordinate c13 = new Coordinate("e7");
    Coordinate c14 = new Coordinate("g6");
    Coordinate c15 = new Coordinate("h6");
    Coordinate c16 = new Coordinate("e8");
    checkShip(carrierUp, "Carrier", 'c', c1, c2, c3, c4, c5, c6, c7);
    checkShip(carrierRight, "Carrier", 'c', c8, c9, c10, c11, c2, c12, c13);
    checkShip(carrierDown, "Carrier", 'c', c1, c2, c3, c12, c5, c14, c15);
    checkShip(carrierLeft, "Carrier", 'c', c9, c10, c2, c12, c13, c16);

    Placement invalid = new Placement("d5h");
    assertThrows(IllegalArgumentException.class, () -> factory.makeCarrier(invalid));
  }

  @Test
  public void test_make_carrier_and_destroyer() {
    V2ShipFactory factory = new V2ShipFactory();
    Placement horizontal = new Placement("d5H");
    Ship<Character> submarine = factory.makeSubmarine(horizontal);
    Ship<Character> destroyer = factory.makeDestroyer(horizontal);
    Coordinate c1 = new Coordinate("d5");
    Coordinate c2 = new Coordinate("d6");
    Coordinate c3 = new Coordinate("d7");
    checkShip(submarine, "Submarine", 's', c1, c2);
    checkShip(destroyer, "Destroyer", 'd', c1, c2, c3);

    Placement invalidPlacement = new Placement("a7u");
    assertThrows(IllegalArgumentException.class, () -> factory.makeSubmarine(invalidPlacement));
    assertThrows(IllegalArgumentException.class, () -> factory.makeDestroyer(invalidPlacement));

  }

  private void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter,
      Coordinate... expectedLocs) {
    assertEquals(expectedName, testShip.getName());
    for (Coordinate c : expectedLocs) {
      assertTrue(testShip.occupiesCoordinates(c));
      assertEquals(expectedLetter, testShip.getDisplayInfoAt(c, true));
    }
  }
}
