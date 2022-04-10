package theworld.utilities;

/**
 * This class contains static methods that provide utilities to the project.
 */
public class MilestoneUtilities {

  /**
   * Check if a parameter is a null reference.

   * @param p the parameter to be checked
   */
  public static void checkParam(Object... p) {
    for (int i = 0; i < p.length; i++) {
      if (p[i] == null) {
        throw new IllegalArgumentException(
                String.format("argument at index %d is null", i));
      }
    }
  }
}
