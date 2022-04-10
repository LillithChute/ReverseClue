package theworld.exceptions;

/**
 * This exception is thrown when a game mechanic related error occurred,
 * such as going over the max turn limit.
 */
public class GameMechanicException extends GameplayException {

  /**
   * Constructs the exception with game mechanic errors.
   *
   * @param reason the reason for the error
   */
  public GameMechanicException(String reason) {
    super(String.format("Game mechanic error due to %s \n", reason));
  }
}
