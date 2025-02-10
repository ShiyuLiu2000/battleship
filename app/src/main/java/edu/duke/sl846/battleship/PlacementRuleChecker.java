package edu.duke.sl846.battleship;

public abstract class PlacementRuleChecker<T> {
  private final PlacementRuleChecker<T> next;

  public PlacementRuleChecker(PlacementRuleChecker<T> next) {
    this.next = next;
  }

  // Subclasses will override this method to specify how they check their own rule
  protected abstract boolean checkMyRule(Ship<T> theShip, Board<T> theBoard);

  public boolean checkPlacement(Ship<T> theShip, Board<T> theBoard) {
    // if we fail our own rule: stop. The placement is not legal
    if (!checkMyRule(theShip, theBoard)) {
      return false;
    }
    // otherwise, ask the rest of the chain
    if (next != null) {
      return next.checkPlacement(theShip, theBoard);
    }
    // if there are no more rules, then the Placement is legal
    return true;
  }
}
