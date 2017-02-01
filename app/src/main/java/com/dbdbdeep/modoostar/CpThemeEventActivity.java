package com.dbdbdeep.modoostar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dbdbdeep.modoostar.helper.BaseActivity;
import com.dbdbdeep.modoostar.helper.BaseAdapter;
import com.dbdbdeep.modoostar.helper.BaseResponse;
import com.dbdbdeep.modoostar.helper.LogHelper;
import com.dbdbdeep.modoostar.holder.CompetitionListItemHolder;
import com.dbdbdeep.modoostar.model.CompetitionTheme;
import com.dbdbdeep.modoostar.model.CompetitionThemeList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CpThemeEventActivity extends BaseActivity {
    @BindView(R.id.btTop2)
    ImageButton btTop2;

    int pageno = 1;         // 현재 페이지 번호
    int totalPage;      // 전체 페이지 수

    @BindView(R.id.btTop1)
    ImageButton btTop1;

    /** 테마 대전 Adapter */
    CompetitionItemListAdapter competitionItemListAdapter;

    /** 테마 대전 ListView */
    @BindView(R.id.lvList)
    ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActionBarVisible(true);
        super.setStatusBarColor(R.color.splashBackground);

        setContentView(R.layout.activity_cp_theme_event);

        ButterKnife.bind(this);

        btTop2.setImageResource(R.drawable.intro_button_03);

        /** 테마 대전 ListView, Adapter */
        competitionItemListAdapter = new CpThemeEventActivity.CompetitionItemListAdapter(this, R.layout.competition_list_item);
        lvList.setAdapter(competitionItemListAdapter);

        getCpThemeList();

        // 스크롤이 발생함을 감지함.
        lvList.setOnScrollListener(new AbsListView.OnScrollListener() {
            boolean lastItemVisibleFlag;

            // OnScrollListener.SCROLL_STATE_IDLE은 스크롤이 이동하다가 멈추었을때
            // 발생되는 스크롤 상태.
            // 즉 스크롤이 바닦에 닿아 멈춘 상태에 처리를 하겠다는 뜻
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // OnScrollListener.SCROLL_STATE_IDLE은 스크롤이 이동하다가 멈추었을때
                // 발생되는 스크롤 상태.
                // 즉 스크롤이 바닦에 닿아 멈춘 상태에 처리를 하겠다는 뜻
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && lastItemVisibleFlag) {
                    // 현재 페이지가 전체 페이지보다 작을 경우
                    // 페이지 수를 1 증가시키고 다시 통신을 시도한다.
                    if (pageno < totalPage) {
                        pageno++;
                        getCpThemeList();
                    }
                }
            }

            /** 스크롤이 발생되는 동안 호출된다 */
            // 현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem)
            //    + 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가
            // 리스트 전체의 갯수(totalItemCount) 보다 크거나 같을때 --> 스크롤이 맨 밑에 도착함
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastItemVisibleFlag = (totalItemCount > 0)
                        && (firstVisibleItem + visibleItemCount >= totalItemCount);
            }
        });
    }

    private void getCpThemeList() {

        /** 통신처리 */
        client.init(R.string.cp_theme_list_by_me, pageno, 10, app_uid, "C");
        client.post(new BaseResponse<CompetitionThemeList>(CpThemeEventActivity.this) {
            @Override
            public void onResponse(CompetitionThemeList response) {
                LogHelper.debug(this, response.toString());
                if (!response.code.equals("000")) {
                    Toast.makeText(CpThemeEventActivity.this, response.msg, Toast.LENGTH_SHORT).show();
                    return;
                }

                totalPage = ((response.total_cnt-1)/10)+1;

                competitionItemListAdapter.addAll(response.list);
            }
        });
    }

    @OnClick(R.id.btTop1)
    public void btTop1Click() {
        Intent intent = new Intent(CpThemeEventActivity.this, CpThemeSpecialActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btTop3)
    public void btTop3Click() {
        Intent intent = new Intent(CpThemeEventActivity.this, CpThemeGeneralActivity.class);
        startActivity(intent);
        finish();
    }

    /** 이상형 월드컵 스페셜 대전 */
    class CompetitionItemListAdapter extends BaseAdapter<CompetitionTheme, CompetitionListItemHolder> {

        public CompetitionItemListAdapter(Activity activity, int resource) {
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

            if (item.cnt_my_participate > 0) {
                holder.btStart.setBackgroundResource(R.drawable.join_gender_girl_on);
                holder.btStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CpThemeEventActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                holder.btStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CpThemeEventActivity.this, "Start", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            if (item.ct_status.equals("Y") && item.cnt_my_participate == 0) {
                holder.btResult.setBackgroundResource(R.drawable.join_gender_boy_on);
                holder.btResult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CpThemeEventActivity.this, "Not yet", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                holder.btResult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CpThemeEventActivity.this, "Result", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            Glide.with(CpThemeEventActivity.this).load(item.file_name_1).thumbnail(0.1f).into(holder.ivLeft);
            Glide.with(CpThemeEventActivity.this).load(item.file_name_2).thumbnail(0.1f).into(holder.ivRight);


/*
            @OnClick(holder.btResult)
            public void onBtResultClick() {
                Toast.makeText(CpThemeEventActivity.this, "Result", Toast.LENGTH_SHORT).show();
            }
*/
        }
    }
}
