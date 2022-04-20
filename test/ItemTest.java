import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import gameinterfaces.iteminterfaces.Item;
import instancecreationhelpers.InstanceBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing an instance of an AutomaticTransmission class.
 */
public class ItemTest {
  private String expectedValidConstuctorOutput;
  private InstanceBuilder builder;

  /**
   * Initialize some expected outputs.
   */
  @Before
  public void setup() {
    expectedValidConstuctorOutput = "Baseball Bat; Damage = 3";
    builder = new InstanceBuilder();
  }

  @Test
  public void testValidConstructor() {
    Item newItem = builder.itemBuilder("Baseball Bat", 3, 1);
    assertEquals(expectedValidConstuctorOutput, newItem.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDamage() {
    builder.itemBuilder("Baseball Bat", 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDamageNegative() {
    builder.itemBuilder("Baseball Bat", -3, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidSpaceLocation() {
    builder.itemBuilder("Baseball Bat", 3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidItemName() {
    builder.itemBuilder("", 3, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidItemNameNull() {
    builder.itemBuilder(null, 3, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDamageAndSpace() {
    builder.itemBuilder("Baseball Bat", -3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNameAndSpace() {
    builder.itemBuilder("", 3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNameAndSpaceAndDamage() {
    builder.itemBuilder("", -3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNameAndDamage() {
    builder.itemBuilder("", -3, 1);
  }

  @Test
  public void testGetNameOfItem() {
    Item newItem = builder.itemBuilder("Baseball Bat", 3, 1);
    assertEquals("Baseball Bat", newItem.getNameOfItem());
  }

  @Test
  public void testGetSpaceIndexOfItem() {
    Item newItem = builder.itemBuilder("Baseball Bat", 3, 1);
    assertEquals(1, newItem.getSpaceIndexOfItem());
  }

  @Test
  public void testIfItemIsNotWithPlayer() {
    Item newItem = builder.itemBuilder("Baseball Bat", 3, 1);
    assertFalse(newItem.isItemWithPlayer());
  }

  @Test
  public void testIfItemIsWithPlayer() {
    Item newItem = builder.itemBuilder("Baseball Bat", 3, 1);
    assertFalse(newItem.isItemWithPlayer());
    newItem.setIsItemWithPlayer();
    assertTrue(newItem.isItemWithPlayer());
  }

  @Test
  public void testSetIsItemWithPlayer() {
    Item newItem = builder.itemBuilder("Baseball Bat", 3, 1);
    assertFalse(newItem.isItemWithPlayer());
    newItem.setIsItemWithPlayer();
    assertTrue(newItem.isItemWithPlayer());
  }

  @Test
  public void testToString() {
    Item newItem = builder.itemBuilder("Baseball Bat", 3, 1);
    assertEquals(expectedValidConstuctorOutput, newItem.toString());
  }

  @Test
  public void testEquals() {
    Item newItem1 = builder.itemBuilder("Baseball Bat", 3, 1);
    Item newItem2 = builder.itemBuilder("Baseball Bat", 3, 1);
    Item newItemDifferent = builder.itemBuilder("Machine Gun", 3, 1);
    assertEquals(newItem1, newItem2);
    assertNotEquals(newItem1, newItemDifferent);
  }

  @Test
  public void testHashCode() {
    Item newItem1 = builder.itemBuilder("Baseball Bat", 3, 1);
    Item newItem2 = builder.itemBuilder("Baseball Bat", 3, 1);
    Item newItemDifferent = builder.itemBuilder("Machine Gun", 3, 1);
    assertEquals(newItem1.hashCode(), newItem2.hashCode());
    assertNotEquals(newItem1.hashCode(), newItemDifferent.hashCode());
  }
}
