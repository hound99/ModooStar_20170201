package com.dbdbdeep.modoostar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dbdbdeep.modoostar.extendView.ExpandableHeightGridView;
import com.dbdbdeep.modoostar.extendView.ExpandableHeightListView;
import com.dbdbdeep.modoostar.helper.BaseActivity;
import com.dbdbdeep.modoostar.helper.BaseAdapter;
import com.dbdbdeep.modoostar.helper.BaseResponse;
import com.dbdbdeep.modoostar.helper.LogHelper;
import com.dbdbdeep.modoostar.holder.CompetitionListItemHolder;
import com.dbdbdeep.modoostar.holder.ModelInfoHolder;
import com.dbdbdeep.modoostar.model.CompetitionTheme;
import com.dbdbdeep.modoostar.model.MainInfo;
import com.dbdbdeep.modoostar.model.ModelInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    /** 모델정보 Grid */
    @BindView(R.id.gvModelInfo)
    ExpandableHeightGridView gvModelInfo;

    /** 모델정보 Adapter */
    ModelInfoAdapter modelInfoAdapter;

    /** 이상형 월드컵 스페셜 대전 ListView */
    @BindView(R.id.lvCompetitionSpecial)
    ExpandableHeightListView lvCompetitionSpecial;

    /** 이상형 월드컵 이벤트 대전 ListView */
    @BindView(R.id.lvCompetitionEvent)
    ExpandableHeightListView lvCompetitionEvent;

    /** 이상형 월드컵 일반 대전 ListView */
    @BindView(R.id.lvCompetitionGeneral)
    ExpandableHeightListView lvCompetitionGeneral;

    /** 이상형 월드컵 스페셜 대전 Adapter */
    CompetitionSpecialAdapter competitionSpecialAdapter;

    /** 이상형 월드컵 이벤트 대전 Adapter */
    CompetitionEventAdapter competitionEventAdapter;

    /** 이상형 월드컵 일반 대전 Adapter */
    CompetitionGenaralAdapter competitionGenaralAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setActionBarVisible(true);
        super.setStatusBarColor(R.color.splashBackground);

        setContentView(R.layout.activity_main_content);
        setSideMenu();
        ButterKnife.bind(this);

        /** 모델정보 Grid, Adapter */
        gvModelInfo.setExpanded(true);
        modelInfoAdapter = new ModelInfoAdapter(this, R.layout.main_model_info_item);
        gvModelInfo.setAdapter(modelInfoAdapter);

        /** 이상형 월드컵 스페셜 대전 ListView, Adapter */
        competitionSpecialAdapter = new CompetitionSpecialAdapter(this, R.layout.competition_list_item);
        lvCompetitionSpecial.setExpanded(true);
        lvCompetitionSpecial.setBackgroundColor(Color.rgb(250, 246, 240));
        lvCompetitionSpecial.setAdapter(competitionSpecialAdapter);

        /** 이상형 월드컵 이벤트 대전 ListView, Adapter */
        competitionEventAdapter = new CompetitionEventAdapter(this, R.layout.competition_list_item);
        lvCompetitionEvent.setExpanded(true);
        lvCompetitionEvent.setBackgroundColor(Color.rgb(238, 247, 249));
        lvCompetitionEvent.setAdapter(competitionEventAdapter);

        /** 이상형 월드컵 일반 대전 ListView, Adapter */
        competitionGenaralAdapter = new CompetitionGenaralAdapter(this, R.layout.competition_list_item);
        lvCompetitionGeneral.setExpanded(true);
        lvCompetitionGeneral.setBackgroundColor(Color.rgb(245, 239, 247));
        lvCompetitionGeneral.setAdapter(competitionGenaralAdapter);

        /** 통신처리 */
        client.init(R.string.main_info, app_uid, sex);
        client.post(new BaseResponse<MainInfo>(MainActivity.this) {
            @Override
            public void onResponse(MainInfo response) {
                LogHelper.debug(this, response.toString());
                if (!response.code.equals("000")) {
                    Toast.makeText(MainActivity.this, response.msg, Toast.LENGTH_SHORT).show();
                    return;
                }

                // 통신 전문에서 모델정보만 Adapter에 설정
                modelInfoAdapter.addAll(response.list);
                competitionSpecialAdapter.addAll(response.list_cp_s);
                competitionEventAdapter.addAll(response.list_cp_e);
                competitionGenaralAdapter.addAll(response.list_cp_g);
            }
        });
    }

    @OnClick(R.id.btMoreModel)
    public void onMoreModelClick() {
        Intent intent = new Intent(MainActivity.this, ModelRankingActivity.class);
        startActivity(intent);
//        finish();
    }

    @OnClick(R.id.btMoreSpecial)
    public void onMoreSpecialClick() {
        Intent intent = new Intent(MainActivity.this, CpThemeSpecialActivity.class);
        startActivity(intent);
//        finish();
    }

    @OnClick(R.id.btMoreEvent)
    public void onMoreEventClick() {
        Intent intent = new Intent(MainActivity.this, CpThemeEventActivity.class);
        startActivity(intent);
//        finish();
    }

    @OnClick(R.id.btMoreGeneral)
    public void onMoreGeneralClick() {
        Intent intent = new Intent(MainActivity.this, CpThemeGeneralActivity.class);
        startActivity(intent);
//        finish();
    }

    /** GridView를 처리하기 위한 Adapter */
    class ModelInfoAdapter extends BaseAdapter<ModelInfo, ModelInfoHolder> {
        int[] medalId = {R.drawable.main_lank_icon_01,
                        R.drawable.main_lank_icon_02,
                        R.drawable.main_lank_icon_03,
                        R.drawable.main_lank_icon_04,
                        R.drawable.main_lank_icon_05,
                        R.drawable.main_lank_icon_06};

        public ModelInfoAdapter(Activity activity, int resource) {
            super(activity, resource);
        }

        @Override
        public void setListItem(ModelInfo item, ModelInfoHolder holder) {
            holder.textView1.setText(item.m_nickname);
            holder.textView2.setText("우승 " + item.mp_cp_champ + " (승 " + item.mp_cp_win + ", 패 "+item.mp_cp_lose + ")");

            Glide.with(MainActivity.this).load(item.mpi_link).thumbnail(0.1f)
                    .into(holder.imageView1);

            // 메달처리
            holder.imageView2.setImageResource(medalId[(item.index-1)%6]);

            // 버튼의 좌우 여백 처리
            int p = (item.index-1) % 3;
            if (p==0) {
                LinearLayout.LayoutParams params
                        = (LinearLayout.LayoutParams) holder.button1.getLayoutParams();
                params.leftMargin = 4;
                holder.button1.setLayoutParams(params);
            } else if (p == 2) {
                LinearLayout.LayoutParams params
                        = (LinearLayout.LayoutParams) holder.button2.getLayoutParams();
                params.rightMargin = 4;
                holder.button2.setLayoutParams(params);
            }
        }
    }

    /** 이상형 월드컵 스페셜 대전 */
    class CompetitionSpecialAdapter extends BaseAdapter<CompetitionTheme, CompetitionListItemHolder> {

        public CompetitionSpecialAdapter(Activity activity, int resource) {
            super(activity, resource);
        }

        @Override
        public void setListItem(CompetitionTheme item, CompetitionListItemHolder holder) {
            holder.textView1.setText(item.ct_title);
            holder.textView2.setText(item.ct_reward_txt);
            holder.textView3.setText("("+item.cnt_participate+"명 참여) "+item.ct_end_txt);

            if (!item.ct_type.equals("C")) {
                // View.GONE --> 숨김
                // View.VISIBLE --> 보임
                // View.INVISIBLE --> 숨기지만 자리는 차지.
                holder.textView2.setVisibility(View.GONE);
            }

            Glide.with(MainActivity.this).load(item.file_name_1).thumbnail(0.1f).into(holder.ivLeft);
            Glide.with(MainActivity.this).load(item.file_name_2).thumbnail(0.1f).into(holder.ivRight);
        }
    }

    /** 이상형 월드컵 이벤트 대전 */
    class CompetitionEventAdapter extends BaseAdapter<CompetitionTheme, CompetitionListItemHolder> {

        public CompetitionEventAdapter(Activity activity, int resource) {
            super(activity, resource);
        }

        @Override
        public void setListItem(CompetitionTheme item, CompetitionListItemHolder holder) {
            holder.textView1.setText(item.ct_title + ">>" + item.cnt_my_participate);
            holder.textView2.setText(item.ct_reward_txt);
            holder.textView3.setText("("+item.cnt_participate+"명 참여) "+item.ct_end_txt);

            if (!item.ct_type.equals("C")) {
                // View.GONE --> 숨김
                // View.VISIBLE --> 보임
                // View.INVISIBLE --> 숨기지만 자리는 차지.
                holder.textView2.setVisibility(View.GONE);
            }

            if (item.cnt_my_participate > 0) {
                holder.btStart.setBackgroundResource(R.drawable.join_gender_girl_on);
                holder.btStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                holder.btStart.setBackgroundResource(R.drawable.main_vs_button_01);
                holder.btStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();
                    }
                });
            }
