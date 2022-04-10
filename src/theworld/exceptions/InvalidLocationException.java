package theworld.exceptions;

import theworld.model.Room;

/**
 * This exception is thrown whenever an attempt of accessing an
 * invalid location in the game world is made.
 */
public class InvalidLocationException extends GameplayException {

  /**
   * Constructs the exception based on an illegal room object.
   *
   * @param r the illegal room object
   */
  public InvalidLocationException(Room r) {
    super(String.format("Room: %s is not a valid location.", r.toString()));
  }

  /**
   * Constructs the exception based on an illegal room index.
   *
   * @param i the illegal room index
   */
  public InvalidLocationException(int i) {
    super(String.format("Room index: %d is not a valid location.", i));
  }
}
