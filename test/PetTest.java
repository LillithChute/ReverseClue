import static org.junit.Assert.assertEquals;

import gameinterfaces.petinterfaces.Pet;
import gamemodels.petmodels.PetImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Basic suite of tests for a Pet object.
 */
public class PetTest {
  private String expectedConstuctorOutput;
  private String expectedGetPetName;

  /**
   * Initialize some expected outputs.
   */
  @Before
  public void setup() {
    expectedConstuctorOutput = "Baby Starro";
    expectedGetPetName = "Baby Starro";
  }

  @Test
  public void testValidConstructor() {
    Pet newPet = new PetImpl("Baby Starro");
    assertEquals(expectedConstuctorOutput, newPet.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNameBlank() {
    Pet newPet = new PetImpl("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNameNull() {
    Pet newPet = new PetImpl(null);
  }
}
