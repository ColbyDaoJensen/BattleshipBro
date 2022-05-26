import static java.lang.System.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class Game
{
  //Variables for ships and players.
  private Board[] playerBoards;
  private Board curPlayer, curOpponent;
  private Scanner keyboard;
  private Scanner file;
  private boolean skipSetup;
  private boolean gameOver;

  //Sets up ships in the game for players
  public static final String CLEARSCREEN = "\033[H\033[2J";
  public static final String[] shipNames = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
  public static final int[] shipSizes = {5, 4, 3, 3, 2};

  //Allows board to be playable
  public Game() throws IOException
  {
    playerBoards = new Board[]{new Board(), new Board()};
    curPlayer = playerBoards[0];
    curOpponent = playerBoards[1];
    keyboard = new Scanner(System.in);
    skipSetup = false;
    gameOver = false;
  }

