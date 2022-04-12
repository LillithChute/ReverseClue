package gameinterfaces.targetinterfaces;

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
