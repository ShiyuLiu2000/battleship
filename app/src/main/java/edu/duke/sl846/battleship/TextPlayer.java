package edu.duke.sl846.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Represents a text-based player for the battleship game.
 */
public class TextPlayer {
  // per-player things
  final Board<Character> theBoard;
  final BoardTextView view;
  // shared by 2 users
  final BufferedReader inputReader;
  final PrintStream out;
  // purely functional, doesn't matter if it's shared
  final AbstractShipFactory<Character> shipFactory;
  String name;

  final ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;

  final int moveUses;
  final int sonarUses;

  /**
   * Constructs the TextPlayer with given Board, input source, output stream, ship
   * factory, and a name.
   * 
   * @param name        is the name given to the TextPlayer.
   * @param theBoard    is the Board used in battleship game.
   * @param inputSource is the input source to read from.
   * @param out         is the output stream to print to.
   * @param factory     is the ship factory to build ships.
   */
  public TextPlayer(String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out,
      AbstractShipFactory<Character> factory) {
    this.name = name;
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = inputSource;
    this.out = out;
    this.shipFactory = factory;
    this.shipsToPlace = new ArrayList<>();
    setupShipCreationList();
    this.shipCreationFns = new HashMap<>();
    setupShipCreationMap();
    this.moveUses = 3;
    this.sonarUses = 3;
  }

  /**
   * Copies the hit marks from old ship to new ship after move.
   * 
   * @param oldShip is the Ship to be moved.
   * @param newShip is the Ship created at the newly moved-to place.
   */
  static void copyHitMarks(Ship<Character> oldShip, Ship<Character> newShip) {
    ArrayList<Coordinate> oldCoordinates = new ArrayList<>();
    for (Coordinate c : oldShip.getCoordinates()) {
      oldCoordinates.add(c);
    }
    ArrayList<Coordinate> newCoordinates = new ArrayList<>();
    for (Coordinate c : newShip.getCoordinates()) {
      newCoordinates.add(c);
    }
    for (int i = 0; i < oldCoordinates.size(); i++) {
      if (oldShip.wasHitAt(oldCoordinates.get(i))) {
        newShip.recordHitAt(newCoordinates.get(i));
      }
    }
  }

  /**
   * Sets up the ship creation functions to facilitate ship creation process.
   * Make the ship factory methods correspond to ship names respectively.
   */
  protected void setupShipCreationMap() {
    shipCreationFns.put("Submarine", (placement) -> shipFactory.makeSubmarine(placement));
    shipCreationFns.put("Destroyer", (placement) -> shipFactory.makeDestroyer(placement));
    shipCreationFns.put("Carrier", (placement) -> shipFactory.makeCarrier(placement));
    shipCreationFns.put("Battleship", (placement) -> shipFactory.makeBattleship(placement));
  }

  /**
   * Sets up list of available ships to facilitate game process.
   */
  protected void setupShipCreationList() {
    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
  }

