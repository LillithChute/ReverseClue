package gameinterfaces.targetinterfaces;

/**
 * A readonly view-model representing the target character of the game.
 */
public interface TargetViewModel {
  /**
   * This returns the name of the target.
   *
   * @return The name of the target.
   */
  String getTargetName();

  /**
   * Gets the target's current health.
   *
   * @return The target's current health.
   */
  int getCurrentHealth();

}
