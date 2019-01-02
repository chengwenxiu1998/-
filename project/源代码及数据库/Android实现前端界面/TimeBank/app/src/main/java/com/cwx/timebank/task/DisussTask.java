package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.cwx.timebank.CustomAdapter;
import com.cwx.timebank.R;
import com.cwx.timebank.bean.DiscussBean;

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
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DisussTask extends AsyncTask<String,Void,List> {
    private Context context;
    private ListView lv;

    public DisussTask(Context context,ListView lv){
        this.context=context;
        this.lv=lv;
    }

    protected List doInBackground(String... strings) {
        List<DiscussBean> discussList=new ArrayList<>();
        URL url= null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            url = new URL( serverUrl+"/DisucssServlet");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            JSONArray array=new JSONArray(res);
            for(int i=array.length()-1;i>=0;i--){
                JSONObject object=array.optJSONObject(i);
                DiscussBean discussBean=new DiscussBean();
                discussBean.setDId(object.getInt("dId"));
                discussBean.setTID(object.getInt("tId"));
                discussBean.setUId(object.getInt("uId"));
                discussBean.setContent(object.getString("content"));
                discussBean.setTcontent(object.getString("tContent"));
                discussBean.setPetName(object.getString("petName"));
                discussList.add(discussBean);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return discussList;
    }

    protected void onPostExecute(List list) {
        if(list!=null &&list.size()!=0){
            CustomAdapter customAdapter=new CustomAdapter(context, R.layout.talk_item,list);
            lv.setAdapter(customAdapter);
        }else{
            Log.e("讨论错误","错误");
        }
    }
}

