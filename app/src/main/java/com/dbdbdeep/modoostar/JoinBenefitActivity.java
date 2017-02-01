package com.dbdbdeep.modoostar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dbdbdeep.modoostar.helper.BaseActivity;
import com.dbdbdeep.modoostar.helper.BaseResponse;
import com.dbdbdeep.modoostar.helper.LogHelper;
import com.dbdbdeep.modoostar.helper.UtilHelper;
import com.dbdbdeep.modoostar.model.MemberInsDone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinBenefitActivity extends BaseActivity {

    @BindView(R.id.llBenefitContainer)
    LinearLayout llBenefitContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActionBarVisible(false);
        setStatusBarColor(R.color.splashBackground);

        setContentView(R.layout.activity_join_benefit);

        ButterKnife.bind(this);

        final LayoutInflater inflater = getLayoutInflater();

        client.init(R.string.member_ins_done);
        client.post(new BaseResponse<MemberInsDone>(JoinBenefitActivity.this) {
            @Override
            public void onResponse(MemberInsDone response) {
                if (!response.code.equals("000")) {
                    Toast.makeText(JoinBenefitActivity.this, response.msg,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                LogHelper.debug(this, response.toString());

                for (int i=0; i<response.msg_list.size(); i++) {
                    View view = inflater.inflate(R.layout.content_join_benefit, null);

                    TextView tvBenefitTitle = (TextView) view.findViewById(R.id.tvBenefitTitle);
                    TextView tvBenefitMessage = (TextView) view.findViewById(R.id.tvBenefitMessage);

                    tvBenefitTitle.setText("가입혜택" + (i+1));
                    tvBenefitMessage.setText(
                            UtilHelper.getInstance().fromHtml(
                                    response.msg_list.get(i)
                            )
                    );

                    llBenefitContainer.addView(view);
                }
            }
        });
    }

    @OnClick(R.id.btClose)
    public void onBtCloseClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
