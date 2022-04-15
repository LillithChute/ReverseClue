package gamemodels.playermodels;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.petinterfaces.Pet;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.playerinterfaces.PlayerViewModel;
import gameinterfaces.spaceinterfaces.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class will represent all the behaviors that can be enacted by any type of player in the
 * game such as moving, picking up items, etc.
 */
public class PlayerImpl implements Player, PlayerViewModel {

  private final String playerName;
  private final List<Item> playerItems;
  private Space space;
  private final int itemLimit;
  private final boolean isHuman;

  /**
   * This builds an instance of a basic player.
   *
   * @param playerName - Name of the player
   * @param playerLocation - Location the player starts at
   * @param itemLimit - The number of items a player can carry.
   * @param  isHuman - Sets whether this player is computer controlled (false) or not.
   */
  public PlayerImpl(String playerName, Space playerLocation, int itemLimit, boolean isHuman) {

    // Validation
    if (playerName == null || playerName.isEmpty() || playerName.isBlank()) {
      throw new IllegalArgumentException("The player must have a name.\n");
    }

    if (playerLocation == null) {
      throw new IllegalArgumentException("This location does not exist.\n");
    }

    if (itemLimit < 1) {
      throw new IllegalArgumentException("The player item limit must be positive.\n");
    }

    this.playerName = playerName;
    this.space = playerLocation;
    this.space.addPlayerToSpace(this);
    this.itemLimit = itemLimit;
    this.isHuman = isHuman;
    playerItems = new ArrayList<>();
  }

  @Override
  public void move(String nameOfLocation) {
    if (nameOfLocation == null || nameOfLocation.isBlank()) {
      throw new IllegalArgumentException("This location does not exist.\n");
    }

    // See if this space is in the neighbors list
    Space neighbor = findNeighborSpace(nameOfLocation);

    if (neighbor != null) {
      // The player is moving from this current space, so remove them
      space.removePlayerFromSpace(this);

      // Assign the Space belonging to this player to the space they are moving to.
      space = neighbor;

      // Now add this player to the move to Space
      space.addPlayerToSpace(this);
    } else {
      throw new IllegalStateException("The space the player is moving to is not a neighbor.\n");
    }
  }

  private Space findNeighborSpace(String nameOfLocation) {
    for (Space currentSpace :
            space.getNeighbors()) {
      if (nameOfLocation.equalsIgnoreCase(currentSpace.getTheNameOfThisSpace())) {
        return currentSpace;
      }
    }

    return null;
  }

  @Override
  public int getLocation() {
    return this.space.getIndexOfTheSpace();
  }

  @Override
  public void takeItem(String itemName) {
    // Validation
    if (itemName == null || itemName.isEmpty() || itemName.isBlank()) {
      throw new IllegalArgumentException("The item must have a name.\n");
    }

    // The player can't carry any more items.
    if (itemLimit < playerItems.size() + 1) {
      throw new IllegalStateException("The player cannot carry more items.\n");
    }

    // Get the Item that the player wants to pick up
    Item itemToBeAcquired = findItem(itemName);

    // The item name is not found
    if (itemToBeAcquired == null) {
      throw new IllegalStateException("Unable to find " + itemName + " in the space\n");
    }

    // The item has already been picked up.
    if (itemToBeAcquired.isItemWithPlayer()) {
      throw new IllegalStateException(itemName + " has been taken.");
    }

    // Is the item in the space with the player
    itemToBeAcquired.setIsItemWithPlayer();

    playerItems.add(itemToBeAcquired);
  }

  private Item findItem(String itemName) {
    for (Item currentItem :
            space.getItems()) {
      if (itemName.equalsIgnoreCase(currentItem.getNameOfItem())) {
        return currentItem;
      }
    }

    return null;
  }

  @Override
  public String getPlayerName() {
    return this.playerName;
  }

  @Override
  public String getPlayerItemsDescription() {
    StringBuilder buildString = new StringBuilder();

    buildString.append(playerName)
            .append(" is carrying:")
            .append("\n");

    // Only return items that are not in evidence (has been used)
    for (Item item :
            playerItems) {
      if (!item.isItemEvidence()) {
        buildString.append(item.getNameOfItem())
                .append("\n");
      }
    }

    return buildString.toString();
  }

  @Override
  public List<Item> getPlayerItems() {
    return playerItems;
  }

  @Override
  public boolean isHuman() {
    return isHuman;
  }

  @Override
  public String describePlayer() {
    StringBuilder buildString = new StringBuilder();

    buildString.append("Player Description:\n");
    buildString.append("\tName: ")
            .append(getPlayerName())
            .append("\n");

    // Items
    buildString.append("\tItems:\n")
            .append("\t\t")
            .append(getPlayerItemsDescription());

    // Location
    String locationName = space.getTheNameOfThisSpace();

    buildString.append("\tLocation: ")
            .append(locationName);

    return buildString.toString();
  }

