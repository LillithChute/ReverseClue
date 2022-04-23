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
    // Validation
    if (game == null) {
      throw new IllegalArgumentException("There is an issue with the game world!\n");
    }

    StringBuilder result = new StringBuilder();
    String playerName = game.getCurrentPlayer().getPlayerName();

    game.getCurrentPlayer().takeItem(itemName);

    result.append("**").append(playerName).append("**\n");
    result.append(playerName).append(" picked up item: ").append(itemName);

    return result.toString();
  }
}
