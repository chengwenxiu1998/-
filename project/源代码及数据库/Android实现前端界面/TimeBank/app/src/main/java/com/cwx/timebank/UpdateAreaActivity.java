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
import android.widget.Toast;

import com.cwx.timebank.task.UpdateAreaTask;

public class UpdateAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_area_layout);

        final SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        //点击返回箭头，返回到上一个页面
        LinearLayout llReturn = findViewById(R.id.ll_return);
        llReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateAreaActivity.this.finish();
            }
        });

        //设置要显示的地区
        final EditText etArea = findViewById(R.id.et_area);
        etArea.setText(sp.getString("uArea",""));

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newArea = etArea.getText().toString();
                if(newArea==null || newArea.equals("")){
                    Toast.makeText(getApplication(),"请输入地址",Toast.LENGTH_LONG).show();
                }else{//启动一个异步任务，连接数据库，修改用户的信息
                    UpdateAreaTask updateAreaTask = new UpdateAreaTask(getApplication());
                    String uid = sp.getInt("userId",0)+"";
                    updateAreaTask.execute(uid,newArea);

                }
            }
        });

    }
}
