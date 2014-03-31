package model.factories.impl;

import model.core.Result;
import model.factories.ResultFactory;

import org.joda.time.DateTime;

public class ResultFactoryImpl implements ResultFactory {

  @Override
  public Result create(final double value, final DateTime dateTime) {
    return new Result(value, dateTime);
  }

}
