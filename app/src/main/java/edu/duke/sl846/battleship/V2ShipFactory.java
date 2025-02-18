package edu.duke.sl846.battleship;

/**
 * Builds a CombinedShip factory for the battleship game.
 */
public class V2ShipFactory implements AbstractShipFactory<Character> {

  /**
   * Makes a battleship composed of 2 small rectangle battleships.
   * 
   * @param where is the Placement of the ship.
   * @return the destroyer ship being created.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  @Override
  public Ship<Character> makeBattleship(Placement where) {
    char orientation = where.getOrientation();
    Coordinate upperLeft = where.getWhere();
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    CombinedShip<Character> ans = new CombinedShip<Character>("Battleship", 'b', '*');
    switch (orientation) {
      case 'U':
        Placement placementUp1 = new Placement(new Coordinate(row, column + 1), 'H');
        Placement placementUp2 = new Placement(new Coordinate(row + 1, column), 'H');
        ans.addCompositionShip(makeShipParts(placementUp1, 1, 1, 'b'));
        ans.addCompositionShip(makeShipParts(placementUp2, 1, 3, 'b'));
        break;
      case 'R':
        Placement placementRight1 = new Placement(new Coordinate(row, column), 'V');
        Placement placementRight2 = new Placement(new Coordinate(row + 1, column + 1), 'V');
        ans.addCompositionShip(makeShipParts(placementRight1, 1, 3, 'b'));
        ans.addCompositionShip(makeShipParts(placementRight2, 1, 1, 'b'));
        break;
      case 'D':
        Placement placementDown1 = new Placement(new Coordinate(row, column), 'H');
        Placement placementDown2 = new Placement(new Coordinate(row + 1, column + 1), 'H');
        ans.addCompositionShip(makeShipParts(placementDown1, 1, 3, 'b'));
        ans.addCompositionShip(makeShipParts(placementDown2, 1, 1, 'b'));
        break;
      case 'L':
        Placement placementLeft1 = new Placement(new Coordinate(row + 1, column), 'V');
        Placement placementLeft2 = new Placement(new Coordinate(row, column + 1), 'V');
        ans.addCompositionShip(makeShipParts(placementLeft1, 1, 1, 'b'));
        ans.addCompositionShip(makeShipParts(placementLeft2, 1, 3, 'b'));
        break;
      default:
        throw new IllegalArgumentException(
            "Battleship only accepts Up(u/U), Right(r/R), Down(d/D), Left(l/L) as orientation, but is " + orientation);
    }
    return ans;
  }

  /**
   * Makes a carrier ship composed of 2 small rectangle carrier ships.
   * 
   * @param where is the Placement of the ship.
   * @return the destroyer ship being created.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  @Override
  public Ship<Character> makeCarrier(Placement where) {
    char orientation = where.getOrientation();
    Coordinate upperLeft = where.getWhere();
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    CombinedShip<Character> ans = new CombinedShip<Character>("Carrier", 'c', '*');
    switch (orientation) {
      case 'U':
        Placement placementUp1 = new Placement(new Coordinate(row, column), 'V');
        Placement placementUp2 = new Placement(new Coordinate(row + 2, column + 1), 'V');
        ans.addCompositionShip(makeShipParts(placementUp1, 1, 4, 'c'));
        ans.addCompositionShip(makeShipParts(placementUp2, 1, 3, 'c'));
        break;
      case 'R':
        Placement placementRight1 = new Placement(new Coordinate(row, column + 1), 'H');
        Placement placementRight2 = new Placement(new Coordinate(row + 1, column), 'H');
        ans.addCompositionShip(makeShipParts(placementRight1, 1, 4, 'c'));
        ans.addCompositionShip(makeShipParts(placementRight2, 1, 3, 'c'));
        break;
      case 'D':
        Placement placementDown1 = new Placement(new Coordinate(row, column), 'V');
        Placement placementDown2 = new Placement(new Coordinate(row + 1, column + 1), 'V');
        ans.addCompositionShip(makeShipParts(placementDown1, 1, 3, 'c'));
        ans.addCompositionShip(makeShipParts(placementDown2, 1, 4, 'c'));
        break;
      case 'L':
        Placement placementLeft1 = new Placement(new Coordinate(row, column + 2), 'H');
        Placement placementLeft2 = new Placement(new Coordinate(row + 1, column), 'H');
        ans.addCompositionShip(makeShipParts(placementLeft1, 1, 3, 'c'));
        ans.addCompositionShip(makeShipParts(placementLeft2, 1, 4, 'c'));
        break;
      default:
        throw new IllegalArgumentException(
            "Carrier only accepts up (u/U), right (r/R), down (d/D), left (l/L) as orientation, but is " + orientation);
    }
    return ans;
  }

  /**
   * Makes a 1x3 destroyer ship.
   * 
   * @param where is the Placement of the ship.
   * @return the destroyer ship being created.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    char orientation = where.getOrientation();
    if (orientation != 'H' && orientation != 'V') {
      throw new IllegalArgumentException(
          "Destroyer only accepts horizontal (h/H) and vertical (v/V) as orientation, but is " + orientation);

    }
    V1ShipFactory rectangleFactory = new V1ShipFactory();
    return rectangleFactory.makeDestroyer(where);
  }

  /**
   * Makes a 1x2 submarine ship.
   * 
   * @param where is the Placement of the ship.
   * @return the submarine ship being created.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    char orientation = where.getOrientation();
    if (orientation != 'H' && orientation != 'V') {
      throw new IllegalArgumentException(
          "Submarine only accepts horizontal (h/H) and vertical (v/V) as orientation, but is " + orientation);

    }
    V1ShipFactory rectangleFactory = new V1ShipFactory();
    return rectangleFactory.makeSubmarine(where);
  }

  /**
   * A helper function to build small parts of RectangleShips that finally combine
   * together to become a CombinedShip.
   * 
   * @param where  is the Placement of the Ship.
   * @param width  is the width of the ship.
   * @param height is the height of the ship.
   * @param letter is the letter of the Ship.
   * @return the generated battleship or carrier ship, or null if letter is
   *         neither 'b' or 'c'.
   */
  public Ship<Character> makeShipParts(Placement where, int width, int height, char letter) {
    V1ShipFactory rectangleFactory = new V1ShipFactory();
    if (letter == 'b') {
      return rectangleFactory.createShip(where, width, height, letter, "Battleship");
    }
    if (letter == 'c') {
      return rectangleFactory.createShip(where, width, height, letter, "Carrier");
    }
    return null;
  }
}
