package edu.duke.sl846.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;

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
  public TextPlayer(String name, Board<Character> theBoard, Reader inputSource, PrintStream out,
      AbstractShipFactory<Character> factory) {
    this.name = name;
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = new BufferedReader(inputSource);
    this.out = out;
    this.shipFactory = factory;
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
   * 
   * @throws IOException if user input cannot build a valid Placement.
   */
  public void doOnePlacement() throws IOException {
    String prompt = "Player A where do you want to put your Destroyer?";
    Placement placement = readPlacement(prompt);
    Ship<Character> ship = shipFactory.makeDestroyer(placement);
    theBoard.tryAddShip(ship);
    BoardTextView view = new BoardTextView(theBoard);
    out.print(view.displayMyOwnBoard());
  }

  /**
   * Runs the placement phase for the player during the game.
   */
  public void doPlacementPhase() throws IOException {
    BoardTextView view = new BoardTextView(theBoard);
    out.print(view.displayMyOwnBoard());
    String message = 
    "--------------------------------------------------------------------------------\n" +
    "Player A: you are going to place the following ships (which are all\n" +
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
