package com.cwx.timebank;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IndexListTask extends AsyncTask<String,Void,List>{
    private Context mContext=null;
    private ListView lv=null;

    public IndexListTask(Context mContext,ListView lv){
        this.mContext=mContext;
        this.lv=lv;
    }
    @Override
    protected List doInBackground(String... strings) {
        List<BuyOrSellTime> tasksList=new ArrayList();
        try {
            //网络访问服务器端
           // URL url = new URL("http://10.7.85.146:8080/TimeBank/IndexServlet");
            URL url = new URL("http://10.7.88.246:8080/TimeBank/IndexServlet");
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            //传入的参数中有中文字符，防止乱码出现
            connection.setRequestProperty("contentType","utf-8");
            //获取输入流
            InputStream in=connection.getInputStream();
            //字节流转换为字符流
            InputStreamReader inputStreamReader=new InputStreamReader(in);//转换流
            BufferedReader reader=new BufferedReader(inputStreamReader);
            String res=reader.readLine();
            //解析JSONArray字符串
            JSONArray array=new JSONArray(res);
            Log.e("12","程文秀"+array.length());
            for(int i=0;i<array.length();++i){
                JSONObject object=array.optJSONObject(i);
                BuyOrSellTime buyOrSellTime=new BuyOrSellTime();
                buyOrSellTime.setuNickName(object.optString("uNickName"));
                //sellTime.setuImage(object.optString("uImage"));
                buyOrSellTime.setTagText(object.optString("tagText"));

                //获取到JSON中的时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dstr=object.optString("uTime");
                String dstr2=object.optString("tEndtime");
                Date date= null;
                Date date2= null;
                try {
                    date = sdf.parse(dstr);
                    date2=sdf.parse(dstr2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                buyOrSellTime.setuTime(date);
                buyOrSellTime.settEndtime(date2);

                buyOrSellTime.settDesc(object.optString("tDesc"));
                buyOrSellTime.settCoinCount(object.getInt("tCoinCount"));
                buyOrSellTime.settId(object.getInt("tId"));
                buyOrSellTime.setuIdAccept(object.getInt("uIdAccept"));
                buyOrSellTime.setTcId(object.getInt("tcId"));
                buyOrSellTime.setTagText(object.optString("tagText"));
                tasksList.add(buyOrSellTime);
            }
            Log.e("TasksList",tasksList.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tasksList;
    }

    @Override
    protected void onPostExecute(List list) {
        if(list!=null &&list.size()!=0){
           IndexCustomAdapter2 adapter=new IndexCustomAdapter2(mContext,list,lv);
           lv.setAdapter(adapter);

        }else{
            Toast.makeText(mContext,"数据加载失败", Toast.LENGTH_SHORT).show();
        }
    }
}
