package com.codbking.calendar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.utility.Helper;

import java.util.Date;
import java.util.List;

/**
 * Created by codbking on 2016/12/18.
 * email:codbking@gmail.com
 * github:https://github.com/codbking
 * blog:http://www.jianshu.com/users/49d47538a2dd/latest_articles
 */

public class CalendarView extends ViewGroup {

    private static final String TAG = "CalendarView";

    public int selectPostion = -1;

    private CaledarAdapter adapter;
    private List<CalendarBean> data;
    private OnItemClickListener onItemClickListener;

    private int row = 6;
    private int column = 7;
    private int itemWidth;
    private int itemHeight;
    private Date mDate;
    public boolean isToMonth;

    public interface OnItemClickListener {
        void onItemClick(View view, int postion, CalendarBean bean, boolean isFromClick, boolean isFromUser);
    }

    public CalendarView(Context context, int row) {
        super(context);
        this.row = row;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public void setAdapter(CaledarAdapter adapter) {
        this.adapter = adapter;
    }

    public void setData(List<CalendarBean> data, boolean isToMonth, Date mDate) {
        this.data = data;
        this.mDate = mDate;
        this.isToMonth = isToMonth;
        setItem();
        requestLayout();
    }


    private void setItem() {
        selectPostion = -1;
        if (adapter == null) {
            throw new RuntimeException("adapter is null,please setadapter");
        }
        int[] date = CalendarUtil.getYMD(mDate);
        for (int i = 0; i < data.size(); i++) {
            CalendarBean bean = data.get(i);
            View view = getChildAt(i);
            View chidView = adapter.getView(view, this, bean);

            if (view == null || view != chidView) {
                addViewInLayout(chidView, i, chidView.getLayoutParams(), true);
            }
            if (isToMonth) {
                if (bean.year == date[0] && bean.moth == date[1] && bean.day == date[2]) {
                    selectPostion = i;
                }
            } else if (selectPostion == -1 && bean.day == 1) {
                selectPostion = i;
            }
            chidView.setSelected(selectPostion == i);
            setItemClick(chidView, i, bean);
        }
    }

    public Object[] getSelect() {
        return new Object[]{getChildAt(selectPostion), selectPostion, data.get(selectPostion)};
    }

    public void setItemClick(final View view, final int potsion, final CalendarBean bean) {
        view.setOnClickListener(Helper.delayClickLitener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("time", bean.year + "-" + bean.moth + "-" + bean.day);
                if (selectPostion == potsion) {
                    return;
                }
                if (bean.mothFlag == 0) {
                    setSelected((ViewGroup) getChildAt(selectPostion), false);
                    setSelected((ViewGroup) getChildAt(potsion), true);
                    selectPostion = potsion;
                }
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, potsion, bean, true, true);
                }
            }
        }));
    }

    public void setSelected(ViewGroup viewGroup, boolean select) {
        if (viewGroup != null) {
            viewGroup.setSelected(select);
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                    setSelected((ViewGroup) viewGroup.getChildAt(i), select);
                } else {
                    viewGroup.getChildAt(i).setSelected(select);
                }
            }
        }
    }

    public int[] getSelectPostion() {
        Rect rect = new Rect();
        try {
            getChildAt(selectPostion).getHitRect(rect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[]{rect.left, rect.top, rect.right, rect.top};
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY));

        itemWidth = parentWidth / column;
        itemHeight = itemWidth;

        View view = getChildAt(0);
        if (view == null) {
            return;
        }
        LayoutParams params = view.getLayoutParams();
        if (params != null && params.height > 0) {
            itemHeight = params.height;
        }
        setMeasuredDimension(parentWidth, itemHeight * row);


        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.measure(MeasureSpec.makeMeasureSpec(itemWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(itemHeight, MeasureSpec.EXACTLY));
        }

        Log.i(TAG, "onMeasure() called with: itemHeight = [" + itemHeight + "], itemWidth = [" + itemWidth + "]");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            layoutChild(getChildAt(i), i, l, t, r, b);
        }
    }

    private void layoutChild(View view, int postion, int l, int t, int r, int b) {

        int cc = postion % column;
        int cr = postion / column;

        int itemWidth = view.getMeasuredWidth();
        int itemHeight = view.getMeasuredHeight();

        l = cc * itemWidth;
        t = cr * itemHeight;
        r = l + itemWidth;
        b = t + itemHeight;
        view.layout(l, t, r, b);

    }
}
