package controllers;

import static play.data.Form.form;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.core.Analysis;
import model.core.Customer;
import model.core.SentimentResult;
import model.core.Term;
import model.dto.AnalysisDto;
import model.dto.SentimentResultDto;
import model.dto.TermDto;
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
import com.google.common.collect.Lists;

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

  public Result getAnalysisData(final Long analysisId) {
    final Analysis analysis = AnalysisRepository.INSTANCE.one(analysisId);
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
    final AnalysisDto analysisDto = new AnalysisDto(analysis.getName());
    for (final Term term : analysis.getTerms()) {
      final TermDto termDto = new TermDto(term.getContent(), term.getOverallResult());
      final List<SentimentResult> results = Lists.newArrayList(term.getResults());
      Collections.sort(results, new Comparator<SentimentResult>() {

        @Override
        public int compare(final SentimentResult o1, final SentimentResult o2) {
          if (o1.getDateTime().isBefore(o2.getDateTime())) {
            return -1;
          }
          else if (o1.getDateTime().isAfter(o2.getDateTime())) {
            return 1;
          }
          return 0;
        }
      });

      for (final SentimentResult result : results) {
        final SentimentResultDto resultDto = new SentimentResultDto(DateTimeUtil.toString(result.getDateTime()), result.getValue());
        termDto.addResult(resultDto);
      }
      analysisDto.addTerm(termDto);
    }
    final ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(analysisDto);
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
