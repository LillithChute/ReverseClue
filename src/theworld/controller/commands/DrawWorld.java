package theworld.controller.commands;

import static theworld.utilities.MilestoneUtilities.checkParam;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import theworld.exceptions.GameplayException;
import theworld.model.World;

/**
 * Represents a command to generate a graphical representation of the world.
 */
public class DrawWorld implements IcontrollerCommand {

  World world;

  /**
   * Constructs a command to draw the world.
   *
   * @param w The world to be drawn
   */
  public DrawWorld(World w) {
    checkParam(w);
    this.world = w;
  }

  @Override
  public String execute() throws GameplayException {
    String sb =
            "Generated the graphical representation. Please find the image in res/images/image.png";
    try {
      File out = new File("res/images/image.png");
      ImageIO.write(world.draw(), "png", out);
    } catch (IOException ex) {
      throw new IllegalStateException();
    }
    return sb;
  }
}
