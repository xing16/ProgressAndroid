package com.xing.progressandroid.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xing.progressandroid.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarView extends LinearLayout implements View.OnClickListener {
    private ImageView preImgView;
    private ImageView nextImgView;
    private TextView todayDateTxtView;
    private GridView gridView;
    private Calendar curCalendar = Calendar.getInstance();

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attributeSet) {
        initView(context);
        setListener();
        render();


    }

    private void setListener() {
        preImgView.setOnClickListener(this);
        nextImgView.setOnClickListener(this);
    }

    private void initView(Context context) {
        setBackgroundColor(getResources().getColor(android.R.color.white));
        LayoutInflater.from(context).inflate(R.layout.layout_calendar_view, this);
        preImgView = findViewById(R.id.iv_pre);
        nextImgView = findViewById(R.id.iv_next);
        todayDateTxtView = findViewById(R.id.tv_today_date);
        gridView = findViewById(R.id.gv_calendar_view);

    }

    private void render() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String todayStr = sdf.format(new Date());
        todayDateTxtView.setText(todayStr);

        Calendar calendar = (Calendar) curCalendar.clone();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // 获取当前月的第一天是星期几
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        Log.e("debug", "firstDayOfWeek = " + firstDayOfWeek);
        int preDays = firstDayOfWeek - 1;
        calendar.add(Calendar.DAY_OF_MONTH, -preDays);
        int count = preDays + getMonthDays();
        ArrayList<Date> arrayList = new ArrayList<>();
        while (arrayList.size() < count) {
            arrayList.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        gridView.setAdapter(new CalendarAdapter(getContext(), R.layout.item_calendar_view, arrayList));
    }

    /**
     * 获取某月天数
     *
     * @return
     */
    private int getMonthDays() {
        Calendar calendar = (Calendar) curCalendar.clone();
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_pre:
                curCalendar.add(Calendar.MONTH, -1);
                render();
                break;
            case R.id.iv_next:
                curCalendar.add(Calendar.MONTH, 1);
                render();
                break;
        }
    }


    private class CalendarAdapter extends ArrayAdapter<Date> {

        private Context context;
        private List<Date> dateList;


        public CalendarAdapter(@NonNull Context context, int resource, @NonNull List<Date> objects) {
            super(context, resource, objects);
            this.context = context;
            this.dateList = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Date date = dateList.get(position);
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_calendar_view, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textView = convertView.findViewById(R.id.tv_calendar_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Date curDate = curCalendar.getTime();
            Log.e("debug", "now month = " + curDate.getMonth());
            Log.e("debug", "date month = " + date.getMonth());
            if (date.getMonth() == curDate.getMonth()) {
                viewHolder.textView.setTextColor(getResources().getColor(android.R.color.black));
            } else {
                viewHolder.textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
            }
            Date now = new Date();
            if (date.getDate() == now.getDate() && date.getMonth() == now.getMonth() && date.getYear() == now.getYear()) {
                viewHolder.textView.setBackgroundResource(R.drawable.shape_calendar_item);
                viewHolder.textView.setTextColor(getResources().getColor(android.R.color.white));
            }
//
//
//                if (date.getDate() == new Date().getDate()) {
//                viewHolder.textView.setBackgroundResource(R.drawable.shape_calendar_item);
//                viewHolder.textView.setTextColor(getResources().getColor(android.R.color.white));
//            } else {
//                viewHolder.textView.setBackgroundResource(0);
//                viewHolder.textView.setTextColor(getResources().getColor(android.R.color.black));
//            }


            viewHolder.textView.setText(String.valueOf((date.getDate())));
            return convertView;
        }

        class ViewHolder {
            TextView textView;
        }
    }

}
