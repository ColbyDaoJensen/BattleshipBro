import static java.lang.System.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class Game
{
  private Board[] playerBoards;
  private Board curPlayer, curOpponent;
  private Scanner keyboard;
  private Scanner file;
  private boolean skipSetup;
  private boolean gameOver;
  
  public static final String CLEARSCREEN = "\033[H\033[2J";
  public static final String[] shipNames = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
  public static final int[] shipSizes = {5, 4, 3, 3, 2};

