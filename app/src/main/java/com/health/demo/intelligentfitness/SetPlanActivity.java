package com.health.demo.intelligentfitness;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.health.demo.intelligentfitness.util.SharedPreferencesUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SetPlanActivity extends BaseActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.tv_step_number)
    EditText tvStepNumber;
    @InjectView(R.id.cb_remind)
    CheckBox cbRemind;
    @InjectView(R.id.tv_remind_time)
    TextView tvRemindTime;
    @InjectView(R.id.btn_save)
    Button btnSave;
    @InjectView(R.id.activity_set_plan)
    LinearLayout activitySetPlan;
    private SharedPreferencesUtils sp;
    private String walk_qty;
    private String remind;
    private String achieveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_plan);
        ButterKnife.inject(this);
        initToolbar("锻炼计划");
        initData();
    }

    @OnClick({R.id.tv_remind_time, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_remind_time:
                showTimeDialog1();
                break;
            case R.id.btn_save:
                save();
                break;
        }
    }
    public void initData() {//获取锻炼计划
        sp = new SharedPreferencesUtils(this);
        String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
        String remind = (String) sp.getParam("remind", "1");
        String achieveTime = (String) sp.getParam("achieveTime", "20:00");
        if (!planWalk_QTY.isEmpty()) {
            if ("0".equals(planWalk_QTY)) {
                tvStepNumber.setText("7000");
            } else {
                tvStepNumber.setText(planWalk_QTY);
            }
        }
        if (!remind.isEmpty()) {
            if ("0".equals(remind)) {
                cbRemind.setChecked(false);
            } else if ("1".equals(remind)) {
                cbRemind.setChecked(true);
            }
        }

        if (!achieveTime.isEmpty()) {
            tvRemindTime.setText(achieveTime);
        }

    }
    private void save() {
        walk_qty = tvStepNumber.getText().toString().trim();
//        remind = "";
        if (cbRemind.isChecked()) {
            remind = "1";
        } else {
            remind = "0";
        }
        achieveTime = tvRemindTime.getText().toString().trim();
        if (walk_qty.isEmpty() || "0".equals(walk_qty)) {
            sp.setParam("planWalk_QTY", "7000");
        } else {
            sp.setParam("planWalk_QTY", walk_qty);
        }
        sp.setParam("remind", remind);

        if (achieveTime.isEmpty()) {
            sp.setParam("achieveTime", "21:00");
            this.achieveTime = "21:00";
        } else {
            sp.setParam("achieveTime", achieveTime);
        }
        finish();
    }
    private void showTimeDialog1() {
        final Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
//        String time = tv_remind_time.getText().toString().trim();
        final DateFormat df = new SimpleDateFormat("HH:mm");
//        Date date = null;
//        try {
//            date = df.parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        if (null != date) {
//            calendar.setTime(date);
//        }
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                String remaintime = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
                Date date = null;
                try {
                    date = df.parse(remaintime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (null != date) {
                    calendar.setTime(date);
                }
                tvRemindTime.setText(df.format(date));
            }
        }, hour, minute, true).show();
    }
}
