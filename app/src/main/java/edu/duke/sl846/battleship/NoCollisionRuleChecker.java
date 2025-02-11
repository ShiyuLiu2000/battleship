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
   * @return true if the placement is valid under this rule, false otherwise.
   */
  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    for (Coordinate c : theShip.getCoordinates()) {
      if (theBoard.whatIsAt(c) != null) {
        return false;
      }
    }
    return true;
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
