package gamemodels;

import java.util.ArrayList;
import java.util.List;

public abstract class Mock {
  protected List<String> mockLog;

  public Mock() {
    this.mockLog = new ArrayList<>();
  }

  public List<String> getMockLog() {
    return new ArrayList<>(mockLog);
  }
}
