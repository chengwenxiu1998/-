package com.cwx.timebank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwx.timebank.task.FindPassword;

public class FindPasswordActivity extends AppCompatActivity {
    private EditText etNewPwd;
    private EditText etSurePwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_reset_password_layout);

        etNewPwd = findViewById(R.id.et_new_password);
        etSurePwd = findViewById(R.id.et_sure_password);

        //点击屏幕空白处，使EditText失去焦点
        final LinearLayout linearLayout = findViewById(R.id.linearlayout);
        linearLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linearLayout.setFocusable(true);
                linearLayout.setFocusableInTouchMode(true);
                linearLayout.requestFocus();
                return false;
            }
        });


        //返回箭头 返回上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindPasswordActivity.this.finish();
            }
        });


        final Button btnSave = findViewById(R.id.btn_save);
        final TextView error = findViewById(R.id.tv_error);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pwdStr = etNewPwd.getText().toString();
                String surePwdStr = etSurePwd.getText().toString();
                if(pwdStr.equals(surePwdStr)){//两次输入的密码一致
                    Intent intent = getIntent();
                    String phone = intent.getStringExtra("phone");
                    String newPwd = etNewPwd.getText().toString();
                    FindPassword updatePassword = new FindPassword(getApplication());
                    updatePassword.execute(phone,newPwd);
                }else{
                    error.setText("两次输入的密码不一致，请重新输入");
                }

            }
        });

        //确认密码失去焦点时，判断两次输入的新密码是否一致
        etSurePwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){//当获得焦点时
                    error.setText("");
                    btnSave.setEnabled(true);
                }else{//当失去焦点时
                    String pwdStr = etNewPwd.getText().toString();
                    String surePwdStr = etSurePwd.getText().toString();
                    if(!pwdStr.equals(surePwdStr)){//若两次输入的密码不一致
                        error.setText("两次输入的密码不一致，请重新输入");
                    }
                }
            }
        });
    }
}
