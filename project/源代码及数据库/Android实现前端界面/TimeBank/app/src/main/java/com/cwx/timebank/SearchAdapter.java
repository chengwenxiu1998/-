package com.cwx.timebank;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cwx.timebank.task.ShowHeadImg;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SearchAdapter extends BaseAdapter {
    private Context context;
    private int itemView;
    private List<BuyOrSellTime> list;
    private ListView lv;
    public SearchAdapter(Context context, List<BuyOrSellTime> list, ListView lv){
        this.context = context;
        this.list = list;
        this.lv=lv;

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
        final BuyOrSellTime buyTime = (BuyOrSellTime) list.get(position);
        final int tcId=buyTime.getTcId();
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            for(int i=0;i<list.size();i++) {
                if (tcId == 1) {
                    convertView = inflater.inflate(R.layout.sell_time_item, null);
                } else {
                    convertView = inflater.inflate(R.layout.buy_time_item, null);
                }
            }
        }
        CircleImageView headImgCIV = convertView.findViewById(R.id.civ_head_img);
        TextView nickname = convertView.findViewById(R.id.tv_nickname);
        final TextView sendTime = convertView.findViewById(R.id.tv_send_time);
        TextView coinCounts = convertView.findViewById(R.id.tv_coin_count);
        final TextView taskTag = convertView.findViewById(R.id.tv_task_tag);
        TextView taskDetails = convertView.findViewById(R.id.tv_task_details);

        //获取任务id
        final int tId=buyTime.gettId();
        if(buyTime.getuImage()!=null && !buyTime.getuImage().equals("")) {
            SharedPreferences sp = context.getSharedPreferences("myServer", MODE_PRIVATE);
            new ShowHeadImg(headImgCIV, sp.getString("serverUrl", "") + buyTime.getuImage()).execute();
        }

        nickname.setText(buyTime.getuNickName());

        //当前时间
        Calendar currentDate=Calendar.getInstance();
        String getuTime=null;
        //计算时间差
        if(buyTime.getuTime()!=null) {
            if (currentDate.get(Calendar.YEAR) - (buyTime.getuTime().getYear()+1900) < 1) {
                if (currentDate.get(Calendar.MONTH) -buyTime.getuTime().getMonth() < 1) {
                    if (currentDate.get(Calendar.DAY_OF_MONTH) - buyTime.getuTime().getDate()<1) {
                        if (currentDate.get(Calendar.HOUR_OF_DAY) - buyTime.getuTime().getHours() < 1) {
                            getuTime = "发布于" + (currentDate.get(Calendar.MINUTE) - buyTime.getuTime().getMinutes()) + "分钟前";
                        } else if (currentDate.get(Calendar.HOUR_OF_DAY) - buyTime.getuTime().getHours() > 1) {
                            if (currentDate.get(Calendar.MINUTE) - buyTime.getuTime().getMinutes() > 0) {
                                getuTime = "发布于" + (currentDate.get(Calendar.HOUR_OF_DAY) - buyTime.getuTime().getHours()) + "小时前";
                            } else {
                                getuTime = "发布于" + (currentDate.get(Calendar.HOUR_OF_DAY) - buyTime.getuTime().getHours() - 1) + "小时前";
                            }
                        }
                    } else if (currentDate.get(Calendar.DAY_OF_MONTH) - buyTime.getuTime().getDate() < 2) {
                        getuTime = "昨天" + new SimpleDateFormat("HH:mm").format(buyTime.getuTime());
                    } else {
                        getuTime = new SimpleDateFormat("MM-dd").format(buyTime.getuTime());
                    }
                } else {
                    getuTime = new SimpleDateFormat("MM-dd").format(buyTime.getuTime());
                }
            } else {
                getuTime = new SimpleDateFormat("yyyy-MM-dd").format(buyTime.getuTime());
            }
        }
        sendTime.setText(getuTime);

        coinCounts.setText(buyTime.gettCoinCount()+"");
        taskTag.setText(buyTime.getTagText());
        taskDetails.setText(buyTime.gettDesc());
        //点击每个item跳转到相应的详情页
        if(lv!=null) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                        if(list.get((int) id).getTcId()==1)
                        {
                            intent.setClassName("com.cwx.timebank", "com.cwx.timebank.DetailActivity");
                            BuyOrSellTime sellTimeDetail = list.get((int) id);
                            intent.putExtra("uImage",sellTimeDetail.getuImage());
                            intent.putExtra("nickname", sellTimeDetail.getuNickName());
                            //当前时间
                            Calendar currentDate = Calendar.getInstance();
                            String getuTime = null;
                            //计算时间差
                            if (sellTimeDetail.getuTime() != null) {
                                if (currentDate.get(Calendar.YEAR) - (sellTimeDetail.getuTime().getYear() + 1900) < 1) {
                                    if (currentDate.get(Calendar.MONTH) - sellTimeDetail.getuTime().getMonth() < 1) {
                                        if (currentDate.get(Calendar.DAY_OF_MONTH) - sellTimeDetail.getuTime().getDate() < 1) {
                                            if (currentDate.get(Calendar.HOUR_OF_DAY) - sellTimeDetail.getuTime().getHours() < 1) {
                                                getuTime = "发布于" + (currentDate.get(Calendar.MINUTE) - sellTimeDetail.getuTime().getMinutes()) + "分钟前";
                                            } else if (currentDate.get(Calendar.HOUR_OF_DAY) - sellTimeDetail.getuTime().getHours() > 1) {
                                                if (currentDate.get(Calendar.MINUTE) - sellTimeDetail.getuTime().getMinutes() > 0) {
                                                    getuTime = "发布于" + (currentDate.get(Calendar.HOUR_OF_DAY) - sellTimeDetail.getuTime().getHours()) + "小时前";
                                                } else {
                                                    getuTime = "发布于" + (currentDate.get(Calendar.HOUR_OF_DAY) - sellTimeDetail.getuTime().getHours() - 1) + "小时前";
                                                }
                                            }
                                        } else if (currentDate.get(Calendar.DAY_OF_MONTH) - sellTimeDetail.getuTime().getDate() < 2) {
                                            getuTime = "昨天" + new SimpleDateFormat("HH:mm").format(sellTimeDetail.getuTime());
                                        } else {
                                            getuTime = new SimpleDateFormat("MM-dd").format(sellTimeDetail.getuTime());
                                        }
                                    } else {
                                        getuTime = new SimpleDateFormat("MM-dd").format(sellTimeDetail.getuTime());
                                    }
                                } else {
                                    getuTime = new SimpleDateFormat("yyyy-MM-dd").format(sellTimeDetail.getuTime());
                                }
                            }
                            intent.putExtra("Time", getuTime);

                            intent.putExtra("taskTag", sellTimeDetail.getTagText());
                            intent.putExtra("taskDetails", sellTimeDetail.gettDesc());
                            intent.putExtra("tId", buyTime.gettId());
                            context.startActivity(intent);
                        }else{
                            intent.setClassName("com.cwx.timebank", "com.cwx.timebank.TaskDetailActivityLi");
                            BuyOrSellTime buyTimeDetail = list.get((int) id);
                            intent.putExtra("uImage",buyTimeDetail.getuImage());
                            intent.putExtra("nickname", buyTimeDetail.getuNickName());
                            //当前时间
                            Calendar currentDate=Calendar.getInstance();
                            String getuTime=null;
                            //计算时间差
                            if(buyTimeDetail.getuTime()!=null) {
                                if (currentDate.get(Calendar.YEAR) - (buyTimeDetail.getuTime().getYear()+1900) < 1) {
                                    if (currentDate.get(Calendar.MONTH) -buyTimeDetail.getuTime().getMonth() < 1) {
                                        if (currentDate.get(Calendar.DAY_OF_MONTH) - buyTimeDetail.getuTime().getDate()<1) {
                                            if (currentDate.get(Calendar.HOUR_OF_DAY) - buyTimeDetail.getuTime().getHours() < 1) {
                                                getuTime = "发布于" + (currentDate.get(Calendar.MINUTE) - buyTimeDetail.getuTime().getMinutes()) + "分钟前";
                                            } else if (currentDate.get(Calendar.HOUR_OF_DAY) - buyTimeDetail.getuTime().getHours() > 1) {
                                                if (currentDate.get(Calendar.MINUTE) - buyTimeDetail.getuTime().getMinutes() > 0) {
                                                    getuTime = "发布于" + (currentDate.get(Calendar.HOUR_OF_DAY) - buyTimeDetail.getuTime().getHours()) + "小时前";
                                                } else {
                                                    getuTime = "发布于" + (currentDate.get(Calendar.HOUR_OF_DAY) - buyTimeDetail.getuTime().getHours() - 1) + "小时前";
                                                }
                                            }
                                        } else if (currentDate.get(Calendar.DAY_OF_MONTH) - buyTimeDetail.getuTime().getDate() < 2) {
                                            getuTime = "昨天" + new SimpleDateFormat("HH:mm").format(buyTimeDetail.getuTime());
                                        } else {
                                            getuTime = new SimpleDateFormat("MM-dd").format(buyTimeDetail.getuTime());
                                        }
                                    } else {
                                        getuTime = new SimpleDateFormat("MM-dd").format(buyTimeDetail.getuTime());
                                    }
                                } else {
                                    getuTime = new SimpleDateFormat("yyyy-MM-dd").format(buyTimeDetail.getuTime());
                                }
                            }
                            String putTime=new SimpleDateFormat("HH:mm").format(buyTimeDetail.getuTime());
                            String endTime=null;
                            if(buyTimeDetail.gettEndtime()!=null) {
                                endTime = new SimpleDateFormat("HH:mm").format(buyTimeDetail.gettEndtime());
                            }

                            intent.putExtra("taskTag", buyTimeDetail.getTagText());
                            intent.putExtra("taskDetails", buyTimeDetail.gettDesc());
                            intent.putExtra("tPutTime",putTime);
                            intent.putExtra("tEndtime",endTime);
                            intent.putExtra("tImageUrl",buyTimeDetail.gettImageUrl());
                            intent.putExtra("tCoinCount",buyTimeDetail.gettCoinCount()+"");
                            context.startActivity(intent);
                        }
                }
            });
        }
        Button btnGetTask = convertView.findViewById(R.id.gettask);
        if(btnGetTask!=null) {
            btnGetTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击按钮连接服务器
                    SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", MODE_PRIVATE);
                    int uId = sharedPreferences.getInt("userId", 0);
                    if (uId != 0) {//若该用户已登录
                        HandleDatabaseTask handleDatabaseTask = new HandleDatabaseTask(context);
                        handleDatabaseTask.execute(tId, uId);
                        Toast.makeText(context, "领取任务成功", Toast.LENGTH_LONG).show();
                    } else {//用户还没有登陆，跳转到登陆页面
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }

                }
            });
        }

        Button btnContackSeller = convertView.findViewById(R.id.btn_contact_seller);
        if(btnContackSeller!=null) {
            btnContackSeller.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = context.getSharedPreferences("userInfo", MODE_PRIVATE);
                    if (sp.getInt("userId", 0) != 0) {//若该用户已登录
                        Intent intent = new Intent();
                        intent.setClassName("com.cwx.timebank", "com.cwx.timebank.ContactSellerActivity");
                        context.startActivity(intent);
                    } else {//用户还没有登陆，跳转到登陆页面
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
        return convertView;
    }
}
