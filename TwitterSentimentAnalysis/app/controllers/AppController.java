package controllers;

import static play.data.Form.form;
import model.core.Analysis;
import model.core.Customer;
import model.repositories.CustomerRepository;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import util.ListUtil;
import application.Constants;
import controllers.authentication.CustomerAuthenticator;

@Authenticated(CustomerAuthenticator.class)
public class AppController extends Controller {

  public Result dashboard() {
    return ok(views.html.dashboard.render(getAuthenticatedCustomer().getAnalysis()));
  }

  public Result listAnalyses() {
    return ok(views.html.listAnalyses.render());
  }

  public Result createAnalysis() {
    final Form<Analysis> form = new Form<Analysis>(Analysis.class);
    return ok(views.html.createAnalysis.render(form));
  }

  public Result addAnalysis() {
    final Form<Analysis> form = form(Analysis.class).bindFromRequest();
    if (form.hasErrors()) {
      return badRequest(views.html.createAnalysis.render(form));
    }
    else {
      final Analysis analysis = getAuthenticatedCustomer().addAnalysis(form.data().get("name"));
      analysis.addTerms(ListUtil.listFromCommaSeparatedText(form.data().get("terms")));
      return redirect(routes.AppController.dashboard());
    }
  }

  private Customer getAuthenticatedCustomer() {
    return CustomerRepository.INSTANCE.one(session(Constants.SESSION_KEY_NAME));
  }
}
