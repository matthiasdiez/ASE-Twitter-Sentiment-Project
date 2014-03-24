package model.repositories.impl;

import model.core.Customer;
import model.repositories.CustomerRepository;
import play.db.ebean.Model.Finder;

public class CustomerRepositoryImpl extends AbstractBaseRepositoryImpl<Customer> implements CustomerRepository {

  private final Finder<Long, Customer> finder = new Finder<Long, Customer>(Long.class, Customer.class);

  @Override
  protected Finder<Long, Customer> finder() {
    return finder;
  }

}
