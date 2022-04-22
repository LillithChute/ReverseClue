package gameinterfaces.spaceinterfaces;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.petinterfaces.Pet;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.targetinterfaces.Target;
import java.util.List;

public interface SpaceViewModel {
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
   * Gets the target from this {@link Space}.  If the Target is not here it returns null.
   *
   * @return The Target if it is here, null otherwise.
   */
  Target getTargetFromThisSpace();

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
   * Retrieves the {@link Pet} from the {@link Space}.
   *
   * @return The {@link Pet}.
   */
  Pet getPet();
}
