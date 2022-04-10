package instancecreationhelpers;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.targetinterfaces.Target;
import gamemodels.itemmodels.ItemImpl;
import gamemodels.playermodels.PlayerImpl;
import gamemodels.spacemodels.SpaceImpl;
import gamemodels.targetmodels.TargetImpl;

/**
 * This class is designed to create instances of Classes for this project.  I was using this
 * structure in the tests.  However, I began needing to create multiple different instances in a
 * single test file and code was being repeated, so I pulled it out into its own class.
 */
public class InstanceBuilder {

  /**
   * Builder method to return a new Item instance.
   *
   * @param name - Name of the item.
   * @param damage Damage the item does.
   * @param spaceLocation - Space the item is in.
   * @return - A new Item instance.
   */
  public Item itemBuilder(String name, int damage, int spaceLocation) {
    return new ItemImpl(name, damage, spaceLocation);
  }

  /**
   * Builder method to return a new Space instance.
   *
   * @param indexOfSpace - The index of the order of this Space in the list
   * @param name - The name of this particular space.
   * @param upperLeftX - The upper left X coordinate
   * @param upperLeftY - The upper left Y coordinate
   * @param lowerRightX - The lower right X coordinate
   * @param lowerRightY - The lower right Y coordinate
   * @return - A new Space instance.
   */
  public Space spaceBuilder(int indexOfSpace, String name, int upperLeftX,
                            int upperLeftY, int lowerRightX, int lowerRightY) {
    return new SpaceImpl(indexOfSpace, name, upperLeftX, upperLeftY, lowerRightX, lowerRightY);
  }

  /**
   * This method is a builder that creates a Target instance.
   *
   * @param name - Name of the target.
   * @param maxIndexOfSpaces - Maximum number of spaces.
   * @param health - Stamina of the target.
   * @return - A new Target.
   */
  public Target targetBuilder(String name, int maxIndexOfSpaces, int health) {
    return new TargetImpl(name, maxIndexOfSpaces, health);
  }

  /**
   * This method creates an instance of a Player.
   *
   * @param playerName - Name of the player.
   * @param playerLocation - The player's location.
   * @param itemLimit - List of items the player is carrying.
   * @param isHuman - True if the player is human, false otherwise.
   * @return An instance of a {@link Player}.
   */
  public Player playerBuilder(String playerName, Space playerLocation, int itemLimit,
                              boolean isHuman) {
    return new PlayerImpl(playerName, playerLocation, itemLimit, isHuman);
  }
}
