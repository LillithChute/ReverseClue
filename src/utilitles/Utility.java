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
