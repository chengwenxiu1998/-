package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwx.timebank.task.FindPhoneTask;
import com.cwx.timebank.task.LoginTask;
import com.cwx.timebank.task.ShowHeadImg;

public class LoginActivity extends AppCompatActivity {

    TextView tvError;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvError = findViewById(R.id.tv_error);

        //返回箭头 返回上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

        //设置之前登录过的用户的头像
        CircleImageView civHeadImg = findViewById(R.id.civ_head_img);
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String imgUrl = sp.getString("uImage","");
        if(imgUrl!=null && !imgUrl.equals("")){
            new ShowHeadImg(civHeadImg,imgUrl).execute();
        }

        //若上次用户选择了记住密码，则在相应位置显示手机号和密码
        boolean isRememberPwd = sp.getBoolean("isRememberPwd",false);
        if(isRememberPwd){
            EditText etPhone = findViewById(R.id.et_login_phone_number);
            EditText etPws = findViewById(R.id.et_login_password);
            etPhone.setText(sp.getString("uPhone",""));
            etPws.setText(sp.getString("uPassword",""));
        }


        //设置透明度
        mySetAlpha();

        //点击注册跳转到注册页面
        TextView tvRegister = findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        //登录功能
        login();

        //点击忘记密码，跳转到输入验证码找回密码界面
        TextView tvForgetPwd = findViewById(R.id.tv_forget_password);
        tvForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etPhoneNum = findViewById(R.id.et_login_phone_number);
                String phone = etPhoneNum.getText().toString();

                if(phone==null || phone.equals("")){
                    tvError.setText("请填入电话号码");
                }else{//启动一个异步任务，判断该手机号是否已注册，若注册，则转到发送验证码界面，若没注册，提醒用户该号码尚未注册
                    FindPhoneTask findPhoneTask = new FindPhoneTask(getApplication(),tvError);
                    findPhoneTask.execute(phone);
                }

            }
        });

    }

    //登录功能
    private void login(){
        Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText etPhoneNum = findViewById(R.id.et_login_phone_number);
                final EditText etPassword = findViewById(R.id.et_login_password);
                String phoneNum = etPhoneNum.getText().toString();
                String password = etPassword.getText().toString();
                if(phoneNum==null || password==null || phoneNum.equals("") || password.equals("")){
                    tvError.setText("用户名或密码不能为空");
                }else{
                    CheckBox cbRememberPwd = findViewById(R.id.cb_remember_password);
                    LoginTask loginTask = new LoginTask(tvError,getApplication(),cbRememberPwd);
                    loginTask.execute(phoneNum,password);
                }
            }
        });
    }

    //设置透明度
    private void mySetAlpha() {
        LinearLayout ll=findViewById(R.id.ll_login);
        ll.getBackground().setAlpha(153);

        EditText etPhoneNumber=findViewById(R.id.et_login_phone_number);
        etPhoneNumber.getBackground().setAlpha(128);

        EditText etPassword=findViewById(R.id.et_login_password);
        etPassword.getBackground().setAlpha(128);

        Button btnLogin=findViewById(R.id.btn_login);
        btnLogin.getBackground().setAlpha(153);
    }
}
