package view.implementations;

import gameinterfaces.iteminterfaces.ItemViewModel;
import java.util.Random;

//TODO: remove this whole thing
public class TestingItem implements ItemViewModel {

  private int index = new Random().nextInt(1000);

  /**
   * Retrieves the name/description of the item.
   *
   * @return The name/description of the item.
   */
  @Override
  public String getNameOfItem() {
    return String.format("TEST ITEM: %d", index);
  }

  /**
   * It will get the index of the space the item is in.
   *
   * @return The index of the space the item is in.
   */
  @Override
  public int getSpaceIndexOfItem() {
    return 0;
  }

  /**
   * Checks to see if this particular item has been picked up.
   *
   * @return True or false depending on whether the item is with a player.
   */
  @Override
  public boolean isItemWithPlayer() {
    return false;
  }

  /**
   * Checks to see if this particular item has been used and is evidence.
   *
   * @return True or false depending on whether the item has been used.
   */
  @Override
  public boolean isItemEvidence() {
    return false;
  }

  /**
   * Gets the amount of damage the item can do.
   *
   * @return The damage amount of the item.
   */
  @Override
  public int getItemDamage() {
    return 0;
  }

  @Override
  public String toString() {
    return this.getNameOfItem();
  }
}
