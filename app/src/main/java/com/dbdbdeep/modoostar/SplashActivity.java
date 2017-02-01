package com.dbdbdeep.modoostar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.dbdbdeep.modoostar.helper.BaseActivity;
import com.dbdbdeep.modoostar.helper.BaseResponse;
import com.dbdbdeep.modoostar.helper.LogHelper;
import com.dbdbdeep.modoostar.helper.PreferencesHelper;
import com.dbdbdeep.modoostar.model.MemberLogin;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setActionBarVisible(false);
        super.setStatusBarColor(R.color.splashBackground);

        setContentView(R.layout.activity_splash);

        client.init(R.string.member_login, androidId);
        client.post(new BaseResponse<MemberLogin>(this) {
            @Override
            public void onResponse(MemberLogin response) {
                LogHelper.debug(this, response.toString());

                // 시스템에러
                if (!response.code.equals("000")) {
                    Toast.makeText(SplashActivity.this, response.msg, Toast.LENGTH_LONG).show();
                    return;
                }

                // 미존재인 경우
                if (response.fg_exists.toUpperCase().equals("N")) {
                    Intent intent = new Intent(SplashActivity.this, JoinActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                    return;
                }

                // 로그인 결과를 저장함
                SharedPreferences.Editor editor = PreferencesHelper.getInstance(SplashActivity.this)
                        .getEditor();
                editor.putString("nickname", response.m_nickname);
                editor.putString("sex", response.m_sex);
                editor.putString("app_uid", response.m_app_uid);
                PreferencesHelper.getInstance(SplashActivity.this).commit();

                // Thread 딜레이 처리
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 그 밖의 경우
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 0);
            }
        });
    }

    // 에니메이션 죽이기
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
