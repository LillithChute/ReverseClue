package gamecommands;

import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;

/**
 * This creates the command to attack the target using some implement of destruction.
 */
public class Attack implements GameCommand {
  private final String itemName;

  /**
   * Constructor to get the necessary item to destroy the target with.
   *
   * @param itemName - Name of the item to use to attack the target.
   */
  public Attack(String itemName) {
    if (itemName == null || itemName.isBlank()) {
      throw new IllegalArgumentException("You must have an item to attack with.");
    }

    this.itemName = itemName;
  }

  @Override
  public String execute(World game) {
    // Validation
    if (game == null) {
      throw new IllegalArgumentException("There is an issue with the game world!\n");
    }

    String result = game.getCurrentPlayer().attack(itemName);
    game.moveTarget();
    game.nextTurn();

    return result;
  }
}
