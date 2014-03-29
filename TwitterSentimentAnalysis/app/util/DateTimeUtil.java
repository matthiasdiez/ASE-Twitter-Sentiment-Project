package util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeUtil {

  private static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";

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
    return fromString(date, DEFAULT_DATE_FORMAT);
  }

  public static DateTime fromString(final String date, final String format) {
    return fromString(date, DateTimeFormat.forPattern(format));
  }

  public static DateTime fromString(final String date, final DateTimeFormatter formatter) {
    return formatter.parseDateTime(date);
  }

}
