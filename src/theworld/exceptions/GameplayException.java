package theworld.exceptions;

/**
 * The abstract base class for all gameplay related (recoverable) exceptions.
 */
public abstract class GameplayException extends RuntimeException {

  /**
   * Constructs the exception by forwarding it to the constructor
   * of RuntimeException.
   *
   * @param msg the message to be forwarded
   */
  public GameplayException(String msg) {
    super(msg);
  }

}
