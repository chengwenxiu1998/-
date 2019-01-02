package com.cwx.timebank;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class WriteVerificationCode extends AppCompatActivity {
    EditText code;
    Button resultButton;
    String phone;//需要接受验证码的手机号码
    TextView regetTextviewCode;//再次获取验证码
    TextView log, timelog, phoneTextview;
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_verification_code);
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        initSMSSDK();
        SMSSDK.getVerificationCode("86", phone);//发送短信验证码到手机号
        initView();
        initData();
        timer.start();

    }

    private void initView() {
        regetTextviewCode =  findViewById(R.id.regetcode);
        back =  findViewById(R.id.write_back);
        code =  findViewById(R.id.verfication_code_edittext);
        resultButton =  findViewById(R.id.submit_verfication);
        phoneTextview =  findViewById(R.id.verfication_phone);
        log =  findViewById(R.id.write_log);
        timelog =  findViewById(R.id.submit_log);
    }

    private void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WriteVerificationCode.this.finish();
            }
        });
        phoneTextview.setText("+86  " + phone);
        log.setText(Html.fromHtml("我们已经发送<font color='#45C01A'>验证码</font>短信到这个号码:"));
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeString = code.getText().toString().trim();
                SMSSDK.submitVerificationCode("86", phone, codeString);
            }
        });

        regetTextviewCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "请求已发送", Toast.LENGTH_SHORT).show();
                SMSSDK.getVerificationCode("86", phone);//发送短信验证码到手机号
                timer.start();
            }
        });
    }


    /**
     * 使用计时器来限定验证码
     * 在发送验证码的过程 不可以再次申请获取验证码 在指定时间之后没有获取到验证码才能重新进行发送
     * 这里限定的时间是60s
     */
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timelog.setText(("接收短信大约需要" + millisUntilFinished / 1000) + "秒");
        }

        @Override
        public void onFinish() {
            regetTextviewCode.setEnabled(true);
            regetTextviewCode.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止使用短信验证 产生内存溢出问题
        SMSSDK.unregisterAllEventHandler();
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

    /**
     * 需要开启一个主线程来显示提示
     */

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            int code1 = msg.what;
            switch (code1) {
                case 0:
//                    Toast.makeText(getApplication(), "验证成功", Toast.LENGTH_SHORT).show();
                    //执行验证成功的操作
                    //跳转到修改密码页面
                    Intent intent = new Intent(getApplication(),FindPasswordActivity.class);
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                    break;
                case 1:
                    Toast.makeText(getApplication(), "您提交的验证码有误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    resultButton.setText("该号码为可信任号码");
                    Toast.makeText(getApplication(), "智能验证成功，即将为您跳转页面", Toast.LENGTH_SHORT).show();
                    showDialog(0);
                    break;
                case 3:
                    Toast.makeText(getApplication(), "验证码获取失败", Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
