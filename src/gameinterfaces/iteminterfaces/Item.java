package gameinterfaces.iteminterfaces;

/**
 * This interface represents the attributes of an item that a player might use to kill the
 * target in this game.  This item has a description/name as well as a location that it currently
 * resides in.
 */
public interface Item extends ItemViewModel {

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
   * Sets whether the item has been picked up.
   */
  void setIsItemWithPlayer();

  /**
   * Checks to see if this particular item has been used and is evidence.
   *
   * @return True or false depending on whether the item has been used.
   */
  boolean isItemEvidence();

  /**
   * Sets whether the item has been used.
   */
  void setItemIsEvidence();

  /**
   * Gets the amount of damage the item can do.
   *
   * @return The damage amount of the item.
   */
  int getItemDamage();
}
