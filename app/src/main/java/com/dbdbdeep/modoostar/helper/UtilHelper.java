package com.dbdbdeep.modoostar.helper;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.DecimalFormat;

/****************************************************************
 * 유틸리티
 *
 * @author 이광호 (totory3217@gmail.com)
 * @version 1.0.0
 ****************************************************************/
public class UtilHelper {
	private static UtilHelper current = null;

	public static UtilHelper getInstance() {
		if (current == null) {
			current = new UtilHelper();
		}

		return current;
	}

	public static void freeInstance() {
		current = null;
	}

	public void setFocus(final Context context, final EditText et) {
		et.requestFocus();

		(new Handler()).postDelayed(new Runnable() {

			@Override
			public void run() {
				InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				mgr.showSoftInput(et, InputMethodManager.SHOW_FORCED);
			}
		}, 500);
	}

	/*************************************************************
	 * 숫자값에 3글자 단위로 콤마 처리
	 *
	 * @param num
	 *            원본 데이터
	 * @return String
	 *************************************************************/
	public String getNumberFormat(int num) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(num).toString();
	}

	public String removeTag(String src) {
		if (src == null)
			return src;
		return src.replaceAll(
				"<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}

	public Spanned fromHtml(String source) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
		} else {
			return Html.fromHtml(source);
		}
	}

	public float convertPixelsToDp(float px){
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return Math.round(dp);
	}

	public float convertDpToPixel(float dp){
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return Math.round(px);
	}
}
