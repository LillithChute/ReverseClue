package gamemodels.targetmodels;

import gameinterfaces.targetinterfaces.Target;
import gameinterfaces.targetinterfaces.TargetViewModel;

/**
 * This class represents the "target" of the game.  That is, the individual that the players are
 * attempting to kill.  The target, as in most games that kill, moves around the board and has
 * some amount of health such that when the target hits zero, it will die.  Once the target dies,
 * the game will end.
 */
public class TargetImpl implements Target, TargetViewModel {
  private final String name;
  private final int maxIndexOfSpaces;
  private int health;
  private int currentSpaceLocation;

  /**
   * This builds a Target, which consists of health, name, and max number of spaces.  The target
   * begins at space zero and each turn moves a space until the target hits the maximum and then
   * the target returns to the beginning.
   *
   * @param name             - Name of the target.
   * @param maxIndexOfSpaces - Essentially the total number of rooms.  If the mx spaces is hit,
   *                         the next move of the target will be back to room index 0.
   * @param health           - Health of the target.
   */
  public TargetImpl(String name, int maxIndexOfSpaces, int health) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("The Target must have a name.");
    }

    if (maxIndexOfSpaces < 1) {
      throw new IllegalArgumentException("The maximum number of space must be greater than 0");
    }

    if (health < 1) {
      throw new IllegalArgumentException("The Target cannot have 0 or negative health to "
          + "start with.");
    }

    this.name = name;
    this.maxIndexOfSpaces = maxIndexOfSpaces;
    this.health = health;
    this.currentSpaceLocation = 0;
  }

  /**
   * This returns the name of the target.
   *
   * @return The name of the target.
   */
  public String getTargetName() {
    return this.name;
  }

  /**
   * Gets the target's current health.
   *
   * @return The target's current health.
   */
  public int getCurrentHealth() {
    return this.health;
  }

  /**
   * This sets the target's health to a new value.  If that value happens to be negative, set the
   * health to zero.  No such thing as negative health.
   *
   * @param newHealth - The new value for the target's health.
   */
  public void setHealth(int newHealth) {
    this.health = Math.max(newHealth, 0);
  }

  @Override
  public String toString() {
    StringBuilder buildString = new StringBuilder();
    buildString.append(this.name)
        .append(";")
        .append(this.health)
        .append(";")
        .append(this.currentSpaceLocation)
        .append(";")
        .append(this.maxIndexOfSpaces);

    return buildString.toString();
  }
}
