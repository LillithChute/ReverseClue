package gameinterfaces.worldcontrollerinterfaces;

import gameinterfaces.worldbuilderinterfaces.World;

/**
 * The interface for the command patter to execute command from the controller.
 */
public interface GameCommand {

  /**
   * The starting point for commands from the controller.
   *
   * @param game - The model to manage playing the game.
   * @return The results of the command that was taken.
   */
  String execute(World game);
}
