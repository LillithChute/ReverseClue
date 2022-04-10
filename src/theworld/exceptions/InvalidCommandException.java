package theworld.exceptions;

/**
 * Represents an exception when an invalid command was entered.
 */
public class InvalidCommandException extends RuntimeException {

  /**
   * Constructs the exception with provided invalid command.
   *
   * @param cmd the invalid command
   */
  public InvalidCommandException(String cmd) {
    super(String.format("User entered invalid command: %s\n", cmd));
  }
}
