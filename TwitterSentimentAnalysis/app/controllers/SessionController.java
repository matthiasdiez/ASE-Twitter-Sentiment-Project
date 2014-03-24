package controllers;

import static play.data.Form.form;
import model.core.Customer;
import model.repositories.CustomerRepository;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.create;
import views.html.login;
import application.Constants;

public class SessionController extends Controller {

  public static class Login {

    private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid user or password";

    public String name;
    public String password;

    public String validate() {
      if (CustomerRepository.INSTANCE.one(name, password) == null) {
        return INVALID_CREDENTIALS_MESSAGE;
      }
      return null;
    }

  }

  public Result login(final String redirect) {
    if (redirect != null) {
      session(Constants.SESSION_KEY_REDIRECT_AFTER_LOGIN, redirect);
    }
    return ok(login.render(form(Login.class)));
  }

  public Result loginHandler() {
    final Form<Login> loginForm = form(Login.class).bindFromRequest();
    if (loginForm.hasErrors()) {
      return badRequest(login.render(loginForm));
    }
    else {
      return finishLogin(loginForm.get().name);
    }
  }

  public Result logout() {
    session().clear();
    return redirect(routes.MainController.index());
  }

  public Result create() {
    return ok(create.render(form(Customer.class)));
  }

  public Result createHandler() {
    final Form<Customer> customerForm = form(Customer.class).bindFromRequest();
    if (customerForm.hasErrors()) {
      return badRequest(create.render(customerForm));
    }
    else {
      final Customer customer = customerForm.get();
      CustomerRepository.INSTANCE.store(customer);
      return finishLogin(customer.getName());
    }
  }

  private Result finishLogin(final String name) {
    initSession(name);
    return redirectToApp();
  }

  private void initSession(final String name) {
    session(Constants.SESSION_KEY_NAME, name);
  }

  private Result redirectToApp() {
    final String redirectUrl = session(Constants.SESSION_KEY_REDIRECT_AFTER_LOGIN);
    if (redirectUrl != null) {
      session().remove(Constants.SESSION_KEY_REDIRECT_AFTER_LOGIN);
      return redirect(redirectUrl);
    }
    return redirect(routes.MainController.app());
  }
}
