package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwx.timebank.task.ShowHeadImg;



public class TaskDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetail);

        //返回箭头 返回上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskDetailActivity.this.finish();
            }
        });

        //联系发布任务者
        Button btnConnectionSeller = findViewById(R.id.btn_connectionseller);
        btnConnectionSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskDetailActivity.this,ContactSellerActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        //根据从我发送的还是我接收的联系 发送人、接收人（设置该按钮上写的字）
        if(intent.getStringExtra("isFromMySend").equals("true")){
            btnConnectionSeller.setText("联系接收人");
        }else{
            btnConnectionSeller.setText("联系发布人");
        }

        //启动一个异步任务类，查找并设置该页面显示的内容
        CircleImageView civHeadImage = findViewById(R.id.civ_head_img);
        TextView tvNickname = findViewById(R.id.tv_nickname);
        TextView tvCoinCount = findViewById(R.id.tv_coin_count);
        TextView tvSendTime = findViewById(R.id.tv_send_time);
        TextView tvStopTime = findViewById(R.id.tv_end_time);
        TextView tvSendArea = findViewById(R.id.tv_send_area);
        TextView tvTag = findViewById(R.id.tv_tag);
        TextView tvText = findViewById(R.id.tv_text);
        ImageView ivImg = findViewById(R.id.iv_img);

        intent.getStringExtra("taskId");
        String headImg = intent.getStringExtra("headImage");
        String nickname = intent.getStringExtra("nickname");
        int coinCount = intent.getIntExtra("coinCount",0);
        String text = intent.getStringExtra("text");
        String tag = intent.getStringExtra("tag");
        String sendTime = intent.getStringExtra("sendTime");
        String stopTime = intent.getStringExtra("endTime");
        String sendArea = intent.getStringExtra("sendArea");
        String imgUrl = intent.getStringExtra("imgUrl");
        //Log.e("12","啦啦啦"+coinCount);

        SharedPreferences sp = getSharedPreferences("myServer", Context.MODE_PRIVATE);

        if(headImg!=null && !headImg.equals("")){
            new ShowHeadImg(civHeadImage,sp.getString("serverUrl","")+headImg).execute();
        }

        tvNickname.setText(nickname);
        tvCoinCount.setText(coinCount+"");
        tvSendTime.setText("发布时间："+sendTime);
        tvStopTime.setText("结束时间："+stopTime);
        tvSendArea.setText("发布地点："+sendArea);
        tvTag.setText("#"+tag+"#");
        tvText.setText(text);
        if(imgUrl!=null && !imgUrl.equals("")){
            new ShowHeadImg(ivImg,sp.getString("serverUrl","")+imgUrl).execute();
        }
    }
}
