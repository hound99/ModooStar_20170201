package com.dbdbdeep.modoostar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.dbdbdeep.modoostar.helper.BaseActivity;
import com.dbdbdeep.modoostar.helper.LoadingDialogHelper;

import im.delight.android.webview.AdvancedWebView;

public class WebViewActivity extends BaseActivity implements AdvancedWebView.Listener {

    private AdvancedWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setActionBarVisible(true);
        super.setStatusBarColor(R.color.splashBackground);

        setContentView(R.layout.activity_web_view);

        // Back버튼 표시하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int url_id = getIntent().getIntExtra("URL_ID", 0);
        if (url_id == 0) {
            Toast.makeText(this, "url_id가 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = getString(url_id);
        if (url == null) {
            Toast.makeText(this, "존재하지 않는 URL 입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // titlebar(actionbar)의 backkey클릭 이벤트
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ;

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return;
        }
        // ...
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        LoadingDialogHelper.getInstance(this).show();
    }

    @Override
    public void onPageFinished(String url) {
        LoadingDialogHelper.getInstance(this).hide();
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
    }

    @Override
    public void onExternalPageRequest(String url) {
    }

}
