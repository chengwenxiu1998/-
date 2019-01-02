package com.cwx.timebank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import java.util.ArrayList;
import java.util.List;


public class BannerActivity extends Fragment{

    RelativeLayout relativeLayout;
    //当创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_layout2, container, false);

        relativeLayout = view.findViewById(R.id.relativeLayout);


//        RecyclerViewHeader header = view.findViewById(R.id.header);
//        RecyclerView rv = view.findViewById(R.id.recyclerView);
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        //设置为垂直布局，这也是默认的
//        layoutManager.setOrientation(OrientationHelper.VERTICAL);
//        //设置布局管理器
//        rv.setLayoutManager(layoutManager);

        ListView listView=view.findViewById(R.id.lv);

        IndexListTask indexListTask = new IndexListTask(getContext(),listView);
        indexListTask.execute();





        //header.attachTo(rv, true);

//        ListView lv=view.findViewById(R.id.lv);


        return view;
    }



    //当View创建完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onCreate(savedInstanceState);

        //添加SlideShowView

        SlideShowView slideShowView = new SlideShowView(getContext());

        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        slideShowView.setLayoutParams(params1);
        relativeLayout.addView(slideShowView);

        //获取我要卖时间TextView进行跳转
        TextView tvIWillSellTime = view.findViewById(R.id.tv_i_will_sell_time);
        tvIWillSellTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SellTimeActivity.class);
                startActivity(intent);
            }
        });

        //获取我要买时间TextView进行跳转
        TextView tvIWillBuyTime = view.findViewById(R.id.tv_i_will_buy_time);
        tvIWillBuyTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),BuyTimeActivity.class);
                startActivity(intent);
            }
        });

        //获取任务中的一项，点击跳转到详情页
//        LinearLayout linearLayout = view.findViewById(R.id.ll_task_list_element);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"根据不同任务跳转到不同的详情页",Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getContext(),DetailActivity.class);
//                startActivity(intent);
//            }
//        });

        //点击搜索图标，跳转到搜索页
        ImageView find = view.findViewById(R.id.iv_find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),FindActivity.class);
                startActivity(intent);
            }
        });

        //点击别的用户的头像，跳到该用户的详细信息页面
//        CircleImageView civUserImg = view.findViewById(R.id.civ_head_img);
//        civUserImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(),UserInfoActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}