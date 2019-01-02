package com.cwx.timebank;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cwx.timebank.task.FindPhoneTaskUpdatePhone;
import com.cwx.timebank.task.UpdatePhoneTask;

import cn.smssdk.SMSSDK;

public class UpdatePhoneActivity extends AppCompatActivity {
    private String phone;
    private Button timeButton;
    private Button btnSubmit;
    private EditText etPwd;
    private EditText etPhone;
    private TextView tvPwdError;
    private TextView tvPhoneError;
    private TextView tvOldPhone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_phone_layout);

        tvPhoneError = findViewById(R.id.tv_phone_error);
        tvPwdError = findViewById(R.id.tv_pwd_error);
        etPwd = findViewById(R.id.et_password);
        etPhone = findViewById(R.id.et_phone);
        timeButton = findViewById(R.id.btn_get_code);
        btnSubmit = findViewById(R.id.btn_save);
        final EditText etCode = findViewById(R.id.et_code);
        tvOldPhone = findViewById(R.id.tv_old_phone);

        //点击返回箭头，返回到上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePhoneActivity.this.finish();
            }
        });

        //设置显示原来绑定的手机号
        SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
        String oldPhone = sp.getString("uPhone","");
        if(oldPhone!=null && !oldPhone.equals("")){
            String oldPhone2 = oldPhone.substring(0,3)+"****"+oldPhone.substring(7,oldPhone.length());
            tvOldPhone.setText(oldPhone2);
        }

        //点击提交按钮
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeString = etCode.getText().toString().trim();
                SMSSDK.submitVerificationCode("86", phone, codeString);
            }
        });


        //new倒计时对象,总共的时间,每隔多少秒更新一次时间
        final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);

        //设置Button点击事件触发倒计时
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd = etPwd.getText().toString();
                phone = etPhone.getText().toString();

                if (pwd == null || pwd.equals("")) {
                    tvPwdError.setText("请输入密码");
                } else if (phone == null || phone.equals("")) {
                    tvPhoneError.setText("请输入新的手机号");
                } else {//判断该手机号是否已经被绑定，若没有，向该手机号发送验证码
                    //启动一个异步任务
                    FindPhoneTaskUpdatePhone findPhoneTaskUpdatePhone = new FindPhoneTaskUpdatePhone(getApplication(),tvPhoneError,myCountDownTimer,handler);
                    findPhoneTaskUpdatePhone.execute(phone);
                }
            }
        });
    }


    //倒计时函数
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            timeButton.setClickable(false);
            timeButton.setText(l/1000+"秒");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            timeButton.setText("重新获取");
            //设置可点击
            timeButton.setClickable(true);
        }

    }




    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            int code1 = msg.what;
            switch (code1) {
                case 0:
                    Toast.makeText(getApplication(), "验证成功", Toast.LENGTH_SHORT).show();
                    //判断输入的原密码是否正确
                    //修改数据库中该用户绑定的手机号（此处不用再判断数据库中是否有该手机号，因为获取验证码时已经判断）
                    String pwd1 = etPwd.getText().toString();
                    SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
                    String pwd2 = sp.getString("uPassword","");
                    int uid = sp.getInt("userId",0);
                    if(pwd1.equals(pwd2)){//用户输入的密码正确
                        UpdatePhoneTask updatePhoneTask = new UpdatePhoneTask(getApplication(),uid,phone);
                        updatePhoneTask.execute(phone);
                    }else{//用户输入的密码错误
                        tvPwdError.setText("密码输入错误");
                    }

                    break;
                case 1:
                    Toast.makeText(getApplication(), "您提交的验证码有误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
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