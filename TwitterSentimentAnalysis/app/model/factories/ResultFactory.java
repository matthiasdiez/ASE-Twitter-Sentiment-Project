package model.factories;

import model.core.Result;
import model.core.Term;
import model.factories.impl.ResultFactoryImpl;

import org.joda.time.DateTime;

public interface ResultFactory {

  public static final ResultFactory INSTANCE = new ResultFactoryImpl();

  public Result create(final Term term, final double value, final DateTime dateTime);

}
