//
//  FrgRc
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.codbking.calendar.CaledarAdapter;
import com.codbking.calendar.CalendarBean;
import com.codbking.calendar.CalendarDateView;
import com.codbking.calendar.CalendarView;
import com.framewidget.newMenu.DateDfSelectDialog;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.act.ActDialogBaojing;
import com.jinqu.standardNew.ada.AdaRc;
import com.jinqu.standardNew.ada.AdaRcAll;
import com.jinqu.standardNew.ada.AdaRcBell;
import com.jinqu.standardNew.ada.AdaRcMonth;
import com.jinqu.standardNew.item.RcTop;
import com.jinqu.standardNew.model.EntitySearch;
import com.jinqu.standardNew.model.ModelBell;
import com.jinqu.standardNew.model.ModelRc;
import com.jinqu.standardNew.model.ModelRcAll;
import com.jinqu.standardNew.util.LunarCalendar;
import com.jinqu.standardNew.view.FloatingActionButton;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.codbking.calendar.CalendarLayout.TYPE_FOLD;
import static com.codbking.calendar.CalendarLayout.TYPE_OPEN;
import static com.jinqu.standardNew.F.COLOR;
import static com.jinqu.standardNew.F.METHOD_GetData;
import static com.jinqu.standardNew.F.METHOD_GetList;
import static com.jinqu.standardNew.F.METHOD_GetRemindSchedulers;
import static com.jinqu.standardNew.F.changeColor;
import static com.jinqu.standardNew.F.font;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.showNormal;
import static com.jinqu.standardNew.R.id.mLinearLayout_bg;

//http://www.jcodecraeer.com/a/opensource/2014/1010/1727.html
public class FrgRc extends BaseFrg implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {

