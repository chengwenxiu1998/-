package com.cwx.timebank;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);

        //返回箭头 返回上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInActivity.this.finish();
            }
        });


        //登录时已将签到信息存入，此时从SharedPreferences中取出相关信息
        SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
        int signDayCount = sp.getInt("signDayCount",0);
        int ifSignIn = sp.getInt("ifSignIn",0);//今天是否签到
        int finishCount = sp.getInt("finishCount",0);

        TextView tvDay = findViewById(R.id.tv_day);
        TextView tvFinishCount = findViewById(R.id.tv_finish_count);
        tvDay.setText(signDayCount+"");
        tvFinishCount.setText(finishCount+"");


    }
}
