package edu.duke.sl846.battleship;

/**
 * Handles textual display of a Board (i.e., converting it to a string to show
 * to the user).
 * It supports two ways to display the Board:
 * - one for the player's own board,
 * - one for the enemy's board.
 */
public class BoardTextView {
  /**
   * The Board to display.
   */
  private final Board toDisplay;

  /**
   * Constructs a BoardView, given the board it will display.
   * 
   * @param toDisplay is the Board to Display.
   */
  public BoardTextView(Board toDisplay) {
    this.toDisplay = toDisplay;
  }

  public String displayMyOwnBoard() {
    return ""; // placeholder
  }
}
