package edu.duke.sl846.battleship;

import java.util.ArrayList;
import java.util.HashSet;

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
  HashSet<Coordinate> enemyMisses;
  final T missInfo;

  /**
   * Constructs a Board with given width and height.
   * By default, the rule of this board is:
   * - all ships should have coordinates that fit in bounds, and
   * - there should be no collisions among ships.
   * 
   * @param width    is the width of this Board.
   * @param height   is the height of this Board.
   * @param missInfo is the view-specific display info to show on enemy's Board if
   *                 there is a miss.*
   * @throws IllegalArgumentException if width or height is negative or zero.
   */
  public BattleShipBoard(int width, int height, T missInfo) {
    this(width, height, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<T>(null)), missInfo);
  }

  /**
   * Constructs a Board with given width, height, and PlacementRuleChecker.
   * 
   * @param width            is the width of this Board.
   * @param height           is the height of this Board.
   * @param placementChecker is the rule checker for this Board.
   * @param missInfo         is the view-specific display info to show on enemy's
   *                         Board if there is a miss.
   * @throws IllegalArgumentException if width or height is negative or zero.
   */
  public BattleShipBoard(int width, int height, PlacementRuleChecker<T> placementChecker, T missInfo) {
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
    this.enemyMisses = new HashSet<>();
    this.missInfo = missInfo;
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
   * @return true if the addition is successful according to the rule checkers,
   *         false otherwise.
   */
  public String tryAddShip(Ship<T> toAdd) {
    String placementProblem = placementChecker.checkPlacement(toAdd, this);
    if (placementProblem == null) {
      myShips.add(toAdd);
      return null;
    } else {
      return placementProblem;
    }
  }

  /**
   * Given a Coordinate, gets the view-specific display info of the Ship (if any)
   * to show as self's Board.
   * 
   * @param where is the Coordinate to check.
   * @return whatever displayInfo of the Ship at those coordinates if a Ship
   *         exists there, null otherwise.
   */
  public T whatIsAtForSelf(Coordinate where) {
    return whatIsAt(where, true);
  }

  /**
   * Given a Coordinate, gets the view-specific display info of the Ship (if any)
   * to show as enemy's Board.
   * 
   * @param where is the Coordinate to check.
   * @return whatever displayInfo of the Ship at those coordinates if a Ship
   *         exists there, null otherwise.
   */
  public T whatIsAtForEnemy(Coordinate where) {
    return whatIsAt(where, false);
  }

  /**
   * Given a Coordinate, gets the Ship (if any) which occupies that Coordinate in
   * self Board or in enemy's Board.
   * 
   * @param where  is the Coordinate to check.
   * @param isSelf is a boolean to indicate whether it's self's or enemy's Board
   *               to be checked.
   * @return whatever displayInfo of the Ship at those coordinates if a Ship
   *         exists there, null otherwise.
   */
  protected T whatIsAt(Coordinate where, boolean isSelf) {
    for (Ship<T> ship : myShips) {
      if (ship.occupiesCoordinates(where)) {
        return ship.getDisplayInfoAt(where, isSelf);
      }
    }
    if (isSelf) {
      return null;
    } else {
      if (enemyMisses.contains(where)) {
        return missInfo;
      } else {
        return null;
      }
    }
  }

  /**
   * Fires at a Coordinate to see what happens.
   * 
   * @param Coordinate is the Coordinate to fire at.
   * @return the injured Ship if c is occupied by that Ship, null otherwise.
   */
  public Ship<T> fireAt(Coordinate c) {
    for (Ship<T> ship : myShips) {
      if (ship.occupiesCoordinates(c)) {
        ship.recordHitAt(c);
        ship.wasHitAt(c);
        return ship;
      }
    }
    enemyMisses.add(c);
    return null;
  }

  /**
   * Checks if all ships has sunk.
   * 
   * @return true if all ships on this Board are sunk, false otherwise.
   */
  public boolean isAllShipSunk() {
    for (Ship<T> ship : myShips) {
      if (!ship.isSunk()) {
        return false;
      }
    }
    return true;
  }
}
