package utilitles;

import gameinterfaces.spaceinterfaces.Space;

/**
 * A static utilities class that provides useful tools for the project.
 */
public class Utility {

  /**
   * Checks if any of the arguments are null.
   *
   * @param args the references to be checked.
   */
  public static void checkNull(Object... args) {
    for (Object o : args) {
      if (o == null) {
        throw new IllegalArgumentException("Parameter is null.");
      }
    }
  }

  /**
   * Validation checker for creating a player in the game.  Every player needs a name, a place
   * to call home, and the number of items they can carry.
   *
   * @param playerName - Name of the player.
   * @param playerLocation - Space the player is starting in.
   * @param itemLimit - Maximum number of items they can carry.
   */
  public static void validatePlayerCreation(String playerName, Space playerLocation,
                                            int itemLimit) {
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
  }
}
