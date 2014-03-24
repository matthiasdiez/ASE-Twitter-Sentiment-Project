package model.core;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import model.base.Identifiable;
import model.factories.AnalysisFactory;
import model.repositories.AnalysisRepository;
import play.db.ebean.Model;

import com.google.common.collect.ImmutableList;

@Entity
public class Customer extends Model implements Identifiable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  private String name;
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

  public Analysis addAnalysis() {
    final Analysis analysis = AnalysisFactory.INSTANCE.create(this);
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

}
