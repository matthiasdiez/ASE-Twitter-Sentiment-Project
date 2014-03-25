package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import views.html.index;
import controllers.authentication.CustomerAuthenticator;

public class MainController extends Controller {

  public Result index() {
    return ok(index.render("Your new application is ready."));
  }

  @Authenticated(CustomerAuthenticator.class)
  public Result app() {
    return ok("Main App Page");
  }

  // TODO remove
  @Authenticated(CustomerAuthenticator.class)
  public Result test() {
    return ok("a test page");
  }
}
