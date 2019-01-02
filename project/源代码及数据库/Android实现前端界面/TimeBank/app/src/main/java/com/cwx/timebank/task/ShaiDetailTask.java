package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.cwx.timebank.R;
import com.cwx.timebank.ShaiDetailAdapter;
import com.cwx.timebank.bean.ShaiShaiCommentBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ShaiDetailTask extends AsyncTask<String,Void,List> {
    private Context context;
    private ListView listView;
    private int sid;

    public  ShaiDetailTask(Context context,ListView listView){
        this.context=context;
        this.listView=listView;
    }
    @Override
    protected List doInBackground(String... strings) {
        sid=Integer.valueOf(String.valueOf(strings[0]));
        List<ShaiShaiCommentBean> ShaiReplyList=new ArrayList<>();
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            URL url = new URL( serverUrl+"/ShaiReplyServlet?sid="+sid);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            JSONArray array=new JSONArray(res);
            for(int i=array.length()-1;i>=0;i--){
                JSONObject object = array.optJSONObject(i);
                ShaiShaiCommentBean shaiShaiCommentBean=new ShaiShaiCommentBean();
                shaiShaiCommentBean.setSid(object.getInt("sid"));
                shaiShaiCommentBean.setRid(object.getInt("rid"));
                shaiShaiCommentBean.setrNickName(object.getString("pickName"));
                shaiShaiCommentBean.setContent(object.getString("content"));
                String d=object.getString("time");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date=sdf.parse(d);
                shaiShaiCommentBean.setDate(date);
                ShaiReplyList.add(shaiShaiCommentBean);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("晒晒回复错误","错误1");
        }

        return ShaiReplyList;
    }
    protected void onPostExecute(List list) {
        if(list!=null &&list.size()!=0){
            ShaiDetailAdapter shaiDetailAdapter=new ShaiDetailAdapter(context, R.layout.shaishai_comment_item,list);
            listView.setAdapter(shaiDetailAdapter);
        }else{
            Log.e("晒晒回复错误","错误");
        }
    }
}

