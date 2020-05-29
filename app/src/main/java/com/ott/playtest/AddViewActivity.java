package com.ott.playtest;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddViewActivity extends AppCompatActivity {
    private ConstraintLayout layout;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addview);
        layout = findViewById(R.id.root_view);
        linearLayout = findViewById(R.id.container);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addviewTwo();
            }
        });
    }
    /**
     * 按钮一的点击事件
     */
    private void addView() {
//        TextView textView = new TextView(this);
//        //获取当前时间并格式化
//        String currentTime = dateToStamp(System.currentTimeMillis());
//        textView.setText("测试一..."+currentTime);
//        textView.setTextColor(getResources().getColor(R.color.colorAccent));
//
//        linearLayout.addView(textView,0);




        PlayWindow playerSimple = new PlayWindow(this);

        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams1.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams1.height = 500;
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams1.addRule(RelativeLayout.CENTER_VERTICAL);
        playerSimple.setLayoutParams(layoutParams1);

        linearLayout.addView(playerSimple,0);
    }
    /**
     * 按钮二的点击事件
     */
    private void addviewTwo() {
        TextView textView = new TextView(this);
        //获取当前时间并格式化
        String currentTime = dateToStamp(System.currentTimeMillis());
        textView.setText("测试二..."+currentTime);
        textView.setTextSize(20f);
        textView.setTextColor(getResources().getColor(R.color.colorPrimary));

        linearLayout.addView(textView,1);
    }
    /**
     *格式化事件
     */
    public String dateToStamp(long s) {
        String res;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(s);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            return "";
        }
        return res;
    }
}
