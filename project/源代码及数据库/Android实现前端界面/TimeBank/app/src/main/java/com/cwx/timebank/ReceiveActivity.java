package com.cwx.timebank;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.cwx.timebank.task.FindMyReceiveTask;

import java.util.ArrayList;
import java.util.List;

public class ReceiveActivity extends AppCompatActivity {
    private ListView lvReceiveList;//进行中的任务
    private ListView lvFinishReceiveList;//已完成的任务
    private TabHost tabHost;
    private List<View> viewList=new ArrayList<View>();
    private String[] tabHostTag={"receive_tab1","receive_tab2"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        //返回箭头 返回上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReceiveActivity.this.finish();
            }
        });

        SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);

        lvReceiveList=findViewById(R.id.lv_receive_list);
        lvFinishReceiveList=findViewById(R.id.lv_receive_finish_list);
        TabHost tabHost = findViewById(android.R.id.tabhost);

        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[0]).setIndicator(getTabView("进行中")). setContent(R.id.receive_tab1));
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[1]).setIndicator(getTabView("已完成")) .setContent(R.id.receive_tab2));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabHostChanged(tabId);
            }
        });

        setTabHostChanged(tabHost.getCurrentTabTag());


        FindMyReceiveTask findMyReceiveTask = new FindMyReceiveTask(getApplication(),sp.getInt("userId",0),lvReceiveList,lvFinishReceiveList);
        findMyReceiveTask.execute();


    }

    private void setTabHostChanged(String tabId) {
        TextView textView1 = viewList.get(0).findViewById(R.id.tv_text);
        TextView textView2 = viewList.get(1).findViewById(R.id.tv_text);
        TextView tvLine1=viewList.get(0).findViewById(R.id.tv_line);
        TextView tvLine2=viewList.get(1).findViewById(R.id.tv_line);
        textView1.setText("进行中");
        textView2.setText("已完成");
        if (tabId.equals(tabHostTag[0])) {
            textView1.setText(Html.fromHtml("<font color=#ffcc66>进行中</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#ffcc66"));
            tvLine2.setBackgroundColor(Color.parseColor("#aaaaaa"));
        } else if (tabId.equals(tabHostTag[1])) {
            textView2.setText(Html.fromHtml("<font color=#ffcc66>已完成</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#aaaaaa"));
            tvLine2.setBackgroundColor(Color.parseColor("#ffcc66"));
        }
    }
    private View getTabView(String text){
        View view= LayoutInflater.from(this).inflate(R.layout.tab_view,null);
        TextView textView= view.findViewById(R.id.tv_text);
        textView.setText(text);
        viewList.add(view);
        return view;
    }

}
