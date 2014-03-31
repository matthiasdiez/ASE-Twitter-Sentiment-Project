package ch.uzh.ifi.seal.ase.team2;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.aliasi.classify.ConditionalClassification;
import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;

public class SentimentClassifier {

	String[] categories;
	LMClassifier classifier;

	public SentimentClassifier(String trainFile) {

		try {
			classifier = (LMClassifier) AbstractExternalizable
					.readObject(new File(trainFile));
			categories = classifier.categories();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String classify(String text) {
		ConditionalClassification classification = classifier.classify(text);
		return classification.bestCategory();
	}
	
	public float getSentimentScore(ArrayList<String> tweetList) {
		float score = 0; 
		for(String tweet : tweetList){
			String sentiment = classify(tweet);
			System.out.println(tweet + ": " + sentiment);
			if(sentiment.equals("pos")){
				score += 1;
			}else if(sentiment.equals("neg")){
				score += 0;
			}else{ // sentiment.equals("neu")){
				score += 0.5;
			}
			System.out.println("Current total score: " + score);
		}
		
		score = score / tweetList.size();
		return score;
	}

}