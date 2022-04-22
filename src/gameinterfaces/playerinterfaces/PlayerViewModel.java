package gameinterfaces.playerinterfaces;

import gameinterfaces.iteminterfaces.Item;
import java.util.List;

/**
 * The readonly view-model representing a player in the game.
 */
public interface PlayerViewModel {

  /**
   * This will return the name of a player.
   *
   * @return The player's name.
   */
  String getPlayerName();

  /**
   * This will return the index location of a player.
   *
   * @return The index of the player's location.
   */
  int getLocation();

  /**
   * * This method returns a description of all items a player is carrying.
   *
   * @return A description of the items the player has.
   */
  String getPlayerItemsDescription();

  /**
   * * This method returns all the items a player is carrying.
   *
   * @return A list of the items the player has.
   */
  List<Item> getPlayerItems();

  /**
   * This will return a description of the player.  That is, the space the player is in, the
   * items the player is carrying.
   *
   * @return - A description of the current player.
   */
  String describePlayer();

  /**
   * This is a method that a player can use to examine the space they are in.  It will return the
   * items in the room, if the target is visible in the room, neighboring rooms, any other players
   * in the room.
   *
   * @return A detailed description of the space the player is in.
   */
  String lookAround();

  /**
   * This method will investigate whether there is a player in the room with this current player or
   * if there is a player in a neighboring space.  In either case, that means this current player
   * can be seen.
   *
   * @return True if the player can be seen, false otherwise.
   */
  boolean canPlayerBeSeen();

}
