package theworld.controller.commands;

import static theworld.utilities.MilestoneUtilities.checkParam;

import theworld.model.BasePlayer;
import theworld.model.Iplayer;

/**
 * The command for a player to inspect its surroundings.
 */
public class PlayerInspect implements IcontrollerCommand {

  private final Iplayer player;

  /**
   * Constructs the command based on the inspecting player.
   *
   * @param p the player performing the action
   */
  public PlayerInspect(Iplayer p) {
    checkParam(p);
    this.player = p;
  }

  @Override
  public String execute() {
    return player.lookAround();
  }
}
