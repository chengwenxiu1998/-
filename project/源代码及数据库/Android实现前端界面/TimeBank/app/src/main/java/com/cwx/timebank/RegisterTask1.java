package com.cwx.timebank;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterTask1 extends AsyncTask {
    private String sPhone;
    private TextView textView;
    public RegisterTask1(TextView textView){
        this.textView=textView;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        sPhone=(String)objects[0];
        try {
            URL url=new URL("http://10.7.88.239:8080/TimeBank/RegisterServlet?phone="+sPhone);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            JSONObject object=new JSONObject(res);
            String flag=object.getString("fructify");
            if(flag.equals("false")){
                textView.setText("该手机号已经被注册");
            }else{
                textView.setText("");
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

