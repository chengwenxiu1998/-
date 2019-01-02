package com.cwx.timebank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


public class BuyTimeActivity extends AppCompatActivity {
    private ListView lv=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_time_listview_layout);
        ImageView imageView=findViewById(R.id.return_shouye);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //从Java中读取买时间列表信息
        lv=findViewById(R.id.lv_frame);
        BuyTimeListTask buyTimeListTask=new BuyTimeListTask(this,lv);
        buyTimeListTask.execute();
    }


}
