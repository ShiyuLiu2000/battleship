/*
 * This source file was generated by the Gradle 'init' task
 */
package edu.duke.sl846.battleship;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;

/**
 * Plays the Battleship game.
 */
public class App {
  TextPlayer player1;
  TextPlayer player2;

  /**
   * Contructs the App with two TextPlayers.
   * 
   * @param player1 is the first player of the game.
   * @param player2 is the second player of the game.
   */
  public App(TextPlayer player1, TextPlayer player2) {
    this.player1 = player1;
    this.player2 = player2;
  }

  /**
   * Runs the placement phase of the battleship game.
   */
  public void doPlacementPhase() throws IOException {
    player1.doPlacementPhase();
  }

  /**
   * Plays the battleship game.
   */
  public static void main(String[] args) throws IOException {
    Board<Character> board1 = new BattleShipBoard<>(10, 20);
    Board<Character> board2 = new BattleShipBoard<>(10, 20);
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    V1ShipFactory factory = new V1ShipFactory();
    TextPlayer player1 = new TextPlayer("A", board1, input, System.out, factory);
    TextPlayer player2 = new TextPlayer("B", board2, input, System.out, factory);
    App app = new App(player1, player2);

    app.doPlacementPhase();
  }
}
