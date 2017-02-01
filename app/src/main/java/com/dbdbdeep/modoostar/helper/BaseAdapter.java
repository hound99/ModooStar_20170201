package com.dbdbdeep.modoostar.helper;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;


public abstract class BaseAdapter<T,H> extends ArrayAdapter<T> {

    private int resource;
    private Activity activity;

    public BaseAdapter(Activity activity, int resource) {
        super(activity, resource, new ArrayList<T>());
        this.resource = resource;
        this.activity = activity;
    }

    /** ListView에 의해서 호출되는 메서드 */
    // R.layout.food_item 파일을 열어서 그 내부에 배치된 컴포넌트에게 데이터를 출력하고,
    // 자신을 호출한 ListView에게 결과를 리턴한다.
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // ListView에게 전달받은 레이아웃 객체를 복사.
        View view = convertView;
        H holder = null;

        if (view == null) {
            // Layout ID를 사용하여 레이아웃 객체 생성 --> Layout XML열기
            LayoutInflater inflater = this.activity.getLayoutInflater();
            view = inflater.inflate(this.resource, null);

            try {
                //holder = ((Class <H>) ((ParameterizedType) getClass().getGenericSuperclass())
                //            .getActualTypeArguments()[0]).newInstance();
                Class<H> clazz = (Class <H>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
                Constructor<H> constructor = clazz.getConstructor(View.class);
                holder = constructor.newInstance(view);

                // holder에 있는 컴포넌트 객체를 할당하기.
                //this.initHolder(view, holder);

                view.setTag(holder);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        // 데이터 객체 하나 꺼내오기
        T item = getItem(position);

        if (item != null) {
            holder = (H) view.getTag();

            // holder내의 컴포넌트에 item의 데이터 출력 + 이벤트 처리
            this.setListItem(item, holder);
        }

        return view;
    }

    //public abstract void initHolder(View view, H holder);

    public abstract void setListItem(final T item, final H holder);
}
