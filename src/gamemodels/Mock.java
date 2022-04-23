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

  /**
   * Obtains all the mock logs received.
   *
   * @return a list containing all the mock logs
   */
  public List<String> getMockLog() {
    return new ArrayList<>(mockLog);
  }

  /**
   * Obtains the last couple of logs appended to this object.
   *
   * @param r number of logs to fetch
   * @return recent appended logs
   */
  public String[] recentMockLog(int r) {
    return mockLog.subList(Math.max(mockLog.size() - r, 0), mockLog.size()).toArray(new String[0]);
  }
}
