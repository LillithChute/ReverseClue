package customexceptions;

/**
 * A custom exception built to remove the checked exceptions.
 */
public class IncorrectFilePathException extends RuntimeException {
  /**
   * Constructor for custom exception.  This is to help with the checked exception issues.
   *
   * @param errorMessage - The error message to be returned.
   */
  public IncorrectFilePathException(String errorMessage) {
    super(errorMessage);
  }
}

