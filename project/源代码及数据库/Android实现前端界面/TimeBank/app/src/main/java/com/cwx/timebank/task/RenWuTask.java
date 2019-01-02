package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.cwx.timebank.bean.TaskBean;

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
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class RenWuTask extends AsyncTask {
    private Context context;
    private TaskBean taskBean;
    private JSONArray userJson;
    String month;
    String day;
    String hour;
    String min;

    public RenWuTask(Context context, TaskBean taskBean,String month,String day,String hour,String min) {
        this.context = context;
        this.taskBean = taskBean;
        this.month=month;
        this.day=day;
        this.hour=hour;
        this.min=min;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        Boolean isLoginSuccessful = false;
        //通过网络访问服务器端登录功能
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            String urlStr = serverUrl+"/InsertTaskServlet?uIdSend="+taskBean.getuIdSend()+"&tcId="+taskBean.getTcId()+
                    "&tDesc="+taskBean.gettDesc()+"&tCoinCount="+taskBean.gettCoinCount()+"&tState="+taskBean.gettState()
                    +"&uIdAccept="+taskBean.getuIdAccept()+"&tagId="+taskBean.getTagId()+"&tEndtimeMonth="+month+"&tEndtimeDay="+day
                    +"&tEndtimeHour="+hour+"&tEndtimeMin="+min
                    +"&t_imgurl="+taskBean.getT_imgurl();
            Log.e("test",urlStr);
            url = new URL(urlStr);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            Log.e("testconnection",connection+"");
            connection.setRequestProperty("contentType","utf-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            Log.e("test","如果给服务器端传的字符有中文，防止字符乱码问题");
            InputStream is = connection.getInputStream();
            Log.e("testis","aaaaaa");
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            Log.e("testinputStreamReader",inputStreamReader+"");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            Log.e("testreader",reader+"");
            String res = reader.readLine();
            Log.e("test+res",res+"");
            Log.e("test","开始解析一个JSON格式的字符串");
            //解析一个JSON格式的字符串
            try {
                JSONObject jsonObject = new JSONObject(res);
                TaskBean task=new TaskBean();

                task.settDesc(jsonObject.getString("tDesc"));
                task.setuIdAccept(jsonObject.getInt("uIdAccept"));
                task.settState(jsonObject.getString("tState"));
                task.settCoinCount(jsonObject.getInt("tCoinCount"));
                task.setTcId(jsonObject.getInt("tcId"));
                task.setTagId(jsonObject.getInt("tagId"));
                task.setuTime((Date)jsonObject.get("uTime"));
                task.setT_imgurl(jsonObject.getString("t_imgurl"));
                task.settEndtime((Date)jsonObject.get("tEndtime"));

                Log.e("RenwuTask",task.toString());
                isLoginSuccessful = jsonObject.getBoolean("isLoginSuccessful");
                userJson = jsonObject.getJSONArray("task");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return isLoginSuccessful;
    }
}
