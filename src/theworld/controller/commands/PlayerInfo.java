package theworld.controller.commands;

import static theworld.utilities.MilestoneUtilities.checkParam;

import theworld.model.BasePlayer;
import theworld.model.Iplayer;

/**
 * The command to display info about a player.
 */
public class PlayerInfo implements IcontrollerCommand {

  private final Iplayer player;

  /**
   * Constructs the command based on the player.
   *
   * @param p the player which info is displayed.
   */
  public PlayerInfo(Iplayer p) {
    checkParam(p);
    this.player = p;
  }

  @Override
  public String execute() {
    return player.getInformation();
  }
}
