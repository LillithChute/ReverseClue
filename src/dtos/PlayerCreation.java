package dtos;

import utilitles.Utility;

/**
 * The DTO that encapsulates data needed to create a new player.
 */
public class PlayerCreation {
  private final String name;
  private final String location;
  private final int limit;

  /**
   * Constructs a player-creation DTO.
   *
   * @param n the name of the player.
   * @param lo the location name of the space to spawn in.
   * @param li the maximum number of items that this player can carry.
   */
  public PlayerCreation(String n, String lo, int li) {
    Utility.checkNull(n, lo);
    this.name = n;
    this.location = lo;
    this.limit = li;
  }

  /**
   * Gets the name associated with the new player.
   *
   * @return the name of the new player.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the name of the space to spawn the player in.
   *
   * @return the name of the space to spawn the player in.
   */
  public String getLocation() {
    return location;
  }

  /**
   * Gets the maximum number of items that this player can carry.
   *
   * @return the maximum number of items that this player can carry.
   */
  public int getLimit() {
    return limit;
  }


}
