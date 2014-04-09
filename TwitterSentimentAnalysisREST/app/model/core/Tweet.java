package model.core;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

import model.base.Identifiable;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Tweet extends Model implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Required
	@ManyToOne
	private Term term;

	@Required
	private String content;

	@Required
	private DateTime dateTime;

	public Tweet(Term term, String content, DateTime dateTime) {
		this.term = term;
		this.content = content;
		this.dateTime = dateTime;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
