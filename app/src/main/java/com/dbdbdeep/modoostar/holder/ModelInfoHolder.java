package com.dbdbdeep.modoostar.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dbdbdeep.modoostar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 메인화면 GridView에 대한 Holder
 */
public class ModelInfoHolder {
    @BindView(R.id.imageView1)
    public ImageView imageView1;

    @BindView(R.id.imageView2)
    public ImageView imageView2;

    @BindView(R.id.button1)
    public Button button1;

    @BindView(R.id.button2)
    public Button button2;

    @BindView(R.id.textView1)
    public TextView textView1;

    @BindView(R.id.textView2)
    public TextView textView2;

    public ModelInfoHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
