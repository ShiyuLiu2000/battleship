package edu.duke.sl846.battleship;

import java.util.ArrayList;

/**
 * Represents a Board in our battleship game.
 */
public class BattleShipBoard<T> implements Board<T> {
  private final int width;
  private final int height;
  // we are NOT going to write a getter for myShips, because nothing outside
  // BattleShipBoard should ever know how we keep out ships in an ArrayList, or be
  // able to operate directly an that ArrayList.
  final ArrayList<Ship<T>> myShips;
  private final PlacementRuleChecker<T> placementChecker;

  /**
   * Constructs a Board with given width and height.
   * 
   * @param width  is the width of this Board.
   * @param height is the height of this Board.
   * @throws IllegalArgumentException if width or height is negative or zero.
   */
  public BattleShipBoard(int width, int height) {
    this(width, height, new InBoundsRuleChecker<T>(null));
  }

  public BattleShipBoard(int width, int height, PlacementRuleChecker<T> placementChecker) {
    if (width <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + width);
    }
    if (height <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + height);
    }
    this.width = width;
    this.height = height;
    this.myShips = new ArrayList<>();
    this.placementChecker = placementChecker;
  }

  /**
   * Gets the width of the Board.
   * 
   * @return the width of the Board.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Gets the height of the Board.
   * 
   * @return the height of the Board.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Attempts to add a Ship on the Board.
   * 
   * @param toAdd is the Ship to be added.
   * @return true if the addition is successful, false otherwise.
   */
  public boolean tryAddShip(Ship<T> toAdd) {
    myShips.add(toAdd);
    return true;
  }

  /**
   * Given a Coordinate, gets the Ship (if any) which occupies that Coordinate.
   * 
   * @param where is the Coordinate to check.
   * @return whatever displayInfo of the Ship at those coordinates if a Ship
   *         exists there, null otherwise.
   */
  public T whatIsAt(Coordinate where) {
    for (Ship<T> ship : myShips) {
      if (ship.occupiesCoordinates(where)) {
        return ship.getDisplayInfoAt(where);
      }
    }
    return null;
  }
}
