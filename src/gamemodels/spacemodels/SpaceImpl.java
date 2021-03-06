package gamemodels.spacemodels;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.petinterfaces.Pet;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.spaceinterfaces.SpaceViewModel;
import gameinterfaces.targetinterfaces.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * A space is a location where a target or a player may move to.  It is, essentially, the game
 * board.  This space can hold one or more items that are used by a player to damage the target.  A
 * space has other spaces that might share a wall.  The space is where all the action happens.
 */
public class SpaceImpl implements Space, SpaceViewModel {
  private final int indexOfThisSpace;
  private final String nameOfThisSpace;
  private final int upperLeftxCoordinate;
  private final int upperLeftyCoordinate;
  private final int lowerRightxCoordinate;
  private final int lowerRightyCoordinate;
  private final List<Item> itemsInThisSpace;
  private final List<Space> neighbors;
  private final List<Player> players;
  private Target target;
  private Pet pet;

  /**
   * The constructor that creates the instance of the SpaceImpl class.  Each space will be created
   * using upper left coordinate and lower right coordinate and extrapolate the walls from those.
   * Additionally, we will have a name for the space and the Index that is the order of this Space
   * in the list.
   *
   * @param indexOfThisSpace      - The index of the order of this Space in the list
   * @param nameOfThisSpace       - The name of this particular space.
   * @param upperLeftxCoordinate  - The upper left X coordinate
   * @param upperLeftyCoordinate  - The upper left Y coordinate
   * @param lowerRightxCoordinate - The lower right X coordinate
   * @param lowerRightyCoordinate - The lower right Y coordinate
   */
  public SpaceImpl(int indexOfThisSpace, String nameOfThisSpace, int upperLeftxCoordinate,
                   int upperLeftyCoordinate, int lowerRightxCoordinate, int lowerRightyCoordinate) {

    // None of the coordinates can be negative
    if (upperLeftxCoordinate < 0 || upperLeftyCoordinate < 0
        || lowerRightxCoordinate < 0 || lowerRightyCoordinate < 0) {
      throw new IllegalArgumentException("None of the coordinates for a space can be negative.");
    }

    // Spaces are built from the upper left corner to the lower right corner.  The grid being like
    // an Excel sheet.  So 0,0 is the upper left point. Therefore, the upper left x value should be
    // smaller than the lower right.  Otherwise, you have a line and not a room
    if (upperLeftxCoordinate >= lowerRightxCoordinate) {
      throw new IllegalArgumentException("Invalid X coordinates.");
    }

    // Same idea goes for the Y coordinates
    if (upperLeftyCoordinate >= lowerRightyCoordinate) {
      throw new IllegalArgumentException("Invalid Y coordinates.");
    }

    if (nameOfThisSpace == null || nameOfThisSpace.isBlank()) {
      throw new IllegalArgumentException("The space must have a name.");
    }

    if (indexOfThisSpace < 0) {
      throw new IllegalArgumentException("The space can't have a negative index.");
    }

    this.indexOfThisSpace = indexOfThisSpace;
    this.nameOfThisSpace = nameOfThisSpace;
    this.upperLeftxCoordinate = upperLeftxCoordinate;
    this.upperLeftyCoordinate = upperLeftyCoordinate;
    this.lowerRightxCoordinate = lowerRightxCoordinate;
    this.lowerRightyCoordinate = lowerRightyCoordinate;

    // Initialize.
    this.itemsInThisSpace = new ArrayList<>();
    this.neighbors = new ArrayList<>();
    this.players = new ArrayList<>();
  }

  @Override
  public int getUpperLeftxCoordinate() {
    return this.upperLeftxCoordinate;
  }

  @Override
  public int getUpperLeftyCoordinate() {
    return this.upperLeftyCoordinate;
  }

  @Override
  public int getLowerRightxCoordinate() {
    return this.lowerRightxCoordinate;
  }

  @Override
  public int getLowerRightyCoordinate() {
    return this.lowerRightyCoordinate;
  }

  @Override
  public void moveTargetToThisSpace(Target target) {
    this.target = target;
  }

  @Override
  public Target getTargetFromThisSpace() {
    return target;
  }

  @Override
  public void putItemsInTheSpace(List<Item> items) {
    if (items == null) {
      throw new IllegalArgumentException("To put items in a Space you must actually have items!");
    }

    // Deep copy.
    for (Item currentItem :
        items) {
      itemsInThisSpace.add(currentItem);
    }
  }

  @Override
  public void setNeighborsOfThisSpace(List<Space> neighbors) {
    if (neighbors == null) {
      throw new IllegalArgumentException("To set neighbors of a Space you must actually have "
              + "neighbors!");
    }

    // Deep copy
    for (Space currentSpace :
        neighbors) {
      this.neighbors.add(currentSpace);
    }
  }

  @Override
  public void addPlayerToSpace(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("You must have a player in order to add to this room.");
    }

    players.add(player);
  }

  @Override
  public void removePlayerFromSpace(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("You must have a player in order to remove them.");
    }

    players.remove(player);
  }

  @Override
  public List<Player> getPlayersInThisSpace() {
    // Deep copy the data
    List<Player> copyOfPlayerList = new ArrayList<>();

    for (Player currentPlayer :
        players) {
      copyOfPlayerList.add(currentPlayer);
    }

    return copyOfPlayerList;
  }

  @Override
  public boolean hasPet() {
    return pet != null;
  }

  @Override
  public void addPetToSpace(Pet pet) {
    // No validation because we need to be able to add a null pet here as a remove.
    this.pet = pet;
  }

  @Override
  public Pet getPet() {
    return pet;
  }

  @Override
  public List<Item> getItems() {
    // Deep copy the data
    List<Item> copyOfItemsList = new ArrayList<>();

    for (Item currentItem :
        itemsInThisSpace) {
      copyOfItemsList.add(currentItem);
    }

    return copyOfItemsList;
  }

  @Override
  public int getIndexOfTheSpace() {
    return this.indexOfThisSpace;
  }

  @Override
  public List<Space> getNeighbors() {
    // Deep copy the data
    List<Space> copyOfNeighborsList = new ArrayList<>();

    for (Space currentNeighbor :
        neighbors) {
      copyOfNeighborsList.add(currentNeighbor);
    }

    return copyOfNeighborsList;
  }

  @Override
  public boolean isTargetInThisSpace() {
    return this.target != null;
  }

  @Override
  public String getTheNameOfThisSpace() {
    return this.nameOfThisSpace;
  }

  @Override
  public String getTheFullSpaceDescription() {
    StringBuilder buildDescription = new StringBuilder();

    // Get name of the space
    buildDescription.append("* NAME: ").append(getTheNameOfThisSpace())
        .append("\n* ")
        .append("\n* ");

    // Get the items here
    buildDescription.append("ITEMS:\n");

    for (Item item :
        itemsInThisSpace) {
      if (!item.isItemWithPlayer()) {
        buildDescription.append("* \t").append(item.getNameOfItem()).append("\n");
      }
    }

    buildDescription.append("* \n");

    // Get neighboring spaces
    buildDescription.append("* NEIGHBORING SPACES:\n");

    for (Space space :
        this.neighbors) {
      buildDescription.append("* \t").append(space.getTheNameOfThisSpace()).append("\n");
    }

    return buildDescription.toString();
  }

  @Override
  public String toString() {
    StringBuilder buildString = new StringBuilder();
    return buildString.append(nameOfThisSpace).toString();
  }
}
