package edu.duke.sl846.battleship;

/**
 * Represents a Board in our battleship game.
 */
public class BattleShipBoard implements Board {
  private final int width;
  private final int height;

  /**
   * Constructs a Board with given width and height.
   * 
   * @param width  is the width of this Board.
   * @param height is the height of this Board.
   * @throws IllegalArgumentException if width or height is negative or zero.
   */
  public BattleShipBoard(int width, int height) {
    if (width <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + width);
    }
    if (height <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + height);
    }
    this.width = width;
    this.height = height;
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
}
