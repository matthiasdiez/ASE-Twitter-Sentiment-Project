package controllers.authentication;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import application.Constants;
import controllers.routes;

public class CustomerAuthenticator extends Security.Authenticator {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUsername(final Context ctx) {
    return ctx.session().get(Constants.SESSION_KEY_NAME);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Result onUnauthorized(final Context ctx) {
    return redirect(routes.SessionController.login(ctx.request().uri()));
  }
}
