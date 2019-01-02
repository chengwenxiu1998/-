package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class IndexCustomAdapter extends RecyclerView.Adapter<IndexCustomAdapter.VH> {
    private Context context;
    private List<BuyOrSellTime> mDatas;
    //静态内部类
    static class VH extends RecyclerView.ViewHolder {
       // public CircleImageView headImg;
        public TextView nickName;
        public TextView time;
        public TextView coin;
        public TextView tagText;
        public TextView details;

        public VH(@NonNull View itemView) {
            super(itemView);
            //头像
            time = itemView.findViewById(R.id.tv_send_time);
           // headImg = itemView.findViewById(R.id.civ_head_img);
            nickName = itemView.findViewById(R.id.tv_nickname);
            coin = itemView.findViewById(R.id.tv_coin_count);
            tagText = itemView.findViewById(R.id.tv_task_tag);
            details = itemView.findViewById(R.id.tv_task_details);

        }
    }

    public IndexCustomAdapter(Context context, List<BuyOrSellTime> data) {
        this.context=context;
        this.mDatas = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_index_item, viewGroup, false);
        return new VH(v);
    }

    //这个方法主要用于适配渲染数据到View中。方法提供给你了一viewHolder而不是原来的convertView。
    @Override
    public void onBindViewHolder(@NonNull VH vh, final int i) {
        final BuyOrSellTime buyTime = (BuyOrSellTime) mDatas.get(i);
        //头像没写
        //当前时间
        Calendar currentDate = Calendar.getInstance();
        String getuTime = null;
        //计算时间差
        if (buyTime.getuTime() != null) {
            if (currentDate.get(Calendar.YEAR) - (buyTime.getuTime().getYear() + 1900) < 1) {
                if (currentDate.get(Calendar.MONTH) - buyTime.getuTime().getMonth() < 1) {
                    if (currentDate.get(Calendar.DAY_OF_MONTH) - buyTime.getuTime().getDate() < 1) {
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
        vh.time.setText(getuTime);
        vh.nickName.setText(buyTime.getuNickName());
        vh.coin.setText(buyTime.gettCoinCount() + "");
        vh.tagText.setText(buyTime.getTagText());
        vh.details.setText(buyTime.gettDesc());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (mDatas.get((int) i).getTcId() == 1) {
                    intent.setClassName("com.cwx.timebank", "com.cwx.timebank.DetailActivity");
                    BuyOrSellTime sellTimeDetail = mDatas.get((int) i);
                    //头像没写
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
                } else {
                    intent.setClassName("com.cwx.timebank", "com.cwx.timebank.TaskDetailActivityLi");
                    BuyOrSellTime buyTimeDetail = mDatas.get((int) i);
                    //头像没写
                    intent.putExtra("nickname", buyTimeDetail.getuNickName());
                    //当前时间
                    Calendar currentDate = Calendar.getInstance();
                    String getuTime = null;
                    //计算时间差
                    if (buyTimeDetail.getuTime() != null) {
                        if (currentDate.get(Calendar.YEAR) - (buyTimeDetail.getuTime().getYear() + 1900) < 1) {
                            if (currentDate.get(Calendar.MONTH) - buyTimeDetail.getuTime().getMonth() < 1) {
                                if (currentDate.get(Calendar.DAY_OF_MONTH) - buyTimeDetail.getuTime().getDate() < 1) {
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
                    String putTime = new SimpleDateFormat("HH:mm").format(buyTimeDetail.getuTime());
                    String endTime = null;
                    if (buyTimeDetail.gettEndtime() != null) {
                        endTime = new SimpleDateFormat("HH:mm").format(buyTimeDetail.gettEndtime());
                    }

                    intent.putExtra("taskTag", buyTimeDetail.getTagText());
                    intent.putExtra("taskDetails", buyTimeDetail.gettDesc());
                    intent.putExtra("tPutTime", putTime);
                    intent.putExtra("tEndtime", endTime);
                    intent.putExtra("tImageUrl", buyTimeDetail.gettImageUrl());
                    intent.putExtra("tCoinCount", buyTimeDetail.gettCoinCount() + "");
                    context.startActivity(intent);
                }
           }
       });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
