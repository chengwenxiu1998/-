package com.cwx.timebank;

import android.content.Context;
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
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentTab4 extends Fragment {
    private List<View> viewList = new ArrayList<View>();
    private String[] tabHostTag = {"tab1", "tab2"};
    View view;
    //当创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.xiaoxi_xiaoxi,container,false);
        //根据布局文件产生视图控件
        //false 表示 创建的view 不放到第二个参数里边
        //第二个参数  参照他的大小来创建View的大小

        // ……
        TabHost tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[0]).setIndicator(getTabView("提醒")).setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[1]).setIndicator(getTabView("私信")).setContent(R.id.tab2));
        setTabHostChanged(tabHost.getCurrentTabTag());
        //给tabHost控件增加事件监听器
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabHostChanged(tabId);
            }
        });

        initListView();
        return view;
    }


    private void setTabHostChanged(String tabId) {
        TextView textView1 = viewList.get(0).findViewById(R.id.tv_text);
        TextView textView2 = viewList.get(1).findViewById(R.id.tv_text);
        TextView tvLine1 = viewList.get(0).findViewById(R.id.tv_line);
        TextView tvLine2 = viewList.get(1).findViewById(R.id.tv_line);
        textView1.setText("提醒");
        textView2.setText("私信");
        if (tabId.equals(tabHostTag[0])) {
            textView1.setText(Html.fromHtml("<font color=#FFCC66>提醒</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#ffcc66"));
            tvLine2.setBackgroundColor(Color.parseColor("#aaaaaa"));

        } else if (tabId.equals(tabHostTag[1])) {
            textView2.setText(Html.fromHtml("<font color=#ffcc66>私信</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#aaaaaa"));
            tvLine2.setBackgroundColor(Color.parseColor("#ffcc66"));
        }
    }

    private View getTabView(String text) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.xiaoxi_tixing, null);
        TextView textView = view.findViewById(R.id.tv_text);
        textView.setText(text);
        viewList.add(view);
        return view;
    }

    //初始化ListView,自定义Adapter
    private void initListView() {
        //获取数据
        List<Map<String,Object>> listData=getDateList();
        //创建Adapter
        CustomAdapter adapter=new CustomAdapter(getContext(), R.layout.xiaoxi_xiaomi,listData);
        // 3. 给ListView设置Adapter
        ListView listView=view.findViewById(R.id.lv_text);
        listView.setAdapter(adapter);

    }

    private class CustomAdapter extends BaseAdapter {
        private Context context;
        private int itemLayoutID;
        private List<Map<String,Object>> data;

        public CustomAdapter(Context context,
                             int itemLayoutID,//item的View模板布局
                             List<Map<String,Object>> data){
            this.context=context;
            this.itemLayoutID=itemLayoutID;
            this.data=data;
        }
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // 根据位置，返回对应位置的Item的显示View
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 从Activity的Context上下文环境中获取 布局填充器（根据布局文件创建相应对象）
            LayoutInflater inflater=LayoutInflater.from(context);
            // 使用布局填充器，根据构造函数中接收到的布局文件ID创建对应对象
            View view=inflater.inflate(itemLayoutID,null);
            // 从viewNew（模板布局文件的跟元素类型 LinearLayout）中获取对应控件
            CircleImageView imageView=view.findViewById(R.id.lv_image);
            TextView textView1=view.findViewById(R.id.tv_text);
            TextView textView2=view.findViewById(R.id.tv_text1);
            TextView textView3=view.findViewById(R.id.tv_text2);

            // 根据Item位置，获取data（List）中对应位置的数据map
            Map<String,Object> map=data.get(position);
            // 从map中根据[键]找到对应的[值]，并设置到相应控件
            imageView.setImageResource((int)map.get("image"));
            textView1.setText((String)map.get("text"));
            textView2.setText((String)map.get("text1"));
            textView3.setText((String)map.get("text2"));
            return view;
        }
    }
    private List<Map<String,Object>> getDateList() {
        Map<String,Object> map1=new HashMap<>();
        map1.put("image", R.drawable.p5);
        map1.put("text","我的小蜜");
        map1.put("text1","您有新的好友加入");
        map1.put("text2","16:38");

        Map<String,Object> map2=new HashMap<>();
        map2.put("image", R.drawable.p5);
        map2.put("text","系统通知");
        map2.put("text1","今日收入20时间币");
        map2.put("text2","昨天");
        List<Map<String,Object>> list=new ArrayList<>();
        list.add(map1);
        list.add(map2);
        return list;



    }

}

