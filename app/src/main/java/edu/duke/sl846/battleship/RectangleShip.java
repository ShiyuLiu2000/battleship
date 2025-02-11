package edu.duke.sl846.battleship;

import java.util.HashSet;

/**
 * Represents rectangle Ship in our battleship game.
 */
public class RectangleShip<T> extends BasicShip<T> {
  private final String name;

  /**
   * Makes a collection of Coordinates of the Ship.
   * 
   * @param upperLeft is the upperLeft Coordinate of the RectangleShip.
   * @param width     is the width of the RectangleShip.
   * @param height    is the height of the Rectangleship.
   * @return a HashSet of the Coordinates of the RectangleShip.
   */
  static HashSet<Coordinate> makeCoordinates(Coordinate upperLeft, int width, int height) {
    HashSet<Coordinate> ans = new HashSet<>();
    int startRow = upperLeft.getRow();
    int startCol = upperLeft.getColumn();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        ans.add(new Coordinate(startRow + i, startCol + j));
      }
    }
    return ans;
  }

  /**
   * Constructs a RectangleShip with a Coordinate, width, height, and the
   * view-specific display information of the Ship.
   * 
   * @param upperLeft   is the upper left Coordinate of the RectangleShip.
   * @param width       is the width of the RectangleShip.
   * @param height      is the height of the RectangleShip.
   * @param displayInfo is the view-specific information of the Ship.
   */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> displayInfo) {
    super(makeCoordinates(upperLeft, width, height), displayInfo);
    this.name = name;
  }

  /**
   * Conviniently constructs a RectangleShip with a Coordinate, width, height, and
   * the view-specific display information of the Ship.
   * 
   * @param upperLeft is the upper left Coordinate of the RectangleShip.
   * @param width     is the width of the RectangleShip.
   * @param height    is the height of the RectangleShip.
   * @param data      is the view-specific information of the Ship when not hit.
   * @param onHit     is the view-specific information of the Ship when hit.
   */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit));
  }

  /**
   * Constructs a test RectangleShip with a Coordinate and the view-specific
   * display information.
   * 
   * @param upperLeft is the upper left Coordinate of the RectangleShip.
   * @param data      is the view-specific information of the Ship when not hit.
   * @param onHit     is the view-specific information of the Ship when hit.
   */
  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this("testship", upperLeft, 1, 1, data, onHit);
  }

  /**
   * Gets the name of the RectangleShip.
   * 
   * @return the name of the RectangleShip.
   */
  @Override
  public String getName() {
    return name;
  }
}
