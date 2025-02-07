package edu.duke.sl846.battleship;

/**
 * Represents the placement at a given {@link Coordinate} with given
 * orientation.
 */
public class Placement {
  private final Coordinate where;
  private final char orientation;

  /**
   * Checks if the orientation character is valid.
   * 
   * @throws IllegalAccessException if the orientation is not valid.
   */
  public void checkValidOrientation(char orientation) {
    char uppercaseOrientation = Character.toUpperCase(orientation);
    if (uppercaseOrientation != 'V' && uppercaseOrientation != 'H') {
      throw new IllegalArgumentException(
          "Orientation must be vertical (v/V) or horizontal (h/H), but is " + orientation);
    }
  }

  /**
   * Constructs a Placement with given Coordinate and orientation.
   * 
   * @param where       is the Coordinate to place.
   * @param orientation is the orientation of the Placement.
   * @throws IllegalArgumentException if orientation is not valid.
   */
  public Placement(Coordinate where, char orientation) {
    this.where = where;
    checkValidOrientation(orientation);
    this.orientation = Character.toUpperCase(orientation);
  }

  /**
   * Constructs a Placement from description String, e.g. C8H, r3v
   * 
   * @param description is the description String. It should be composed of 3
   *                    characters: the first two can build a valid
   *                    {@link Coordinate}, and the last is a valid orientation.
   * @throws IllegalArgumentException if the description String is either not of
   *                                  length 3, not beginning with valid
   *                                  Coordinate description substring, or not
   *                                  ending with valid orientation character.
   */
  public Placement(String description) throws IllegalArgumentException {
    if (description.length() != 3) {
      throw new IllegalArgumentException("Placement description string should be of length 3, but is " + description);
    }
    Coordinate coordinate = new Coordinate(description.substring(0, 2)); // may throw IllegalArgumentException
    this.where = coordinate;
    checkValidOrientation(description.charAt(2)); // may throw IllegalArgumentException
    this.orientation = Character.toUpperCase(description.charAt(2));
  }

  /**
   * Gets the Coordinate of the Placement.
   * 
   * @return the Coordinate of the Placement.
   */
  public Coordinate getWhere() {
    return where;
  }

  /**
   * Gets the orientation of the Placement.
   * 
   * @return the orientation of the Placement.
   */
  public char getOrientation() {
    return orientation;
  }

  /**
   * Compares two Placements to see if they are equal: same Coordinate, same
   * orientation (case insensitive).
   * 
   * @param object is the Object being compared to.
   * @return true if the two Placements have the same Coordinate to place and the
   *         same orientation, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object.getClass().equals(getClass())) {
      Placement placement = (Placement) object;
      return where.equals(placement.getWhere()) && orientation == placement.getOrientation();
    }
    return false;
  }

  /**
   * Gives textual representation of the Placement, e.g. (1, 2), V
   * 
   * @return textual representation of the Placement.
   */
  @Override
  public String toString() {
    return where.toString() + ", " + orientation;
  }

  /**
   * Generates hashcode for a Placement.
   * (Use the fact that Java's String have a good hashcode)
   * 
   * @return hashcode of a Placement.
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
