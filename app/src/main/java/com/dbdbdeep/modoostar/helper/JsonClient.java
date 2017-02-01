package com.dbdbdeep.modoostar.helper;

import android.content.Context;
import android.util.Log;

import com.dbdbdeep.modoostar.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class JsonClient {

    private static JsonClient current;
    private AsyncHttpClient client;
    private Context context;
    private String prefix;
    private String urlPath;
    private RequestParams requestParams;

    public static JsonClient getInstance(Context context) {
        if (current == null) {
            current = new JsonClient();
        }

        current.setContext(context);
        current.prefix = current.context.getString(R.string.url_prefix);
        return current;
    }

    private JsonClient() {
        this.client = new AsyncHttpClient();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void post(BaseResponse response) {
        Log.d("PARAMS", requestParams.toString());
        client.post(prefix + urlPath, requestParams, response);
    }

    public void get(BaseResponse response) {
        Log.d("PARAMS", requestParams.toString());
        client.get(prefix + urlPath, requestParams, response);
    }

    public void init(int url_id, Object ... params) {
        // ex) /member/member_login.php?m_imei=%1$s&service_key=app_w
        String url = this.context.getString(url_id, params);

        if (this.requestParams != null) {
            this.requestParams = null;
        }
        this.requestParams = new RequestParams();
        String urlParams = null;

        // URL에 ?가 있는지 검사
        int p = url.indexOf("?");

        if (p > -1) {
            urlPath = url.substring(0, p);
            urlParams = url.substring(p+1);
        } else {
            urlPath = url;
        }

        Log.d("PATH", urlPath);

        if (urlParams != null) {
            String[] data = urlParams.split("&");
            for (int i=0; i<data.length; i++) {
                String[] temp = data[i].split("=");

                if (temp.length == 2) {
                    requestParams.put(temp[0], temp[1]);
                }
            }
        } // end if
    }
}
