package edu.duke.sl846.battleship;

/**
 * Represents a general Board for our battleship game.
 * The Board interface provides getters for width and height of the Board.
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
  public boolean tryAddShip(Ship<T> toAdd);

  /**
   * Given a Coordinate, gets whichever thing that occupies that Coordinate.
   * 
   * @param where is the Coordinate to check.
   * @return whatever info about the thing that occupies those coordinates if a
   *         thing exists there, null otherwise.
   */
  public T whatIsAt(Coordinate where);
}
