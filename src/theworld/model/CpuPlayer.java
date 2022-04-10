package theworld.model;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Represents a CPU- controller player.
 */
public class CpuPlayer extends BasePlayer {

  /**
   * Constructs a CPU controlled player.

   * @param n the name of the player
   * @param str the strength (carrying capacity) of the player
   */
  public CpuPlayer(String n, int str) {
    super(n, str);
  }

  /**
   * Gets the information about this player.

   * @return the information about this player
   */
  public String getInformation() {
    return String.format("CPU %s", super.getInformation());
  }

  /**
   * Allows the CPU character to roam around the game world,
   * randomly performing several possible actions.

   * @return The description of what the CPU character just did.
   */
  @Override
  public String roamAround() {
    Room r = DependencyManager.shared().queryRoom(this);
    List<Room> neighbors = DependencyManager.shared().queryNeighbors(r);
    Room target = DependencyManager.shared().queryCharacterRoom();
    if (r == target) {
      Item toUse = this.backpack
              .stream()
              .max(Comparator.comparingInt(Item::getDamage))
              .orElse(null);
      return this.attack(toUse);
    }
    int i = r.getItems().isEmpty() ? 0 : 1;
    int actionid = (new Random()).nextInt(2 + i);
    switch (actionid) {
      case 0:
        Room next = neighbors.get((new Random()).nextInt(neighbors.size()));
        DependencyManager.shared().getWorld().movePlayer(this, next);
        return String.format("%s decided to go to %s\n", getName(), next);
      case 1:
        return String.format("%s found out that he's in\n%s\n", getName(), r);
      case 2:
        Item picked = r.getItems().remove((new Random()).nextInt(r.getItems().size()));
        this.backpack.add(picked);
        return String.format("%s is satisfied with the item he just picked up: %s\n",
                getName(), picked);
      default:
        break;
    }
    return "idle";
  }



  @Override
  public String toString() {
    return String.format("CPU %s", super.toString());
  }
}
