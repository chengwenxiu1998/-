package com.cwx.timebank.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cwx.timebank.CircleImageView;
import com.cwx.timebank.R;
import com.cwx.timebank.TaskDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class FindMySendTask extends AsyncTask<Object,Object,Map<String,List<Map<String,Object>>>> {
    private Context context;
    private int uid;
    private ListView lvReady;//待接收的任务
    private ListView lvDoingList;//进行中的任务
    private ListView lvFinishList;//已完成的任务


    public FindMySendTask(Context context, int uid, ListView lvReady, ListView lvDoingList, ListView lvFinishList) {
        this.context = context;
        this.uid = uid;
        this.lvReady = lvReady;
        this.lvDoingList = lvDoingList;
        this.lvFinishList = lvFinishList;
    }

    @Override
    protected Map<String, List<Map<String, Object>>> doInBackground(Object... objects) {
        Map<String, List<Map<String, Object>>> map = new HashMap();
        List<Map<String, Object>> readyLists = new ArrayList<>();
        List<Map<String, Object>> doingLists = new ArrayList<>();
        List<Map<String, Object>> finishLists = new ArrayList<>();

        //通过网络访问服务器端查询该用户已经发布的任务
        //已发布的任务分为两种：待接收，进行中，已完成
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl", "");
            String urlStr = serverUrl + "/FindMySendServlet?uid=" + uid;
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("contentType", "UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();

            //解析一个JSON格式的字符串
            try {
                JSONObject jsonObject = new JSONObject(res);
                JSONArray readyArr = (JSONArray) jsonObject.get("ready");
                JSONArray doingArr = (JSONArray) jsonObject.get("doing");
                JSONArray finishArr = (JSONArray) jsonObject.get("finish");

                for (int j = 0; j < readyArr.length(); ++j) {
                    Log.e("12","ready"+j);
                    JSONObject obj = (JSONObject) readyArr.get(j);
                    Map<String, Object> task = new HashMap<>();

                    task.put("sendTimeArea", obj.get("uTime"));
                    task.put("coinCount", obj.get("tCoinCount"));
                    task.put("desc", obj.get("tDesc"));

                    task.put("sendHeadImg",obj.get("sendHeadImg"));
                    task.put("taskId", obj.get("tid"));
                    task.put("sendTime", obj.get("uTime"));
                    task.put("endTime", obj.get("tEndTime"));
                    task.put("sendArea", obj.get("sendAddress"));
                    task.put("imgUrl", obj.get("tImgUrl"));
                    task.put("tag", obj.get("tag"));
                    task.put("sendNickname", obj.get("sendNickname"));

                    readyLists.add(task);
                }
                for (int j = 0; j < doingArr.length(); ++j) {
                    Log.e("12","doing"+j);
                    JSONObject obj = (JSONObject) doingArr.get(j);
                    Map<String, Object> task = new HashMap<>();
                    task.put("sendTimeArea", obj.get("uTime"));
                    task.put("coinCount", obj.get("tCoinCount"));
                    task.put("desc", obj.get("tDesc"));

                    task.put("sendHeadImg",obj.get("sendHeadImg"));
                    task.put("taskId", obj.get("tid"));
                    task.put("sendTime", obj.get("uTime"));
                    task.put("endTime", obj.get("tEndTime"));
                    task.put("sendArea", obj.get("sendAddress"));
                    task.put("imgUrl", obj.get("tImgUrl"));
                    task.put("tag", obj.get("tag"));
                    task.put("sendNickname", obj.get("sendNickname"));
                    doingLists.add(task);
                }

                for (int i = 0; i < finishArr.length(); i++) {
                    Log.e("12","finish"+i);
                    JSONObject obj = (JSONObject) finishArr.get(i);
                    Map<String, Object> task = new HashMap<>();

                    task.put("sendTimeArea", obj.get("uTime"));
                    task.put("coinCount", obj.get("tCoinCount"));
                    task.put("desc", obj.get("tDesc"));

                    task.put("sendHeadImg",obj.get("sendHeadImg"));
                    task.put("taskId", obj.get("tid"));
                    task.put("sendTime", obj.get("uTime"));
                    task.put("endTime", obj.get("tEndTime"));
                    task.put("sendArea", obj.get("sendAddress"));
                    task.put("imgUrl", obj.get("tImgUrl"));
                    task.put("tag", obj.get("tag"));
                    task.put("sendNickname", obj.get("sendNickname"));
                    finishLists.add(task);
                }
                map.put("readyLists", readyLists);
                map.put("doingLists", doingLists);
                map.put("finishLists", finishLists);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    protected void onPostExecute(Map<String, List<Map<String, Object>>> map) {
        super.onPostExecute(map);

        List<Map<String, Object>> readyLists = map.get("readyLists");
        List<Map<String, Object>> doingLists = map.get("doingLists");
        List<Map<String, Object>> finishLists = map.get("finishLists");
        Log.e("12",readyLists.toString());
        //创建Adapter 给ListView设置Adapter
        CustomAdapter adapter1=new CustomAdapter(context, R.layout.layout_item,readyLists);
        lvReady.setAdapter(adapter1);
        CustomAdapter adapter2=new CustomAdapter(context, R.layout.layout_item,doingLists);
        lvDoingList.setAdapter(adapter2);
        CustomAdapter adapter3=new CustomAdapter(context, R.layout.layout_item,finishLists);
        lvFinishList.setAdapter(adapter3);

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
            CircleImageView imageView=view.findViewById(R.id.civ_image);
            TextView tvNickname=view.findViewById(R.id.tv_nickname);
            TextView tvSendTimeArea=view.findViewById(R.id.tv_send_time_area);
            TextView tvCoinCount=view.findViewById(R.id.tv_coin_count);
            TextView tvDesc = view.findViewById(R.id.tv_desc);

            // 根据Item位置，获取data（List）中对应位置的数据map
            final Map<String,Object> map=data.get(position);
            // 从map中根据[键]找到对应的[值]，并设置到相应控件
            SharedPreferences sp = context.getSharedPreferences("userInfo", MODE_PRIVATE);
            new ShowHeadImg(imageView,sp.getString("uImage","")).execute();
            tvNickname.setText(sp.getString("uNickname",""));
            tvSendTimeArea.setText((String)map.get("sendTimeArea")+" "+sp.getString("uArea",""));
            tvCoinCount.setText(map.get("coinCount").toString());
            tvDesc.setText("#"+(String)map.get("tag")+"#  "+(String)map.get("desc"));
            //设置点击事件，点击跳转到事件详情界面
            LinearLayout llItem = view.findViewById(R.id.ll_item);
            llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context,"根据不同的任务，跳转到不同的任务详情页",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, TaskDetailActivity.class);
                    intent.putExtra("taskId", (int) map.get("taskId"));
                    intent.putExtra("headImage", (String) map.get("sendHeadImg"));
                    intent.putExtra("nickname", (String) map.get("sendNickname"));
                    intent.putExtra("coinCount", (Integer) map.get("coinCount"));
                    intent.putExtra("text", (String) map.get("desc"));
                    intent.putExtra("tag", (String) map.get("tag"));
                    intent.putExtra("sendTime", (String) map.get("sendTime"));
                    intent.putExtra("endTime", (String) map.get("endTime"));
                    intent.putExtra("sendArea", (String) map.get("sendArea"));
                    intent.putExtra("imgUrl", (String) map.get("imgUrl"));
                    intent.putExtra("isFromMySend","true");

                    context.startActivity(intent);
                }
            });
            return view;
        }
    }
}

