package model.core;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import model.base.Identifiable;

import org.joda.time.DateTime;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Result extends Model implements Identifiable {

  private static final long serialVersionUID = 1L;

  private static final double MIN_VALUE = 0;
  private static final double MAX_VALUE = 1;

  private final double value;

  @Id
  private Long id;

  @Required
  @ManyToOne
  private Term term;

  @Required
  private final DateTime dateTime;

  public Result(final double value, final DateTime dateTime) {
    this.value = roundValue(value);
    this.dateTime = dateTime;
  }

  @Override
  public Long getId() {
    return id;
  }

  private double roundValue(final double value) {
    return Math.max(MIN_VALUE, Math.min(MAX_VALUE, value));
  }

  public double getValue() {
    return value;
  }

  public DateTime getDateTime() {
    return dateTime;
  }

}
