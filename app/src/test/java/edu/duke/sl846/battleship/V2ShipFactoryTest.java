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
    checkShip(battleshipDown, "Battleship", 'b', c1,c2,c3,c5);
    checkShip(battleshipLeft, "Battleship", 'b', c2, c4,c5,c8);

    Placement invalid = new Placement("d5v");
    assertThrows(IllegalArgumentException.class, () -> factory.makeBattleship(invalid));
  }

  @Disabled
  @Test
  public void test_make_carrier() {
    V2ShipFactory factory = new V2ShipFactory();
    Placement horizontal = new Placement("d5H");
    Ship<Character> submarine = factory.makeSubmarine(horizontal);
    Ship<Character> destroyer = factory.makeDestroyer(horizontal);

    Coordinate c1 = new Coordinate("d5");
    Coordinate c2 = new Coordinate("d6");
    Coordinate c3 = new Coordinate("d7");

    checkShip(submarine, "Submarine", 's', c1, c2);
    checkShip(destroyer, "Destroyer", 'd', c1, c2, c3);
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
