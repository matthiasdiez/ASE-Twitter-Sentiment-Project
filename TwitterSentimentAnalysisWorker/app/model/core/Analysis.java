package model.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import model.base.Identifiable;
import model.factories.TermFactory;
import model.repositories.TermRepository;

import org.joda.time.DateTime;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import util.DateTimeUtil;

import com.google.common.collect.ImmutableList;

@Entity
public class Analysis extends Model implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@ManyToOne
	private final Customer owner;

	@Required
	private String name;

	private DateTime startDateTime;
	private DateTime endDateTime;

	@ManyToMany
	private final List<Term> terms = new ArrayList<Term>();

	public Analysis(final Customer owner, final String name) {
		this.owner = owner;
		this.name = name;
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

	public List<String> getTermsAsStrings() {
		final List<String> result = new ArrayList<String>();
		for (final Term term : getTerms()) {
			result.add(term.getContent());
		}
		return result;
	}

	public Term addTerm(final String content) {
		Term term = TermRepository.INSTANCE.one(content);
		if (term == null) {
			term = TermFactory.INSTANCE.create(content);
			TermRepository.INSTANCE.store(term);
			term.refresh();
		}
		terms.add(term);
		this.save();
		return term;
	}

	public List<Term> addTerms(final Collection<String> contents) {
		final List<Term> newTerms = new ArrayList<Term>();
		for (final String content : contents) {
			newTerms.add(addTerm(content));
		}
		return ImmutableList.copyOf(newTerms);
	}

	public void removeTerm(final Term term) {
		terms.remove(term);
		this.save();
	}

	public DateTime getStartDateTime() {
		return startDateTime;
	}

	public boolean setStartDateTime(final DateTime startDateTime) {
		if (this.startDateTime == null) {
			this.startDateTime = DateTimeUtil.nowOrLater(startDateTime);
			return true;
		}
		return false;
	}

	public DateTime getEndDateTime() {
		return endDateTime;
	}

	public boolean setEndDateTime(final DateTime endDateTime) {
		if (this.endDateTime == null) {
			this.endDateTime = endDateTime;
			return true;
		}
		return false;
	}

	public boolean start() {
		return setStartDateTime(DateTime.now());
	}

	public boolean finish() {
		return setEndDateTime(DateTime.now());
	}

	public boolean isActive() {
		final DateTime now = DateTime.now();
		return now.isAfter(startDateTime) && (now.isBefore(endDateTime) || endDateTime == null);
	}

	@Override
	public void save() {
		for (final Term term : terms) {
			term.save();
		}
		super.save();
	}

	@Override
	public void delete() {
		for (final Term term : terms) {
			term.delete();
		}
		super.delete();
	}

}
