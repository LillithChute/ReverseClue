package theworld.exceptions;

import theworld.model.BasePlayer;
import theworld.model.Iplayer;
import theworld.model.Item;
import theworld.model.Room;

/**
 * This exception is thrown when player tries to pick up an item that's
 * not present in the room.
 */
public class NoSuchItemException extends GameplayException {

  /**
   * Constructs the exception based on an item and a room.
   *
   * @param i the missing item
   * @param r the room which does not contain the item
   */
  public NoSuchItemException(Item i, Room r) {
    super(String.format("Item: %s is not located in room %s", i, r));
  }

  /**
   * Constructs the exception based on an item and a player.
   *
   * @param i the missing item
   * @param p the player who's not carrying the item
   */
  public NoSuchItemException(Item i, Iplayer p) {
    super(String.format("Item: %s is not carried by the player: %s", i, p));
  }

  /**
   * Constructs the exception based on an item index and a player.
   *
   * @param i the missing item index
   * @param p the player who's not carrying the item
   */
  public NoSuchItemException(int i, Iplayer p) {
    super(String.format("Item index: %d is not carried by the player: %s", i, p));
  }

}
