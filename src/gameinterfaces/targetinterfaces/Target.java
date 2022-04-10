package gameinterfaces.targetinterfaces;

/**
 * This interface will represent the actions that a target in the game may take.
 */
public interface Target {

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

  /**
   * This sets the target's health to a new value.  If that value happens to be negative, set the
   * health to zero.  No such thing as negative health.
   *
   * @param newHealth - The new value for the target's health.
   */
  void setHealth(int newHealth);
}
