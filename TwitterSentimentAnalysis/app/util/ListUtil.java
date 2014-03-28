package util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

  private static final String COMMA = ",";
  private static final String EMPTY = "";

  public static List<String> listFromCommaSeparatedText(final String text) {
    final List<String> result = new ArrayList<String>();
    if (text != null && !text.equals(EMPTY)) {
      final String[] items = text.split(COMMA);
      for (final String item : items) {
        result.add(item.trim());
      }
    }

    return result;
  }

}
