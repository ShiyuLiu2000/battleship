package edu.duke.sl846.battleship;

/**
 * A simple way of displaying information of a {@link Ship}.
 * There are only two kinds of status: hit and not hit.
 */
public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {
  private T myData;
  private T onHit;

  /**
   * Constructs a simple display holding the info data.
   * 
   * @param myData is the display when that Coordinate of ship is not hit.
   * @param onHit  is the display when that Coordinate of ship is hit.
   */
  public SimpleShipDisplayInfo(T myData, T onHit) {
    this.myData = myData;
    this.onHit = onHit;
  }

  /**
   * Gets the display information for a specific Coordinate of a ship.
   * The displayed information varies according to whether the ship has been hit
   * at that specific Coodinate.
   * 
   * @param where is the Coordinate to get display info for.
   * @param hit   is a boolean value to say if the ship has been hit at where.
   * @return the display info data on that Coordinate.
   */
  @Override
  public T getInfo(Coordinate where, boolean hit) {
    if (hit) {
      return onHit;
    } else {
      return myData;
    }
  }
}