  @Override
  public String lookAround() {
    StringBuilder buildString = new StringBuilder();

    // Intro
    buildString.append(getPlayerName()).append(" is in here in ")
            .append(space.getTheNameOfThisSpace())
            .append(".\n\n");

    // The Items in this room
    buildString = getItemsInTheSpace(buildString, space);

    // Target information
    buildString = getTargetInformation(buildString, space);

    // Pet information
    buildString = getPetInformation(buildString, space);

    // Players in this space?
    buildString.append(getPlayersInTheSpace(space));

    // Get neighbor information
    buildString.append(getNeighboringSpaceInfo());

    return buildString.toString().trim();
  }

  private StringBuilder getTargetInformation(StringBuilder buildString, Space currentSpace) {
    // Target information for this space
    buildString.append("Target information for this room:\n");

    // Is target here?
    if (currentSpace.isTargetInThisSpace()) {
      buildString.append("\t")
              .append(currentSpace.getTargetFromThisSpace().getTargetName())
              .append(" is here.\n\n");
    } else {
      buildString.append("\t")
              .append("The target is not here.\n\n");
    }

    return buildString;
  }

  private StringBuilder getPetInformation(StringBuilder buildString, Space currentSpace) {
    // Target information for this space
    buildString.append("Pet information for this room:\n");

    // Is Pet here?
    if (currentSpace.hasPet()) {
      buildString.append("\t")
              .append(currentSpace.getPet().getName())
              .append(" is here.\n\n");
    } else {
      buildString.append("\t")
              .append("The pet is not here.\n\n");
    }

    return buildString;
  }

