package theworld.controller.commands;

import static theworld.utilities.MilestoneUtilities.checkParam;

import theworld.model.Iplayer;
import theworld.model.Item;

/**
 * Command representing an attack action.
 */
public class PlayerAttack implements IcontrollerCommand {

  private final Iplayer player;
  private final Item item;

  /**
   * Constructs the command from a player and the item used.
   *
   * @param p the player performing the attack
   * @param i the item used
   */
  public PlayerAttack(Iplayer p, Item i) {
    checkParam(p);
    this.player = p;
    this.item = i;
  }

  @Override
  public String execute() {
    return this.player.attack(item);
  }
}
