package com.cwx.timebank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cwx.timebank.task.ShowHeadImg;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button btnConnectionSeller = findViewById(R.id.btn_connectionseller);
        btnConnectionSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=DetailActivity.this.getSharedPreferences("userInfo", MODE_PRIVATE);
                if(sp.getInt("userId",0)!=0) {//若该用户已登录
                    Intent intent=new Intent(DetailActivity.this,ContactSellerActivity.class);
                    startActivity(intent);
                }else{//用户还没有登陆，跳转到登陆页面
                    Intent intent = new Intent(DetailActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        //获取intent中的数据
        final Intent intent=getIntent();
        String uImage=intent.getStringExtra("uImage");
        String nickname=intent.getStringExtra("nickname");
        String Time=intent.getStringExtra("Time");
        String taskTag=intent.getStringExtra("taskTag");
        String taskDetails=intent.getStringExtra("taskDetails");
        final int tId=intent.getIntExtra("tId",0);
        TextView petname=findViewById(R.id.tv_detail_petname);
        TextView time=findViewById(R.id.tv_detail_time);
        TextView tag=findViewById(R.id.tv_tag);
        TextView detail=findViewById(R.id.tv_detail);
        CircleImageView touxaing=findViewById(R.id.iv_touxiang);

        if(uImage!=null && !uImage.equals("")) {
            SharedPreferences sp = DetailActivity.this.getSharedPreferences("myServer", MODE_PRIVATE);
            new ShowHeadImg(touxaing,sp.getString("serverUrl", "") + uImage).execute();
        }

        petname.setText(nickname);
        time.setText(Time);
        tag.setText(taskTag);
        detail.setText(taskDetails);

        Button btnGetTask = findViewById(R.id.gettask);
        btnGetTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = DetailActivity.this.getSharedPreferences("userInfo", MODE_PRIVATE);
                int uId=sharedPreferences.getInt("userId",0);
                if(sharedPreferences.getInt("userId",0)!=0) {//若该用户已登录
                    HandleDatabaseTask handleDatabaseTask=new HandleDatabaseTask(DetailActivity.this);
                    handleDatabaseTask.execute(tId,uId);
                    Toast.makeText(DetailActivity.this,"领取任务成功",Toast.LENGTH_LONG).show();
                }else{//用户还没有登陆，跳转到登陆页面
                    Intent intent = new Intent(DetailActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
