package gamemodels.petmodels;

import gameinterfaces.petinterfaces.Pet;
import gameinterfaces.petinterfaces.PetViewModel;

/**
 * An implementation of a Pet.  The pet allows spaces to be virtually invisible.  A pet is located
 * in a space.
 */
public class PetImpl implements Pet, PetViewModel {
  private final String name;

  /**
   * Constructor for the Pet class.  THe pet has a name.
   *
   * @param name - The name of the pet.
   */
  public PetImpl(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("The pet must have a name.");
    }

    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
