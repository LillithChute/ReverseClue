import static org.junit.Assert.assertEquals;

import gameinterfaces.targetinterfaces.Target;
import instancecreationhelpers.InstanceBuilder;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit testing class for the Target class.
 */
public class TargetTest {
  private String expectedConstuctorOutput;
  private String expectedGetTargetName;
  private InstanceBuilder getInstance;

  /**
   * Initialize some expected outputs.
   */
  @Before
  public void setup() {
    expectedConstuctorOutput = "Starro;50;0;9";
    expectedGetTargetName = "Starro";
    getInstance = new InstanceBuilder();
  }

  @Test
  public void testValidConstructor() {
    Target newTarget = getInstance.targetBuilder("Starro", 9, 50);
    assertEquals(expectedConstuctorOutput, newTarget.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidHealth() {
    getInstance.targetBuilder("Starro", 9, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNameBlank() {
    getInstance.targetBuilder("", 9, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNameNull() {
    getInstance.targetBuilder(null, 9, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMaximumNumberOfSpaces() {
    getInstance.targetBuilder("Starro", 0, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidHealthAndMaxSpace() {
    getInstance.targetBuilder("Starro", 0, -50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNameAndMaxSpace() {
    getInstance.targetBuilder("", -1, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNameMaxSpaceAndHealth() {
    getInstance.targetBuilder("", 0, -50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNameAndHealth() {
    getInstance.targetBuilder("", 9, -50);
  }

  @Test
  public void testGetTargetName() {
    Target newTarget = getInstance.targetBuilder("Starro", 9, 50);
    assertEquals(expectedGetTargetName, newTarget.getTargetName());
  }

  @Test
  public void testGetCurrentHealth() {
    Target newTarget = getInstance.targetBuilder("Starro", 9, 50);
    assertEquals(50, newTarget.getCurrentHealth());
  }

  @Test
  public void testSetCurrentHealth() {
    Target newTarget = getInstance.targetBuilder("Starro", 9, 50);
    assertEquals(50, newTarget.getCurrentHealth());
    newTarget.setHealth(5);
    assertEquals(5, newTarget.getCurrentHealth());
  }

  @Test
  public void testSetCurrentHealthNegativeValueShouldBeZero() {
    Target newTarget = getInstance.targetBuilder("Starro", 9, 50);
    assertEquals(50, newTarget.getCurrentHealth());
    newTarget.setHealth(-5);
    assertEquals(0, newTarget.getCurrentHealth());
  }

  @Test
  public void testToString() {
    Target newTarget = getInstance.targetBuilder("Starro", 9, 50);
    assertEquals(expectedConstuctorOutput, newTarget.toString());
  }
}
