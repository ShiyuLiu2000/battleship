package edu.duke.sl846.battleship;

/**
 * Represents a general Board for our battleship game.
 */
public interface Board<T> {
  /**
   * Gets the width of the Board.
   * 
   * @return the width of the Board.
   */
  public int getWidth();

  /**
   * Gets the height of the Board.
   * 
   * @return the height of the Board.
   */
  public int getHeight();

  /**
   * Attempts to add a Ship on the Board.
   * 
   * @param toAdd is the Ship to be added.
   * @return true if the addition is successful, false otherwise.
   */
  public String tryAddShip(Ship<T> toAdd);

  /**
   * Given a Coordinate, gets the view-specific display info of the Ship (if any)
   * to show as self's Board.
   * 
   * @param where is the Coordinate to check.
   * @return whatever displayInfo of the Ship at those coordinates if a Ship
   *         exists there, null otherwise.
   */
  public T whatIsAtForSelf(Coordinate where);

  /**
   * Given a Coordinate, gets the view-specific display info of the Ship (if any)
   * to show as enemy's Board.
   * 
   * @param where is the Coordinate to check.
   * @return whatever displayInfo of the Ship at those coordinates if a Ship
   *         exists there, null otherwise.
   */
  public T whatIsAtForEnemy(Coordinate where);

  /**
   * Fires at a Coordinate to see what happens.
   * 
   * @param Coordinate is the Coordinate to fire at.
   * @return the injured Ship if c is occupied by that Ship, null otherwise.
   */
  public Ship<T> fireAt(Coordinate c);

  /**
   * Checks if all ships has sunk.
   * 
   * @return true if all ships on this Board are sunk, false otherwise.
   */
  public boolean isAllShipSunk();
}
