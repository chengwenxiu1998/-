package com.cwx.timebank.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;

import com.cwx.timebank.WriteVerificationCode;

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

public class FindPhoneTask extends AsyncTask<String,Boolean,Boolean> {

    private String phone;
    private Context context;
    private TextView tvError;
    public FindPhoneTask(Context context,TextView tvError){
        this.context = context;
        this.tvError = tvError;
    }

    @Override
    protected Boolean doInBackground(String[] strings) {
        boolean isHavaThePhone = false;
        phone = strings[0];
        //通过网络访问服务器端修改密码功能
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            String urlStr = serverUrl+"/FindPhoneServlet?phone="+phone;
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
                isHavaThePhone = jsonObject.getBoolean("isHavaThePhone");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return isHavaThePhone;
    }

    @Override
    protected void onPostExecute(Boolean b) {
        if(b){//数据库中有该号码
            Intent intent = new Intent(context,WriteVerificationCode.class);
            intent.putExtra("phone",phone);
            context.startActivity(intent);
        }else{
            tvError.setText("该电话号码未注册，请注册或使用您已注册的号码");
        }

    }
}
