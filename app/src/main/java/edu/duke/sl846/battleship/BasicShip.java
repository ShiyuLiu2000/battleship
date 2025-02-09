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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isSunk'");
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'recordHitAt'");
  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'wasHitAt'");
  }

  @Override
  public T getDisplayInfoAt(Coordinate where) {
    // TODO this is not right. We need to look up the hit status of this coordinate
    return myDisplayInfo.getInfo(where, false);
  }

}
