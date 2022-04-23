package gamecommands;

import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;

/**
 * This command is for the player to move the pet.
 */
public class MovePet implements GameCommand {
  private final String nameOfSpace;

  /**
   * Constructor to get the space the player wants to move the pet to and move it.
   *
   * @param nameOfSpace - Space the pet is moving to.
   */
  public MovePet(String nameOfSpace) {
    if (nameOfSpace == null || nameOfSpace.isBlank()) {
      throw new IllegalArgumentException("The name of the space you are moving the pet to cannot "
          + "be blank.");
    }

    this.nameOfSpace = nameOfSpace;
  }

  @Override
  public String execute(World game) {
    if (game == null) {
      throw new IllegalArgumentException("The game manager can't be null.");
    }

    StringBuilder result = new StringBuilder();

    // Get the Space the pet is moving to
    Space spacePetIsMovingTo = game.getTheSpaceByName(nameOfSpace.trim());

    // Get the current player and list of game spaces (pet can be move anywhere) and then move
    // the pet
    game.getCurrentPlayer().movePet(spacePetIsMovingTo, game.getSpaces());
    result.append("**").append(game.getCurrentPlayer().getPlayerName()).append("**\n");
    result.append("Moved pet to location: ").append(nameOfSpace.trim());

    // Target moves
    game.moveTarget();

    // This is a turn
    game.nextTurn();

    return  result.toString();
  }
}
