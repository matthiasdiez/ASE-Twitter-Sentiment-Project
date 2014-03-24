package model.core;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import model.base.Identifiable;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Term extends Model implements Identifiable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @Required
  @ManyToOne
  private final Analysis analysis;

  @Required
  private final String content;

  public Term(final Analysis analysis, final String content) {
    this.analysis = analysis;
    this.content = content;
  }

  @Override
  public Long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

}
