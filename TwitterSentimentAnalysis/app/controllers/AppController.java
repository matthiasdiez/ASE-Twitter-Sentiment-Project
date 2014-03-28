package controllers;

import model.core.Analysis;
import model.core.AnalysisExecution;
import model.core.Customer;
import model.repositories.CustomerRepository;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
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
    return TODO;
  }

  public Result listAnalysisExecutions(final Long analysisId) {
    return TODO;
  }

  public Result createAnalysisExecution(final Long analysisId) {
    final Form<AnalysisExecution> form = new Form<AnalysisExecution>(AnalysisExecution.class);
    return ok(views.html.createAnalysisExecution.render(form));
  }

  public Result addAnalysisExecution() {
    return TODO;
  }

  private Customer getAuthenticatedCustomer() {
    return CustomerRepository.INSTANCE.one(session(Constants.SESSION_KEY_NAME));
  }
}
