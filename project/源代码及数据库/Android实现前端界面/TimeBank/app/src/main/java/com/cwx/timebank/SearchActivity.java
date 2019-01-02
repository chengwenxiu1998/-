package com.cwx.timebank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        //点击取消回到上一页面
        Button btnCancle=findViewById(R.id.btn_cancle);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //搜索框内容
        final EditText etSearch=findViewById(R.id.et_search_content);
        final Intent intent=getIntent();
        etSearch.setText(intent.getStringExtra("searchContent"));

        ListView lv=findViewById(R.id.lv_search);
        SearchListTask searchListTask=new SearchListTask(this,lv);
        searchListTask.execute(etSearch.getText().toString());
    }
}
