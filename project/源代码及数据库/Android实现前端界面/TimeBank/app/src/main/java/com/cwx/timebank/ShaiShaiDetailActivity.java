package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cwx.timebank.task.ShaiDetailTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShaiShaiDetailActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shaishai_detail_layout);
        Intent intent = getIntent();
        String string1 = intent.getStringExtra("netName");
        String string2 = intent.getStringExtra("content");
        String string3 = intent.getStringExtra("which");
        textView1 = findViewById(R.id.put_name);
        textView2 = findViewById(R.id.put_content);
        textView1.setText(string1);
        textView2.setText(string2);
        ListView listView = findViewById(R.id.lv_frame);
        ShaiDetailTask shaiDetailTask = new ShaiDetailTask(getApplication(), listView);
        shaiDetailTask.execute(string3);
    }


}
