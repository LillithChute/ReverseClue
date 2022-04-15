import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.targetinterfaces.Target;
import instancecreationhelpers.InstanceBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing an instance of a Space class.
 */
public class SpaceTest {
  private String expectedConstructorOutput;
  private String expectedItemsOutput;
  private int expectedItemCount;
  private List<Item> locationZero;
  private List<Item> locationOne;
  private List<Item> locationTwo;
  private InstanceBuilder builder;
  private Target target;
  private List<Space> spaces;
  private List<Space> armoryNeighbors;
  private List<Space> diningHallNeighbors;
  private List<Space> kitchenNeighbors;
  private Space diningHall;
  private Space kitchen;
  private Space armory;
  private Space tennesseRoom;
  private Space parlor;
  private Space wineCellar;
  private Space drawingRoom;
  private Space billiardRoom;
  private Space trophyRoom;

  /**
   * Initialize some expected outputs.
   */
  @Before
  public void setup() {
    builder = new InstanceBuilder();
    expectedConstructorOutput = "2;Jotunheim;1;1;4;4";
    expectedItemsOutput = "Baseball Bat;2;2;Machete;3;2;";
    expectedItemCount = 2;
    target = builder.targetBuilder("Starro", 9, 50);

    // Make spaces
    spaces = new ArrayList<>();
    armoryNeighbors = new ArrayList<>();
    diningHallNeighbors = new ArrayList<>();
    kitchenNeighbors = new ArrayList<>();

    // Create spaces
    diningHall = builder.spaceBuilder(3, "Dining Hall", 12, 11, 21, 20);
    tennesseRoom = builder.spaceBuilder(17, "Tennesse Room", 8, 11, 11, 20);
    parlor = builder.spaceBuilder(15, "Parlor", 10, 5, 15, 10);
    kitchen = builder.spaceBuilder(8, "Kitchen", 16, 3, 21, 10);
    wineCellar = builder.spaceBuilder(19, "Wine Cellar", 22, 5, 23, 12);
    drawingRoom = builder.spaceBuilder(4, "Drawing Room", 22, 13, 25, 18);
    armory = builder.spaceBuilder(0, "Armory", 22, 19, 23, 26);
    billiardRoom = builder.spaceBuilder(1, "Billiard Room", 16, 21, 21, 28);
    trophyRoom = builder.spaceBuilder(18, "Trophy Room", 10, 21, 15, 26);

    // Add neighbors
    armoryNeighbors.add(diningHall);
    armoryNeighbors.add(drawingRoom);
    armoryNeighbors.add(billiardRoom);

    diningHallNeighbors.add(tennesseRoom);
    diningHallNeighbors.add(parlor);
    diningHallNeighbors.add(kitchen);
    diningHallNeighbors.add(wineCellar);
    diningHallNeighbors.add(drawingRoom);
    diningHallNeighbors.add(armory);
    diningHallNeighbors.add(billiardRoom);
    diningHallNeighbors.add(trophyRoom);

    kitchenNeighbors.add(diningHall);
    kitchenNeighbors.add(parlor);
    kitchenNeighbors.add(wineCellar);

    // Make items
    locationZero = new ArrayList<>();
    locationOne = new ArrayList<>();
    locationTwo = new ArrayList<>();

    // Create items and add to list
    Item itemBat = builder.itemBuilder("Baseball Bat", 2, 2);
    locationTwo.add(itemBat);
    Item itemMachete = builder.itemBuilder("Machete", 3, 2);
    locationTwo.add(itemMachete);
    Item itemRats = builder.itemBuilder("Rats", 4, 1);
    locationOne.add(itemRats);
    Item itemPolkaDots = builder.itemBuilder("Polka-Dots", 5, 0);
    locationZero.add(itemPolkaDots);
    Item itemDetachableArms = builder.itemBuilder("Detachable Arms", 1, 0);
    locationZero.add(itemDetachableArms);

    // Arrange
    armory.putItemsInTheSpace(locationZero);
    armory.setNeighborsOfThisSpace(armoryNeighbors);
    diningHall.setNeighborsOfThisSpace(diningHallNeighbors);
    kitchen.setNeighborsOfThisSpace(kitchenNeighbors);

    // Add spaces to the list
    spaces.add(diningHall);
    spaces.add(tennesseRoom);
    spaces.add(parlor);
    spaces.add(kitchen);
    spaces.add(wineCellar);
    spaces.add(drawingRoom);
    spaces.add(armory);
    spaces.add(billiardRoom);
    spaces.add(trophyRoom);
  }

