package gamecommands;

import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;

/**
 * This command invokes the method that will save an image file to the current directory the
 * program is running in.
 */
public class SaveWorldImage implements GameCommand {

  @Override
  public String execute(World game) {
    // Validation
    if (game == null) {
      throw new IllegalArgumentException("The game has not been initialized.\n");
    }

    return game.printWorldImageToDisk();
  }
}
