package com.cwx.timebank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FindActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_layout);

        //点击取消回到上一页
        Button btnCancle=findViewById(R.id.btn_cancle);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //搜索框内容
        final EditText etSearch=findViewById(R.id.et_search_content);

        //点击不同标签显示不同内容
        final TextView tvBuyMeal=findViewById(R.id.tv_buy_meal);
        final TextView tvPickExpress=findViewById(R.id.tv_pick_express);
        final TextView tvArrangeSport=findViewById(R.id.tv_arrange_sport);
        final TextView tvArrangeStudy=findViewById(R.id.tv_arrange_study);
        final TextView tvDrawWater=findViewById(R.id.tv_draw_water);
        final TextView tvShopping=findViewById(R.id.tv_shopping);
        final TextView tvOther=findViewById(R.id.tv_other);

        tvBuyMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent=tvBuyMeal.getText().subSequence(1,4).toString();
                etSearch.setText(searchContent);
                Intent intent=new Intent();
                intent.setClass(FindActivity.this,SearchActivity.class);
                intent.putExtra("searchContent",searchContent);
                startActivity(intent);
            }
        });

        tvPickExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent=tvPickExpress.getText().subSequence(1,5).toString();
                etSearch.setText(searchContent);
                Intent intent=new Intent();
                intent.setClass(FindActivity.this,SearchActivity.class);
                intent.putExtra("searchContent",searchContent);
                startActivity(intent);
            }
        });

        tvArrangeSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent=tvArrangeSport.getText().subSequence(1,4).toString();
                etSearch.setText(searchContent);
                Intent intent=new Intent();
                intent.setClass(FindActivity.this,SearchActivity.class);
                intent.putExtra("searchContent",searchContent);
                startActivity(intent);
            }
        });

        tvArrangeStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent=tvArrangeStudy.getText().subSequence(1,4).toString();
                etSearch.setText(searchContent);
                Intent intent=new Intent();
                intent.setClass(FindActivity.this,SearchActivity.class);
                intent.putExtra("searchContent",searchContent);
                startActivity(intent);
            }
        });

        tvDrawWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent=tvDrawWater.getText().subSequence(1,4).toString();
                etSearch.setText(searchContent);
                Intent intent=new Intent();
                intent.setClass(FindActivity.this,SearchActivity.class);
                intent.putExtra("searchContent",searchContent);
                startActivity(intent);
            }
        });

        tvShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent=tvShopping.getText().subSequence(1,4).toString();
                etSearch.setText(searchContent);
                Intent intent=new Intent();
                intent.setClass(FindActivity.this,SearchActivity.class);
                intent.putExtra("searchContent",searchContent);
                startActivity(intent);
            }
        });

        tvOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent=tvOther.getText().subSequence(1,3).toString();
                etSearch.setText(searchContent);
                Intent intent=new Intent();
                intent.setClass(FindActivity.this,SearchActivity.class);
                intent.putExtra("searchContent",searchContent);
                startActivity(intent);
            }
        });

    }
}
