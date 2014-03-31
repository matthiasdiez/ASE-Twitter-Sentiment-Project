package service.sentiment;
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
			classifier = (LMClassifier) AbstractExternalizable.readObject(new File(trainFile));
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
	
	public double getSentimentScore(ArrayList<String> tweetList) {
		double score = 0; 
		for(String tweet : tweetList){
			String sentiment = classify(tweet);
			if(sentiment.equals("pos")){
				score += 1;
			}else if(sentiment.equals("neg")){
				score += 0;
			}else{ // sentiment.equals("neu")){
				score += 0.5;
			}
		}
		
		score = score / tweetList.size();
		return score;
	}

}