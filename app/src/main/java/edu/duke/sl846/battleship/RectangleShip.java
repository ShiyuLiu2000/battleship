package edu.duke.sl846.battleship;

import java.util.HashSet;

public class RectangleShip {
  /**
   * Makes a collection of Coordinates of the Ship.
   * @param upperLeft is the upperLeft Coordinate of the RectangleShip.
   * @param width is the width of the RectangleShip.
   * @param height is the height of the Rectangleship.
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
}
