package com.cwx.timebank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cwx.timebank.bean.DisussReplyBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DetailAdapter extends BaseAdapter {
    private Context context;
    private int itemView;
    private List<DisussReplyBean> list;

    public DetailAdapter(Context context,int itemView,List<DisussReplyBean> list){
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
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(itemView, null);
        }
        TextView textView1=convertView.findViewById(R.id.tv_nickname);
        TextView textView2=convertView.findViewById(R.id.tv_send_time);
        TextView textView3=convertView.findViewById(R.id.tv_talk_text);

        DisussReplyBean disussReplyBean=(DisussReplyBean)list.get(position);
        textView1.setText(disussReplyBean.getPetName());
        //当前时间
        Calendar currentDate=Calendar.getInstance();
        String getuTime=null;
        //计算时间差
        if(disussReplyBean.getReplyTime()!=null) {
            if (currentDate.get(Calendar.YEAR) - (disussReplyBean.getReplyTime().getYear()+1900) < 1) {
                if (currentDate.get(Calendar.MONTH) -disussReplyBean.getReplyTime().getMonth() < 1) {
                    if (currentDate.get(Calendar.DAY_OF_MONTH) - disussReplyBean.getReplyTime().getDate()<1) {
                        if (currentDate.get(Calendar.HOUR_OF_DAY) -disussReplyBean.getReplyTime().getHours() < 1) {
                            getuTime = "发布于" + (currentDate.get(Calendar.MINUTE) - disussReplyBean.getReplyTime().getMinutes()) + "分钟前";
                        } else if (currentDate.get(Calendar.HOUR_OF_DAY) - disussReplyBean.getReplyTime().getHours() > 1) {
                            if (currentDate.get(Calendar.MINUTE) - disussReplyBean.getReplyTime().getMinutes() > 0) {
                                getuTime = "发布于" + (currentDate.get(Calendar.HOUR_OF_DAY) - disussReplyBean.getReplyTime().getHours()) + "小时前";
                            } else {
                                getuTime = "发布于" + (currentDate.get(Calendar.HOUR_OF_DAY) - disussReplyBean.getReplyTime().getHours() - 1) + "小时前";
                            }
                        }
                    } else if (currentDate.get(Calendar.DAY_OF_MONTH) - disussReplyBean.getReplyTime().getDate() < 2) {
                        getuTime = "昨天" + new SimpleDateFormat("HH:mm").format(disussReplyBean.getReplyTime());
                    } else {
                        getuTime = new SimpleDateFormat("MM-dd").format(disussReplyBean.getReplyTime());
                    }
                } else {
                    getuTime = new SimpleDateFormat("MM-dd").format(disussReplyBean.getReplyTime());
                }
            } else {
                getuTime = new SimpleDateFormat("yyyy-MM-dd").format(disussReplyBean.getReplyTime());
            }
        }

        textView2.setText(getuTime);
        textView3.setText(disussReplyBean.getReplyContent());

        return convertView;
    }

}

