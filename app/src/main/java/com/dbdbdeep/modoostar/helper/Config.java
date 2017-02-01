package com.dbdbdeep.modoostar.helper;

/****************************************************************
 * 전체 라이브러리의 환경설정 클래스<br/>
 * App의 Intro화면등과 같이 최초로 실행되는 Activity에서 이 클래스내의 메소드들을 통한 환경설정을 처리한다.
 *
 * @author 이광호 (totory3217@gmail.com)
 * @version 1.0.0
 ****************************************************************/
public class Config {
	/** 로그 출력 여부 */
	private static boolean LogVisible = true;

	/** 로그에 함께 출력할 TAG MSG */
	private static String LogTag = "TOTORY-LIB";

	/*************************************************************
	 * 로그 출력 여부를 구한다.
	 *
	 * @return boolean
	 *************************************************************/
	public static final boolean isLogVisible() {
		return LogVisible;
	}

	/*************************************************************
	 * 로그 출력 여부를 설정한다.
	 *
	 * @param logVisible
	 *            설정 여부 (boolean)
	 *************************************************************/
	public static final void setLogVisible(boolean logVisible) {
		LogVisible = logVisible;
	}

	/*************************************************************
	 * 로그에 출력할 태그를 구한다.
	 *
	 * @return String
	 *************************************************************/
	public static final String getLogTag() {
		return LogTag;
	}

	/*************************************************************
	 * 로그에 출력할 태그를 설정한다.
	 *
	 * @param logTag
	 *            태그
	 *************************************************************/
	public static final void setLogTag(String logTag) {
		LogTag = logTag;
	}
}
