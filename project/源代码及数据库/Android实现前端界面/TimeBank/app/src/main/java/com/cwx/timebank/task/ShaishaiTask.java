package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.cwx.timebank.R;
import com.cwx.timebank.ShaiAdapter;
import com.cwx.timebank.bean.ShaishaiBean;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ShaishaiTask extends AsyncTask<String,Void,List> {
    private Context context;
    private ListView listView;

    public ShaishaiTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected List doInBackground(String... strings) {
        List<ShaishaiBean> shaishaiList = new ArrayList<ShaishaiBean>();

        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            url = new URL(serverUrl+"/ShaishaiServlet");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("contentType", "UTF-8");
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            JSONArray array = new JSONArray(res);
            for (int i = array.length() - 1; i >= 0; i--) {
                JSONObject object = array.optJSONObject(i);
                ShaishaiBean shaishaiBean = new ShaishaiBean();
                shaishaiBean.setSid(object.getInt("sid"));
                shaishaiBean.setUid(object.getInt("uid"));
                shaishaiBean.setPetName(object.getString("petName"));
                shaishaiBean.setCount(object.getInt("scount"));
                shaishaiBean.setReply(object.getInt("reply"));
                shaishaiBean.setText(object.getString("stext"));
                //获取到JSON中的时间
                String d = object.getString("stime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(d);
                shaishaiBean.setTime(date);
                shaishaiList.add(shaishaiBean);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("错误", "false");
        }
        return shaishaiList;
    }

    protected void onPostExecute(List list) {
        if (list != null && list.size() != 0) {
            ShaiAdapter shaiAdapter = new ShaiAdapter(context, R.layout.list_view, list);
            listView.setAdapter(shaiAdapter);
        } else {
            Log.e("晒晒错误", "错误");
        }
    }
}
