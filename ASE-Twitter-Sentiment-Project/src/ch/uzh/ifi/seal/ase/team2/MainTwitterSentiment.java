package ch.uzh.ifi.seal.ase.team2;

import twittersentiment.classifier.ClassifierBuilder;
import twittersentiment.classifier.WekaClassifier;
import twittersentiment.util.Options;
import weka.classifiers.bayes.NaiveBayes;

public class MainTwitterSentiment {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		System.out.println("Sample program from twitter-sentiment-analysis");

		final ClassifierBuilder clb = new ClassifierBuilder();
		final Options opt = new Options();
		clb.setOpt(opt);
		// only select terms with are used more than once
		opt.setSelectedFeaturesByFrequency(true);
		// only select 150 features
		opt.setNumFeatures(10000);
		// ignore / remove emoticons
		opt.setRemoveEmoticons(true);
		// prepare the data structure for test and train (part of initialization?
		clb.prepareTrain();
		clb.prepareTest();
		// use naive bayes (for statistical results)
		final NaiveBayes nb = new NaiveBayes();
		// initialize Weka classifier
		final WekaClassifier wc = clb.constructClassifier(nb);
		// classify given tweet (as string)
		System.out.println(wc.classify("i am very sad"));
	}
}
