package fu.berlin.apptap.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utillity {

    public static String getDateTimeFromTimestamp(Long time, String timeFormat) {
        if (timeFormat == null) {
            timeFormat = "dd/MM/yy HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat, Locale.GERMANY);
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    public static String getDateTimeFromTimestamp(String timeString, String timeFormat) {
        long time = 0l;
        try {
            time = Long.parseLong(timeString);
        } catch (NumberFormatException e) {
            Log.e("AppTapp_Error", "NumberFormatException in fu.berlin.apptap.util.Utillity: " + e.getMessage());
        }
        return getDateTimeFromTimestamp(time, timeFormat);
    }
}
