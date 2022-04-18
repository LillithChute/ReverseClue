package utilitles;

import java.util.Objects;

public class Utility {
  public static void checkNull(Object... args) {
    for (Object o : args) {
      if (o == null) {
        throw new IllegalArgumentException("Parameter is null.");
      }
    }
  }
}
