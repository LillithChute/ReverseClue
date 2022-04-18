import controller.GameController;
import gameinterfaces.worldbuilderinterfaces.World;
import gamemodels.gamemanagermodels.WorldImpl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

/** The main class that kicks off the application.*/
public class Main {

  /**
   * This method will get a filename and process it.  Use the model as it exists to create a
   * world and then perform a few functions such as get a description of a space, move the target,
   * create a buffered image of the world, and get the neighbors of a particular space.
   *
   * @param args Command line argument of fully qualified file name.
   */
  public static void main(String[] args) {
    Readable file;
    World game;

    if (args[0] == null || args[0].trim().isEmpty()) {
      System.out.println("You need to specify a file!");
    } else {
      try {
        file = new BufferedReader(new FileReader(args[0]));
        game = new WorldImpl(file);

        if(game == null) {
          System.out.println("There was an issue processing the world building file!");
        } else {
          new GameController(new InputStreamReader(System.in), System.out).start(game);
        }
      } catch (FileNotFoundException ex) {
        System.out.println("Unable to locate or open the file.");
      }
    }
  }
}
