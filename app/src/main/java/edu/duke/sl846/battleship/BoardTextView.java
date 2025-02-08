package edu.duke.sl846.battleship;

/**
 * Handles textual display of a {@link Board} (i.e., converting it to a string
 * to show
 * to the user).
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
   * Gives textual display of the Board.
   * 
   * @return the textual display of the Board.
   */
  public String displayMyOwnBoard() {
    StringBuilder ans = new StringBuilder();
    ans.append(makeHeader());
    // ans.append(makeEmptyBody());
    ans.append(makeBody());
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
   * Makes the body line, e.g. A | | | A\n
   * 
   * @param c   is the character printed inside the line as an indicator.
   * @param row is the row number used to check the existence of ships inside it.
   * @return the String that makes up the body of the given Board.
   * @throws IllegalArgumentException if c is not from A to Z.
   */
  String makeBodyLine(char c, int row) {
    if (c < 'A' || c > 'Z') {
      throw new IllegalArgumentException("Character indicator must be from A to Z, but is " + c);
    }
    StringBuilder ans = new StringBuilder();
    ans.append(c);
    ans.append(" ");
    String sep = "";
    for (int column = 0; column < toDisplay.getWidth(); column++) {
      ans.append(sep);
      Character s = toDisplay.whatIsAt(new Coordinate(row, column));
      if (s != null) {
        ans.append(s.toString());
      } else {
        ans.append(" ");
      }
      sep = "|";
    }
    ans.append(" ");
    ans.append(c);
    ans.append("\n");
    return ans.toString();
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
      ans.append(makeBodyLine(c, 0));
      c++;
    }
    return ans.toString();
  }

  /**
   * Builds the whole body of the Board with Ships.
   * 
   * @return the String of the whole body of the Board with Ships.
   */
  String makeBody() {
    StringBuilder ans = new StringBuilder();
    char c = 'A';
    for (int row = 0; row < toDisplay.getHeight(); row++) {
      ans.append(makeBodyLine(c, row));
      c++;
    }
    return ans.toString();
  }
}
