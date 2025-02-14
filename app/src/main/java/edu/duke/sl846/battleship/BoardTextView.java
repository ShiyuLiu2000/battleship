package edu.duke.sl846.battleship;

import java.util.function.Function;

/**
 * Handles textual display of a {@link Board} (i.e., converting it to a string
 * to show to the user).
 * It supports two ways to display the Board:
 * - one for the player's own board,
 * - one for the enemy's board.
 */
public class BoardTextView {
  /**
   * The Board to display.
   */
  private final Board<Character> toDisplay;

  /**
   * Constructs a BoardView, given the board it will display.
   * 
   * @param toDisplay is the Board to Display.
   * @throws IllegalArgumentException if the board is larger than 10x26.
   */
  public BoardTextView(Board<Character> toDisplay) {
    if (toDisplay.getHeight() > 26 || toDisplay.getWidth() > 10) {
      throw new IllegalArgumentException("To have a textual representation, Board must be no larger than 10x26, but is "
          + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }
    this.toDisplay = toDisplay;
  }

  /**
   * Gives textual display of self's Board, including the placed Ships.
   * 
   * @return the textual display of self's Board.
   */
  public String displayMyOwnBoard() {
    return displayAnyBoard((c) -> toDisplay.whatIsAtForSelf(c));
  }

  /**
   * Gives textual display of enemy's Board, including the placed Ships.
   * 
   * @return the textual display of enemy's Board.
   */
  public String displayEnemyBoard() {
    return displayAnyBoard((c) -> toDisplay.whatIsAtForEnemy(c));
  }

  /**
   * Gives textual display of self's or enemy's Board, including the placed ships.
   * 
   * @param getSquareFn is the method to determine how to get the Character inside
   *                    the given Coordinate.
   */
  protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn) {
    StringBuilder ans = new StringBuilder();
    ans.append(makeHeader());
    ans.append(makeBody(getSquareFn));
    ans.append(makeHeader());
    return ans.toString();
  }

  /**
   * Makes the header line, e.g. 0|1|2|3\n
   * 
   * @return the String that is the header line of the given Board.
   */
  String makeHeader() {
    StringBuilder ans = new StringBuilder("  ");
    // start with nothing to separate, then switch to '|' to separate
    String sep = "";
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }

  /**
   * Makes the body line, e.g. |c| |
   * 
   * @param row         is the row number used to check the existence of ships
   *                    inside it.
   * @param getSquareFn is the method to determine how to get the Character inside
   *                    the given Coordinate.
   * @return the String that makes up the body of the given Board.
   */
  String makeBodyLine(int row, Function<Coordinate, Character> getSquareFn) {
    StringBuilder ans = new StringBuilder();
    ans.append(" ");
    String sep = "";
    for (int column = 0; column < toDisplay.getWidth(); column++) {
      ans.append(sep);
      Character s = getSquareFn.apply(new Coordinate(row, column));
      if (s != null) {
        ans.append(s.toString());
      } else {
        ans.append(" ");
      }
      sep = "|";
    }
    ans.append(" ");
    return ans.toString();
  }

  /**
   * Makes the body line for self's Board, e.g. | |d|
   * This method is for facilitate tests after refactoring.
   * 
   * @param row is the row number used to check the existence of ships inside it.
   * @return the String that makes up the body of the self's Board.
   */
  String makeBodyLineForSelf(int row) {
    return makeBodyLine(row, (c) -> toDisplay.whatIsAtForSelf(c));
  }

  /**
   * Builds the whole body of the empty Board.
   * 
   * @return the String of the whole body of the empty Board.
   */
  String makeEmptyBody() {
    StringBuilder ans = new StringBuilder();
    char c = 'A';
    for (int i = 0; i < toDisplay.getHeight(); i++) {
      ans.append(c);
      ans.append(makeBodyLine(0, (coordinate) -> {
        return ' ';
      }));
      ans.append(c);
      ans.append("\n");
      c++;
    }
    return ans.toString();
  }

  /**
   * Builds the whole body of the Board with Ships.
   * 
   * @param getSquareFn is the method to determine how to get the Character inside
   *                    the given Coordinate.
   * @return the String of the whole body of the Board with Ships.
   */
  String makeBody(Function<Coordinate, Character> getSquareFn) {
    StringBuilder ans = new StringBuilder();
    char c = 'A';
    for (int row = 0; row < toDisplay.getHeight(); row++) {
      ans.append(c);
      ans.append(makeBodyLine(row, getSquareFn));
      ans.append(c);
      ans.append("\n");
      c++;
    }
    return ans.toString();
  }

  /**
   * Builds the whole body of self's Board with Ships.
   * This method is to facilitate tests after refactoring.
   * 
   * @return the String of the whole body of self's Board with Ships.
   */
  String makeBodyForSelf() {
    return makeBody((c) -> toDisplay.whatIsAtForSelf(c));
  }
}
