package com.cwx.timebank.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cwx.timebank.ReceiveActivity;

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

public class UpdateTaskStateTask extends AsyncTask<Object,Object,Integer> {
    private Context context;
    private int taskId;

    public UpdateTaskStateTask(Context context, int taskId){
        this.context = context;
        this.taskId = taskId;
    }
    @Override
    protected Integer doInBackground(Object[] objects) {
        int updateRowCount=0;
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl", "");
            String urlStr = serverUrl + "/UpdateTaskStateServlet?tid=" +taskId;
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("contentType", "UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();

            //解析一个JSON格式的字符串
            try {
                JSONObject jsonObject = new JSONObject(res);
                updateRowCount = jsonObject.getInt("updateRowCount");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updateRowCount;
    }

    @Override
    protected void onPostExecute(Integer o) {
        super.onPostExecute(o);
        if(o!=0){//修改成功
            Toast.makeText(context,"任务已成功完成",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context,ReceiveActivity.class);
            context.startActivity(intent);
        }else{//修改失败
            Toast.makeText(context,"系统抖了一下，请稍后再试",Toast.LENGTH_LONG).show();
        }
    }
}
