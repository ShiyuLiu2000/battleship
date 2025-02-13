package edu.duke.sl846.battleship;

/**
 * A placement rule checker that checks specifically if all the coordinates of a
 * {@link Ship} do not have collision with any existing Ships inside a
 * {@link Board}.
 */
public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {

  /**
   * Given a Ship and a Board, checks if all the coordinates of the Ship do not
   * have collision with any existing Ships inside the Board.
   * 
   * @param theShip  is the Ship used to check the placement rule.
   * @param theBoard is the Board used to check the placement rule.
   * @return null if the placement is valid under this rule, otherwise, a desciptive String about what went wrong in detail.
   */
  @Override
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    for (Coordinate c : theShip.getCoordinates()) {
      if (theBoard.whatIsAtForSelf(c) != null) {
        return "That placement is invalid: the ship overlaps another ship.";
      }
    }
    return null;
  }

  /**
   * Constructs a chained placement rule checker.
   * 
   * @param next is the next chained placement rule to be checked.
   */
  public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }
}
