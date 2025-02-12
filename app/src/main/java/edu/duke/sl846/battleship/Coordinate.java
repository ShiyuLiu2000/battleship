package edu.duke.sl846.battleship;

/**
 * Represents the coordinate of a given place with row and column.
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
   * Compares two Coordinates to see if they are equal: same row number, same
   * column number.
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
   * Gives textual representation of the Coordinate, e.g. (1, 2)
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

  /**
   * Constructs a Coordinate from description String, e.g. A3, c7
   * 
   * @param description is the description String. It should be composed of 2
   *                    characters: the first is a-z or A-Z to indicate the row,
   *                    and the second is 0-9 to indicate the column.
   * @throws IllegalArgumentException if description String is either not of
   *                                  length 2, not beginning with a-z / A-Z, or
   *                                  not ending with 0-9.
   */
  public Coordinate(String description) {
    if (description.length() != 2) {
      throw new IllegalArgumentException("Coordinate description string should be of length 2, but is " + description.length());
    }
    String uppercaseDescription = description.toUpperCase();
    char rowIndicator = uppercaseDescription.charAt(0);
    if (rowIndicator < 'A' || rowIndicator > 'Z') {
      throw new IllegalArgumentException(
          "Coordinate description string should begin with a-z or A-Z, but is beginning with " + rowIndicator);
    }
    char colIndicator = uppercaseDescription.charAt(1);
    if (colIndicator < '0' || colIndicator > '9') {
      throw new IllegalArgumentException(
          "Coordinate description string should end with 0-9, but is ending with " + colIndicator);
    }
    int rowNumber = rowIndicator - 'A';
    int colNumber = Integer.parseInt(String.valueOf(colIndicator));
    this.row = rowNumber;
    this.column = colNumber;
  }
}
