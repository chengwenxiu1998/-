package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendTalkTask extends AsyncTask {
    private Context context;

    public SendTalkTask(Context context){
        this.context=context;
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        URL url= null;
        try {
            url = new URL("http://10.7.88.239:8080/TimeBank/SendTalkServlet");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            JSONObject object=new JSONObject(res);
            String lastO=object.getString("last");
            Log.e("结果",lastO);
            if(lastO.equals("flase")){
                Intent intent=new Intent();
                intent.setClass(context,LoginActivity.class);
                context.startActivity(intent);
            }else{
                Intent intent=new Intent();
                intent.setClass(context,SendTalkActivity.class);
                context.startActivity(intent);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
