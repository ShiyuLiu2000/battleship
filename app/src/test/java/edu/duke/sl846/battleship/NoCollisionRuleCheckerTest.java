package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_check_no_collision_rule() {
    V1ShipFactory factory = new V1ShipFactory();
    PlacementRuleChecker<Character> checker = new NoCollisionRuleChecker<Character>(null);
    Board<Character> theBoard = new BattleShipBoard<>(5, 10, checker, 'X');
    Coordinate c1 = new Coordinate(6, 4);
    Coordinate c2 = new Coordinate(4, 4);
    Coordinate c3 = new Coordinate(9, 4);

    Ship<Character> destroyer1 = factory.makeDestroyer(new Placement(c1, 'v'));
    assertNull(checker.checkPlacement(destroyer1, theBoard));
    theBoard.tryAddShip(destroyer1);
    Ship<Character> destroyer2 = factory.makeDestroyer(new Placement(c2, 'v'));
    assertEquals("That placement is invalid: the ship overlaps another ship.", checker.checkPlacement(destroyer2, theBoard));
    theBoard.tryAddShip(destroyer2);
    Ship<Character> testShip = new RectangleShip<Character>(c3, 's', '*');
    assertNull(checker.checkPlacement(testShip, theBoard));
    theBoard.tryAddShip(testShip);
  }
}
