package edu.duke.sl846.battleship;

/**
 * Represents the coordinate of a given place.
 */
public class Coordinate {
  private final int row;
  private final int column;

  /**
   * Constructs a Coordinate with given row and column.
   * 
   * @param row    is the row number of the coordinate.
   * @param column is the column number of the coordinate.
   */
  public Coordinate(int row, int column) {
    this.row = row;
    this.column = column;
  }

  /**
   * Gets the row number of the Coordinate.
   * 
   * @return the row number of the Coordinate.
   */
  public int getRow() {
    return row;
  }

  /**
   * Gets the column number of the Coordinate.
   * 
   * @return the column number of the Coordinate.
   */
  public int getColumn() {
    return column;
  }

  /**
   * Compares two Coordinates to see if they have equality.
   * 
   * @param object is the Object being compared to.
   * @return true if the two Coordinates have the same row number and column
   *         number, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object.getClass().equals(getClass())) {
      Coordinate coordinate = (Coordinate) object;
      return row == coordinate.row && column == coordinate.column;
    }
    return false;
  }

  /**
   * Gives textual representation of the Coordinate, e.g. (1,2)
   * 
   * @return textual representation of the Coordinate.
   */
  @Override
  public String toString() {
    return "(" + row + ", " + column + ")";
  }

  /**
   * Generates hashcode for a Coordinate.
   * (Use the fact that Java's String have a good hashcode)
   * 
   * @return hashcode of a Coordinate.
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
