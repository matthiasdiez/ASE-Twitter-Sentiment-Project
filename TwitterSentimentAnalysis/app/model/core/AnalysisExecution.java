package model.core;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import model.base.Identifiable;

import org.joda.time.DateTime;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class AnalysisExecution extends Model implements Identifiable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @Required
  private final Analysis analysis;

  @Required
  private DateTime startDateTime;
  @Required
  private DateTime endDateTime;

  @Embedded
  private AnalysisResult result;

  public AnalysisExecution(final Analysis analysis) {
    this.analysis = analysis;
  }

  @Override
  public Long getId() {
    return id;
  }

  public Analysis getAnalysis() {
    return analysis;
  }

  public DateTime getStartDateTime() {
    return startDateTime;
  }

  public void setStartDateTime(final DateTime startDateTime) {
    this.startDateTime = startDateTime;
  }

  public DateTime getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(final DateTime endDateTime) {
    this.endDateTime = endDateTime;
  }

  public AnalysisResult getResult() {
    return result;
  }

}