  /**
   * Gives a prompt to user, then read from user input for Placement.
   * 
   * @param prompt is the String to inform user on what to input.
   * @return the Placement corresponding to user input.
   * @throws IOException if the user input cannot build a valid Placement.
   */
  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    if (s == null) {
      throw new EOFException("The input for Placement is empty.");
    }
    Placement ans = null;
    try {
      ans = new Placement(s);
    } catch (IllegalArgumentException e) {
      out.println("That placement is invalid: it does not have the correct format.\n" + e.toString());
    }
    return ans;
  }

  /**
   * Places one Ship on the Board.
   * (This method is deprecated. Used for tests only.)
   * 
   * @throws IOException if the user input cannot build a valid Placement.
   */
  public void doOnePlacement() throws IOException {
    String prompt = "Player " + name + " where do you want to put your Destroyer?";
    Placement placement = readPlacement(prompt);
    Ship<Character> ship = shipFactory.makeDestroyer(placement);
    theBoard.tryAddShip(ship);
    out.print(view.displayMyOwnBoard());
  }

  /**
   * Places a specific type of Ship on the Board.
   * 
   * @param shipName is the name of the Ship.
   * @param createFn is the method to create a Ship based on Placement.
   * @throws IOException if the user input cannot build a valid Placement.
   */
  public String doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    String prompt = "--------------------------------------------------------------------------------\n" + "Player "
        + name + " where do you want to place a " + shipName + "?\n"
        + "--------------------------------------------------------------------------------";
    Placement placement = readPlacement(prompt);
    String placementProblem = null;
    while (placement == null) {
      placement = readPlacement(prompt);
    }
    try {
      Ship<Character> ship = createFn.apply(placement);
      placementProblem = theBoard.tryAddShip(ship);
    } catch (IllegalArgumentException e) {
      return "The placement is not valid: " + e.getMessage();
    }
    out.print(view.displayMyOwnBoard());
    return placementProblem;
  }

  /**
   * Runs the placement phase for the player during the game.
   * 
   * @throws IOException if the user input cannot build a valid Placement.
   */
  public void doPlacementPhase() throws IOException {
    String message = "--------------------------------------------------------------------------------\n" +
        "Player " + name + ": you are going to place the following ships (which are all\n" +
        "rectangular). For each ship, type the coordinate of the upper left\n" +
        "side of the ship, followed by either H (for horizontal) or V (for\n" +
        "vertical).  For example M4H would place a ship horizontally starting\n" +
        "at M4 and going to the right.  You have\n" +
        "\n" +
        "2 \"Submarines\" ships that are 1x2\n" +
        "3 \"Destroyers\" that are 1x3\n" +
        "3 \"Battleships\" that are 1x4\n" +
        "2 \"Carriers\" that are 1x6\n";
    out.print(message);
    out.print("--------------------------------------------------------------------------------\n");
    out.print("Your current ocean:\n");
    out.print(view.displayMyOwnBoard());
    for (int i = 0; i < shipsToPlace.size(); i++) {
      String shipName = shipsToPlace.get(i);
      String placementProblem = doOnePlacement(shipName, shipCreationFns.get(shipName));
      while (placementProblem != null) {
        out.println(placementProblem);
        placementProblem = doOnePlacement(shipName, shipCreationFns.get(shipName));
      }
    }
    out.print("--------------------------------------------------------------------------------\n\n\n");
  }

  /**
   * Gives a prompt to user, then read from user input for Coordinate.
   * 
   * @param prompt is the String to inform user on what to input.
   * @return the Coordinate corresponding to user input.
   * @throws IOException if the user input cannot build a valid Coordinate.
   */
  public Coordinate readCoordinate(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    if (s == null) {
      throw new EOFException("The input for Coordinate is empty.");
    }
    Coordinate ans = null;
    try {
      ans = new Coordinate(s);
    } catch (IllegalArgumentException e) {
      out.println("That coordinate is invalid: it does not have the correct format.\n" + e.toString());
    }
    return ans;
  }

  /**
   * Runs the attacking phase for the player during the game.
   * 
   * @param enemyBoard is enemy's Board.
   * @throws IOException if the user input cannot build a valid Placement.
   */
  public void doAttackingPhase(TextPlayer enemy) throws IOException {
    out.print("\n\n--------------------------------------------------------------------------------\n");
    out.print("Player " + name + "'s turn:\n");
    String myHeader = "Your ocean";
    String enemyHeader = "Player " + enemy.name + "'s ocean";
    out.print(view.displayMyBoardWithEnemyNextToIt(enemy.view, myHeader, enemyHeader));
    String prompt = "--------------------------------------------------------------------------------\n" + "Player "
        + name + " where do you want to attack?\n"
        + "--------------------------------------------------------------------------------";
    Coordinate coordinate = readCoordinate(prompt);
    while (coordinate == null) {
      coordinate = readCoordinate(prompt);
    }
    Ship<Character> injuredShip = enemy.theBoard.fireAt(coordinate);
    if (injuredShip == null) {
      String missInfo = "--------------------------------------------------------------------------------\n"
          + "You missed!\n" + "--------------------------------------------------------------------------------\n";
      out.print(missInfo);
    } else {
      String hitInfo = "--------------------------------------------------------------------------------\n"
          + "You hit a " + injuredShip.getName() + "!\n"
          + "--------------------------------------------------------------------------------\n";
      out.print(hitInfo);
    }
    out.print(view.displayMyBoardWithEnemyNextToIt(enemy.view, myHeader, enemyHeader));
    out.print("--------------------------------------------------------------------------------\n");
  }

  public void printWinningMessage() {
    String winMessage = "Player " + name + " wins! Congratulations!!!\n";
    out.print(winMessage);
    out.print("This game has ended. Please hit Ctrl - D to stop the game.\n");
    out.print("--------------------------------------------------------------------------------\n");
  }

  public boolean playOneTurnAgainst(TextPlayer enemy) throws IOException {
    doAttackingPhase(enemy);
    if (enemy.theBoard.isAllShipSunk()) {
      printWinningMessage();
      return true;
    }
    return false;
  }
}
