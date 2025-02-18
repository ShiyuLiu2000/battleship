package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V2ShipFactoryTest {
  @Test
  public void test_make_battleship() {
    V2ShipFactory factory = new V2ShipFactory();
    Placement up = new Placement("d5U");
    Ship<Character> submarine = factory.makeSubmarine(horizontal);
    Ship<Character> destroyer = factory.makeDestroyer(horizontal);

    Coordinate c1 = new Coordinate("d5");
    Coordinate c2 = new Coordinate("d6");
    Coordinate c3 = new Coordinate("d7");

    checkShip(submarine, "Submarine", 's', c1, c2);
    checkShip(destroyer, "Destroyer", 'd', c1, c2, c3);
  }

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
