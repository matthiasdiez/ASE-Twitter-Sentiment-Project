package model.factories;

import model.core.Customer;

public interface CustomerFactory {

  public Customer create(final String name, final String password);

}
