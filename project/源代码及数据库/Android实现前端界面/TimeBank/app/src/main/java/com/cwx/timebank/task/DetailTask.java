package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.cwx.timebank.DetailAdapter;
import com.cwx.timebank.R;
import com.cwx.timebank.bean.DisussReplyBean;

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

public class DetailTask extends AsyncTask<String,Void,List> {
    private Context context;
    private ListView listView;
    private int did;
    public DetailTask(Context context,ListView listView){
        this.context=context;
        this.listView=listView;
    }
    @Override
    protected List doInBackground(String... strings) {
        did=Integer.parseInt(strings[0]);
        List<DisussReplyBean> replyList=new ArrayList<>();

        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            url = new URL(serverUrl+"/DisucssReplyServlet?did="+did);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            JSONArray array=new JSONArray(res);
            for(int i=array.length()-1;i>=0;i--){
                JSONObject object = array.optJSONObject(i);
                DisussReplyBean replyBean=new DisussReplyBean();
                replyBean.setDid(object.getInt("did"));
                replyBean.setRid(object.getInt("rid"));
                replyBean.setPetName(object.getString("pickName"));
                replyBean.setReplyContent(object.getString("cotent"));
                String d=object.getString("time");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date=sdf.parse(d);
                replyBean.setReplyTime(date);
                replyList.add(replyBean);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return replyList;
    }
    protected void onPostExecute(List list) {
        if(list!=null &&list.size()!=0){
            DetailAdapter detailAdapter=new DetailAdapter(context, R.layout.join_talk_item,list);
            listView.setAdapter(detailAdapter);
        }else{
            Log.e("晒晒错误","错误");
        }
    }
}

