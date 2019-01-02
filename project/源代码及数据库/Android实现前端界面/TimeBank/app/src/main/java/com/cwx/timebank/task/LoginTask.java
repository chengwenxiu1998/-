package com.cwx.timebank.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cwx.timebank.MainActivity;

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

import static android.content.Context.MODE_PRIVATE;

public class LoginTask extends AsyncTask<String,Object,Boolean>{
    private TextView tvError;
    private Context context;
    private CheckBox cbRememberPwd;
    private JSONArray userJson;
    private JSONObject signInInfo;

    private String phoneNum;
    private String password;


    public LoginTask(TextView tvError,Context context,CheckBox cbRememberPwd){
        this.context = context;
        this.tvError = tvError;
        this.cbRememberPwd = cbRememberPwd;
    }

    @Override
    protected Boolean doInBackground(String[] strings) {
        phoneNum = strings[0];
        password = strings[1];
        Boolean isLoginSuccessful = false;
        //通过网络访问服务器端登录功能
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            String urlStr = serverUrl+"/LoginServlet?phoneNum="+phoneNum+"&password="+password;
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
                isLoginSuccessful = jsonObject.getBoolean("isLoginSuccessful");
                userJson = jsonObject.getJSONArray("user");
                signInInfo = jsonObject.getJSONObject("signInInfo");
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

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean == true){//登录成功
            SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isRememberPwd",cbRememberPwd.isChecked());//保存该用户是否勾选记住密码，true表示记住密码，false表示不记住密码
            try {
                editor.putInt("userId", userJson.getInt(0));
                editor.putString("uName", userJson.getString(1));
                editor.putString("uPhone",userJson.getString(2));
                editor.putString("uSex",userJson.getString(3));
                editor.putString("uArea",userJson.getString(4));
                editor.putString("uNickname",userJson.getString(5));
                SharedPreferences sp = context.getSharedPreferences("myServer", MODE_PRIVATE);
                editor.putString("uImage",sp.getString("serverUrl","")+userJson.getString(6));
                editor.putString("uPassword",userJson.getString(7));
                editor.putString("uIdCard",userJson.getString(8));
                editor.putInt("uCoin",userJson.getInt(9));

                editor.putInt("signDayCount",(Integer)signInInfo.get("signDayCount"));
                editor.putInt("ifSignIn",(Integer)signInInfo.get("ifSignIn"));
                editor.putInt("finishCount",(Integer)signInInfo.get("finishCount"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            editor.commit();

            SharedPreferences sp = context.getSharedPreferences("isFromMy",MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sp.edit();
            editor1.putBoolean("fromMy",true);
            editor1.commit();
            Intent intent = new Intent(context,MainActivity.class);
            context.startActivity(intent);
        }else{//登录失败
            tvError.setText("登录失败");
        }
    }
}
