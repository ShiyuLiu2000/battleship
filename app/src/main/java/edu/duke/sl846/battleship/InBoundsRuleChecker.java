package edu.duke.sl846.battleship;

/**
 * A placement rule checker that checks specifically if all the coordinates of a
 * {@link Ship} are inside the boundaries of a {@link Board}.
 */
public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  /**
   * Given a Ship and a Board, checks if all the coordinates of the Ship are
   * inside the boundaries of the Board.
   * 
   * @param theShip  is the Ship used to check the placement rule.
   * @param theBoard is the Board used to check the placement rule.
   * @return true if the placement is valid under this rule, false otherwise.
   */
  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    int width = theBoard.getWidth();
    int height = theBoard.getHeight();
    for (Coordinate c : theShip.getCoordinates()) {
      if (c.getRow() < 0 || c.getRow() >= height || c.getColumn() < 0 || c.getColumn() >= width) {
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
  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }
}
