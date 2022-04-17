package gamemodels.itemmodels;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.iteminterfaces.ItemViewModel;

import java.util.Objects;

/**
 * An item is a "tool" for lack of a better word that will be used to kill the target of this game.
 * Items can be literally anything.  All items will have attributes to them that will determine the
 * effectiveness of the item in destroying the target.
 */
public class ItemImpl implements Item, ItemViewModel {
  private final String name;
  private final int damage;
  private final int spaceLocation;
  private boolean isWithPlayer;
  private boolean isEvidence;

  /**
   * Constructor that builds an Item class.  The item will have a name, damage amount, and a
   * place it is located.  None of these are mutable.  It is expected that the name, damage, and
   * the location can't be empty or negative.
   *
   * @param name - The name or description of the item.
   * @param damage - The amount of damage the item can do.
   * @param spaceLocation - The location on the board of the item.
   */
  public ItemImpl(String name, int damage, int spaceLocation) {

    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("The item must have a name.");
    }

    if (damage < 1) {
      throw new IllegalArgumentException("The item must do damage greater than 0.");
    }

    if (spaceLocation < 0) {
      throw new IllegalArgumentException("The item must be located in a valid space.");
    }

    this.name = name;
    this.damage = damage;
    this.spaceLocation = spaceLocation;
    this.isWithPlayer = false;
    this.isEvidence = false;
  }

  @Override
  public String getNameOfItem() {
    return this.name;
  }

  @Override
  public int getSpaceIndexOfItem() {
    return this.spaceLocation;
  }

  @Override
  public boolean isItemWithPlayer() {
    return this.isWithPlayer;
  }

  @Override
  public void setIsItemWithPlayer() {
    this.isWithPlayer = !this.isWithPlayer;
  }

  @Override
  public boolean isItemEvidence() {
    return isEvidence;
  }

  @Override
  public void setItemIsEvidence() {
    this.isEvidence = !this.isEvidence;
  }

  @Override
  public int getItemDamage() {
    return this.damage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemImpl item = (ItemImpl) o;
    return damage == item.damage
            && spaceLocation == item.spaceLocation
            && isWithPlayer == item.isWithPlayer
            && isEvidence == item.isEvidence
            && name.equals(item.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, damage, spaceLocation, isWithPlayer, isEvidence);
  }

  @Override
  public String toString() {
    StringBuilder buildItemString = new StringBuilder();
    buildItemString.append(name)
            .append(";")
            .append(damage)
            .append(";")
            .append(spaceLocation);

    return buildItemString.toString();
  }
}
