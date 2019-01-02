package com.cwx.timebank;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SendTalkActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private EditText editText;
    private String eContent;
    private int UserId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_talk_layout);

        //返回箭头 返回上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendTalkActivity.this.finish();
            }
        });
        sp=getSharedPreferences("userInfo",MODE_PRIVATE);
        UserId=sp.getInt("userId",0);
        Button btnSend=findViewById(R.id.btn_send);
        editText=findViewById(R.id.tv_label);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eContent=editText.getText().toString();
                SendTalkTask1 sendTalkTask=new SendTalkTask1(getApplication());
                sendTalkTask.execute(eContent,UserId);
            }
        });
    }

}
