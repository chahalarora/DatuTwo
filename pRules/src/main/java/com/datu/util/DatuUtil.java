package com.datu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatuUtil {
  // fucntion to return the date difference in days
  public static long daysBetween(Date date1, Date date2) {
    if (date1 == null || date2 == null) {
      return 0;
    }

    long ONE_HOUR = 60 * 60 * 1000L;
    return ((date2.getTime() - date1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));

    // return Date(date2.getDate() - date1.getDate()).getDay();
  }

  public static Date cDate(String strDate) {
    try {
      if (strDate == null || strDate.equals("")) {
        return null;
      }

      SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
      Date date = format.parse(strDate);

      return date;
    } catch (ParseException e) {
      return null;
    }
  }
}