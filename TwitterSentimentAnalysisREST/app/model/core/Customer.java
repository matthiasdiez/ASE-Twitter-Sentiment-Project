package model.core;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import model.base.Identifiable;
import model.factories.AnalysisFactory;
import model.repositories.AnalysisRepository;
import model.repositories.CustomerRepository;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Pattern;
import play.data.validation.Constraints.Required;
import play.data.validation.ValidationError;
import play.db.ebean.Model;
import application.Messages;

import com.google.common.collect.ImmutableList;

@Entity
public class Customer extends Model implements Identifiable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @Required
  @MinLength(value = 3, message = Messages.TOO_SHORT_NAME_MESSAGE)
  @Pattern(value = "(\\w)+", message = Messages.INVALID_NAME_MESSAGE)
  // \w = A word character, short for [a-zA-Z_0-9]
  private String name;

  @Required
  @MinLength(value = 5, message = Messages.TOO_SHORT_PASSWORD_MESSAGE)
  @Pattern(value = "(\\w)+", message = Messages.INVALID_PASSWORD_MESSAGE)
  private String password;

  @OneToMany(mappedBy = "owner")
  private List<Analysis> analyses;

  public Customer(final String name, final String password) {
    this.name = name;
    this.password = password;
  }

  @Override
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public List<Analysis> getAnalysis() {
    return ImmutableList.copyOf(analyses);
  }

  public Analysis addAnalysis(final String name) {
    final Analysis analysis = AnalysisFactory.INSTANCE.create(this, name);
    AnalysisRepository.INSTANCE.store(analysis);
    return AnalysisRepository.INSTANCE.one(analysis.getId());
  }

  @Override
  public void save() {
    for (final Analysis analysis : analyses) {
      analysis.save();
    }
    super.save();
  }

  @Override
  public void delete() {
    for (final Analysis analysis : analyses) {
      analysis.delete();
    }
    super.delete();
  }

  public List<ValidationError> validate() {
    if (CustomerRepository.INSTANCE.one(this.name) != null) {
      return ImmutableList.of(new ValidationError("name", Messages.NAME_ALREADY_EXISTS_MESSAGE));
    }
    else {
      return null;
    }
  }

}
