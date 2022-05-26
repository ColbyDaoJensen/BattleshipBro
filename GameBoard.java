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

  public void play() throws IOException
  {
    startScreen();

    if(skipSetup)
    {
      playerBoards[0].setName("Player 1");
      playerBoards[0].presetBoard(1);
      playerBoards[1].setName("Player 2");
      playerBoards[1].presetBoard(2);
    }
    else
    {
      setupBoard(1);
      setupBoard(2);
    }
    
    while(!gameOver)
    {
      nextTurnScreen();
      gameOver = takeTurn();
      if(!gameOver)
        swapBoards();
    }
  }

  private void setupBoard(int player) throws IOException
  {
    out.println("*** PLAYER " + player + " SETUP ***\n");
    Board curBoard = playerBoards[player-1];
    boolean orient;

    out.print("Enter your name:\t");
    curBoard.setName(keyboard.nextLine().trim());
    out.println();

    //Game board tells you where to place ships.
    //Game will ask you to place peg horizontal or vertical.
    //Game will tell you where to put the top leg of the ship anywhere. However,
    //A wrong position will make you restart the process.
    for(int i = 0; i < shipNames.length; i++)
    {
      curBoard.printBoardForOwner();

      out.print("Place your " + shipNames[i].toUpperCase());
      out.println(" (" + shipSizes[i] + " pegs)...");

      out.print("Horizontal or vertical? Enter V or H:\t");
      String choice = keyboard.nextLine().trim();
      if(choice.equalsIgnoreCase("V"))
        orient = Board.VERTICAL;
      else if(choice.equalsIgnoreCase("H"))
        orient = Board.HORIZONTAL;
      else
      {
        out.println();
        i--;
        continue;
      }

      out.print("Enter location of top/leftmost peg:\t\t");
      String loc = keyboard.nextLine().trim();
      if(!curBoard.placeShip(loc, orient, i+1))
      {
        out.println();
        i--;
        continue;
      }

      out.println();
    }

    curBoard.printBoardForOwner();
    out.println("Player " + curBoard.getName() + " setup complete!");
    enterToContinue();
  }

