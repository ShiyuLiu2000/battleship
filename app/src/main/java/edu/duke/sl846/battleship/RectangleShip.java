package edu.duke.sl846.battleship;

import java.util.HashSet;

/**
 * Represents a rectangle Ship in our battleship game.
 */
public class RectangleShip<T> extends BasicShip<T> {
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
   * Constructs a RectangleShip with a Coordinate, width, and height.
   * 
   * @param upperLeft is the upper left Coordinate of the RectangleShip.
   * @param width is the width of the RectangleShip.
   * @param height is the height of the RectangleShip.
   */
  public RectangleShip(Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> displayInfo) {
    super(makeCoordinates(upperLeft, width, height), displayInfo);
  }

  // convinience constructor
  public RectangleShip(Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit));
  }

  public RectangleShip(Coordinate upperLeft, T data, T onHit)
  {
    this(upperLeft, 1, 1, data, onHit);
  }
}
