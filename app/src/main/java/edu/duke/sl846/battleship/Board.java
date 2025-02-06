package edu.duke.sl846.battleship;

/**
 * Represents a general Board for our battleship game.
 * The Board interface provides getters for width and height of the Board.
 */
public interface Board {

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
}
