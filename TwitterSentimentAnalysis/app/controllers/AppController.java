package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import controllers.authentication.CustomerAuthenticator;

@Authenticated(CustomerAuthenticator.class)
public class AppController extends Controller {

  public Result app() {
    return ok(views.html.app.render());
  }

  public Result listAnalyses() {
    return ok(views.html.listanalyses.render());
  }

  public Result createAnalyse() {
    return ok(views.html.createanalyse.render());
  }
}
