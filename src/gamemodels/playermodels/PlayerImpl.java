package gamemodels.playermodels;

import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.worldbuilderinterfaces.World;

/**
 * This class will represent all the behaviors that can be enacted by any type of player in the
 * game such as moving, picking up items, etc.
 */
public class PlayerImpl extends BasePlayer {

  /**
   * This builds an instance of a basic player.
   *
   * @param playerName     - Name of the player
   * @param playerLocation - Location the player starts at
   * @param itemLimit      - The number of items a player can carry.
   */
  public PlayerImpl(String playerName, Space playerLocation, int itemLimit) {
    super(playerName, playerLocation, itemLimit);
  }

  @Override
  public String takeRandomAction(World world) {
    return String.format("%s's Turn, please take action.", this.playerName);
  }

  @Override
  public boolean completedTurn() {
    return true;
  }
}
