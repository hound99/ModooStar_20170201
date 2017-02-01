package com.dbdbdeep.modoostar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dbdbdeep.modoostar.helper.BaseActivity;
import com.dbdbdeep.modoostar.helper.BaseResponse;
import com.dbdbdeep.modoostar.helper.LogHelper;
import com.dbdbdeep.modoostar.helper.PreferencesHelper;
import com.dbdbdeep.modoostar.model.MemberIns;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinActivity extends BaseActivity {

    @BindView(R.id.etNickname)
    EditText etNickname;

    @BindView(R.id.rgGender)
    RadioGroup rgGender;

    @BindViews({R.id.cbTerms1, R.id.cbTerms2})
    CheckBox[] cbTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarVisible(false);
        setStatusBarColor(R.color.splashBackground);
        setContentView(R.layout.activity_join);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btStart)
    public void onStartClick() {
        final int gender = rgGender.getCheckedRadioButtonId();
        if (gender != R.id.rdGenderF && gender != R.id.rdGenderM) {
            Toast.makeText(this, "성별을 선택하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        final String nickName = etNickname.getText().toString().trim();
        if (nickName.equals("")) {
            Toast.makeText(this, "닉네임을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!cbTerms[0].isChecked()) {
            Toast.makeText(this, "서비스 이용약관에 동의하셔야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!cbTerms[1].isChecked()) {
            Toast.makeText(this, "개인정보 수집 및 이용에 동의하셔야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }


        // /member/member_ins_upd.php?mode=WRITE&amp;m_imei=%1$s&amp;m_nickname=%2$s&amp;m_sex=%3$s&amp;m_ip=%4$s&amp;service_key=app_w
        client.init(R.string.member_ins, androidId, nickName, gender == R.id.rdGenderF ? "W" : "M", "ip address");
        client.post(new BaseResponse<MemberIns>(this) {
            @Override
            public void onResponse(MemberIns response) {
                if (!response.code.equals("000")) {
                    Toast.makeText(JoinActivity.this, response.msg, Toast.LENGTH_SHORT).show();
                    return;
                }

                LogHelper.debug(this, response.toString());

                // 로그인 결과를 저장함
                SharedPreferences.Editor editor = PreferencesHelper.getInstance(JoinActivity.this)
                        .getEditor();
                editor.putString("nickname", response.m_nickname);
                editor.putString("sex", response.m_sex);
                editor.putString("app_uid", response.m_app_uid);
                PreferencesHelper.getInstance(JoinActivity.this).commit();

                Intent intent = new Intent(JoinActivity.this, JoinBenefitActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @OnClick(R.id.btnArrow1)
    public void onBtnArrow1Click() {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("URL_ID", R.string.url_terms);
        startActivity(intent);
    }

    @OnClick(R.id.btnArrow2)
    public void onBtnArrow2Click() {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("URL_ID", R.string.url_hello);
        startActivity(intent);
    }
}
