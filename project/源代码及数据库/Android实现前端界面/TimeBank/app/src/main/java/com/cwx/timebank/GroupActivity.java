package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.cwx.timebank.task.DisussTask;
import com.cwx.timebank.task.ShaishaiTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mob.tools.utils.DeviceHelper.getApplication;

public class GroupActivity extends Fragment {
    private SharedPreferences sp;
    private List<View> viewList=new ArrayList<View>();
    private String[] tabHostTag={"tab1","tab2"};

    private TabHost tabHost;
    //当创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_group,container,false);


        tabHost = view.findViewById(android.R.id.tabhost);
        sp = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        //点击加号，发表话题
        TextView tvSendTalk = view.findViewById(R.id.tv_send_talk);
        tvSendTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sp.getInt("userId",0)!=0) {//若该用户已登录
                    Intent intent = new Intent(getContext(),SendTalkActivity.class);
                    startActivity(intent);
                }else{//用户还没有登陆，跳转到登陆页面
                    SharedPreferences sp = getContext().getSharedPreferences("isFromMy",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("isFromQuanZi",true);
                    editor.commit();
                    Intent intent = new Intent(getContext(),LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        // 3. 给ListView设置Adapter
        ListView listView=view.findViewById(R.id.lv_text);
        DisussTask disussTask=new DisussTask(getApplication(),listView);
        disussTask.execute();

        ListView lvList=view.findViewById(R.id.lv_list);
        ShaishaiTask shaishaiTask=new ShaishaiTask(getApplication(),lvList);
        shaishaiTask.execute();

        return view;
    }

    //当View创建完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[0]).setIndicator(getTabView("晒晒")). setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[1]).setIndicator(getTabView("讨论")) .setContent(R.id.tab2));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabHostChanged(tabId);
            }
        });

        setTabHostChanged(tabHost.getCurrentTabTag());
    }
    private void setTabHostChanged(String tabId) {
        TextView textView1 = viewList.get(0).findViewById(R.id.tv_text);
        TextView textView2 = viewList.get(1).findViewById(R.id.tv_text);
        TextView tvLine1=viewList.get(0).findViewById(R.id.tv_line);
        TextView tvLine2=viewList.get(1).findViewById(R.id.tv_line);
        textView1.setText("讨论");
        textView2.setText("晒晒");
        if (tabId.equals(tabHostTag[0])) {
            textView1.setText(Html.fromHtml("<font color=#ffcc66>讨论</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#ffcc66"));
            tvLine2.setBackgroundColor(Color.parseColor("#aaaaaa"));
        } else if (tabId.equals(tabHostTag[1])) {
            textView2.setText(Html.fromHtml("<font color=#ffcc66>晒晒</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#aaaaaa"));
            tvLine2.setBackgroundColor(Color.parseColor("#ffcc66"));
        }
    }
    private View getTabView(String text){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.tab_view,null);
        TextView textView= view.findViewById(R.id.tv_text);
        textView.setText(text);
        viewList.add(view);
        return view;
    }

}
