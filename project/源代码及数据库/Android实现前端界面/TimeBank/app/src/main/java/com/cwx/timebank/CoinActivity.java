package com.cwx.timebank;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwx.timebank.task.FindCoinRecordTask;

public class CoinActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_record_layout);

        //返回箭头 返回上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoinActivity.this.finish();
            }
        });

        SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
        //设置 显示该用户的金币数
        TextView tvCoinCount = findViewById(R.id.tv_coin_count);
        tvCoinCount.setText(sp.getInt("uCoin",0)+"");

        AdapterView adapterView = findViewById(R.id.coin_record_list);

        FindCoinRecordTask findCoinRecordTask = new FindCoinRecordTask(getApplication(),sp.getInt("userId",0),adapterView);
        findCoinRecordTask.execute();
    }
}
