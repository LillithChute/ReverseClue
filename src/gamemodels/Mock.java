package gamemodels;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract mock base class, containing features that are used in controller testing.
 */
public abstract class Mock {
  protected List<String> mockLog;

  /**
   * Constructs this mock class, initializing the log.
   */
  public Mock() {
    this.mockLog = new ArrayList<>();
  }

  public List<String> getMockLog() {
    return new ArrayList<>(mockLog);
  }
}
