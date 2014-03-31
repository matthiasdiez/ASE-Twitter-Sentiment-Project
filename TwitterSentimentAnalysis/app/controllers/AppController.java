package controllers;

import static play.data.Form.form;
import model.core.Analysis;
import model.core.Customer;
import model.repositories.AnalysisRepository;
import model.repositories.CustomerRepository;

import org.joda.time.DateTime;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import util.DateTimeUtil;
import util.ListUtil;
import application.Constants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.authentication.CustomerAuthenticator;

@Authenticated(CustomerAuthenticator.class)
public class AppController extends Controller {

  private static final String ERROR = "error";

  public Result main() {
    return redirect(routes.AppController.listAnalyses());
  }

  public Result listAnalyses() {
    return ok(views.html.listAnalyses.render(getAuthenticatedCustomer().getAnalysis()));
  }

  public Result displayAnalysis(final Long id) {
    final Analysis analysis = AnalysisRepository.INSTANCE.one(id);
    if (analysis == null) {
      return notFound();
    }
    return ok(views.html.displayAnalysis.render(analysis));
  }

  public Result getAnalysisData(final Long id) {
    final Analysis analysis = AnalysisRepository.INSTANCE.one(id);
    if (analysis == null) {
      return notFound();
    }
    final String result = createJson(analysis);
    if (result.equals(ERROR)) {
      return notFound();
    }
    return ok(result);
  }

  private String createJson(final Analysis analysis) {
    final ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(analysis);
    }
    catch (final JsonProcessingException e) {
      e.printStackTrace();
    }
    return ERROR;
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
      final Analysis analysis = getAuthenticatedCustomer().addAnalysis(form.get().getName());
      analysis.addTerms(ListUtil.listFromCommaSeparatedText(form.data().get("terms")));
      final DateTime startDateTime = DateTimeUtil.fromString(form.data().get("startDateTimeString"));
      final DateTime endDateTime = DateTimeUtil.fromString(form.data().get("endDateTimeString"));
      if (startDateTime != null) {
        analysis.setStartDateTime(startDateTime);
      }
      if (endDateTime != null) {
        analysis.setEndDateTime(endDateTime);
      }
      analysis.save();
      return redirect(routes.AppController.listAnalyses());
    }
  }

  public Result startAnalysis(final Long id) {
    final Analysis analysis = AnalysisRepository.INSTANCE.one(id);
    analysis.start();
    analysis.save();
    return redirect(routes.AppController.listAnalyses());
  }

  public Result finishAnalysis(final Long id) {
    final Analysis analysis = AnalysisRepository.INSTANCE.one(id);
    analysis.finish();
    analysis.save();
    return redirect(routes.AppController.listAnalyses());
  }

  private Customer getAuthenticatedCustomer() {
    return CustomerRepository.INSTANCE.one(session(Constants.SESSION_KEY_NAME));
  }
}
