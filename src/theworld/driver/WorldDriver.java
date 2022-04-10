package theworld.driver;


import java.io.IOException;
import java.io.InputStreamReader;
import javax.management.InstanceAlreadyExistsException;
import theworld.controller.WorldGameController;
import theworld.model.World;

/**
 * The Driver class to run the MyWorld project.
 */
public class WorldDriver {
  /**
   * The main driver class to demonstrate the project.

   * @param args command-line arguments
   */
  public static void main(String[] args) {
    final String path = args[0];
    final int turnLimit = Integer.parseInt(args[1]);
    World myWorld = World.parseWorldFromFile(path);
    WorldGameController controller = new WorldGameController(myWorld, turnLimit,
            new InputStreamReader(System.in), System.out);
    controller.gameLoop();
  }
}
