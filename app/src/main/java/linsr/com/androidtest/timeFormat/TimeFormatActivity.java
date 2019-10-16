package linsr.com.androidtest.timeFormat;

import android.text.TextUtils;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import linsr.com.androidtest.R;
import linsr.com.androidtest.base.BaseActivity;

public class TimeFormatActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_time_format;
    }

    @Override
    protected void init() {

    }

    public void onTimeFormat(View view) {
        String time = "2019-09-14 18:08:26";
        String result = getTimeStr(time);
        System.out.println(result);
    }


    public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DAY_FORMAT = "yyyy-MM-dd";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String HOUR_MIN_FORMAT = "HH:mm";

    private static SimpleDateFormat FORMATER_FULL = new SimpleDateFormat(FULL_DATE_FORMAT, Locale.getDefault());
    private static SimpleDateFormat FORMATER_DAY = new SimpleDateFormat(DAY_FORMAT, Locale.getDefault());
    private static SimpleDateFormat FORMATER_YEAR = new SimpleDateFormat(YEAR_FORMAT, Locale.getDefault());
    private static SimpleDateFormat FORMATER_YEAR_LOCAL = new SimpleDateFormat("yyyy年", Locale.getDefault());
    private static SimpleDateFormat FORMATER_HHMM = new SimpleDateFormat(HOUR_MIN_FORMAT, Locale.getDefault());
    private static SimpleDateFormat FORMATER_MMDD = new SimpleDateFormat("  MM月dd日", Locale.getDefault());
    private static SimpleDateFormat FORMATER_MMDDHHMM = new SimpleDateFormat("  MM月dd日 HH:mm", Locale.getDefault());
    private static SimpleDateFormat FORMATER_YESTERDAY = new SimpleDateFormat(" HH:mm", Locale.getDefault());

    public String getTimeStr(String timeStr) {
        String result = "";
        if (TextUtils.isEmpty(timeStr)) {
            return result;
        }
        result = formatDisplayTime1(timeStr);
        return result;
    }

    public String formatDisplayTime1(String time) {
        String display = "";
        int tMin = 60 * 1000;
        int tHour = 60 * tMin;
        int tDay = 24 * tHour;

        if (time != null && time.length() > 0) {
            try {
                Date tDate;
                if (isInteger(time)) {
                    tDate = new Date(Long.valueOf(time));
                } else {
                    tDate = FORMATER_FULL.parse(time);
                }
                Date today = new Date();
                Date thisYear = new Date(FORMATER_YEAR.parse(FORMATER_YEAR.format(today)).getTime());
                Date yesterday = new Date(FORMATER_DAY.parse(FORMATER_DAY.format(today)).getTime());
                Date beforeYes = new Date(yesterday.getTime() - tDay);
                if (tDate != null) {
                    if (tDate.before(thisYear)) {
                        display = FORMATER_YEAR_LOCAL.format(tDate);
                    } else {
                        if (tDate.after(yesterday)) {
                            display = FORMATER_HHMM.format(tDate);
                        } else if (tDate.after(beforeYes) && tDate.before(yesterday)) {
                            display = "昨天"+FORMATER_YESTERDAY.format(tDate);
                        } else {
                            display = FORMATER_MMDDHHMM.format(tDate);
                        }
                    }
                }
                display = "昨天"+FORMATER_YESTERDAY.format(tDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return display;
    }

    private boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


}
