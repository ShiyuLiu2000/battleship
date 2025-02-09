package edu.duke.sl846.battleship;

import java.util.HashMap;

public class BasicShip implements Ship<Character> {
  private final Coordinate myLocation;
  HashMap<Coordinate, Boolean> myPieces; // put all coordinates the ship occupies, and track which ones have been hit

  /**
   * Constructs a BasicShip with given location.
   * 
   * @param where is the place of BasicShip.
   */
  public BasicShip(Coordinate where) {
    this.myLocation = where;
  }

  /**
   * Checks if the given location is occupied by the BasicShip.
   * 
   * @param where is the Coordinate to be checked.
   * @return true if where is occupied by the BasicShip, false otherwise.
   */
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    return where.equals(myLocation);
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
  public Character getDisplayInfoAt(Coordinate where) {
    return 's';
  }

}