/*
            if (item.cnt_my_participate == 0) {
                holder.btStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                holder.btStart.setBackgroundResource(R.drawable.join_gender_girl_on);
                holder.btStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                });
            }
*/
            if (item.ct_status.equals("Y") && item.cnt_my_participate == 0) {
                holder.btResult.setBackgroundResource(R.drawable.join_gender_boy_on);
                holder.btResult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Not yet", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                holder.btResult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Result", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            Glide.with(MainActivity.this).load(item.file_name_1).thumbnail(0.1f).into(holder.ivLeft);
            Glide.with(MainActivity.this).load(item.file_name_2).thumbnail(0.1f).into(holder.ivRight);
        }
    }

    /** 이상형 월드컵 일반 대전 */
    class CompetitionGenaralAdapter extends BaseAdapter<CompetitionTheme, CompetitionListItemHolder> {

        public CompetitionGenaralAdapter(Activity activity, int resource) {
            super(activity, resource);
        }

        @Override
        public void setListItem(CompetitionTheme item, CompetitionListItemHolder holder) {
            holder.textView1.setText(item.ct_title);
            holder.textView2.setText(item.ct_reward_txt);
            holder.textView3.setText("("+item.cnt_participate+"명 참여) "+item.ct_end_txt);

            if (!item.ct_type.equals("C")) {
                // View.GONE --> 숨김
                // View.VISIBLE --> 보임
                // View.INVISIBLE --> 숨기지만 자리는 차지.
                holder.textView2.setVisibility(View.GONE);
            }

            Glide.with(MainActivity.this).load(item.file_name_1).thumbnail(0.1f).into(holder.ivLeft);
            Glide.with(MainActivity.this).load(item.file_name_2).thumbnail(0.1f).into(holder.ivRight);
        }
    }
}