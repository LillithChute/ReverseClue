package theworld.exceptions;

/**
 * This exception is thrown when there's no player to actually
 * play the game.
 */
public class NotEnoughPlayersException extends GameplayException {
  /**
   * Constructs the exception.
   */
  public NotEnoughPlayersException() {
    super("No players added to the game, can't start!");
  }
}
