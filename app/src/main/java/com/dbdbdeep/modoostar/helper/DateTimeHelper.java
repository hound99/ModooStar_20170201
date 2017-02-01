package com.dbdbdeep.modoostar.helper;

import java.util.Calendar;
import java.util.Locale;

/****************************************************************
 * 날짜 처리 유틸리티
 *
 * @author 이광호 (totory3217@gmail.com)
 * @version 1.0.0
 ****************************************************************/
public class DateTimeHelper {
    /*************************************************************
     * 현재 날짜와 시간을 yyyymmddhhmiss 형으로 리턴한다.
     *
     * @return String (yyyymmddhhmiss)
     *************************************************************/
    public static String getNowDateTimeString() {
        Calendar cal = Calendar.getInstance();
        int yy = cal.get(Calendar.YEAR);
        int mm = cal.get(Calendar.MONTH) + 1;
        int dd = cal.get(Calendar.DAY_OF_MONTH);
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m = cal.get(Calendar.MINUTE);
        int s = cal.get(Calendar.SECOND);
        cal = null;
        return String.format(Locale.getDefault(), "%04d%02d%02d%02d%02d%02d", yy, mm, dd, h, m, s);
    }

    /*************************************************************
     * 현재 날짜와 시간을 파라미터로 전달된 포멧에 맞춰서 리턴한다.
     *
     * @return String (yyyymmddhhmiss)
     *************************************************************/
    public static String getNowDateTimeString(String format) {
        Calendar cal = Calendar.getInstance();
        int yy = cal.get(Calendar.YEAR);
        int mm = cal.get(Calendar.MONTH) + 1;
        int dd = cal.get(Calendar.DAY_OF_MONTH);
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m = cal.get(Calendar.MINUTE);
        int s = cal.get(Calendar.SECOND);
        cal = null;
        return String.format(Locale.getDefault(), format, yy, mm, dd, h, m, s);
    }

    /*************************************************************
     * 현재 날짜를 int형 배열로 리턴한다.
     *
     * @return int[3]
     *************************************************************/
    public static int[] getNowDate() {
        int[] date = new int[3];
        Calendar cal = Calendar.getInstance();
        date[0] = cal.get(Calendar.YEAR);
        date[1] = cal.get(Calendar.MONTH) + 1;
        date[2] = cal.get(Calendar.DAY_OF_MONTH);
        cal = null;
        return date;
    }

    /*************************************************************
     * 현재 시간을 int형 배열로 리턴한다.
     *
     * @return int[3]
     *************************************************************/
    public static int[] getNowTime() {
        int[] time = new int[3];
        Calendar cal = Calendar.getInstance();
        time[0] = cal.get(Calendar.HOUR_OF_DAY);
        time[1] = cal.get(Calendar.MINUTE);
        time[2] = cal.get(Calendar.SECOND);
        cal = null;
        return time;
    }

    /*************************************************************
     * 현재 날짜와 시간을 int형 배열로 리턴한다.
     *
     * @return int[6]
     *************************************************************/
    public static int[] getNowDateTime() {
        int[] date = new int[6];
        Calendar cal = Calendar.getInstance();
        date[0] = cal.get(Calendar.YEAR);
        date[1] = cal.get(Calendar.MONTH) + 1;
        date[2] = cal.get(Calendar.DAY_OF_MONTH);
        date[3] = cal.get(Calendar.HOUR_OF_DAY);
        date[4] = cal.get(Calendar.MINUTE);
        date[5] = cal.get(Calendar.SECOND);
        cal = null;
        return date;
    }

    /*************************************************************
     * YYYYMMDD 형식의 문자열을 숫자형태로 변환한다.
     *
     * @param date 날짜를 담는 문자열 (ex: 19820314)
     * @return int[3]
     *************************************************************/
    public static int[] getDate(String date) {
        if (date.length() < 8) {
            return null;
        }

        int[] date_list = new int[3];

        date_list[0] = Integer.parseInt(date.substring(0, 4));
        date_list[1] = Integer.parseInt(date.substring(4, 6));
        date_list[2] = Integer.parseInt(date.substring(6, 8));

        return date_list;
    }

    /*************************************************************
     * YYYYMMDD 형식의 문자열을 정해진 포멧으로 변환한다.
     *
     * @param date   날짜를 담는 문자열 (ex: 19820314)
     * @param format 날짜를 담기 위한 포멧 (ex: %04d-%02d-%02d)
     * @return String
     *************************************************************/
    public static String getDate(String date, String format) {
        int[] date_list = getDate(date);

        if (date_list == null) {
            return null;
        }

        return String.format(Locale.getDefault(), format, date_list[0], date_list[1], date_list[2]);
    }

