package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cwx.timebank.R;

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

public class FindCoinRecordTask extends AsyncTask<Object,Object,List<Map<String,Object>>>{
    private Context context;
    private int uid;
    private AdapterView adapterView;
    public FindCoinRecordTask(Context context,int uid,AdapterView adapterView){
        this.context = context;
        this.uid = uid;
        this.adapterView = adapterView;
    }
    @Override
    protected List<Map<String,Object>> doInBackground(Object[] objects) {
        List<Map<String,Object>> list = new ArrayList<>();
        //通过网络访问服务器端查询交易记录功能
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            String urlStr = serverUrl+"/FindCoinRecordServlet?uid="+uid;
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();

            //解析一个JSON格式的字符串
            try {
                JSONObject jsonObject = new JSONObject(res);
                JSONArray arr = (JSONArray)jsonObject.get("records");
               for(int i=0;i<arr.length();i++){
                    JSONObject object = (JSONObject)arr.get(i);
                    Map map = new HashMap();
                    String ctrFinishTime = object.get("ctrFinishTime").toString();
                    int ctrCount = (int)object.get("ctrCount");
                    String addOrReduce = object.get("addOrReduce").toString();
                    if(addOrReduce.equals("+")){//卖时间
                        map.put("type","卖时间");
                    }else{
                        map.put("type","买时间");
                    }

                    map.put("time",ctrFinishTime);
                   map.put("record",addOrReduce+ctrCount);
                    list.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<Map<String,Object>> list) {
        super.onPostExecute(list);
        //创建适配器，将数据源和每一项的视图联系起来
        CoinCustomAdapter adapter = new CoinCustomAdapter(context, R.layout.coin_record_item,list);
        //给AdapterView控件绑定adapter
        adapterView.setAdapter(adapter);
    }

    private class CoinCustomAdapter extends BaseAdapter {
        private Context context;
        private int itemView;
        private List<Map<String,Object>> data;
        public CoinCustomAdapter(Context context,int itemView,List<Map<String,Object>> data){
            this.context = context;
            this.itemView = itemView;
            this.data = data;
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemView,null);
            }
            TextView tvType = convertView.findViewById(R.id.tv_type);
            TextView tvTime = convertView.findViewById(R.id.tv_time);
            TextView tvRecord = convertView.findViewById(R.id.tv_record);

            Map<String,Object> itemData = data.get(position);

            tvType.setText(itemData.get("type").toString());
            tvTime.setText(itemData.get("time").toString());
            tvRecord.setText(itemData.get("record").toString());
            String tag = itemData.get("record").toString().substring(0,1);
            if(tag.equals("-")){
                tvRecord.setTextColor(0xffff0000);
            }

            return convertView;
        }
    }
}
