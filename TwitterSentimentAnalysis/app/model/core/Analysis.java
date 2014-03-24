package model.core;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import model.base.Identifiable;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Analysis extends Model implements Identifiable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @Required
  @ManyToOne
  private final Customer owner;

  @OneToMany(mappedBy = "analysis")
  private List<Term> terms;

  public Analysis(final Customer owner) {
    this.owner = owner;
  }

  @Override
  public Long getId() {
    return id;
  }

  public Customer getOwner() {
    return owner;
  }

}
