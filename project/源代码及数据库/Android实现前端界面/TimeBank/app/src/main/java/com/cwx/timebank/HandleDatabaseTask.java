package com.cwx.timebank;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

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
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class HandleDatabaseTask extends AsyncTask{
    private Context context;
    private int tId;
    private int uId;

    public  HandleDatabaseTask(Context context){
        this.context=context;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        tId= (Integer) objects[0];
        uId=(Integer) objects[1];
        try {
            //通过网络访问服务器端
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            URL url=new URL(serverUrl+"/HandleDatabaseServlet?tid="+tId+"&&uId="+uId);
            //URL url = new URL("http://192.168.16.1:8080/TimeBank/HandleDatabaseServlet?tid="+tId+"&&uId="+uId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //传入的参数中有中文字符，防止乱码出现
            connection.setRequestProperty("contentType", "utf-8");
            //获取输入流
            InputStream in = connection.getInputStream();
            //字节流转换为字符流
            InputStreamReader inputStreamReader = new InputStreamReader(in);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            //解析JSON格式字符串
            if(res!=null) {
                JSONObject object = new JSONObject(res);
                BuyOrSellTime buyOrSellTime=new BuyOrSellTime();
                buyOrSellTime.settId(object.optInt("tId"));
                buyOrSellTime.setuId(object.optInt("uId"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dstr=object.optString("tAcceptTime");
                Date date= null;
                try {
                    date = sdf.parse(dstr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                buyOrSellTime.settAcceptTime(date);
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
