package edu.duke.sl846.battleship;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T> {
  // put all coordinates the ship occupies, and track which ones have been hit
  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;

  /**
   * Constructs a BasicShip with given locations.
   * 
   * @param where is the Iterable of the Coordinates that make up the BasicShip.
   */
  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo) {
    this.myPieces = new HashMap<Coordinate, Boolean>();
    for (Coordinate c : where) {
      myPieces.put(c, false);
    }
    this.myDisplayInfo = myDisplayInfo;
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

  @Override
  public boolean isSunk() {
    for (Coordinate c: myPieces.keySet()) {
      if (wasHitAt(c) == false) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    checkCoordinateInThisShip(where);
    myPieces.put(where, true);
  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    checkCoordinateInThisShip(where);
    if (myPieces.get(where) == true) {
      return true;
    }
    else {
      return false;
    }
  }

  @Override
  public T getDisplayInfoAt(Coordinate where) {
    boolean wasHit = wasHitAt(where);
    return myDisplayInfo.getInfo(where, wasHit);
  }

  public void checkCoordinateInThisShip(Coordinate c) {
    if (myPieces.containsKey(c) == false) {
      throw new IllegalArgumentException("The Coordinate " + c.toString() + " is not in the ship.");
    }
  }

  @Override
  public Iterable<Coordinate> getCoordinates() {
    return myPieces.keySet();
  }
}
