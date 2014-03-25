package model.factories.impl;

import model.core.Customer;
import model.factories.CustomerFactory;

public class CustomerFactoryImpl implements CustomerFactory {

  @Override
  public Customer create(final String name, final String password) {
    return new Customer(name, password);
  }

}
