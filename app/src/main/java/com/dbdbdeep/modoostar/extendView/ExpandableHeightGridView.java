package com.dbdbdeep.modoostar.extendView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * 스크롤뷰 안에서 GridView가 자동으로 사이즈를 Fix받도록한 확장 클래스.
 * @developer : 이 광 호 (leekh4232@gmail.com)
 * @lastModify : 2017-01-20
 *
 * [XML Use]
        <com.dbdbdeep.modoostar.extendView.ExpandableHeightGridView
        android:id="@+id/spotsView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="15dp"
        android:layout_marginBottom="15dp"
        android:layout_marginLeft="5dp"
        android:layout_marginRight="5dp"
        android:horizontalSpacing="10dp"
        android:isScrollContainer="false"
        android:numColumns="5"
        android:stretchMode="columnWidth"
        android:verticalSpacing="10dp" />

 *  [Java Use]
        mGridView = (ExpandableHeightGridView) getView().findViewById(R.id.spotsView);
        mGridView.setExpanded(true);
        SpotsAdapter adapter = new SpotsAdapter(getActivity(), R.layout.spot_item,params);
        mGridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
 */
public class ExpandableHeightGridView extends GridView
{
    boolean expanded = false;

    public ExpandableHeightGridView(Context context)
    {
        super(context);
    }

    public ExpandableHeightGridView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ExpandableHeightGridView(Context context, AttributeSet attrs,
                                    int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public boolean isExpanded()
    {
        return expanded;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (isExpanded())
        {
            int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);

            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
        }
        else
        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setExpanded(boolean expanded)
    {
        this.expanded = expanded;
    }
}
