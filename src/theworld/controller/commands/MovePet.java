package theworld.controller.commands;

import theworld.exceptions.InvalidLocationException;
import theworld.model.DependencyManager;
import theworld.model.Pet;
import theworld.model.Room;

/**
 * The command class representing an action for moving the pet.
 */
public class MovePet implements IcontrollerCommand {

  private final Pet pet;
  private final int roomIndex;

  /**
   * Constructs the command based on the pet object and a room index.
   *
   * @param p the pet object
   * @param r the room index
   */
  public MovePet(Pet p, int r) {
    this.pet = p;
    this.roomIndex = r;
  }

  @Override
  public String execute() {
    try {
      Room target = DependencyManager.shared().queryRoom(this.roomIndex);
      pet.setLocation(roomIndex);
      pet.resetTraversal();
      return "The pet was moved to a place far, far away...";
    } catch (IllegalStateException ex) {
      throw new InvalidLocationException(this.roomIndex);
    }
  }
}
