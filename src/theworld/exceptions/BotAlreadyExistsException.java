package theworld.exceptions;

import theworld.model.TargetCharacter;

/**
 * This exception is thrown when a CPU controlled player already exists.
 */
public class BotAlreadyExistsException extends GameplayException {

  /**
   * Constructs an exception when the player already exists.
   *
   * @param invalid The invalid character
   * @param existed The corresponding existing character
   */
  public BotAlreadyExistsException(TargetCharacter invalid, TargetCharacter existed) {
    super(String.format("Bot character %s cannot be added, another bot %s is already in the game",
            invalid, existed));
  }
}
