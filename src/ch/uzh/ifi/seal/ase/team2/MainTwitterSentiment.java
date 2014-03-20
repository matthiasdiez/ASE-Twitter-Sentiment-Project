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
	public static void main(String[] args) throws Exception {
		System.out.println("Sample program from twitter-sentiment-analysis");
		
		ClassifierBuilder clb = new ClassifierBuilder();
		Options opt = new Options();
		clb.setOpt(opt);
		//seleziona solo i termini utilizzati più di una volta
		opt.setSelectedFeaturesByFrequency(true);
		//seleziona solamente 150 termini
		opt.setNumFeatures(150);
		//rimuove le emoticons
		opt.setRemoveEmoticons(true);
		//prepara le strutture dati per il train e il test
		clb.prepareTrain();
		clb.prepareTest();
		//classificatore Weka
		NaiveBayes nb = new NaiveBayes();
		//costruzione e memorizzazione su disco del classificatore
		WekaClassifier wc = clb.constructClassifier(nb);
		//classificazione di un tweet
		wc.classify("i am very sad");
	}

}