    public CalendarDateView mCalendarDateView;
    public AbPullListView mAbPullListView;
    public FloatingActionButton fab;
    public View view_top;
    public TextView mTextView_month;
    public TextView mTextView_week;
    public TextView mTextView_year;
    public RadioButton mRadioButton1;
    public RadioButton mRadioButton2;
    public RadioButton mRadioButton3;
    public RadioButton mRadioButton4;
    public RadioGroup mRadioGroup;
    public LinearLayout mLinearLayout_week;
    public com.alamkanak.weekview.WeekView weekView;
    public com.codbking.calendar.CalendarLayout mCalendarLayout;
    public RelativeLayout mRelativeLayout_time;
    public AbPullListView mAbPullListView_all;
    public int code;
    public String time;
    public List<ModelRcAll.RowsBean> list = new ArrayList<>();
    public static String df = "yyyy-MM-dd HH:mm:ss";
    public RadioButton mRadioButton5;
    public AbPullListView mAbPullListView_bell;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_rc);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 10086:
                fab.setColorNormal(Color.parseColor(COLOR));
                fab.setColorPressed(Color.parseColor(COLOR));
                fab.setColorRipple(Color.parseColor(COLOR));
                mCalendarDateView.getAdapter().notifyDataSetChanged();
                break;
            case 888:
                List<EntitySearch> mEntitySearchs = (List<EntitySearch>) obj;
                filter(mEntitySearchs);
                break;
            case 114:
                list.clear();
                mAbPullListView_bell.reLoad();
                break;
            case 115:
                list.clear();
                mAbPullListView.reLoad();
                mAbPullListView_all.reLoad();
                break;
            case 1:
                mRadioGroup.check(R.id.mRadioButton4);
                break;
        }
    }

    public void filter(List<EntitySearch> mEntitySearchs) {
        String startdate = "", enddate = "", text = "";
        for (EntitySearch mEntitySearch : mEntitySearchs) {
            if (mEntitySearch.title.equals("开始时间")) {
                startdate = mEntitySearch.value;
            }
            if (mEntitySearch.title.equals("结束时间")) {
                enddate = mEntitySearch.value;
            }
            if (mEntitySearch.title.equals("关键字")) {
                text = mEntitySearch.value;
            }
        }
        mAbPullListView_all.setPostApiLoadParams(METHOD_GetList, "empID", mModelUsreLogin.UserInfo.EmpID, "startdate", startdate, "enddate", enddate, "text", text);
    }

    private void initView() {
        findVMethod();
    }


    private void findVMethod() {
        mCalendarDateView = (CalendarDateView) findViewById(R.id.mCalendarDateView);
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mCalendarDateView.setAdapter(new CaledarAdapter() {
            public RelativeLayout mRelativeLayout_pt;
            public ImageView mMImageView_dot;
            public TextView mTextView_nl;
            public TextView mTextView_num;
            public TextView mTextView_xiu;

            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarBean bean) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_rl, null);
                }

                mTextView_xiu = (TextView) convertView.findViewById(R.id.mTextView_xiu);
                mTextView_num = (TextView) convertView.findViewById(R.id.mTextView_num);
                mTextView_nl = (TextView) convertView.findViewById(R.id.mTextView_nl);
                mMImageView_dot = (ImageView) convertView.findViewById(R.id.mMImageView_dot);
                mRelativeLayout_pt = (RelativeLayout) convertView.findViewById(R.id.mRelativeLayout_pt);
                GradientDrawable myGrad = (GradientDrawable) getResources().getDrawable(R.drawable.shape_blue_yuan);
                myGrad.setColor(Color.parseColor(COLOR));
                mMImageView_dot.setBackground(myGrad);
                mTextView_nl.setText(LunarCalendar.solarToLunar(bean.year, bean.moth, bean.day));
                mTextView_num.setText("" + bean.day);
                if (bean.mothFlag != 0) {//非当前月
                    mTextView_num.setTextAppearance(getActivity(), R.style.text_style_11_hui_rili);
                    mTextView_nl.setTextAppearance(getActivity(), R.style.text_style_11_hui_rili);
                    mMImageView_dot.setVisibility(View.INVISIBLE);
                } else {
                    mTextView_num.setTextAppearance(getActivity(), R.style.text_style_11_black_rili);
                    mTextView_nl.setTextAppearance(getActivity(), R.style.text_style_11_gray_rili);
                    mMImageView_dot.setVisibility(View.INVISIBLE);
                    if (list != null) {
                        for (ModelRcAll.RowsBean mModelRc : list) {
                            if (AbDateUtil.getOffectDay(AbDateUtil.getDateByFormat(mModelRc.StartTime, df).getTime(), AbDateUtil.getDateByFormat(bean.year + "-" + bean.moth + "-" + bean.day, "yyyy-MM-dd").getTime()) == 0) {
                                mMImageView_dot.setVisibility(View.VISIBLE);
                                break;
                            }
                        }
                    }
                }
                return convertView;
            }
        });
        mTextView_month = (TextView) findViewById(R.id.mTextView_month);
        mTextView_week = (TextView) findViewById(R.id.mTextView_week);
        mTextView_year = (TextView) findViewById(R.id.mTextView_year);
        mRadioButton1 = (RadioButton) findViewById(R.id.mRadioButton1);
        mRadioButton2 = (RadioButton) findViewById(R.id.mRadioButton2);
        mRadioButton3 = (RadioButton) findViewById(R.id.mRadioButton3);
        mRadioButton4 = (RadioButton) findViewById(R.id.mRadioButton4);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mLinearLayout_week = (LinearLayout) findViewById(R.id.mLinearLayout_week);
        weekView = (com.alamkanak.weekview.WeekView) findViewById(R.id.weekView);
        mCalendarLayout = (com.codbking.calendar.CalendarLayout) findViewById(R.id.mCalendarLayout);
        mRelativeLayout_time = (RelativeLayout) findViewById(R.id.mRelativeLayout_time);
        mAbPullListView_all = (AbPullListView) findViewById(R.id.mAbPullListView_all);
        mRadioButton5 = (RadioButton) findViewById(R.id.mRadioButton5);
        mAbPullListView_bell = (AbPullListView) findViewById(R.id.mAbPullListView_bell);
        weekView.setOnEventClickListener(this);
        weekView.setMonthChangeListener(this);
        weekView.setEventLongPressListener(this);
        weekView.setEmptyViewLongPressListener(this);
        setupDateTimeInterpreter();
        mRadioButton4.setTypeface(font);
        mRadioButton5.setTypeface(font);
        mCalendarDateView.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion, CalendarBean bean, boolean isFromClick, boolean isFromUser) {
                if (isFromClick || isFromUser) {
                    setTime(bean.year + "-" + bean.moth + "-" + bean.day, isFromClick ? bean.mothFlag != 0 : isFromClick);
                }
            }

        });
        mCalendarDateView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fab.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgCreateRcDetail.class, TitleAct.class);
            }
        }));
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.mRadioButton1) {
                    ((RcTop) view_top.getTag()).set(0);
                    code = 0;
                    mLinearLayout_week.setVisibility(View.VISIBLE);
                    mCalendarLayout.setVisibility(View.VISIBLE);
                    weekView.setVisibility(View.GONE);
                    mAbPullListView_all.setVisibility(View.GONE);
                    mAbPullListView_bell.setVisibility(View.GONE);
                    Frame.HANDLES.sentAll("FrgFx", 0, false);
                    mAbPullListView.reLoad();
                } else if (checkedId == R.id.mRadioButton2) {
                    weekView.setNumberOfVisibleDays(7);
                    code = 1;
                    mLinearLayout_week.setVisibility(View.GONE);
                    mCalendarLayout.setVisibility(View.GONE);
                    mAbPullListView_all.setVisibility(View.GONE);
                    mAbPullListView_bell.setVisibility(View.GONE);
                    weekView.setVisibility(View.VISIBLE);
                    Frame.HANDLES.sentAll("FrgFx", 0, true);
                } else if (checkedId == R.id.mRadioButton3) {
                    weekView.setNumberOfVisibleDays(1);
                    code = 2;
                    mLinearLayout_week.setVisibility(View.GONE);
                    mCalendarLayout.setVisibility(View.GONE);
                    mAbPullListView_all.setVisibility(View.GONE);
                    mAbPullListView_bell.setVisibility(View.GONE);
                    weekView.setVisibility(View.VISIBLE);
                    Frame.HANDLES.sentAll("FrgFx", 0, false);
                    mAbPullListView.reLoad();
                } else if (checkedId == R.id.mRadioButton4) {
                    code = 3;
                    mLinearLayout_week.setVisibility(View.GONE);
                    mCalendarLayout.setVisibility(View.GONE);
                    weekView.setVisibility(View.GONE);
                    mAbPullListView_all.setVisibility(View.VISIBLE);
                    mAbPullListView_bell.setVisibility(View.GONE);
                    Frame.HANDLES.sentAll("FrgFx", 0, false);
                } else if (checkedId == R.id.mRadioButton5) {
                    code = 4;
                    mLinearLayout_week.setVisibility(View.GONE);
                    mCalendarLayout.setVisibility(View.GONE);
                    weekView.setVisibility(View.GONE);
                    mAbPullListView_all.setVisibility(View.GONE);
                    mAbPullListView_bell.setVisibility(View.VISIBLE);
                    Frame.HANDLES.sentAll("FrgFx", 0, false);
                }

            }
        });
        mRelativeLayout_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDfSelectDialog mDateSelectDialog = new DateDfSelectDialog(getContext(), null, 3);
                mDateSelectDialog.show();
                mDateSelectDialog.setOnSelected(new DateDfSelectDialog.OnSelected() {
                    @Override
                    public void onSelected(Dialog dia, String selected) {
                        setTime(selected, true);
                    }
                });
            }
        });
    }

    public void loaddata() {
        mAbPullListView.setPullRefreshEnable(false);
        view_top = RcTop.getView(getContext(), null);
        mAbPullListView.addHeaderView(view_top);
        setTime(AbDateUtil.getStringByFormat(new Date(), "yyyy-MM-dd"), true);
        mAbPullListView_all.setPageSize(Integer.MAX_VALUE);
        mAbPullListView_all.setPostApiLoadParams(METHOD_GetList, "empID", mModelUsreLogin.UserInfo.EmpID);
        mAbPullListView_all.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelRcAll mModelRcAll = (ModelRcAll) json2Model(content, ModelRcAll.class);
                List<ModelRcAll.RowsBean> list_s = new ArrayList();
                for (ModelRcAll.RowsBean item : mModelRcAll.rows) {
                    int count = Math.abs(AbDateUtil.getOffectDay(AbDateUtil.getDateByFormat(item.StartTime, df).getTime(), AbDateUtil.getDateByFormat(item.EndTime, df).getTime()));
                    for (int j = 0; j <= count; j++) {
                        ModelRcAll.RowsBean son = new ModelRcAll.RowsBean();
                        son.Content = item.Content;
                        son.ID = item.ID;
                        son.StartTime = j == 0 ? item.StartTime : AbDateUtil.getStringByOffset(AbDateUtil.getDateByFormat(item.StartTime, df), "yyyy-MM-dd", Calendar.DATE, j) + " 8:30:00.000";
                        son.EndTime = j == count ? item.EndTime : AbDateUtil.getStringByOffset(AbDateUtil.getDateByFormat(item.StartTime, df), "yyyy-MM-dd", Calendar.DATE, j) + " 17:30:00.000";
                        list_s.add(son);
                    }
                }
                if (list.size() <= 0) {
                    list.addAll(list_s);
                }
                mCalendarDateView.getAdapter().notifyDataSetChanged();
                weekView.notifyDatasetChanged();
                return new AdaRcAll(getContext(), list_s);
            }
        });
        mAbPullListView_bell.setPostApiLoadParams(METHOD_GetRemindSchedulers);
        mAbPullListView_bell.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                List<Integer> data_history = Helper.readBuilder("bell", new ArrayList<Integer>());
                ModelBell[] data = (ModelBell[]) json2Model(content, ModelBell[].class);
                for (ModelBell mModelBell : data) {
                    if (data_history.contains(mModelBell.ID)) {
                        continue;
                    }
                    showNormal(getContext(), "你有一个日程提醒:" + mModelBell.Content);
                    startActivity(new Intent(getContext(), ActDialogBaojing.class).putExtra("data", mModelBell));
                    data_history.add(mModelBell.ID);
                    mRadioGroup.check(R.id.mRadioButton5);
                }
                Helper.saveBuilder("bell", data_history);
                return new AdaRcBell(getContext(), Arrays.asList(data));
            }
        });
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelRc[] data = (ModelRc[]) json2Model(content, ModelRc[].class);
                if (code == 0) {
                    return new AdaRcMonth(getContext(), Arrays.asList(data));
                } else {
                    ((RcTop) view_top.getTag()).set(data.length > 0 ? 1 : -1);
                    return new AdaRc(getContext(), Arrays.asList(data));
                }
            }
        });
    }

    public void setTime(String time, boolean isFromClick) {
        mTextView_month.setText(time.split("-")[1] + "月");
        mTextView_year.setText(time.split("-")[0] + "年");
        ((RcTop) view_top.getTag()).setTime(time);
        mTextView_week.setText(AbDateUtil.getWeekNumber(time, "yyyy-MM-dd"));
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(AbDateUtil.getDateByFormat(time, "yyyy-MM-dd"));
        weekView.goToDate(mCalendar);
//        CalendarBean mCalendarBean = getMonthOfDayList(Integer.valueOf(time.split("-")[0]), Integer.valueOf(time.split("-")[1])).get(0);
//        startTime = mCalendarBean.year + "-" + mCalendarBean.moth + "-" + mCalendarBean.day;
        this.time = time;
        if (isFromClick) {
            mCalendarDateView.mDate = AbDateUtil.getDateByFormat(time, "yyyy-MM-dd");
            mCalendarDateView.initData();
        }
        mAbPullListView.setGetApiLoadParams(METHOD_GetData, "startTime", time, "endTime", AbDateUtil.getStringByOffset(time, "yyyy-MM-dd", Calendar.DATE, 0) + " 23:59", "timeshift", -480);
    }


    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();
        if (list != null)
            for (ModelRcAll.RowsBean mModelRc : list) {
                WeekViewEvent mWeekViewEvent = toWeekViewEvent(mModelRc.Content, mModelRc.StartTime, mModelRc.EndTime);
                mWeekViewEvent.setId(mModelRc.ID);
                if (eventMatches(mWeekViewEvent, newYear, newMonth)) {
                    matchedEvents.add(mWeekViewEvent);
                }
            }
        return matchedEvents;
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        String startTime = AbDateUtil.getStringByFormat(time.getTime(), "yyyy-MM-dd'T'HH:00");
        String endTime = AbDateUtil.getStringByOffset(AbDateUtil.getStringByFormat(time.getTime(), "yyyy-MM-dd'T'HH:00"), "yyyy-MM-dd'T'HH:00", Calendar.HOUR, 1);
        Helper.startActivity(getContext(), FrgCreateRcDetail.class, TitleAct.class, "startTime", startTime, "endTime", endTime);
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Helper.startActivity(getContext(), FrgCreateRcDetail.class, TitleAct.class, "id", event.getId() + "");
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }

    private void setupDateTimeInterpreter() {
        weekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 12 ? (hour - 12) + " PM" : hour + " AM";
            }
        });
    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    @SuppressLint("SimpleDateFormat")
    public WeekViewEvent toWeekViewEvent(String event, String StartTime, String EndTime) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(AbDateUtil.getDateByFormat(StartTime, df));
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(AbDateUtil.getDateByFormat(EndTime, df));

        // Create an week view event.
        WeekViewEvent weekViewEvent = new WeekViewEvent();
        weekViewEvent.setName(event);
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(endTime);
        return weekViewEvent;
    }
}