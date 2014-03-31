package model.core;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import model.base.Identifiable;
import model.factories.ResultFactory;
import model.repositories.ResultRepository;

import org.joda.time.DateTime;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.google.common.collect.ImmutableList;

@Entity
public class Term extends Model implements Identifiable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @Required
  private final String content;

  @ManyToMany(mappedBy = "terms")
  private List<Analysis> analyses;

  @OneToMany(mappedBy = "term")
  private List<Result> results;

  public Term(final String content) {
    this.content = content;
  }

  @Override
  public Long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public List<Result> getResults() {
    return ImmutableList.copyOf(results);
  }

  public Result addResult(final Double value, final DateTime dateTime) {
    final Result result = ResultFactory.INSTANCE.create(this, value, dateTime);
    ResultRepository.INSTANCE.store(result);
    this.save();
    return result;
  }
}
