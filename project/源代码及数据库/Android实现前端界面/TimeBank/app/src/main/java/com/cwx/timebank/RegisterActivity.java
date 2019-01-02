package com.cwx.timebank;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;

;

public class RegisterActivity extends AppCompatActivity {
    private EditText petName;//昵称
    private EditText phone;//手机号
    private EditText password;//密码
    private EditText repassword;//确认密码
    private TextView gain;//获得验证码
    private EditText writer;//写验证码
    private CheckBox checkBox;//是否选中
    private TextView textView2;
    private String UpetName=null;
    private String Uphone=null;
    private String Upassword=null;
    private String Urepassword=null;
    private  Button btnRegister;
//    private String AppKey = "2941e0eae5638";
//    private String APPSECRET = "51bf20e8b8311f93858195d8b2a000eb";

    private String CodeText;
    private String Phone;



    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Toast.makeText(getApplicationContext(), "提交验证码成功",
                            Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);
                    myCountDownTimer.start();
                    // 已经验证
//                    Toast.makeText(getApplicationContext(), "验证码已经发送",
//                            Toast.LENGTH_SHORT).show();
                }
            } else {
                int status = 0;
                try {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;

                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    status = object.optInt("status");
                    if (!TextUtils.isEmpty(des)) {
                        Toast.makeText(RegisterActivity.this, des,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    SMSLog.getInstance().w(e);
                }
            }
        }

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //设置透明度
        LinearLayout ll=findViewById(R.id.ll_register);
        ll.getBackground().setAlpha(153);

        petName=findViewById(R.id.et_petname);
        petName.getBackground().setAlpha(128);
        petName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    UpetName=petName.getText().toString();
                    if(TextUtils.isEmpty(UpetName)) {
                        Toast.makeText(RegisterActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        phone=findViewById(R.id.et_phone_number);
        phone.getBackground().setAlpha(128);
        textView2=findViewById(R.id.tv_title2);
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Uphone=phone.getText().toString();
                    if(TextUtils.isEmpty(Uphone)){
                        Toast.makeText(RegisterActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                    }else{
                        RegisterTask1 registerTask1 = new RegisterTask1(textView2);
                        registerTask1.execute(Uphone);
                    }

                }


            }
        });

        password=findViewById(R.id.et_password);
        password.getBackground().setAlpha(128);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Upassword=password.getText().toString();
                    if(TextUtils.isEmpty(Upassword)){
                        Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        repassword=findViewById(R.id.et_sure_password);
        repassword.getBackground().setAlpha(128);
        repassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Urepassword=repassword.getText().toString();
                    if(TextUtils.isEmpty(Urepassword)){
                        Toast.makeText(RegisterActivity.this,"确认密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                    if(!Urepassword.equals(Upassword)){
                        Toast.makeText(RegisterActivity.this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        init();
        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);


        EditText etMessageCaptcha=findViewById(R.id.et_message_captcha);
        etMessageCaptcha.getBackground().setAlpha(128);

        checkBox=findViewById(R.id.check);


        btnRegister=findViewById(R.id.btn_register);
        btnRegister.getBackground().setAlpha(153);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(UpetName==null||Uphone==null||Upassword==null||Urepassword==null){
                    Toast.makeText(RegisterActivity.this,"不能为空",Toast.LENGTH_SHORT).show();

                }else if(!checkBox.isChecked()){
                    Toast.makeText(RegisterActivity.this,"未同意协议",Toast.LENGTH_SHORT).show();
                }else{
                    RegisterTask task=new RegisterTask(getApplication());
                    task.execute(UpetName,Uphone,Upassword);
                }
            }

        });


    }

    private void init() {
        gain=findViewById(R.id.tv_click);
        writer=findViewById(R.id.et_message_captcha);
        gain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(Uphone)) {
                    getContentResolver().registerContentObserver(
                            Uri.parse("content://sms"), true,
                            new SmsObserver(new Handler()));
                    SMSSDK.getVerificationCode("86", Uphone);
                    Phone=Uphone;

                } else {
                    Toast.makeText(RegisterActivity.this, "电话号码不能为空", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    private class SmsObserver extends ContentObserver {

        public SmsObserver(Handler handler) {
            super(handler);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onChange(boolean selfChange) {
            // TODO Auto-generated method stub
            StringBuilder sb = new StringBuilder();
            Cursor cursor = getContentResolver().query(
                    Uri.parse("content://sms/inbox"), null, null, null, null);
            cursor.moveToNext();
            sb.append("body=" + cursor.getString(cursor.getColumnIndex("body")));

            System.out.println(sb.toString());
            Pattern pattern = Pattern.compile("[^0-9]");
            Matcher matcher = pattern.matcher(sb.toString());
            CodeText = matcher.replaceAll("");
            writer.setText(CodeText);
            cursor.close();
            super.onChange(selfChange);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
        getContentResolver().unregisterContentObserver(new SmsObserver(handler));
    };

    //倒计时函数
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
//            btnRegister.setClickable(false);
            gain.setText(l/1000+"秒");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            gain.setText("重新获取");
            //设置可点击
//            btnRegister.setClickable(true);
        }

    }


}
