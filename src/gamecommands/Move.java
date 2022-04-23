package gamecommands;

import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;

/**
 * This command will move a player to a neighboring space.
 */
public class Move implements GameCommand {
  private final String nameOfSpace;

  /**
   * The constructor will take the name of the space where the player is moving to.
   *
   * @param nameOfSpace - Name of the space the player is moving to.
   */
  public Move(String nameOfSpace) {
    // Validation
    if (nameOfSpace == null || nameOfSpace.isEmpty() || nameOfSpace.isBlank()) {
      throw new IllegalArgumentException("The space must have a name.\n");
    }

    this.nameOfSpace = nameOfSpace;
  }

  @Override
  public String execute(World game) {
    if (game == null) {
      throw new IllegalArgumentException("The game manager can't be null.");
    }
    StringBuilder result = new StringBuilder();

    // Add this space to the Player object
    game.getCurrentPlayer().move(nameOfSpace.trim());
    // Add the Player to the Space object

    result.append("**").append(game.getCurrentPlayer().getPlayerName()).append("**\n");
    result.append("Moved to location: ").append(nameOfSpace.trim());

    return  result.toString();
  }
}
