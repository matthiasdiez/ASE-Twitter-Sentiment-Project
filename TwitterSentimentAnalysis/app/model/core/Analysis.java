package model.core;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import model.base.Identifiable;
import model.factories.TermFactory;
import model.repositories.TermRepository;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.google.common.collect.ImmutableList;

@Entity
public class Analysis extends Model implements Identifiable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @Required
  @ManyToOne
  private final Customer owner;

  @Required
  private String name;

  @OneToMany(mappedBy = "analysis")
  private List<Term> terms;

  @OneToMany(mappedBy = "analysis")
  private List<AnalysisExecution> executions;

  public Analysis(final Customer owner, final String name) {
    this.owner = owner;
  }

  @Override
  public Long getId() {
    return id;
  }

  public Customer getOwner() {
    return owner;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public List<Term> getTerms() {
    return ImmutableList.copyOf(terms);
  }

  public Term addTerm(final Analysis analysis, final String content) {
    final Term term = TermFactory.INSTANCE.create(analysis, content);
    TermRepository.INSTANCE.store(term);
    return TermRepository.INSTANCE.one(term.getId());
  }

  public boolean deleteTerm(final Term term) {
    if (term.getAnalysis().equals(this)) {
      TermRepository.INSTANCE.delete(id);
      return true;
    }
    return false;
  }

  public List<AnalysisExecution> getAnalysisExecutions() {
    return ImmutableList.copyOf(executions);
  }

  @Override
  public void save() {
    for (final Term term : terms) {
      term.save();
    }
    for (final AnalysisExecution analysis : executions) {
      analysis.save();
    }
    super.save();
  }

  @Override
  public void delete() {
    for (final Term term : terms) {
      term.delete();
    }
    for (final AnalysisExecution analysis : executions) {
      analysis.delete();
    }
    super.delete();
  }

}
