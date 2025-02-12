package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_checkInBoundsRule() {
    V1ShipFactory factory = new V1ShipFactory();
    PlacementRuleChecker<Character> checker = new InBoundsRuleChecker<Character>(null);
    Board<Character> theBoard = new BattleShipBoard<>(5, 10, checker);
    Ship<Character> destroyer1 = factory.makeDestroyer(new Placement(new Coordinate(8, 4), 'v'));
    assertNotNull(checker.checkPlacement(destroyer1, theBoard));
    Ship<Character> destroyer2 = factory.makeDestroyer(new Placement(new Coordinate(5, 4), 'v'));
    assertNull(checker.checkPlacement(destroyer2, theBoard));
    Ship<Character> testShip1 = new RectangleShip<Character>(new Coordinate(9, 4), 's', '*');
    assertNull(checker.checkPlacement(testShip1, theBoard));
    Ship<Character> testShip2 = new RectangleShip<Character>(new Coordinate(-1, 4), 's', '*');
    assertNotNull(checker.checkPlacement(testShip2, theBoard));
    Ship<Character> testShip3 = new RectangleShip<Character>(new Coordinate(9, -1), 's', '*');
    assertNotNull(checker.checkPlacement(testShip3, theBoard));
  }
}
