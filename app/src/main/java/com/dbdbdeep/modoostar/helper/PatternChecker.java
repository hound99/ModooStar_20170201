package com.dbdbdeep.modoostar.helper;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 문자열의 형식을 검사하는 기능을 갖는 클래스
 */
public class PatternChecker {
    /*************************************************************
     * 주어진 문자열의 null 여부와 공백여부를 체크한다.
     *
     * @param txt 원본 문자열
     * @return boolean
     *************************************************************/
    public static boolean isValue(String txt) {
        boolean result = false;

        if (txt != null) {
            if (!txt.trim().equals("")) {
                result = true;
            }
        }

        return result;
    }

    /**
     * 숫자 모양에 대한 형식 검사
     */
    public static boolean isNum(String str) {
        return Pattern.matches("^[0-9]*$", str);
    }

    /**
     * 영문으로만 구성되었는지에 대한 형식 검사
     */
    public static boolean isEng(String str) {
        return Pattern.matches("^[a-zA-Z]*$", str);
    }

    /**
     * 한글로만 구성되었는지에 대한 형식 검사
     */
    public static boolean isKor(String str) {
        return Pattern.matches("^[ㄱ-ㅎ가-힣]*$", str);
    }

    /**
     * 영문과 숫자로만 구성되었는지에 대한 형식 검사
     */
    public static boolean isEngNum(String str) {
        return Pattern.matches("^[a-zA-Z0-9]*$", str);
    }

    /**
     * 한글과 숫자로만 구성되었는지에 대한 형식 검사
     */
    public static boolean isKorNum(String str) {
        return Pattern.matches("^[ㄱ-ㅎ가-힣0-9]*$", str);
    }

    /**
     * 이메일 형식인지에 대한 검사 --> "아이디@도메인"의 형식을 충족해야 한다.
     */
    public static boolean isEmail(String str) {
        return Pattern.matches("^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$", str);
    }

    /**
     * 핸드폰번호인지에 대한 형식검사. 반드시 앞자리가 010,010,016~9 사이를 충족해야 하며,
     * 각 연결부는 "-"로 구분되어야 한다. 각 부분에 대한 자리수도 충족시켜야 한다.
     */
    public static boolean isCellPhone(String str, String pipe) {
        if (pipe == null) {
            pipe = "";
        }
        String pattern = String.format(Locale.getDefault(), "^01(?:0|1|[6-9])%s(?:\\d{3}|\\d{4})%s\\d{4}$", pipe, pipe);
        return Pattern.matches(pattern, str);
    }

    /**
     * 전화번호인지에 대한 형식검사. 각 연결부는 "-"로 구분되어야 한다.
     * 각 부분에 대한 자리수도 충족시켜야 한다.
     */
    public static boolean isTel(String str, String pipe) {
        if (pipe == null) {
            pipe = "";
        }
        String pattern = String.format(Locale.getDefault(), "^\\d{2,3}-\\d{3,4}-\\d{4}$", pipe, pipe);
        return Pattern.matches(pattern, str);
    }

    /**
     * 주민번호에 대한 글자수 및 뒷자리 첫글자가 1~4의 범위에 있는지에 대한 검사
     */
    public static boolean isJumin(String str) {
        return Pattern.matches("^\\d{6}-[1-4]\\d{6}", str);
    }

    /**
     * 아이피주소 형식에 대한 검사
     */
    public static boolean isIP(String str) {
        return Pattern.matches("([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})", str);
    }
}
