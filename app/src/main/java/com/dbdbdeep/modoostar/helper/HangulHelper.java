package com.dbdbdeep.modoostar.helper;

import java.util.ArrayList;

public class HangulHelper {
	public static class HangulStructure {
		String m_szChoSung = "";
		String m_szJwungSung = "";
		String m_szJongSung = "";

		public HangulStructure(char p1) {
			m_szChoSung += p1;
			m_szJwungSung = "";
			m_szJongSung = "";
		}

		public HangulStructure(char p1, char p2) {
			m_szChoSung += p1;
			m_szJwungSung += p2;
			m_szJongSung = "";
		}

		public HangulStructure(char p1, char p2, char p3) {
			m_szChoSung += p1;
			m_szJwungSung += p2;
			m_szJongSung += p3;
		}
	}

	// ㄱ ㄲ ㄴ ㄷ ㄸ ㄹ ㅁ ㅂ ㅃ ㅅ ㅆ ㅇ ㅈ ㅉ ㅊ ㅋ ㅌ ㅍ ㅎ
	public final static char[] ChoSung = { 0x3131, 0x3132, 0x3134, 0x3137,
			0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147,
			0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
	// ㅏ ㅐ ㅑ ㅒ ㅓ ㅔ ㅕ ㅖ ㅗ ㅘ ㅙ ㅚ ㅛ ㅜ ㅝ ㅞ ㅟ ㅠ ㅡ ㅢ ㅣ
	public final static char[] JwungSung = { 0x314f, 0x3150, 0x3151, 0x3152,
			0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a,
			0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162,
			0x3163 };
	// ㄱ ㄲ ㄳ ㄴ ㄵ ㄶ ㄷ ㄹ ㄺ ㄻ ㄼ ㄽ ㄾ ㄿ ㅀ ㅁ ㅂ ㅄ ㅅ ㅆ ㅇ ㅈ ㅊ ㅋ ㅌ ㅍ ㅎ
	public final static char[] JongSung = { 0, 0x3131, 0x3132, 0x3133, 0x3134,
			0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d,
			0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146,
			0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

	public static ArrayList<HangulStructure> getDivide(String s) {

		ArrayList<HangulStructure> result = new ArrayList<HangulStructure>();
		int cho;// 초성
		int jung;// 중성
		int jong;// 종성

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch >= 0xAC00 && ch <= 0xD7A3) { // "AC00:가" ~ "D7A3:힣" 에 속한 글자면
												// 분해
				jong = ch - 0xAC00;
				cho = jong / (21 * 28);
				jong = jong % (21 * 28);
				jung = jong / 28;
				jong = jong % 28;
				if (jong != 0) {// 받침이 있으면..
					result.add(new HangulStructure(ChoSung[cho],
							JwungSung[jung], JongSung[jong]));
				} else {
					result.add(new HangulStructure(ChoSung[cho],
							JwungSung[jung]));
				}
			} else {
				// 한글이 아닐 경우..
				result.add(new HangulStructure(ch));
			}
		}
		return result;
	}

	public static String getFirstDivideString(String s) {
		String result = "";
		int cho;// 초성
		int jong;// 종성

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			// "AC00:가" ~ "D7A3:힣" 에 속한 글자면 분해
			if (ch >= 0xAC00 && ch <= 0xD7A3) {
				jong = ch - 0xAC00;
				cho = jong / (21 * 28);
				result += ChoSung[cho];
			} else {
				// 한글이 아닐 경우..
				result += ch;
			}
		}
		return result;
	}

	public static String getFirstDivide(String s) {
		String result = "";
		int cho;

		char ch = s.charAt(0);
		if (ch >= 0xAC00 && ch <= 0xD7A3) {
			cho = (ch - 0xAC00) / (21 * 28);
			result += ChoSung[cho];
		} else {
			// 한글이 아닐 경우..
			result += ch;
		}

		return result;
	}
}