  @Override
  public boolean canPlayerBeSeen() {
    // Let's see if there are any other players in this room.  If the list of players is more
    // than one, then that means there is this current player plus one more.
    if (space.getPlayersInThisSpace().size() > 1) {
      return true;
    }

    // If there are no players in the space with the current player AND the pet is in the room,
    // then the current player cannot be seen.
    if (space.hasPet()) {
      return false;
    }

    // If the above cases are not true, then we need to check the neighboring spaces.  If there is
    // a player in a neighbor space, then this player can be seen
    for (Space currentSpace :
            space.getNeighbors()) {
      // There is a player in a neighboring space
      if (currentSpace.getPlayersInThisSpace().size() > 0) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void movePet(Space spaceToMovePetTo, List<Space> allSpaces) {
    if (spaceToMovePetTo == null) {
      throw new IllegalArgumentException("The space to move the pet to cannot be "
              + "null.");
    }

    if (allSpaces == null) {
      throw new IllegalArgumentException("We need a list of the spaces in the game to perform the"
              + " move action for the pet.");
    }

    // We need to get the space the Pet is in and get the pet from it
    Space petSpace = null;
    Pet pet = null;

    for (Space currentSpace :
            allSpaces) {
      if (currentSpace.hasPet()) {
        petSpace = currentSpace;
        pet = currentSpace.getPet();
        break;
      }
    }

    // Now, we take the pet out of the space it is in and put it in the space it is going to.
    // Unless it is the same space
    if (!spaceToMovePetTo.equals(petSpace)) {
      spaceToMovePetTo.addPetToSpace(pet);
      petSpace.addPetToSpace(null);
    }
  }

  @Override
  public String attack(String itemName) {
    StringBuilder result = new StringBuilder();

    // Validation
    if (itemName == null || itemName.isBlank()) {
      throw new IllegalArgumentException("You must use an item to attack the target.");
    }

    // Make sure they are in the same room
    if (!space.isTargetInThisSpace()) {
      throw new IllegalStateException("The target is not in the same space and can't be attacked.");
    }

    // Before we do anything, we need to check if the player can be seen.  If they can, nothing
    // further will happen other than the item goes to evidence.
    if (canPlayerBeSeen()) {
      result.append("You were seen!  Your attack was not successful.");

      // Put item into evidence
      if (!"Poke in the Eye".equalsIgnoreCase(itemName)) {
        Item attackingItem = getPlayerItemByName(itemName);
        // Flag the item as evidence
        attackingItem.setItemIsEvidence();
        // remove from the player inventory
        playerItems.remove(attackingItem);
        result.append("  ").append(attackingItem.getNameOfItem()).append(" is now evidence.");
      }

      return result.toString();
    }

    // Get the item player has chosen.  If the item is Poke in the Eye, that is always 1 point of
    // damage and isn't part of the ITEM in the WORLD situation
    if ("Poke in the Eye".equalsIgnoreCase(itemName)) {
      space.getTargetFromThisSpace().setHealth(
              space.getTargetFromThisSpace().getCurrentHealth() - 1);

      result.append("You poked ").append(space.getTargetFromThisSpace().getTargetName())
              .append(" in the eye and did 1 point of damage!")
              .append("  ").append(space.getTargetFromThisSpace().getTargetName())
              .append(" now has ").append(space.getTargetFromThisSpace().getCurrentHealth())
              .append(" health remaining.");
    } else {
      Item attackingItem = getPlayerItemByName(itemName);

      // this item is not in our inventory
      if (attackingItem == null) {
        throw new IllegalStateException("You do not have this item in your inventory!");
      }

      // We have the item.  Let's damage the target
      space.getTargetFromThisSpace().setHealth(
              space.getTargetFromThisSpace().getCurrentHealth() - attackingItem.getItemDamage());

      // The item is now evidence
      attackingItem.setItemIsEvidence();

      // remove from the player inventory
      playerItems.remove(attackingItem);

      // Tell the player what happened
      result.append("You attacked with ").append(attackingItem.getNameOfItem()).append(" and did ")
              .append(attackingItem.getItemDamage()).append(" points of damage to ")
              .append(space.getTargetFromThisSpace().getTargetName()).append(".  ")
              .append(attackingItem.getNameOfItem()).append(" is now evidence.")
              .append("  ").append(space.getTargetFromThisSpace().getTargetName())
              .append(" now has ").append(space.getTargetFromThisSpace().getCurrentHealth())
              .append(" health remaining.");
    }

    // Target is dead
    if (space.getTargetFromThisSpace().getCurrentHealth() == 0) {
      result.append("\n\nCongratulations!  You killed ").append(space.getTargetFromThisSpace()
              .getTargetName()).append("!\n");
    }

    return result.toString();
  }

  private String getPlayersInTheSpace(Space currentSpace) {
    StringBuilder buildString = new StringBuilder();
    buildString.append("The following players are in the room:\n");

    // Get the players in this room
    if (currentSpace.getPlayersInThisSpace().size() > 0) {
      for (Player currentPlayer :
              currentSpace.getPlayersInThisSpace()) {
        if (currentPlayer != this) {
          // It is not the current player, so it is a different one
          buildString.append(currentPlayer.getPlayerName())
                  .append(" is in ")
                  .append(currentSpace.getTheNameOfThisSpace())
                  .append("\n");
        }
      }
    }

    // if we only have the title line, no one was here
    if (buildString.toString()
            .equalsIgnoreCase("The following players are in the room:\n")) {
      buildString.append("\tNone.\n");
    }

    return buildString.toString();
  }

  private String getNeighboringSpaceInfo() {
    StringBuilder neighboringInfo = new StringBuilder();
    neighboringInfo.append("\nHere is the information about neighboring spaces:\n\n");

    // Get the neighbors
    List<Space> neighbors = space.getNeighbors();

    // Now, for each room we want items, players, and target data
    for (Space currentNeighbor :
            neighbors) {
      neighboringInfo.append("This space is ").append(currentNeighbor.getTheNameOfThisSpace())
              .append("\n");

      if (!currentNeighbor.hasPet()) {
        // Item information
        neighboringInfo = getItemsInTheSpace(neighboringInfo, currentNeighbor);

        // Target information
        neighboringInfo = getTargetInformation(neighboringInfo, currentNeighbor);

        // Players in this space?
        neighboringInfo.append(getPlayersInTheSpace(currentNeighbor));
      }
    }

    return neighboringInfo.toString();
  }

  private StringBuilder getItemsInTheSpace(StringBuilder buildString, Space currentSpace) {
    // The Items in this room
    buildString.append("Here is a list of items located here:\n");

    for (Item item :
            currentSpace.getItems()) {
      if (!item.isItemWithPlayer()) {
        buildString.append("\t").append(item.getNameOfItem()).append("\n");
      }
    }

    buildString.append("\n");

    return buildString;
  }

  private Item getPlayerItemByName(String itemName) {
    if (itemName == null || itemName.isBlank()) {
      throw new IllegalArgumentException("Name of the item can't be blank.");
    }

    for (Item currentItem :
            playerItems) {
      if (currentItem.getNameOfItem().equalsIgnoreCase(itemName)) {
        return currentItem;
      }
    }

    return null;
  }

  @Override
  public String toString() {
    StringBuilder buildString = new StringBuilder();

    buildString.append(playerName)
            .append(";")
            .append(space.getIndexOfTheSpace())
            .append(";")
            .append(itemLimit);

    for (Item item :
            playerItems) {
      buildString.append(";")
              .append(item.getNameOfItem());
    }

    return buildString.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PlayerImpl that = (PlayerImpl) o;
    return space.getIndexOfTheSpace() == that.space.getIndexOfTheSpace()
            && itemLimit == that.itemLimit
            && playerName.equals(that.playerName)
            && Objects.equals(playerItems, that.playerItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerName, playerItems, space.getIndexOfTheSpace(), itemLimit);
  }
}
