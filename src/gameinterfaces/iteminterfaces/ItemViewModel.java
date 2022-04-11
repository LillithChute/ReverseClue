package gameinterfaces.iteminterfaces;

public interface ItemViewModel {
  /**
   * Retrieves the name/description of the item.
   *
   * @return The name/description of the item.
   */
  String getNameOfItem();

  /**
   * It will get the index of the space the item is in.
   *
   * @return The index of the space the item is in.
   */
  int getSpaceIndexOfItem();

  /**
   * Checks to see if this particular item has been picked up.
   *
   * @return True or false depending on whether the item is with a player.
   */
  boolean isItemWithPlayer();

  /**
   * Checks to see if this particular item has been used and is evidence.
   *
   * @return True or false depending on whether the item has been used.
   */
  boolean isItemEvidence();

  /**
   * Gets the amount of damage the item can do.
   *
   * @return The damage amount of the item.
   */
  int getItemDamage();
}
