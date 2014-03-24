package model.factories;

import model.core.Customer;
import model.factories.impl.CustomerFactoryImpl;

public interface CustomerFactory {

  public static final CustomerFactory INSTANCE = new CustomerFactoryImpl();

  public Customer create(final String name, final String password);

}
