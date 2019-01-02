package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwx.timebank.bean.DiscussBean;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int itemView;
    private List<DiscussBean> list;

    public CustomAdapter(Context context,int itemView,List<DiscussBean> list){
        this.context=context;
        this.itemView=itemView;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // 从Activity的Context上下文环境中获取 布局填充器（根据布局文件创建相应对象）
        if(convertView==null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(itemView, null);
        }

        ImageView imageView1=convertView.findViewById(R.id.lv_image1);
        CircleImageView imageView=convertView.findViewById(R.id.lv_image);
        TextView textView1=convertView.findViewById(R.id.tv_text);
        TextView textView2=convertView.findViewById(R.id.tv_text1);

        final DiscussBean discussBean=(DiscussBean)list.get(position);
        imageView1.setImageResource(R.drawable.heart);
        imageView.setImageResource(R.drawable.p5);
        textView1.setText("#"+discussBean.getTcontent()+"#");
        textView2.setText(discussBean.getContent());

        Button btnJoinTalk = convertView.findViewById(R.id.btn_join_talk);
        btnJoinTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiscussBean discussBean1=(DiscussBean)list.get(position);
                Intent intent = new Intent(context,JoinTalkActivity.class);
                intent.putExtra("netName",discussBean1.getPetName());
                intent.putExtra("tag",discussBean1.getTcontent());
                intent.putExtra("content",discussBean1.getContent());
                intent.putExtra("which",""+discussBean1.getDId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

}

