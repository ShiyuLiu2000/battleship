package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementRuleCheckerTest {
  @Test
  public void test_chained_rule_checker() {
    PlacementRuleChecker<Character> inBoundsChecker = new InBoundsRuleChecker<Character>(null);
    PlacementRuleChecker<Character> noCollisionChecker = new NoCollisionRuleChecker<Character>(inBoundsChecker);
    V1ShipFactory factory = new V1ShipFactory();
    Board<Character> theBoard = new BattleShipBoard<>(5, 10, noCollisionChecker);
    Coordinate c1 = new Coordinate(7, 4);
    Coordinate c2 = new Coordinate(5, 4);

    Ship<Character> destroyer1 = factory.makeDestroyer(new Placement(c1, 'h'));
    assertNotNull(noCollisionChecker.checkPlacement(destroyer1, theBoard));
    Ship<Character> destroyer2 = factory.makeDestroyer(new Placement(c1, 'v'));
    assertNull(noCollisionChecker.checkPlacement(destroyer2, theBoard));
    theBoard.tryAddShip(destroyer1);
    Ship<Character> destroyer3 = factory.makeDestroyer(new Placement(c2, 'v'));
    assertNotNull(noCollisionChecker.checkPlacement(destroyer3, theBoard));
  }
}
