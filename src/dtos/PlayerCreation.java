package dtos;

import utilitles.Utility;

public class PlayerCreation {
  private final String name;
  private final String location;
  private final int limit;

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public int getLimit() {
    return limit;
  }

  public PlayerCreation(String n, String lo, int li) {
    Utility.checkNull(n, lo);
    this.name = n;
    this.location = lo;
    this.limit = li;
  }


}
