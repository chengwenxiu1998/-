package com.cwx.timebank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.Statement;

public class SellTimeActivity extends AppCompatActivity {
    private ListView lv = null;
    private static Connection conn;
    private  static Statement st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_time_listview_layout);
        ImageView imageView = findViewById(R.id.iv_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //卖时间列表信息
        lv = findViewById(R.id.lv_frame);
        SellTimeListTask sellTimeListTask = new SellTimeListTask(this, lv);
        sellTimeListTask.execute();
    }
}
