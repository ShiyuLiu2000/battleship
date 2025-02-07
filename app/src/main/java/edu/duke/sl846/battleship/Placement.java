package edu.duke.sl846.battleship;

/**
 * Represents the placement at a given {@link Coordinate} with given
 * orientation.
 */
public class Placement {
  private final Coordinate where;
  private final char orientation;

  /**
   * Constructs a Placement with given Coordinate and orientation.
   * 
   * @param where       is the Coordinate to place.
   * @param orientation is the orientation of the Placement.
   * @throws IllegalArgumentException if orientation is neither vertical (v/V) nor
   *                                  horizontal (h/H).
   */
  public Placement(Coordinate where, char orientation) {
    this.where = where;
    char uppercaseOrientation = Character.toUpperCase(orientation);
    if (uppercaseOrientation != 'V' && uppercaseOrientation != 'H') {
      throw new IllegalArgumentException(
          "Orientation must be vertical (v/V) or horizontal (h/H), but is " + orientation);
    }
    this.orientation = uppercaseOrientation;
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
  
}
