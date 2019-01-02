package com.cwx.timebank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwx.timebank.task.ShowHeadImg;

public class TaskDetailActivityLi extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetaili);

        Button btnConnectionSeller = findViewById(R.id.btn_connectionseller);
        btnConnectionSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = TaskDetailActivityLi.this.getSharedPreferences("userInfo", MODE_PRIVATE);
                if(sharedPreferences.getInt("userId",0)!=0) {//若该用户已登录
                    Intent intent = new Intent(TaskDetailActivityLi.this,ContactSellerActivity.class);
                    startActivity(intent);
                }else{//用户还没有登陆，跳转到登陆页面
                    Intent intent = new Intent(TaskDetailActivityLi.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        ImageView imgReturn=findViewById(R.id.iv_return);
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final Intent intent=getIntent();
        String uImage=intent.getStringExtra("uImage");
        String nickname=intent.getStringExtra("nickname");
        String putTime=intent.getStringExtra("tPutTime");
        String taskTag=intent.getStringExtra("taskTag");
        String taskDetails=intent.getStringExtra("taskDetails");
        String tEndtime=intent.getStringExtra("tEndtime");
        String tImageUrl=intent.getStringExtra("tImageUrl");
        String tCoinCount=intent.getStringExtra("tCoinCount");

        CircleImageView touxiang=findViewById(R.id.iv_touxiang);
        TextView petname=findViewById(R.id.tv_detail_petname);
        TextView time=findViewById(R.id.tv_put_time);
        TextView tag=findViewById(R.id.tv_tag);
        TextView detail=findViewById(R.id.tv_detail);
        TextView money=findViewById(R.id.tv_money);
        TextView endTime=findViewById(R.id.tv_end_time);
        ImageView imageView=findViewById(R.id.iv_put_image);

        if(uImage!=null && !uImage.equals("")) {
            SharedPreferences sp = TaskDetailActivityLi.this.getSharedPreferences("myServer", MODE_PRIVATE);
            new ShowHeadImg(touxiang, sp.getString("serverUrl", "") + uImage).execute();
        }
        if(tImageUrl!=null && !tImageUrl.equals("")) {
            SharedPreferences sp = TaskDetailActivityLi.this.getSharedPreferences("myServer", MODE_PRIVATE);
            new ShowHeadImg(imageView, sp.getString("serverUrl", "") +tImageUrl).execute();
        }

        petname.setText(nickname);
        time.setText(putTime);
        tag.setText(taskTag);
        detail.setText(taskDetails);
        money.setText(tCoinCount);
        endTime.setText(tEndtime);
    }
}