    /*************************************************************
     * HHMISS 형식의 문자열을 숫자배열형태로 변환한다.
     *
     * @param time 시간을 담는 문자열 (ex: 023041)
     * @return int[3]
     *************************************************************/
    public static int[] getTime(String time) {
        if (time.length() < 6) {
            return null;
        }

        int[] time_list = new int[3];

        time_list[0] = Integer.parseInt(time.substring(0, 2));
        time_list[1] = Integer.parseInt(time.substring(2, 4));
        time_list[2] = Integer.parseInt(time.substring(4, 6));

        return time_list;
    }

    /*************************************************************
     * HHMISS 형식의 문자열을 정해진 포멧으로 변환한다.
     *
     * @param time   시간을 담는 문자열 (ex: 023041)
     * @param format 시간을 담기 위한 포멧 (ex: %02d:%02d:%02d)
     * @return String
     *************************************************************/
    public static String getTime(String time, String format) {
        int[] time_list = getTime(time);

        if (time_list == null) {
            return null;
        }

        return String.format(Locale.getDefault(), format, time_list[0], time_list[1], time_list[2]);
    }

    /*************************************************************
     * YYYYMMDDHHMISS 형식의 문자열을 숫자 배열 형태로 변환한다.
     *
     * @param datetime 날짜와 시간을 담는 문자열 (ex: 19820314013012)
     * @return int[6]
     *************************************************************/
    public static int[] getDateTime(String datetime) {
        if (datetime.length() != 14) {
            return null;
        }

        int[] list = new int[6];

        list[0] = Integer.parseInt(datetime.substring(0, 4));
        list[1] = Integer.parseInt(datetime.substring(4, 6));
        list[2] = Integer.parseInt(datetime.substring(6, 8));
        list[3] = Integer.parseInt(datetime.substring(8, 10));
        list[4] = Integer.parseInt(datetime.substring(10, 12));
        list[5] = Integer.parseInt(datetime.substring(12, 14));

        return list;
    }

    /*************************************************************
     * YYYYMMDDHHMISS 형식의 문자열을 정해진 포멧으로 변환한다.
     *
     * @param datetime 날짜와 시간을 담는 문자열 (ex: 19820314013012)
     * @param format   시간을 담기 위한 포멧 (ex: %04d-%04d-%04d %02d:%02d:%02d)
     * @return String
     *************************************************************/
    public static String getDateTime(String datetime, String format) {
        int[] list = getDateTime(datetime);

        if (list == null) {
            return null;
        }

        return String.format(format, list[0], list[1], list[2],
                list[3], list[4], list[5]);
    }

    /*************************************************************
     * YYYYMMDDHHMISS 형식의 문자열을 yyyy-mm-dd hh:mi:ss 포멧으로 변환한다.
     *
     * @param datetime 날짜와 시간을 담는 문자열 (ex: 19820314013012)
     * @return String
     *************************************************************/
    public static String getDateTimeString(String datetime) {
        return getDateTime(datetime, "%04d-%02d-%02d %02d;%02d;%02d");
    }

    /*************************************************************
     * YYMMDD 형식의 문자열에서 요일 인덱스를 리턴한다.
     *
     * @param date 날짜를 담는 문자열 (ex: 19820314)
     * @return int (1=일,7=토)
     *************************************************************/
    public static int getDayIndex(String date) {
        int nDay = 0;
        if (date.length() > 8) {
            date = date.substring(0, 8);
        }

        int[] list = getDate(date);

        if (list != null) {
            Calendar c = Calendar.getInstance();
            c.set(list[0], list[1] - 1, list[2]);
            nDay = c.get(Calendar.DAY_OF_WEEK);
        }

        return nDay;
    }

    /*************************************************************
     * YYMMDD 형식의 문자열에서 요일을 의미하는 3글자로 구성된 영문 문자열을 리턴한다.
     *
     * @param date 날짜를 담는 문자열 (ex: 19820314)
     * @return String
     *************************************************************/
    public static String getDayStringEng(String date) {
        String list[] = new String[]{"SUN", "MON", "TUE", "WED", "THU",
                "FRI", "SAT"};
        return list[getDayIndex(date) - 1];
    }

    /*************************************************************
     * YYMMDD 형식의 문자열에서 요일을 의미하는 문자열을 한글로 리턴한다.
     *
     * @param date 날짜를 담는 문자열 (ex: 19820314)
     * @return String
     *************************************************************/
    public static String getDayStringKor(String date) {
        String list[] = new String[]{"일", "월", "화", "수", "목", "금", "토"};
        return list[getDayIndex(date) - 1];
    }
}
