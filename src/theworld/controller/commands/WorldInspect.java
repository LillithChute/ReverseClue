package theworld.controller.commands;

import static theworld.utilities.MilestoneUtilities.checkParam;

import java.awt.Point;
import theworld.model.World;

/**
 * The command to inspect a point in the world.
 */
public class WorldInspect implements IcontrollerCommand {

  private final Point target;
  private final World world;

  /**
   * Constructs the command based on a point and the world object.
   *
   * @param p the point to inspect for
   * @param w the world model object
   */
  public WorldInspect(Point p, World w) {
    checkParam(p, w);
    this.target = p;
    this.world = w;
  }

  @Override
  public String execute() {
    return world.inspect(target);
  }
}
