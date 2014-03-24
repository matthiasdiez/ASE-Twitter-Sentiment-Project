package model.repositories;

import model.core.Customer;
import model.repositories.impl.CustomerRepositoryImpl;

public interface CustomerRepository extends BaseRepository<Customer> {

  public static final CustomerRepository INSTANCE = new CustomerRepositoryImpl();

}
