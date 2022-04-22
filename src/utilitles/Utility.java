package utilitles;

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
}
