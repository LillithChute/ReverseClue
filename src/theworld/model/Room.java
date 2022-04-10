package theworld.model;

import static theworld.utilities.MilestoneUtilities.checkParam;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import theworld.exceptions.GameplayException;

/**
 * Model class that represent a single room (space) in the world.
 */
public class Room {

  private List<Item> items;

  private String name;
  private Point leftPos;
  private Point rightPos;
  private List<Iplayer> players;


  private Room() {
    this.players = new ArrayList<>();
    this.name = "";
    this.items = new ArrayList<>();
    this.leftPos = null;
    this.rightPos = null;
  }

  /**
   * Parse a single room from a line of raw string.
   *
   * @param raw the raw string representation of a room
   * @return a model Room object
   */
  public static Room parseRoom(String raw) {
    checkParam(raw);
    String[] info = raw.split(" ");
    final int ly = Integer.parseInt(raw.substring(0, 2).trim());
    final int lx = Integer.parseInt(raw.substring(3, 5).trim());
    final int ry = Integer.parseInt(raw.substring(6, 8).trim());
    final int rx = Integer.parseInt(raw.substring(9, 11).trim());
    Room res = new Room();
    res.items = new ArrayList<>();
    res.name = raw.substring(12);
    res.leftPos = new Point(lx, ly);
    res.rightPos = new Point(rx, ry);
    return res;
  }

  /**
   * Gets the name of the room.
   *
   * @return the name of the room
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the room.
   *
   * @param name the name of the room
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the top-left point of the room.
   *
   * @return the top-left point of the room
   */
  public Point getLeftPos() {
    return leftPos;
  }




  /**
   * Obtain a rectangle object that represents the boundaries of the room.
   *
   * @return the boundary rectangle
   */
  public Rectangle getRectangle() {
    Dimension d = new Dimension(rightPos.x - leftPos.x, rightPos.y - leftPos.y);
    return new Rectangle(leftPos, d);
  }

  /**
   * Determine if a specific point lies within the boundaries of this room.
   *
   * @param p the point to be inspected
   * @return if the point is inside the boundary
   */
  public boolean containsPoint(Point p) {
    checkParam(p);
    return p.x >= leftPos.x
            && p.y >= leftPos.y
            && p.x <= rightPos.x
            && p.y <= rightPos.y;
  }

  /**
   * Determine if another room is the neighbor of this room.
   *
   * @param other the other room to be determined
   * @return if the other room is the neighbor of this room
   */
  public boolean isNeighborOf(Room other) {
    checkParam(other);
    //Do Top side
    for (int i = this.leftPos.x; i <= this.rightPos.x; i++) {
      if (other.containsPoint(new Point(i, this.leftPos.y - 1))) {
        return true;
      }
    }
    //Do Bottom side
    for (int i = this.leftPos.x; i <= this.rightPos.x; i++) {
      if (other.containsPoint(new Point(i, this.rightPos.y + 1))) {
        return true;
      }
    }
    //Do Top side
    for (int i = this.leftPos.y; i <= this.rightPos.y; i++) {
      if (other.containsPoint(new Point(this.leftPos.x - 1, i))) {
        return true;
      }
    }
    //Do Top side
    for (int i = this.leftPos.y; i <= this.rightPos.y; i++) {
      if (other.containsPoint(new Point(this.rightPos.x + 1, i))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the items the room contains.
   *
   * @return the items in the room
   */
  public List<Item> getItems() {
    return items;
  }

  /**
   * Sets the items of the room.
   *
   * @param items the items in the room
   */
  public void setItems(List<Item> items) {
    checkParam(items);
    this.items = items;
  }

  /**
   * Emit the name, items, and relevant item information of this room.
   *
   * @return an information string.
   */
  public String getInformation(boolean full) {
    StringBuilder sb = new StringBuilder();
    try {
      sb.append(String.format("Name: %s \nIndex: %d\nItems:\n", this.name,
              DependencyManager.shared().queryRoomIndex(this)));
      for (Item i : this.items) {
        sb.append(String.format("%d %s", items.indexOf(i), i.getInformation()));
      }
      for (Iplayer p : players) {
        sb.append(String.format("Player: %s\n", p.getName()));
      }
      if (full) {
        for (Room r : DependencyManager.shared().queryNeighbors(this)) {
          sb.append(r.getInformation(false));
        }
      }
      return sb.toString();
    } catch (GameplayException ex) {
      return "Invalid Room.";
    }
  }

  /**
   * Gets the shorter information string about this object.
   *
   * @return an information string representing this room
   */
  public String getInformation() {
    return this.getInformation(false);
  }

  /**
   * Emit a string representation of this class.
   *
   * @return The formatted string representation.
   */
  @Override
  public String toString() {
    try {
      return String.format("Name: %s, Index: %d\n", this.name,
              DependencyManager.shared().queryRoomIndex(this));
    } catch (GameplayException ex) {
      return "Invalid Room.";
    }
  }

  /**
   * Gets the players in this room.
   *
   * @return the players in this room
   */
  public List<Iplayer> getPlayers() {
    return players;
  }

}
