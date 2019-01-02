package com.cwx.timebank;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.cwx.timebank.task.FindMySendTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendActivity extends AppCompatActivity {
    private List<View> viewList = new ArrayList<View>();
    private String[] tabHostTag = {"tab1", "tab2","tab3"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_item);

        //返回箭头 返回上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendActivity.this.finish();
            }
        });

        SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);


        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[0]).setIndicator(getTabView("待接收")).setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[1]).setIndicator(getTabView("进行中")).setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[2]).setIndicator(getTabView("已结束")).setContent(R.id.tab3));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabHostChanged(tabId);
            }
        });

        setTabHostChanged(tabHost.getCurrentTabTag());

        ListView lvReady = findViewById(R.id.lv_ready);
        ListView lvDoing = findViewById(R.id.lv_doing);
        ListView lvFinish = findViewById(R.id.lv_finish);

        FindMySendTask findMySendTask = new FindMySendTask(getApplication(),sp.getInt("userId",0),lvReady,lvDoing,lvFinish);
        findMySendTask.execute();

    }


    private void setTabHostChanged(String tabId) {
        TextView textView1 = viewList.get(0).findViewById(R.id.tv_text);
        TextView textView2 = viewList.get(1).findViewById(R.id.tv_text);
        TextView textView3=viewList.get(2).findViewById(R.id.tv_text);
        TextView tvLine1 = viewList.get(0).findViewById(R.id.tv_line);
        TextView tvLine2 = viewList.get(1).findViewById(R.id.tv_line);
        TextView tvLine3 = viewList.get(2).findViewById(R.id.tv_line);
        textView1.setText("待接收");
        textView2.setText("进行中");
        textView3.setText("已完成");
        if (tabId.equals(tabHostTag[0])) {
            textView1.setText(Html.fromHtml("<font color=#ffcc66>待接收</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#ffcc66"));
            tvLine2.setBackgroundColor(Color.parseColor("#aaaaaa"));
            tvLine3.setBackgroundColor(Color.parseColor("#aaaaaa"));
        } else if (tabId.equals(tabHostTag[1])) {
            textView2.setText(Html.fromHtml("<font color=#ffcc66>进行中</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#aaaaaa"));
            tvLine2.setBackgroundColor(Color.parseColor("#ffcc66"));
            tvLine3.setBackgroundColor(Color.parseColor("#aaaaaa"));
        }else{
            textView3.setText(Html.fromHtml("<font color=#ffcc66>已完成</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#aaaaaa"));
            tvLine2.setBackgroundColor(Color.parseColor("#aaaaaa"));
            tvLine3.setBackgroundColor(Color.parseColor("#ffcc66"));
        }
    }

    private View getTabView(String text) {
        View view = LayoutInflater.from(this).inflate(R.layout.fragment_tab1, null);
        TextView textView = view.findViewById(R.id.tv_text);
        textView.setText(text);
        viewList.add(view);
        return view;
    }

}
