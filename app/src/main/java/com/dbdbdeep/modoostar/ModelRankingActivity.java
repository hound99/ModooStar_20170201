package com.dbdbdeep.modoostar;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dbdbdeep.modoostar.helper.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModelRankingActivity extends BaseActivity {
    @BindView(R.id.btTop1)
    ImageButton btTop1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setActionBarVisible(true);
        super.setStatusBarColor(R.color.splashBackground);

        setContentView(R.layout.activity_model_ranking);

        ButterKnife.bind(this);

        btTop1.setImageResource(R.drawable.intro_button_03);
    }

    @OnClick(R.id.btTop2)
    public void btTop2Click() {
        Toast.makeText(ModelRankingActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
    }


}
