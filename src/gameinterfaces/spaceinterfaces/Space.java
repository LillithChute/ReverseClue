package gameinterfaces.spaceinterfaces;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.petinterfaces.Pet;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.targetinterfaces.Target;
import java.util.List;

/**
 * This interface represents the spaces that a target and the players will move through.  I call it
 * a space because it might not be a room per se.  It could represent a town, a planet, another
 * dimension.  This will contain all the data necessary to represent a space in the game.  A room
 * has neighboring rooms.  It has item(s) in it.  It can have a player or the target.  A room will
 * have attributes as well, like walls and a name.
 */
public interface Space {

  /**
   * This method will collect all the items contained in this room.  Any room can have 0 to many
   * items in it.
   *
   * @return - A list of items in this room.
   */
  List<Item> getItems();

  /**
   * This will return the ordinal index associated with this space.
   *
   * @return The list index of this particular space.
   */
  int getIndexOfTheSpace();

  /**
   * This method calculates all the spaces that share a wall with this particular space.  It returns
   * a list of them.
   *
   * @return A list of all the spaces that share a wall with this space.
   */
  List<Space> getNeighbors();

  /**
   * This determines if the target is currently in this space.
   *
   * @return If the target is currently in this space.
   */
  boolean isTargetInThisSpace();

  /**
   * Returns the name of this particular space.
   *
   * @return The name of this space.
   */
  String getTheNameOfThisSpace();

  /**
   * This return the description of this space.  That description consists of the name of the
   * space, the items in the space, and all the names of the neighbors of the space.
   *
   * @return A full description of the space.
   */
  String getTheFullSpaceDescription();

  /**
   * Get the upper left X coordinate of this space.
   *
   * @return Upper left X
   */
  int getUpperLeftxCoordinate();

  /**
   * Gets the upper left Y coordinate of this space.
   *
   * @return Upper left Y coordinate.
   */
  int getUpperLeftyCoordinate();

  /**
   * Gets the lower left X coordinate of this space.
   *
   * @return lower left X coordinate.
   */
  int getLowerRightxCoordinate();

  /**
   * Gets the lower right Y coordinate of this space.
   *
   * @return lower right Y coordinate.
   */
  int getLowerRightyCoordinate();

  /**
   * Puts the target in this space.
   *
   * @param target - The Target.
   */
  void moveTargetToThisSpace(Target target);

  /**
   * Gets the target from this {@link Space}.  If the Target is not here it returns null.
   *
   * @return The Target if it is here, null otherwise.
   */
  Target getTargetFromThisSpace();

  /**
   * Given a list of items, it will put those items into this space.
   *
   * @param items - A list of items to place in this space.
   */
  void putItemsInTheSpace(List<Item> items);

  /**
   * Given a list of {@link Space}, assign those spaces as the current space's neighbors.
   *
   * @param neighbors - List of spaces that are neighbors.
   */
  void setNeighborsOfThisSpace(List<Space> neighbors);

  /**
   * A player has a Space.  That is how they perform their actions.  However, to attack, we need
   * to know if they are seen.  So, in this crazy circular reference, a player also needs to be IN
   * a Space.  That way, when we get a player and look at their Space, we can see if anyone is
   * there.
   *
   * @param player - A player to be added.
   */
  void addPlayerToSpace(Player player);

  /**
   * So, when a Player moves, they need to be removed from the Space they are currently in.
   *
   * @param player - The Player to be removed.
   */
  void removePlayerFromSpace(Player player);

  /**
   * Gets all the players in this space.
   *
   * @return A list of all the players in the space.
   */
  List<Player> getPlayersInThisSpace();

  /**
   * This method checks to see if the {@link Space} has the pet in it.
   *
   * @return True if the pet is in the space, false otherwise.
   */
  boolean hasPet();

  /**
   * This will take a Pet and add it to this {@link Space}.  You can add a null Pet.
   *
   * @param pet - The {@link Pet} to be added.
   */
  void addPetToSpace(Pet pet);

  /**
   * Retrieves the {@link Pet} from the {@link Space}.
   *
   * @return The {@link Pet}.
   */
  Pet getPet();
}
