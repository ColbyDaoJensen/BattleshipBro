import java.io.IOException;
import static java.lang.System.*;

public class Main 
{
  //Game is created from the added code.
  //Game changes screens now.
  public static void main(String[] args) throws IOException
  {
    try
    {
      GameBoard game = new GameBoard();
      game.play();
    }

    //Game clears screen 
    catch(IOException e)
    {
      out.println(Game.CLEARSCREEN);
      out.println("Terminated due to IOException:\n");
      out.println(e.getMessage());
    }
  }
}

