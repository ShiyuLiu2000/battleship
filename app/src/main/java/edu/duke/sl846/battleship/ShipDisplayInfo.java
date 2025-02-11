package edu.duke.sl846.battleship;

/**
 * An Interface for display information about a {@link Ship}.
 */
public interface ShipDisplayInfo<T> {
  /**
   * Gets the display information for a specific Coordinate of a ship.
   * The displayed information varies according to whether the ship has been hit
   * at that specific Coodinate.
   * 
   * @param where is the Coordinate to get display info for.
   * @param hit   is a boolean value to say if the ship has been hit at where.
   * @return the display info data on that Coordinate.
   */
  public T getInfo(Coordinate where, boolean hit);
}
