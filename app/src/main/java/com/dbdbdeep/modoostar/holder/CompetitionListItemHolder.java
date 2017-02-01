package com.dbdbdeep.modoostar.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dbdbdeep.modoostar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompetitionListItemHolder {
    @BindView(R.id.ivLeft)
    public ImageView ivLeft;

    @BindView(R.id.ivRight)
    public ImageView ivRight;

    @BindView(R.id.textView1)
    public TextView textView1;

    @BindView(R.id.textView2)
    public TextView textView2;

    @BindView(R.id.textView3)
    public TextView textView3;

    @BindView(R.id.btStart)
    public Button btStart;

    @BindView(R.id.btResult)
    public Button btResult;

    public CompetitionListItemHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
