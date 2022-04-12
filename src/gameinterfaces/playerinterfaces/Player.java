package gameinterfaces.playerinterfaces;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.spaceinterfaces.Space;
import java.util.List;

/**
 * This interface wil represent the actions that a player in the game may take.
 */
public interface Player {

  /**
   * This will move a player to a valid space.  By valid, the player may only move
   * to a neighboring space.
   *
   * @param nameOfSpace - The space the player will move to.
   */
  void move(String nameOfSpace);

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
   *  * This method returns a description of all items a player is carrying.
   *
   * @return A description of the items the player has.
   */
  String getPlayerItemsDescription();

  /**
   *  * This method returns all the items a player is carrying.
   *
   * @return A list of the items the player has.
   */
  List<Item> getPlayerItems();

  /**
   * This method allows a player to pick up an item that is in the room.
   *
   * @param item - The item to be picked up.
   */
  void takeItem(String item);

  /**
   * Need a way to be able to tell if a player is computer controlled or human.
   *
   * @return True if player is human, false otherwise.
   */
  boolean isHuman();

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

  /**
   * This method will move the pet to the designated space.  Unfortunately, the Player is not
   * aware of all the Spaces, yet can move the pet anywhere.  So, I need to pass the list of all
   * the space to the player so they have them available.
   *
   * @param spaceToMoveTo - The space to move the pet to.
   * @param  allSpaces - All the spaces in the game.
   */
  void movePet(Space spaceToMoveTo, List<Space> allSpaces);

  /**
   * This will allow the player to attack the target using the chosen item.  As long as the
   * player can't be seen, the attack will be successful.
   *
   * @param itemName - THe item the player is using to attack the target.
   * @return The results of the attack.
   */
  String attack(String itemName);
}
