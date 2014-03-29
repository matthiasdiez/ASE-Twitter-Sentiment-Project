package model.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;

import model.base.Identifiable;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import twitter4j.Status;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

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

	@ManyToOne
	private final Map<Long, Status> tweetsToAnalyze = new HashMap<Long, Status>();

	// Timestamp that allows to check when the latest Sentiment Analysis was run
	private DateTime latestSentimentAnalysis;

	public Term(final String content) {
		this.content = content;
		latestSentimentAnalysis = DateTime.now();
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

	public Map<Long, Status> getTweetsToAnalyze() {
		return ImmutableMap.copyOf(tweetsToAnalyze);
	}

	public Status getTweet(long id) {
		return tweetsToAnalyze.get(id);
	}

	public void addStatus(Status status) {
		tweetsToAnalyze.put(status.getId(), status);
	}

	public void setLatestSentimentAnalysis(DateTime dateTime) {
		this.latestSentimentAnalysis = dateTime;
	}

	public DateTime getLatestSentimentAnalysis() {
		return latestSentimentAnalysis;
	}
}
