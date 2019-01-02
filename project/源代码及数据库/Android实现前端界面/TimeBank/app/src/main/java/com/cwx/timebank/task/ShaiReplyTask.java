package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
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

import static android.content.Context.MODE_PRIVATE;

public class ShaiReplyTask extends AsyncTask {
    private Context context;
    private int sid;
    private int uid;
    private String content;
    private String replyTime;
    public ShaiReplyTask( Context context){
        this.context = context;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        sid=Integer.valueOf(String.valueOf(objects[0]));
        uid=Integer.valueOf(String.valueOf(objects[1]));
        content=(String)objects[2];
        replyTime=(String)objects[3];
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            URL url=new URL(serverUrl+"/AddCommentServlet?Sid="+sid+"&content="+content+"&reply="+replyTime+"&uid="+uid);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            JSONObject object=new JSONObject(res);
            Log.e("结果",object.getString("lasty"));
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

