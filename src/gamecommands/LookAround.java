package gamecommands;

import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;

/**
 * This command allows the player to look around and then moves the target.
 */
public class LookAround implements GameCommand {
  @Override
  public String execute(World game) {
    String result = game.getCurrentPlayer().lookAround();
    game.moveTarget();
    game.nextTurn();

    return result;
  }
}
