package model.core;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import model.base.Identifiable;

import org.joda.time.DateTime;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Analysis extends Model implements Identifiable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @Required
  @ManyToOne
  private Customer owner;

  @ManyToMany
  private List<Term> terms;

  @Required
  private final DateTime startDateTime;
  @Required
  private final DateTime endDateTime;

  public Analysis(final DateTime startDateTime, final DateTime endDateTime) {
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
  }

  @Override
  public Long getId() {
    return id;
  }

  public DateTime getStartDateTime() {
    return startDateTime;
  }

  public DateTime getEndDateTime() {
    return endDateTime;
  }

}
