package util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeUtil {

  // http://joda-time.sourceforge.net/apidocs/org/joda/time/format/DateTimeFormat.html
  private static final String DEFAULT_DATE_FORMAT_DB = "MM/dd/yyyy HH:mm";

  public static DateTime min(final DateTime a, final DateTime b) {
    return a.isBefore(b) ? a : b;
  }

  public static DateTime max(final DateTime a, final DateTime b) {
    return a.isAfter(b) ? a : b;
  }

  public static DateTime nowOrLater(final DateTime dateTime) {
    return max(dateTime, DateTime.now());
  }

  public static DateTime fromString(final String date) {
    return fromString(date, DEFAULT_DATE_FORMAT_DB);
  }

  public static DateTime fromString(final String date, final String format) {
    return fromString(date, DateTimeFormat.forPattern(format));
  }

  public static DateTime fromString(final String date, final DateTimeFormatter formatter) {
    return formatter.parseDateTime(date);
  }

  public static String toString(final DateTime dateTime) {
    return DateTimeFormat.forPattern(DEFAULT_DATE_FORMAT_DB).print(dateTime);
  }
}