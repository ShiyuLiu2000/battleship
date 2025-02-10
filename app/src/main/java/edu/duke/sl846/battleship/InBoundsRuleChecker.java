package edu.duke.sl846.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    int width = theBoard.getWidth();
    int height = theBoard.getHeight();
    for (Coordinate c: theShip.getCoordinates()) {
      if (c.getRow() < 0 || c.getRow() >= height || c.getColumn() < 0 || c.getColumn() >= width) {
        return false;
      }
    }
    return true;
  }

  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }
}
