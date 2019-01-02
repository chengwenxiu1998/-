package com.cwx.timebank;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TalkRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showAlertDialog1();
        showAlertDialog2();
    }
    //显示一个最简单的对话框
    private void showAlertDialog1() {
        Button btnClear=findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、创建AlertDialog.Builder对话框构造器
                AlertDialog.Builder builder
                        = new AlertDialog.Builder(TalkRecordActivity.this);//专门完成AlertDialog创建
                //2、对构造器进行设置
                //builder.setTitle("这是提示信息");//标题
                builder.setMessage("清空消息列表后，聊天记录依然保留，确定要清空消息列表？");//信息主体
                //3、设置对话框按钮，并绑定相应监听器
                //positive积极
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TalkRecordActivity.this.finish();
                    }
                });
                //消极
                builder.setNegativeButton("取消", null);
                //4、使用构造器Builder创建一个AlertDialog对话框对象
                AlertDialog alertDialog=builder.create();
                //5、设置对话框不能被取消（不能点击其他地方被消失)
                alertDialog.setCancelable(false);
                // 6、显示对话框
                alertDialog.show();
            }
        });
    }

    private void showAlertDialog2() {
        Button btnDelete=findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、创建AlertDialog.Builder对话框构造器
                AlertDialog.Builder builder
                        = new AlertDialog.Builder(TalkRecordActivity.this);//专门完成AlertDialog创建
                //2、对构造器进行设置
                //builder.setTitle("这是提示信息");//标题
                builder.setMessage("将清空所有个人和群的聊天记录");//信息主体
                //3、设置对话框按钮，并绑定相应监听器
                //positive积极
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TalkRecordActivity.this.finish();
                    }
                });
                //消极
                builder.setNegativeButton("取消", null);
                //4、使用构造器Builder创建一个AlertDialog对话框对象
                AlertDialog alertDialog=builder.create();
                //5、设置对话框不能被取消（不能点击其他地方被消失)
                alertDialog.setCancelable(false);
                // 6、显示对话框
                alertDialog.show();
            }
        });
    }
}
