package gamecommands;

import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;

/**
 * This command provides the necessary steps to pick up an item.
 */
public class PickUpItem implements GameCommand {
  private final String itemName;

  /**
   * Given the name of an item, this method will have the character pick it up.
   *
   * @param itemName - Name of the item.
   */
  public PickUpItem(String itemName) {
    // Validation
    if (itemName == null || itemName.isEmpty() || itemName.isBlank()) {
      throw new IllegalArgumentException("The item must have a name.\n");
    }

    this.itemName = itemName;
  }

  @Override
  public String execute(World game) {
    game.getCurrentPlayer().takeItem(itemName);
    game.moveTarget();
    game.nextTurn();

    return "Player picked up item: " + itemName;
  }
}
