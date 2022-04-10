package theworld.controller.commands;

import static theworld.utilities.MilestoneUtilities.checkParam;

import theworld.exceptions.GameplayException;
import theworld.model.BasePlayer;
import theworld.model.Iplayer;
import theworld.model.World;

/**
 * The command to add a player into the game.
 */
public class AddPlayer implements IcontrollerCommand {

  int roomIndex;
  Iplayer player;
  World world;


  /**
   * Constructs a player adding command.
   *
   * @param w The world to put the player in
   * @param p The player to add into the game
   * @param r the starting room index of the player
   */
  public AddPlayer(World w, Iplayer p, int r) {
    checkParam(w, p);
    this.player = p;
    this.roomIndex = r;
    this.world = w;
  }

  @Override
  public String execute() {
    try {
      this.world.addPlayer(player, roomIndex);
    } catch (GameplayException ex) {
      return ex.getMessage();
    }
    return String.format("Added player %s.\n", this.player);
  }
}
