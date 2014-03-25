package model.core;

import javax.persistence.Embeddable;

@Embeddable
public class AnalysisResult {

  private static final double MIN_VALUE = 0;
  private static final double MAX_VALUE = 1;

  private final double value;

  public AnalysisResult(final double value) {
    this.value = roundValue(value);
  }

  private double roundValue(final double value) {
    return Math.max(MIN_VALUE, Math.min(MAX_VALUE, value));
  }

  public double get() {
    return value;
  }

}
