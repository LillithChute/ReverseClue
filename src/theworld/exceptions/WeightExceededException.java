package theworld.exceptions;


import theworld.model.Iplayer;

/**
 * This exception is thrown when the player attempts to carry
 * another item above the weight limit.
 */
public class WeightExceededException extends GameplayException {

  /**
   * Constructs the exception with a player.
   *
   * @param p the player who's carrying limit is exceed
   */
  public WeightExceededException(Iplayer p) {
    super(String.format("Player %s can only carry %d items, can't pick up.", p, p.getStrength()));
  }
}
