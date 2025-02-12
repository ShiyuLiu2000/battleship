package edu.duke.sl846.battleship;

/**
 * A placement rule checker for a {@link Board}.
 */
public abstract class PlacementRuleChecker<T> {
  private final PlacementRuleChecker<T> next;

  /**
   * Constructs a chained placement rule checker.
   * 
   * @param next is the next chained placement rule to be checked.
   */
  public PlacementRuleChecker(PlacementRuleChecker<T> next) {
    this.next = next;
  }

  /**
   * Abstracted method for future child PlacementRuleChecker classes to check
   * their own rule.
   * 
   * @param theShip  is the Ship used to check the placement rule.
   * @param theBoard is the Board used to check the placement rule.
   * @return null if the placement is valid under this rule, otherwise, a
   *         desciptive String about what went wrong in detail.
   */
  protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);

  /**
   * Checks if the placement is valid using a chain of placement rules.
   * 
   * @param theShip  is the Ship used to check the placement rule.
   * @param theBoard is the Board used to check the placement rule.
   * @return null if the placement is valid under this rule, otherwise, a
   *         desciptive String about what went wrong in detail.
   */
  public String checkPlacement(Ship<T> theShip, Board<T> theBoard) {
    // if we fail our own rule: stop. The placement is not legal
    String placementProblem = checkMyRule(theShip, theBoard);
    if (placementProblem != null) {
      return placementProblem;
    }
    // if there are no more rules, then the Placement is legal
    if (next == null) {
      return null;
    }
    // otherwise, ask the rest of the chain
    else {
      return next.checkPlacement(theShip, theBoard);
    }
  }
}
