package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {
  private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes) {
    // read from String like an input stream
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    // use PrintStream to write data into bytes instead of to screen
    // set autoflush to true to ensure data becomes immediately available in bytes
    PrintStream output = new PrintStream(bytes, true);
    // create a board and a factory
    Board<Character> board = new BattleShipBoard<Character>(w, h);
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer("A", board, input, output, shipFactory);
  }

  @Test
  void test_read_placement() throws IOException {
    // use ByteArrayOutputStream to collect the output
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);

    String prompt = "Please enter a location for a Ship: ";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');
    for (int i = 0; i < expected.length; i++) {
      Placement placement = player.readPlacement(prompt);
      assertEquals(placement, expected[i]); // did we get the right Placement back
      assertEquals(prompt + "\n", bytes.toString()); // should have printed prompt and newline
      bytes.reset(); // clear out bytes for next time around
    }
  }

  @Test
  public void test_do_one_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);

    String prompt = "Player A where do you want to put your Destroyer?";
    for (int i = 0; i < 3; i++) {
      player.doOnePlacement();
      BoardTextView view = new BoardTextView(player.theBoard);
      assertEquals(prompt + "\n" + view.displayMyOwnBoard(), bytes.toString());
      bytes.reset();
    }
  }

  @Test
  public void test_setup_ship_creation_list_and_map() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 26, "", bytes);

    assertEquals(10, player.shipsToPlace.size());
    assertEquals("Submarine", player.shipsToPlace.get(0));
    assertEquals("Destroyer", player.shipsToPlace.get(3));
    assertEquals("Battleship", player.shipsToPlace.get(6));
    assertEquals("Carrier", player.shipsToPlace.get(9));

    assertEquals(4, player.shipCreationFns.size());
    Placement testPlacement = new Placement("D2H");
    assertEquals("Submarine", player.shipCreationFns.get("Submarine").apply(testPlacement).getName());
    assertEquals("Destroyer", player.shipCreationFns.get("Destroyer").apply(testPlacement).getName());
    assertEquals("Battleship", player.shipCreationFns.get("Battleship").apply(testPlacement).getName());
    assertEquals("Carrier", player.shipCreationFns.get("Carrier").apply(testPlacement).getName());
  }

  @Test
  public void test_do_one_placement_with_parameters() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "a4v\n", bytes);

    String prompt = "Player A where do you want to place a Carrier?";
    player.doOnePlacement("Carrier", player.shipCreationFns.get("Carrier"));
    assertEquals(prompt + "\n" + player.view.displayMyOwnBoard(), bytes.toString());
    bytes.reset();
  }
}
