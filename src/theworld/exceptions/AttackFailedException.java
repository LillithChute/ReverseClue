package theworld.exceptions;

/**
 * Represents an exception when an attack is failed.
 */
public class AttackFailedException extends GameplayException {
  /**
   * Constructs an exception for a failed attack.
   *
   * @param reason the reason of the failure
   */
  public AttackFailedException(String reason) {
    super(reason);
  }
}
