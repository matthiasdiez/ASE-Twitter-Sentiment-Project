package model.factories.impl;

import model.core.SentimentResult;
import model.core.Term;
import model.factories.ResultFactory;

import org.joda.time.DateTime;

public class ResultFactoryImpl implements ResultFactory {

  @Override
  public SentimentResult create(final Term term, final double value, final DateTime dateTime) {
    return new SentimentResult(term, value, dateTime);
  }

}
