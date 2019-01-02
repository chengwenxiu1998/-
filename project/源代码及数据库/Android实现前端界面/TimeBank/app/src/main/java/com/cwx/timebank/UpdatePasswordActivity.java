package com.cwx.timebank;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cwx.timebank.task.ShowHeadImg;
import com.cwx.timebank.task.UpdatePasswordTask;

public class UpdatePasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_password_layout);

        //设置该界面要显示的头像
        CircleImageView civHeadImg = findViewById(R.id.civ_head_img);
        final SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String imgUrl = sp.getString("uImage","");
        if(imgUrl!=null && !imgUrl.equals("")){
            new ShowHeadImg(civHeadImg,imgUrl).execute();
        }

        //设置透明度
        LinearLayout ll=findViewById(R.id.ll_xiugaimima);
        ll.getBackground().setAlpha(153);

        EditText etOrginalPassword=findViewById(R.id.et_orginal_password);
        etOrginalPassword.getBackground().setAlpha(128);

        EditText etNewPassword=findViewById(R.id.et_new_password);
        etNewPassword.getBackground().setAlpha(128);

        EditText etSurePassword=findViewById(R.id.et_sure_password);
        etSurePassword.getBackground().setAlpha(128);

        Button btnSave=findViewById(R.id.btn_save);
        btnSave.getBackground().setAlpha(153);


        final EditText etOldPwd = findViewById(R.id.et_orginal_password);
        final EditText etNewPwd = findViewById(R.id.et_new_password);
        final EditText etSureNewPwd = findViewById(R.id.et_sure_password);
        final TextView tvError = findViewById(R.id.tv_error);
        //点击保存按钮，检测新旧密码 修改密码
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPwd = etOldPwd.getText().toString();
                String newPwd = etNewPwd.getText().toString();
                String surePwd = etSureNewPwd.getText().toString();
                if(oldPwd.equals(sp.getString("uPassword",""))){//用户输入的原密码正确
                    if(newPwd.equals(surePwd)){//两次密码输入一致
                        //启动修改密码的异步任务，进行密码修改
                        UpdatePasswordTask updatePasswordTask = new UpdatePasswordTask(getApplication());
                        updatePasswordTask.execute(sp.getInt("userId",0)+"",newPwd);

                    }else{
                        tvError.setText("两次密码输入不一致，请重新输入");
                    }
                }else{//用户输入的原密码错误
                    Toast.makeText(getApplication(),"原密码输入错误",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
