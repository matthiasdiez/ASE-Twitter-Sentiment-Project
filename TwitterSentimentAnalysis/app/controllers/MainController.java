package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import views.html.index;
import controllers.authentication.CustomerAuthenticator;

public class MainController extends Controller {

  public Result index() {
    return ok(index.render());
  }

  @Authenticated(CustomerAuthenticator.class)
  public Result app() {
    return ok(views.html.app.render("Twitter Minder"));
  }

  // TODO remove
  @Authenticated(CustomerAuthenticator.class)
  public Result test() {
    return ok("a test page");
  }
}
