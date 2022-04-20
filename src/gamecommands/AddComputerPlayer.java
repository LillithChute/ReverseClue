package gamecommands;

import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;

/**
 * Command to add a computer player to the game.
 */
public class AddComputerPlayer implements GameCommand {
  private final String playerName;
  private final String playerLocation;
  private final int itemLimit;

  /**
   * Constructor for adding a player to the game.
   *
   * @param playerName - Name of the player.
   * @param playerLocation - Starting location of the player.
   * @param itemLimit - Maximum number of items the player can carry.
   */
  public AddComputerPlayer(String playerName, String playerLocation, int itemLimit) {
    // Validation
    if (playerName == null || playerName.isEmpty() || playerName.isBlank()) {
      throw new IllegalArgumentException("The player must have a name.\n");
    }

    if (playerLocation == null) {
      throw new IllegalArgumentException("This location does not exist.\n");
    }

    if (itemLimit < 1) {
      throw new IllegalArgumentException("The player item limit must be positive.\n");
    }

    this.playerName = playerName;
    this.playerLocation = playerLocation;
    this.itemLimit = itemLimit;
  }

  @Override
  public String execute(World game) {
    if (game == null) {
      throw new IllegalArgumentException("The game manager can't be null.");
    }

    Space location = game.getTheSpaceByName(playerLocation);

    game.addComputerPlayer(playerName, location, itemLimit);

    StringBuilder result = new StringBuilder();
    result.append("Added computer player: ")
            .append(playerName)
            .append("  Location: ")
            .append(playerLocation)
            .append("  Max Items: ")
            .append(itemLimit)
            .append("\n");

    return result.toString();
  }
}
