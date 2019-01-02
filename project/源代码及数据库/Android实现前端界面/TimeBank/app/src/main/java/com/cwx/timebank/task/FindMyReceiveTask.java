package com.cwx.timebank.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

public class FindMyReceiveTask extends AsyncTask<Object, Object, Map<String, List<Map<String, Object>>>> {
    private Context context;
    private int uid;
    private ListView lvReceiveList;//进行中的任务
    private ListView lvFinishReceiveList;//已完成的任务


    public FindMyReceiveTask(Context context, int uid, ListView lvReceiveList, ListView lvFinishReceiveList) {
        this.context = context;
        this.uid = uid;
        this.lvReceiveList = lvReceiveList;
        this.lvFinishReceiveList = lvFinishReceiveList;
    }

    @Override
    protected Map<String, List<Map<String, Object>>> doInBackground(Object... objects) {
        Map<String, List<Map<String, Object>>> map = new HashMap();
        List<Map<String, Object>> doingLists = new ArrayList<>();
        List<Map<String, Object>> finishLists = new ArrayList<>();

        //通过网络访问服务器端查询该用户已经接受的任务
        //已接收的任务分为两种：进行中，已完成
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl", "");
            String urlStr = serverUrl + "/FindMyReceiveServlet?uid=" + uid;
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
                JSONArray doingArr = (JSONArray) jsonObject.get("doing");
                JSONArray finishArr = (JSONArray) jsonObject.get("finish");

                for (int j = 0; j < doingArr.length(); ++j) {
                    JSONObject obj = (JSONObject) doingArr.get(j);
                    Map<String, Object> task = new HashMap<>();
                    task.put("receiveMoney", obj.get("tCoinCount"));
                    task.put("receiveDetail", obj.get("tDesc"));
                    task.put("timeAndAddress", obj.get("uTime") + "  " + obj.get("sendAddress"));
                    task.put("receivePetName", obj.get("sendNickname"));
                    task.put("receiveTouxiang", obj.get("sendHeadImg"));
                    task.put("yaoQiu", obj.get("tag"));
                    task.put("taskId", obj.get("tid"));
                    task.put("sendTime", obj.get("uTime"));
                    task.put("endTime", obj.get("tEndTime"));
                    task.put("sendArea", obj.get("sendAddress"));
                    task.put("imgUrl", obj.get("tImgUrl"));
                    doingLists.add(task);
                }

                for (int i = 0; i < finishArr.length(); i++) {
                    JSONObject obj = (JSONObject) finishArr.get(i);
                    Map<String, Object> task = new HashMap<>();

                    task.put("receiveMoney", obj.get("tCoinCount"));
                    task.put("receiveDetail", obj.get("tDesc"));
                    task.put("timeAndAddress", obj.get("uTime") + "  " + obj.get("sendAddress"));
                    task.put("receivePetName", obj.get("sendNickname"));
                    task.put("receiveTouxiang", obj.get("sendHeadImg"));
                    task.put("yaoQiu", obj.get("tag"));
                    task.put("taskId", obj.get("tid"));
                    task.put("sendTime", obj.get("uTime"));
                    task.put("endTime", obj.get("tEndTime"));
                    task.put("sendArea", obj.get("sendAddress"));
                    task.put("imgUrl", obj.get("tImgUrl"));
                    finishLists.add(task);
                }
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
        List<Map<String, Object>> doingLists = map.get("doingLists");
        List<Map<String, Object>> finishLists = map.get("finishLists");


        MyReceiveAdapter adapter1 = new MyReceiveAdapter(context, R.layout.receive_finish_list_view, finishLists);
        lvFinishReceiveList.setAdapter(adapter1);

        MyReceiveAdapter adapter2 = new MyReceiveAdapter(context, R.layout.receivelist_view, doingLists);
        lvReceiveList.setAdapter(adapter2);

    }


    public class MyReceiveAdapter extends BaseAdapter {

        private Context context;
        private int itemLayoutId;
        private List<Map<String, Object>> datalist;

        public MyReceiveAdapter(Context context, int itemLayoutId,
                                List<Map<String, Object>> datalist) {
            this.context = context;
            this.itemLayoutId = itemLayoutId;
            this.datalist = datalist;
        }

        @Override
        public int getCount() {
            return datalist.size();
        }

        @Override
        public Object getItem(int position) {
            return datalist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemLayoutId, null);
            }
            CircleImageView receiveTouxiang = convertView.findViewById(R.id.iv_receive_touxiang);
            TextView receivePetName = convertView.findViewById(R.id.tv_receive_petname);
            TextView receiveMoney = convertView.findViewById(R.id.tv_receive_money);
            TextView timeAndAddress = convertView.findViewById(R.id.tv_time_address);
            TextView yaoQiu = convertView.findViewById(R.id.tv_yaoqiu);
            TextView receiveDetail = convertView.findViewById(R.id.tv_receive_detail);
            final TextView tvTaskId = convertView.findViewById(R.id.tv_task_id);//将id写在上边，方便后期操作，但用户是不可见的
            final Map<String, Object> map = datalist.get(position);
//            receiveTouxiang.setImageResource((int));
            SharedPreferences sp = context.getSharedPreferences("myServer", MODE_PRIVATE);
            new ShowHeadImg(receiveTouxiang, sp.getString("serverUrl", "") + map.get("receiveTouxiang").toString()).execute();
            receivePetName.setText((String) map.get("receivePetName"));
            receiveMoney.setText(map.get("receiveMoney").toString());
            timeAndAddress.setText((String) map.get("timeAndAddress"));
            yaoQiu.setText("#" + (String) map.get("yaoQiu") + "#");
            receiveDetail.setText((String) map.get("receiveDetail"));
            tvTaskId.setText(map.get("taskId").toString());
            //进行中的任务，点击我已完成，修改相应数据库
            Button button = convertView.findViewById(R.id.btn_finished);
            if (button != null) {//说明是进行中的任务，已完成的任务列表中没有此按钮
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //修改相应的任务为已完成
                        UpdateTaskStateTask updateTaskStateTask = new UpdateTaskStateTask(context, (int) map.get("taskId"));
                        updateTaskStateTask.execute();
                    }
                });
            }
            LinearLayout llItem = convertView.findViewById(R.id.ll_item);
            llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context,"根据不同的任务，跳转到不同的任务详情页",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, TaskDetailActivity.class);
                    intent.putExtra("taskId", (int) map.get("taskId"));
                    intent.putExtra("headImage", (String) map.get("receiveTouxiang"));
                    intent.putExtra("nickname", (String) map.get("receivePetName"));
                    intent.putExtra("coinCount", (Integer) map.get("receiveMoney"));
                    intent.putExtra("text", (String) map.get("receiveDetail"));
                    intent.putExtra("tag", (String) map.get("yaoQiu"));
                    intent.putExtra("sendTime", (String) map.get("sendTime"));
                    intent.putExtra("endTime", (String) map.get("endTime"));
                    intent.putExtra("sendArea", (String) map.get("sendArea"));
                    intent.putExtra("imgUrl", (String) map.get("imgUrl"));
                    intent.putExtra("isFromMySend","false");
                    context.startActivity(intent);
                }
            });
            return convertView;
        }
    }
}
