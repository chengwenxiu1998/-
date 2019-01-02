package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
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
import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static android.content.Context.MODE_PRIVATE;

public class FindPhoneTaskUpdatePhone extends AsyncTask<String,Boolean,Boolean> {

    private String phone;
    private Context context;
    private TextView tvPhoneError;
    private CountDownTimer myCountDownTimer;
    private Handler handler;

    public FindPhoneTaskUpdatePhone(Context context, TextView tvPhoneError,CountDownTimer myCountDownTimer,Handler handler){
        this.context = context;
        this.tvPhoneError = tvPhoneError;
        this.myCountDownTimer = myCountDownTimer;
        this.handler = handler;
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
            tvPhoneError.setText("该电话号码已被绑定，请重新换一个");
        }else{//数据库中无该号码
            myCountDownTimer.start();
            initSMSSDK();
            SMSSDK.getVerificationCode("86", phone);//发送短信验证码到手机号
        }

    }

    private void initSMSSDK() {
        //初始化短信验证
        //SMSSDK.initSDK(this, APPKEY, APPSECRET);
        //注册短信回调
        SMSSDK.registerEventHandler(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交成功

                    try {
                        if (data != null) {
                            HashMap<String, Object> hashMap = (HashMap) data;//提交验证码之后得到返回的数据（返回的数据是手机，和国家代码）
                            String getphone = (String) hashMap.get("phone");
                            if (getphone.equals(phone)) {
                                Message message = new Message();//验证成功
                                message.what = 0;
                                handler.sendMessage(message);
                            }
                        } else {
                            Message message = new Message();//提交的验证码错误
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    } catch (Exception e) {
                        Message message = new Message();//提交的验证码错误
                        message.what = 1;
                        handler.sendMessage(message);
                    }

                    //  startActivity(new Intent(WriteVerificationCode.this,ModifyPassword.class));
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        boolean smart = (Boolean) data;
                        if (smart) {
                            Message message = new Message();//智能验证成功
                            message.what = 2;
                            handler.sendMessage(message);
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    //  startActivity(new Intent(WriteVerificationCode.this,ModifyPassword.class));
                                    //WriteVerificationCode.this.finish();
                                }
                            }, 2000);


                        } else {

                        }
                    }
                } else {
                    Message message = new Message();//得到验证码错误
                    message.what = 3;
                    handler.sendMessage(message);
                }


            }
        });
    }
}
