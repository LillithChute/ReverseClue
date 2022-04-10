package theworld.controller.commands;

import static theworld.utilities.MilestoneUtilities.checkParam;

import theworld.model.BasePlayer;
import theworld.model.Iplayer;
import theworld.model.Item;

/**
 * The command for a player to pick up an item.
 */
public class Pickup implements IcontrollerCommand {

  private final Iplayer player;
  private final Item item;

  /**
   * Constructs the command based on the player and the item to pick up.
   *
   * @param p the player performing the action
   * @param i the item to pick up
   */
  public Pickup(Iplayer p, Item i) {
    checkParam(p, i);
    this.player = p;
    this.item = i;
  }

  @Override
  public String execute() {
    player.pickUp(item);
    return String.format("Successfully picked up item %s", item);
  }
}
