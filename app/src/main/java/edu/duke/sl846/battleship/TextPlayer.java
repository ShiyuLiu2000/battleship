package edu.duke.sl846.battleship;

import java.io.BufferedReader;
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
   * @throws IOException if user input cannot build a valid Placement.
   */
  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    return new Placement(s);
  }

  /**
   * Places one Ship on the Board.
   * (This method is deprecated. Used for tests only.)
   * 
   * @throws IOException if user input cannot build a valid Placement.
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
   * @throws IOException if user input cannot build a valid Placement of the Ship.
   */
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
    Ship<Character> s = createFn.apply(p);
    theBoard.tryAddShip(s);
    out.print(view.displayMyOwnBoard());
  }

  /**
   * Runs the placement phase for the player during the game.
   */
  public void doPlacementPhase() throws IOException {
    out.print(view.displayMyOwnBoard());
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
        "2 \"Carriers\" that are 1x6\n" +
        "--------------------------------------------------------------------------------\n";
    out.print(message);
    doOnePlacement();
  }
}
