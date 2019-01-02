package com.cwx.timebank;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //创建一个列表，保存选项卡视图
    private List<View> viewList = new ArrayList<>();
    private FragmentTabHost tabHost;
    private int[] tabHostIconNormal = {R.drawable.index_normal, R.drawable.quanzi_normal, R.drawable.faburenwu, R.drawable.xiaoxi_normal, R.drawable.my_normal};
    private String[] tabHostText = {"首页","圈子","","消息","我的"};
    private Class[] fragmentArr = {BannerActivity.class,GroupActivity.class,SendActivity1.class,FragmentTab4.class,MyInfoActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //该APP一启动就将要访问的服务器地址保存在getSharedPreferences，方便以后修改只修改这一个地方
        SharedPreferences sharedPreferences = getSharedPreferences("myServer",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("serverUrl","http://10.7.88.246:8080/TimeBank");
//        editor.putString("serverUrl","http://10.7.85.146:8080/TimeBank");
        editor.commit();

        //初始化FragmentTabHost
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
        //如果从“我的”Activity访问MainActivity，则显示我的选项卡（即在“我的”点击登录、退出登录依旧在“我的”选项卡
        SharedPreferences sharedPreferences = getSharedPreferences("isFromMy",MODE_PRIVATE);
        if(sharedPreferences.getBoolean("fromMy",false)){
            tabHost.setCurrentTabByTag("我的");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("fromMy");
            editor.commit();
        }
        SharedPreferences sharedPreferences1=getSharedPreferences("isFromMy",MODE_PRIVATE);
        if(sharedPreferences.getBoolean("isFromQuanZi",false)){
            tabHost.setCurrentTabByTag("圈子");
            SharedPreferences.Editor editor=sharedPreferences1.edit();
            editor.remove("isFromQuanZi");
            editor.commit();
        }

        setTabHostIcon(tabHost.getCurrentTabTag());
    }

    private void setTabHostIcon(String tabId){
        ImageView imageView0 = viewList.get(0).findViewById(R.id.iv_image);
        ImageView imageView1 = viewList.get(1).findViewById(R.id.iv_image);
        ImageView imageView3 = viewList.get(3).findViewById(R.id.iv_image);
        ImageView imageView4 = viewList.get(4).findViewById(R.id.iv_image);
        imageView0.setImageResource(R.drawable.index_normal);
        imageView1.setImageResource(R.drawable.quanzi_normal);
        imageView3.setImageResource(R.drawable.xiaoxi_normal);
        imageView4.setImageResource(R.drawable.my_normal);

        //若传过来tab1
        if(tabId.equals("首页")){
            imageView0.setImageResource(R.drawable.index_selected);
        }else if(tabId.equals("圈子")){
            imageView1.setImageResource(R.drawable.quanzi_selected);
        }else if(tabId.equals("消息")){
            imageView3.setImageResource(R.drawable.xiaoxi_selected);
        }else{
            imageView4.setImageResource(R.drawable.my_selected);
        }
    }

    private View getTabHostView(int index){
        View view = getLayoutInflater().inflate(R.layout.fragment_tab,null);
        TextView textView = view.findViewById(R.id.tv_text);
        ImageView imageView = view.findViewById(R.id.iv_image);
        textView.setText(tabHostText[index]);
        imageView.setImageResource(tabHostIconNormal[index]);
        viewList.add(view);
        return view;
    }
}