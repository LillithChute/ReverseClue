package theworld.exceptions;

/**
 * This exception is thrown when the user attempt to start a game
 * without a CPU player present.
 */
public class NoBotException extends GameplayException {
  /**
   * Constructs the exception for no CPU player.
   */
  public NoBotException() {
    super("No CPU player is added, can't start game!");
  }
}
