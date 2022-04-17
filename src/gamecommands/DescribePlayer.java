package gamecommands;

import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;

/**
 * Provides the sequence of commands needed to describe the current player.
 */
public class DescribePlayer implements GameCommand {
  @Override
  public String execute(World game) {
    // Validation
    if (game == null) {
      throw new IllegalArgumentException("There is an issue with the game world!\n");
    }

    String result = game.getCurrentPlayer().describePlayer();
    game.moveTarget();
    game.nextTurn();

    return result;
  }
}
