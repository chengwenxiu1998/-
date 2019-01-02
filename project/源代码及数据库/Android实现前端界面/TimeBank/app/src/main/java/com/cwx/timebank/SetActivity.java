package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        //返回箭头 返回上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetActivity.this.finish();
            }
        });

        //在加载页面前，判断该用户是否已经实名认证
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String idCard = sp.getString("uIdCard",null);
        TextView tvRenzheng = findViewById(R.id.tv_renzheng);
        if(idCard !=null && !idCard.equals("")){//该用户已经实名认证
            tvRenzheng.setText("已认证");
            //该栏目不可点击，设置右边的右箭头不可见
            ImageView iv = findViewById(R.id.iv_arrow);
            iv.setVisibility(ImageView.INVISIBLE);
        }else{//未认证
            tvRenzheng.setText("未认证");
            //给该栏目添加点击事件
            LinearLayout llAttest=findViewById(R.id.ll_attest);
            llAttest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.setClass(SetActivity.this,AttestActivity.class);
                    startActivity(intent);
                }
            });
        }

        LinearLayout llHelp=findViewById(R.id.ll_help);
        llHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(SetActivity.this,HelpActivity.class);
                startActivity(intent);
            }
        });

        TextView tvNewMessageRemainder = findViewById(R.id.tv_new_message_remainder);
        tvNewMessageRemainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(SetActivity.this,NewMessageRemainderActivity.class);
                startActivity(intent);
            }
        });

        //聊天记录
        LinearLayout llTalkRecord = findViewById(R.id.ll_talk_record);
        llTalkRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(SetActivity.this,TalkRecordActivity.class);
                startActivity(intent);
            }
        });

        //个人信息
        TextView tvMyInfo = findViewById(R.id.tv_my_info);
        tvMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),UpdateMyInfo.class);
                startActivity(intent);
            }
        });

    }
}