  @Test
  public void testValidConstructor() {
    Space newSpace = builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, 4);
    assertEquals(expectedConstructorOutput, newSpace.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfNegativeUpperLeftX() {
    builder.spaceBuilder(2, "Jotunheim", -1, 1, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfNegativeUpperLeftY() {
    builder.spaceBuilder(2, "Jotunheim", 1, -1, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfNegativeLowerRightX() {
    builder.spaceBuilder(2, "Jotunheim", 1, 1, -4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfNegativeLowerRightY() {
    builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, -4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfNegativeLowerRightYandNegativeUpperLeftX() {
    builder.spaceBuilder(2, "Jotunheim", -1, 1, 4, -4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfNegativeLowerRightXandNegativeUpperLeftX() {
    builder.spaceBuilder(2, "Jotunheim", -1, 1, -4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfNegativeCoordinates() {
    builder.spaceBuilder(2, "Jotunheim", -1, -1, -4, -4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfNegativeIndexOfTheSpace() {
    builder.spaceBuilder(-2, "Jotunheim", 1, 1, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfNameIsEmpty() {
    builder.spaceBuilder(2, "", 1, 1, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfNameIsNull() {
    builder.spaceBuilder(2, null, 1, 1, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfUpperLeftXisGreaterThanLowerRightX() {
    builder.spaceBuilder(2, "Jotunheim", 100, 1, 40, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfUpperLeftYisGreaterThanLowerRightY() {
    builder.spaceBuilder(2, "Jotunheim", 100, 100, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfUpperLeftXisGreaterThanLowerRightXandUpperLeftYisGreaterThanLowerRightY() {
    builder.spaceBuilder(2, "Jotunheim", 1, 10, 4, 4);
  }

  @Test
  public void testGetNumberOfItemsInThisSpace() {
    Space newSpace = builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, 4);

    // Add the items to the space
    newSpace.putItemsInTheSpace(locationTwo);

    assertEquals(expectedItemCount, newSpace.getItems().size());
  }

  @Test
  public void testGetToStringValueOfItemsInThisSpace() {
    StringBuilder buildString = new StringBuilder();
    Space newSpace = builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, 4);
    newSpace.putItemsInTheSpace(locationTwo);
    List<Item> itemsInSpace = newSpace.getItems();

    for (Item item :
            itemsInSpace) {
      buildString.append(item.toString()).append(";");
    }

    assertEquals(expectedItemsOutput, buildString.toString());
  }

  @Test
  public void testGetNumberOfItemsInThisSpaceIsZero() {
    Space newSpace = builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, 4);

    assertEquals(0, newSpace.getItems().size());
  }

  @Test
  public void testGetIndexOfThisSpace() {
    Space newSpace = builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, 4);
    assertEquals(2, newSpace.getIndexOfTheSpace());
  }

  @Test
  public void testTargetIsNotInThisSpace() {
    Space newSpace = builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, 4);
    newSpace.moveTargetToThisSpace(null);
    assertFalse(newSpace.isTargetInThisSpace());
  }

  @Test
  public void testTargetIsInThisSpace() {
    Space newSpace = builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, 4);
    assertFalse(newSpace.isTargetInThisSpace());

    //  Set target here
    newSpace.moveTargetToThisSpace(target);

    // Target should be in this space.
    assertTrue(newSpace.isTargetInThisSpace());
  }

  @Test
  public void testSetTargetInThisSpace() {
    Space newSpace = builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, 4);
    assertFalse(newSpace.isTargetInThisSpace());

    //  Set target here
    newSpace.moveTargetToThisSpace(target);

    // Target should be in this space.
    assertTrue(newSpace.isTargetInThisSpace());
  }

  @Test
  public void testGetTargetInThisSpace() {
    Space newSpace = builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, 4);
    assertFalse(newSpace.isTargetInThisSpace());

    //  Set target here
    newSpace.moveTargetToThisSpace(target);

    // Target should be in this space.
    assertEquals("Starro", newSpace.getTargetFromThisSpace().getTargetName());
  }

  @Test
  public void testGetTheNameOfThisSpace() {
    Space newSpace = builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, 4);
    assertEquals("Jotunheim", newSpace.getTheNameOfThisSpace());
  }

  @Test
  public void testGetTheNeighborsOfDiningHall() {
    // Get the neighbors of the dining hall.
    // We know the number of neighbors for this test should be 8.
    List<Space> neighbors = this.diningHall.getNeighbors();
    assertEquals(8, neighbors.size());

    // Get the names of the spaces
    assertEquals("Tennesse Room;Parlor;Kitchen;Wine Cellar;Drawing Room;Armory;"
            + "Billiard Room;Trophy Room;", getNamesOfSpaces(neighbors));
  }

  @Test
  public void testGetTheNeighborsOfKitchen() {
    // Get the neighbors of the Kitchen.
    // We know the number of neighbors for this test should be 3.
    List<Space> neighbors = this.kitchen.getNeighbors();
    assertEquals(3, neighbors.size());

    // Get the names of the spaces
    assertEquals("Dining Hall;Parlor;Wine Cellar;", getNamesOfSpaces(neighbors));
  }

  @Test
  public void testGetTheNeighborsOfArmory() {
    // Get the neighbors of the Kitchen.
    // We know the number of neighbors for this test should be 3.
    List<Space> neighbors = this.armory.getNeighbors();
    assertEquals(3, neighbors.size());

    // Get the names of the spaces
    assertEquals("Dining Hall;Drawing Room;Billiard Room;", getNamesOfSpaces(neighbors));
  }

  private String getNamesOfSpaces(List<Space> spaces) {
    StringBuilder buildString = new StringBuilder();

    for (Space space :
            spaces) {
      buildString.append(space.getTheNameOfThisSpace()).append(";");
    }

    return buildString.toString();
  }

  @Test
  public void testGetSpaceDescription() {
    String roomDescription = this.armory.getTheFullSpaceDescription();

    // Get the names of the spaces
    assertEquals("* NAME: Armory\n"
            + "* \n"
            + "* ITEMS:\n"
            + "* \tPolka-Dots\n"
            + "* \tDetachable Arms\n"
            + "* \n"
            + "* NEIGHBORING SPACES:\n"
            + "* \tDining Hall\n"
            + "* \tDrawing Room\n"
            + "* \tBilliard Room\n", roomDescription);
  }

  @Test
  public void testToString() {
    Space newSpace = builder.spaceBuilder(2, "Jotunheim", 1, 1, 4, 4);
    assertEquals(expectedConstructorOutput, newSpace.toString());
  }

  @Test
  public void testEquals() {
    Space diningHallTheSame = builder.spaceBuilder(3, "Dining Hall",
            12, 11, 21, 20);
    assertEquals(diningHallTheSame, diningHall);
    assertNotEquals(diningHall, kitchen);
  }

  @Test
  public void testHashCode() {
    Space diningHallTheSame = builder.spaceBuilder(3, "Dining Hall",
            12, 11, 21, 20);
    assertEquals(diningHall.hashCode(), diningHallTheSame.hashCode());
    assertNotEquals(diningHall.hashCode(), kitchen.hashCode());
  }
}
