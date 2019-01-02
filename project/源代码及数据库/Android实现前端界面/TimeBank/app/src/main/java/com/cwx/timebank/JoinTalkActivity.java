package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cwx.timebank.task.DetailTask;
import com.cwx.timebank.task.DiscussReplyTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinTalkActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private Button button;
    private EditText editText;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talk_listview_layout);
        final Intent intent=getIntent();
        final String string1=intent.getStringExtra("netName");
        final String string2=intent.getStringExtra("tag");
        final String string3=intent.getStringExtra("content");
        final String did=intent.getStringExtra("which");


        //谁发表的话题
        textView1=findViewById(R.id.put);
        textView1.setText(string1+"发表的话题");
        textView2=findViewById(R.id.tagc);
        textView2.setText(string2);
        textView3=findViewById(R.id.topic);
        textView3.setText(string3);
        ListView listView=findViewById(R.id.lv_frame);
        DetailTask detailTask=new DetailTask(getApplication(),listView);
        detailTask.execute(did);
        editText=findViewById(R.id.reply);
        button=findViewById(R.id.reply_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp=getSharedPreferences("userInfo",Context.MODE_PRIVATE);

                if(sp.getInt("userId",0)!=0){//用户已经登录
                    //回复的内容
                    String reply=editText.getText().toString();
                    //回复人的ID
                    int uid=sp.getInt("userId",0);
                    //回复人的昵称
                    String name=sp.getString("uNickname","");
                    //回复的时间
                    Date date=new Date();
                    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time=format.format(date);
                    Log.e("回复时间",time);
                    //讨论的id
                    int did1=Integer.parseInt(did);
                    Log.e("谈论的id",""+did1);

                    editText.setText("");

                    DiscussReplyTask discussReplyTask=new DiscussReplyTask(getApplication());
                    discussReplyTask.execute(did1,uid,reply,time);


                    Intent intent2=new Intent();
                    intent2.setClass(JoinTalkActivity.this,JoinTalkActivity.class);
                    intent2.putExtra("netName",string1);
                    intent2.putExtra("tag",string2);
                    intent2.putExtra("content",string3);
                    intent2.putExtra("which",""+did1);
                    startActivity(intent2);


                }else{
                    Intent intent1=new Intent();
                    intent1.setClass(JoinTalkActivity.this,LoginActivity.class);
                    startActivity(intent1);
                }

            }
        });
    }


}
