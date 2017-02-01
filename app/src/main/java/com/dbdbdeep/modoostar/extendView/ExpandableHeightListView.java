package com.dbdbdeep.modoostar.extendView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * 스크롤뷰 안에서 ListView가 자동으로 사이즈를 Fix받도록한 확장 클래스.
 *
 * @developer : 이 광 호 (leekh4232@gmail.com)
 * @lastModify : 2017-01-20
 *
 * [XML Use]
 <com.dbdbdeep.modoostar.extendView.ExpandableHeightListView
    android:id="@+id/activity_job_offer_listCareer"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:scrollbars="none" />
 *
 * [Java Use]
 mListView = (ExpandableHeightListView) getView().findViewById(R.id.spotsView);
 mListView.setExpanded(true);
 */
public class ExpandableHeightListView extends ListView {
    boolean expanded = false;

    public ExpandableHeightListView(Context context) {
        super(context);
    }


    public ExpandableHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public ExpandableHeightListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public boolean isExpanded() {
        return expanded;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isExpanded()) {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

}
