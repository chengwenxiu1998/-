package com.cwx.timebank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FindTagActivity extends AppCompatActivity {
    //创建一个列表，保存选项卡视图
    private List<View> viewList = new ArrayList<>();

    private FragmentTabHost tabHost;
    private String[] tabHostText = {"我要买时间","我要卖时间"};
    private Class[] fragmentArr = {FragmentTabBuyTime.class,FragmentTabSellTime.class};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_tag_layout);
        initTabHost();
    }

    private void initTabHost() {
        tabHost = findViewById(android.R.id.tabhost);


        tabHost.setup(this,getSupportFragmentManager(),android.R.id.tabhost);

        for(int i = 0 ; i < fragmentArr.length ; i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabHostText[i]).setIndicator(getTabHostView(i));
            tabHost.addTab(tabSpec,fragmentArr[i],null);
        }

        //给tabHost控件增加事件监听器
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabHostIcon(tabId);
            }
        });

        setTabHostIcon(tabHost.getCurrentTabTag());
    }

    private void setTabHostIcon(String tabId){
        //标签上显示的文本
        TextView tvTabText0 = viewList.get(0).findViewById(R.id.tv_text);
        TextView tvTabText1 = viewList.get(1).findViewById(R.id.tv_text);
        //标签的下划线
        TextView tvUnderline0 = viewList.get(0).findViewById(R.id.tv_underline);
        TextView tvUnderline1 = viewList.get(1).findViewById(R.id.tv_underline);



        tvTabText0.setTextColor(0xff000000);
        tvTabText1.setTextColor(0xff000000);

        tvUnderline0.setBackgroundColor(0xffffffff);
        tvUnderline1.setBackgroundColor(0xffffffff);

        tvTabText0.getPaint().setFakeBoldText(false);
        tvTabText1.getPaint().setFakeBoldText(false);

        //若传过来tab1
        if(tabId.equals("我要买时间")){
            tvTabText0.setTextColor(0xffffcc66);
            tvUnderline0.setBackgroundColor(0xffffcc66);
            tvTabText0.getPaint().setFakeBoldText(true);//加粗显示
        }else{
            tvTabText1.setTextColor(0xffffcc66);
            tvUnderline1.setBackgroundColor(0xffffcc66);
            tvTabText1.getPaint().setFakeBoldText(true);//加粗显示
        }
    }

    private View getTabHostView(int index){
        View view = getLayoutInflater().inflate(R.layout.send_task_fragment_tab,null);
        TextView textView = view.findViewById(R.id.tv_text);
        textView.setText(tabHostText[index]);
        viewList.add(view);
        return view;
    }
}

