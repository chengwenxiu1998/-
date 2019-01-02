package com.cwx.timebank;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cwx.timebank.task.RealNameAuthenticationTask;
import com.cwx.timebank.task.ShowHeadImg;

public class AttestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attest);

        //设置透明度
        LinearLayout ll=findViewById(R.id.ll_shiming);
        ll.getBackground().setAlpha(153);

        final EditText etName=findViewById(R.id.et_name);
        etName.getBackground().setAlpha(128);

        final EditText etShenfenzheng=findViewById(R.id.et_shenfenzheng);
        etShenfenzheng.getBackground().setAlpha(128);

        //返回箭头 返回上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttestActivity.this.finish();
            }
        });

        //设置之前登录过的用户的头像
        CircleImageView civHeadImg = findViewById(R.id.civ_head_img);
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String imgUrl = sp.getString("uImage","");
        if(imgUrl!=null && !imgUrl.equals("")){
            new ShowHeadImg(civHeadImg,imgUrl).execute();
        }


        SharedPreferences sp2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final int uid = sp2.getInt("userId",0);

        //保存
        Button btnSave=findViewById(R.id.btn_save);
        btnSave.getBackground().setAlpha(153);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启一个异步任务，保存用户的认证信息
                String name = etName.getText().toString();
                String shenFenZheng = etShenfenzheng.getText().toString();
                if(name.equals("") || shenFenZheng.equals("") || name==null || shenFenZheng==null){
                    Toast.makeText(getApplication(),"请输入真是姓名和身份证号",Toast.LENGTH_LONG).show();
                }else{
                    RealNameAuthenticationTask realNameAuthenticationTast = new RealNameAuthenticationTask(getApplication(),uid,name,shenFenZheng);
                    realNameAuthenticationTast.execute();
                }
            }
        });

    }
}
