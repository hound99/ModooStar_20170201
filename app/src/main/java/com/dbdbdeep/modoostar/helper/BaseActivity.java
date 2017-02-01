package com.dbdbdeep.modoostar.helper;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.dbdbdeep.modoostar.R;

/**
 * Created by leekh on 2017. 1. 6..
 */

public class BaseActivity extends AppCompatActivity {

    // 장치 고유 아이디
    public String androidId;
    public JsonClient client;
    public String nickname;
    public String sex;
    public String app_uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = JsonClient.getInstance(this);

        // 장치 고유아이디 얻기
        androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.d("ANDROID-ID", androidId);

        // 저장되어 있는 전역정보(회원로그인 결과값) 불러오기
        SharedPreferences preferences = PreferencesHelper.getInstance(this)
                .getPreferences();
        nickname = preferences.getString("nickname", null);
        sex = preferences.getString("sex", null);
        app_uid = preferences.getString("app_uid", null);

        LogHelper.debug(this, "nickname=%s, sex=%s, app_uid=%s", nickname, sex, app_uid);
    }

    /** 상태바 색상 처리 */
    public void setStatusBarColor(int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    /** 액션바 숨김처리 */
    public void setActionBarVisible(boolean visible) {
        if (visible) {
            setActionBar();
        } else {
            // 타이틀바 없애기 --> 4.x 이하용.
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            // 타이틀바 없애기 --> 5.x 이상용.
            // 타이틀바 없애기 --> 5.x 이상용.
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        }
    }

    public void setActionBar() {
        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을
        // true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);


        // Set custom view layout
        View mCustomView = getLayoutInflater().inflate(
                                R.layout.actionbar_top, null);
        actionBar.setCustomView(mCustomView);

        // Set no padding both side
        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        actionBar.setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#ffffff")));

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(mCustomView, params);
    }

    public void setSideMenu() {
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        ImageButton btActionBarLeft = (ImageButton) findViewById(R.id.btActionBarLeft);
        btActionBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Hello World");
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }
}
