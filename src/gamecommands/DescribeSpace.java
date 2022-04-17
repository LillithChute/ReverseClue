package gamecommands;

import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;

/**
 * This command gets the description of a space by its name.
 */
public class DescribeSpace implements GameCommand {
  final String nameOfSpace;

  /**
   * Get the name of the space, so we can get a description of it.
   *
   * @param spaceName - Name of the space to be described.
   */
  public DescribeSpace(String spaceName) {
    // Validation
    if (spaceName == null || spaceName.isEmpty() || spaceName.isBlank()) {
      throw new IllegalArgumentException("The space must have a name.\n");
    }

    this.nameOfSpace = spaceName;
  }

  @Override
  public String execute(World game) {
    // Validation
    if (game == null) {
      throw new IllegalArgumentException("There is an issue with the game world!\n");
    }

    var space = game.getTheSpaceByName(nameOfSpace);
    return space.getTheFullSpaceDescription();
  }
}
