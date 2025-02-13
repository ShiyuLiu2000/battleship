package edu.duke.sl846.battleship;

import java.util.HashMap;

/**
 * A basic kind of Ship.
 */
public abstract class BasicShip<T> implements Ship<T> {
  // put all coordinates the ship occupies, and track which ones have been hit
  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;
  protected ShipDisplayInfo<T> enemyDisplayInfo;

  /**
   * Constructs a BasicShip with given locations and given display info settings.
   * 
   * @param where            is the Iterable of the Coordinates that make up the
   *                         BasicShip.
   * @param myDisplayInfo    is the information display settings for self's Ship.
   * @param enemyDisplayInfo is the information display settings for enemy's Ship.
   */
  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    this.myPieces = new HashMap<Coordinate, Boolean>();
    for (Coordinate c : where) {
      myPieces.put(c, false);
    }
    this.myDisplayInfo = myDisplayInfo;
    this.enemyDisplayInfo = enemyDisplayInfo;
  }

  /**
   * Checks if the given location is occupied by the BasicShip.
   * 
   * @param where is the Coordinate to be checked.
   * @return true if where is occupied by the BasicShip, false otherwise.
   */
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    return myPieces.containsKey(where);
  }

  /**
   * Check if this ship has been hit in all of its locations meaning it has been
   * sunk.
   * 
   * @return true if this ship has been sunk, false otherwise.
   */
  @Override
  public boolean isSunk() {
    for (Coordinate c : myPieces.keySet()) {
      if (wasHitAt(c) == false) {
        return false;
      }
    }
    return true;
  }

  /**
   * Make this ship record that it has been hit at the given coordinate. The
   * specified coordinate must be part of the ship.
   * 
   * @param where specifies the coordinates that were hit.
   * @throws IllegalArgumentException if where is not part of the Ship
   */
  @Override
  public void recordHitAt(Coordinate where) {
    checkCoordinateInThisShip(where);
    myPieces.put(where, true);
  }

  /**
   * Check if this ship was hit at the specified coordinates. The coordinates must
   * be part of this Ship.
   * 
   * @param where is the coordinates to check.
   * @return true if this ship as hit at the indicated coordinates, and false
   *         otherwise.
   * @throws IllegalArgumentException if the coordinates are not part of this
   *                                  ship.
   */
  @Override
  public boolean wasHitAt(Coordinate where) {
    checkCoordinateInThisShip(where);
    if (myPieces.get(where) == true) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Return the view-specific information at the given coordinate. This coordinate
   * must be part of the ship.
   * 
   * @param where  is the coordinate to return information for.
   * @param isSelf is the boolean that indicates whether it's self's Board or
   *               enemy's Board to show.
   * @throws IllegalArgumentException if where is not part of the Ship
   * @return The view-specific information at that coordinate.
   */
  @Override
  public T getDisplayInfoAt(Coordinate where, boolean isSelf) {
    boolean wasHit = wasHitAt(where);
    if (isSelf) {
      return myDisplayInfo.getInfo(where, wasHit);
    } else {
      return enemyDisplayInfo.getInfo(where, wasHit);
    }
  }

  /**
   * Helps to check if a given Coordinate is inside the BasicShip.
   * 
   * @param c is the Coordinate to be checked.
   * @throws IllegalArgumentException if the Coordinate is not in the ship.
   */
  public void checkCoordinateInThisShip(Coordinate c) {
    if (myPieces.containsKey(c) == false) {
      throw new IllegalArgumentException("The Coordinate " + c.toString() + " is not in the ship.");
    }
  }

  /**
   * Get all of the Coordinates that this Ship occupies.
   * 
   * @return an Iterable with the Coordinates that this Ship occupies.
   */
  @Override
  public Iterable<Coordinate> getCoordinates() {
    return myPieces.keySet();
  }
}
