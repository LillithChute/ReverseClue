package theworld.controller.commands;

import static theworld.utilities.MilestoneUtilities.checkParam;

import theworld.model.BasePlayer;
import theworld.model.Iplayer;
import theworld.model.Room;
import theworld.model.World;

/**
 * The command to move a player to another location.
 */
public class Moving implements IcontrollerCommand {

  private final Iplayer player;
  private final Room room;
  private final World world;

  /**
   * The constructor for the moving command.

   * @param p the target player
   * @param r the room to move to
   * @param w the world model class
   */
  public Moving(Iplayer p, Room r, World w) {
    checkParam(p, r, w);
    this.player = p;
    this.room = r;
    this.world = w;
  }

  @Override
  public String execute() {
    world.movePlayer(player, room);
    return String.format("Successfully moved player %s to %s.", player, room);
  }
}
