package com.cwx.timebank.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cwx.timebank.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;

public class FindPassword extends AsyncTask<String,Integer,Integer>{

    private Context context;

    public FindPassword(Context context){
       this.context = context;
    }

    @Override
    protected Integer doInBackground(String[] strings) {
        int insertRowCount = 0;
        //通过网络访问服务器端修改密码功能
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            String urlStr = serverUrl+"/FindPasswordServlet?phone="+strings[0]+"&password="+strings[1];
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
                insertRowCount = jsonObject.getInt("insertRowCount");
            } catch (JSONException e) {
                e.printStackTrace();
            }
         } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return insertRowCount;
    }

    @Override
    protected void onPostExecute(Integer i) {
        if(i!=0){//密码修改成功，跳转到登录页面
            Intent intent = new Intent(context,LoginActivity.class);
            context.startActivity(intent);
        }else{
            Toast.makeText(context,"密码修改失败，请重新输入",Toast.LENGTH_LONG).show();
        }

    }
}
