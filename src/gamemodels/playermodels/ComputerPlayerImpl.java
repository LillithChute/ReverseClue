package gamemodels.playermodels;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.worldbuilderinterfaces.World;
import java.util.List;
import java.util.Random;

/**
 * A computer player is a special kind of player.  Not AI like, more like stupid.  However, the
 * computer player is different in that it will randomly select actions to take based on what
 * is allowed.
 */
public class ComputerPlayerImpl extends BasePlayer {

  /**
   * Constructs a computer player capable of taking its own actions.
   *
   * @param playerName the name of the player
   * @param playerLocation the staring location of the player
   * @param itemLimit the maximum number of items the player can carry
   */
  public ComputerPlayerImpl(String playerName, Space playerLocation, int itemLimit) {
    super(playerName, playerLocation, itemLimit);
  }

  @Override
  public String takeRandomAction(World world) {
    if (world == null) {
      throw new IllegalArgumentException("The World must exist!");
    }

    Random random = new Random();

    // If the computer is in the same room as the target and can't be seen the computer WILL
    // choose to attack.
    if (space.isTargetInThisSpace() && !canPlayerBeSeen()) {
      // Computer will attack if it can't be seen
      if (getPlayerItems().size() == 0) {
        // Computer has no items so it pokes target
        String result = attack("Poke in the Eye");

        return result;
      } else {
        // We have at least one item
        Item highestDamage = getHighestDamageItem(getPlayerItems());

        // We have the most damaging weapon.  Use it
        String result = attack(highestDamage.getNameOfItem());
        this.actionFinished = false;
        return result;
      }
    } else {
      int randomChoice = random.nextInt(4);
      this.actionFinished = false;
      return calculateComputerChoice(randomChoice, world);
    }
  }

  private Item getHighestDamageItem(List<Item> playerItems) {
    Item theMostDamage = null;

    for (Item currentItem :
        playerItems) {
      if (theMostDamage == null) {
        theMostDamage = currentItem;
      } else if (currentItem.getItemDamage() > theMostDamage.getItemDamage()) {
        theMostDamage = currentItem;
      }
    }

    return theMostDamage;
  }

  private String calculateComputerChoice(int randomChoice, World world) {
    String playerName;
    String result = null;
    Random random = new Random();

    switch (randomChoice) {
      case 0:
        // look around
        result = lookAround();
        break;
      case 1:
        // describe player
        result = describePlayer();
        break;
      case 2:
        // pick up an item
        String itemName = getItemToPickUp();
        playerName = getPlayerName();

        if (itemName == null) {
          result = "There was nothing for " + playerName + " to pick up.";
        } else {
          StringBuilder buildString = new StringBuilder();

          // For the computer player, we want to avoid the exception of a full backpack.  This is
          // because it will get the GUI controller stuck on the computer player's turn.  Thus,
          // check the item limit and if it is full move on.
          if (playerItems.size() < itemLimit) {
            takeItem(itemName);
            buildString.append("**").append(playerName).append("**\n");
            buildString.append(playerName).append(" picked up item: ").append(itemName);

            result = buildString.toString();
          } else {
            buildString.append("**").append(playerName).append("**\n");
            buildString.append(playerName).append(" cannot carry more items.");

            result = buildString.toString();
          }
        }
        break;
      case 3:
        // move
        StringBuilder buildString = new StringBuilder();

        String location = getNameOfSpaceToMoveTo();
        playerName = getPlayerName();

        move(location);

        buildString.append("**").append(playerName).append("**\n");
        buildString.append(playerName).append(" Moved to location:  ").append(location);

        result = buildString.toString();
        break;
      case 4:
        // move Pet
        boolean successfulMove = false;
        Space movingPetTo = null;

        while (!successfulMove) {
          // Get the total number of spaces
          int randomPetRoom = random.nextInt(world.getSpaces().size());

          // Get the Space at the random index
          movingPetTo = world.getSpaces().get(randomPetRoom);

          // See if the pet is already there
          if (!movingPetTo.hasPet()) {
            successfulMove = true;
          }
        }

        // We have a space to move to now
        movePet(movingPetTo, world.getSpaces());
        break;
      default:
        break;
    }

    return result;
  }

  private String getNameOfSpaceToMoveTo() {
    // How many neighbors?
    List<Space> neighbors = space.getNeighbors();

    if (neighbors.size() > 0) {
      // Pick one at random
      Random randomLocation = new Random();
      return neighbors.get(randomLocation.nextInt(neighbors.size())).getTheNameOfThisSpace();
    }

    return null;
  }

  private String getItemToPickUp() {
    // How many items?
    int itemCount = space.getItems().size();

    if (itemCount > 0) {
      // Items might be picked up so loop until we get one or there are none
      for (int i = 0; i < itemCount; i++) {
        if (!space.getItems().get(i).isItemWithPlayer()) {
          return space.getItems().get(i).getNameOfItem();
        }
      }
    }

    return null;
  }

  @Override
  public boolean completedTurn() {
    if (!this.actionFinished) {
      this.actionFinished = true;
    }
    return false;
  }
}
