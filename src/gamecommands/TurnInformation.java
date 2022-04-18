package gamecommands;

import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;

/**
 * This command figures out whose turn it is and provides some basic details about where they are
 * and general game info that they should know.
 */
public class TurnInformation implements GameCommand {
  @Override
  public String execute(World game) {
    // Validation
    if (game == null) {
      throw new IllegalArgumentException("There is an issue with the game world!\n");
    }

    return game.getCurrentPlayerTurnInfo();
  }
}
