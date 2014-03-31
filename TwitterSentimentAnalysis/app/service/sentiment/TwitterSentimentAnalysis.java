package service.sentiment;

import java.util.ArrayList;

public class TwitterSentimentAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SentimentClassifier sentimentClassifier = new SentimentClassifier("files/classifier.txt");
		ArrayList<String> tweetList = new ArrayList<String>();
		tweetList.add("McDonald's sucks!");
		tweetList.add("McDonald's food is shit.");
		tweetList.add("I just drove around the McDonald's drive-in... :)");
		tweetList.add("McDonald's should design new burgers.");
		tweetList.add("McDonald's staff is always very unfriendly.");
		System.out.println("Sentiment score for McDonald's: " + sentimentClassifier.getSentimentScore(tweetList));
	}

}
