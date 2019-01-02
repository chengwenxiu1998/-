package com.cwx.timebank;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.cwx.timebank.bean.TaskBean;
import com.cwx.timebank.task.RenWuTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class SendActivity1 extends Fragment {
    TabHost tabHost;
    private TaskBean taskBean;
    float scaleWidth;
    float scaleHeight;
    int h;
    boolean num=false;
    Bitmap bp;
    ImageView imageview;
    private int i=0;//用来作为图片的名称

    public static final int TAKE_PHOTO1 = 3;
    public static final int TAKE_PHOTO = 2;//声明一个请求码，用于识别返回的结果
    private Uri imageUri;

    private File mPhotoFile;
    private String mPhotoPath;
    public final static int CAMERA_RESULT = 1;
    ImageView  imageshow;
    ImageView  imageshowSell;
    ImageView ivAddImageView;
    ImageView ivSellAddImageView;
    private List<View> viewList=new ArrayList<View>();
    private String[] tabHostTag={"tab1","tab2"};
    private final int ImageCode = 0;
    private final int ImageCode1 = 1;
    private final String Image_Type="image/*";

    EditText etSendTask;
    EditText etSellSendTask;
    // EditText etSellTimeMoney;
    //  EditText etSellSellTimeMoney;
    EditText etMiaoshu;
    EditText etSellMiaoshu;
    Spinner spinner;
    Spinner spinner1;
    Spinner spinnerEndTimeMonth;
    Spinner spinnerEndTimeDay;
    Spinner spinnerEndTimeHour;
    Spinner spinnerEndTimeMin;
    Spinner spinnerEndTimeMonth1;
    Spinner spinnerEndTimeDay1;
    Spinner spinnerEndTimeHour1;
    Spinner spinnerEndTimeMin1;
    String month;
    String day;
    String hour;
    String min;
    //当创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_send,container,false);
        return view;
    }

    //当View创建完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //返回箭头 到首页
        ImageView ivReturn = view.findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[0]).setIndicator(getTabView("买时间")). setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[1]).setIndicator(getTabView("卖时间")) .setContent(R.id.tab2));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabHostChanged(tabId);
            }
        });

        setTabHostChanged(tabHost.getCurrentTabTag());


        etSendTask=view.findViewById(R.id.et_send_task);
        etMiaoshu=view.findViewById(R.id.et_miaoshu);


        etSellSendTask=view.findViewById(R.id.et_sell_send_task);
        etSellMiaoshu=view.findViewById(R.id.et_sell_miaoshu);

        final TextView tvSellTimeMoney=view.findViewById(R.id.tv_time_money);
        // etSellTimeMoney=view.findViewById(R.id.et_time_money);

        final TextView tvSellSellTimeMoney=view.findViewById(R.id.tv_sell_time_money);
        //etSellSellTimeMoney=view.findViewById(R.id.et_sell_time_money);


        //spinner
        spinner= view.findViewById(R.id.sp_spinner_shijianbi);
        spinner1= view.findViewById(R.id.sp_sell_spinner_shijianbi);
        spinnerEndTimeMonth= view.findViewById(R.id.sp_endtime_month);
        spinnerEndTimeMonth1= view.findViewById(R.id.sp_endtime_month1);
        spinnerEndTimeDay = view.findViewById(R.id.sp_endtime_day);
        spinnerEndTimeDay1 = view.findViewById(R.id.sp_endtime_day1);
        final String[] strDay1 = new String[31];
        for(int i=0;i<31;i++){strDay1[i]=i+1+"";}
        final String[] strDay2 = new String[30];
        for(int i=0;i<30;i++){strDay2[i]=i+1+"";}
        final String[] strDay3 = new String[29];
        for(int i=0;i<29;i++){strDay3[i]=i+1+"";}
        final String[] strDay4 = new String[28];
        for(int i=0;i<28;i++){strDay4[i]=i+1+"";}
        spinnerEndTimeMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp=spinnerEndTimeMonth.getSelectedItem().toString();
                if (temp.equals("1")||temp.equals("3")||temp.equals("5")||temp.equals("7")||temp.equals("8")||temp.equals("10")||temp.equals("12")){
                    ArrayAdapter daySpinnerAdapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay1);
                    spinnerEndTimeDay.setAdapter(daySpinnerAdapter1);
                }else if (temp.equals("4")||temp.equals("6")||temp.equals("9")||temp.equals("11")){
                    ArrayAdapter daySpinnerAdapter2 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay2);
                    spinnerEndTimeDay.setAdapter(daySpinnerAdapter2);
                }else if(temp.equals("2")){
                    Calendar c = Calendar.getInstance();//
                    int mYear = c.get(Calendar.YEAR); // 获取当前年份
                    if ((mYear%4)==0){
                        ArrayAdapter daySpinnerAdapter3 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay3);
                        spinnerEndTimeDay.setAdapter(daySpinnerAdapter3);
                    }else{
                        ArrayAdapter daySpinnerAdapter4 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay4);
                        spinnerEndTimeDay.setAdapter(daySpinnerAdapter4);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ArrayAdapter daySpinnerAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay1);
                spinnerEndTimeDay.setAdapter(daySpinnerAdapter);
            }
        });
        spinnerEndTimeMonth1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp=spinnerEndTimeMonth1.getSelectedItem().toString();
                if (temp.equals("1")||temp.equals("3")||temp.equals("5")||temp.equals("7")||temp.equals("8")||temp.equals("10")||temp.equals("12")){
                    ArrayAdapter daySpinnerAdapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay1);
                    spinnerEndTimeDay1.setAdapter(daySpinnerAdapter1);
                }else if (temp.equals("4")||temp.equals("6")||temp.equals("9")||temp.equals("11")){
                    ArrayAdapter daySpinnerAdapter2 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay2);
                    spinnerEndTimeDay1.setAdapter(daySpinnerAdapter2);
                }else if(temp.equals("2")){
                    Calendar c = Calendar.getInstance();//
                    int mYear = c.get(Calendar.YEAR); // 获取当前年份
                    if ((mYear%4)==0){
                        ArrayAdapter daySpinnerAdapter3 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay3);
                        spinnerEndTimeDay1.setAdapter(daySpinnerAdapter3);
                    }else{
                        ArrayAdapter daySpinnerAdapter4 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay4);
                        spinnerEndTimeDay1.setAdapter(daySpinnerAdapter4);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ArrayAdapter daySpinnerAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay1);
                spinnerEndTimeDay.setAdapter(daySpinnerAdapter);
            }
        });

        spinnerEndTimeHour = view.findViewById(R.id.sp_endtime_hour);
        spinnerEndTimeHour1 = view.findViewById(R.id.sp_endtime_hour1);
        spinnerEndTimeMin = view.findViewById(R.id.sp_endtime_min);
        spinnerEndTimeMin1 = view.findViewById(R.id.sp_endtime_min1);


        final Button btnHelpBuyFood=view.findViewById(R.id.btn_help_buy_food);
        btnHelpBuyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnHelpBuyFood.getText());
                etSendTask.setCursorVisible(false);
                etMiaoshu.setHint("描述详情");
                // etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnSellHelpBuyFood=view.findViewById(R.id.btn_sell_help_buy_food);
        btnSellHelpBuyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellHelpBuyFood.getText());
                etSellSendTask.setCursorVisible(false);
                etSellMiaoshu.setHint("描述详情");
                //  etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnDaiqu=view.findViewById(R.id.btn_daiqukuaidi);
        btnDaiqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnDaiqu.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                //  etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellDaiqu=view.findViewById(R.id.btn_sell_daiqukuaidi);
        btnSellDaiqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellDaiqu.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");
                //  etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnDaijie=view.findViewById(R.id.btn_daijieshui);
        btnDaijie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnDaijie.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                //etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellDaijie=view.findViewById(R.id.btn_sell_daijieshui);
        btnSellDaijie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellDaijie.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");
                //   etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnYueguang=view.findViewById(R.id.btn_yueguangjie);
        btnYueguang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnYueguang.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                // etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellYueguang=view.findViewById(R.id.btn_sell_yueguangjie);
        btnSellYueguang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellYueguang.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");
                //etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnYueyun=view.findViewById(R.id.btn_yueyundong);
        btnYueyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnYueyun.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                //etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellYueyun=view.findViewById(R.id.btn_sell_yueyundong);
        btnSellYueyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellYueyun.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");
                //etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnYuexue=view.findViewById(R.id.btn_yuexuexi);
        btnYuexue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnYuexue.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                // etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellYuexue=view.findViewById(R.id.btn_sell_yuexuexi);
        btnSellYuexue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellYuexue.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");
                // etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnQita=view.findViewById(R.id.btn_qita);
        btnQita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnQita.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                // etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellQita=view.findViewById(R.id.btn_sell_qita);
        btnSellQita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellQita.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");
                // etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        ivAddImageView=view.findViewById(R.id.iv_add_photo);
        ivAddImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
        imageshow = (ImageView)view.findViewById(R.id.iv_photo);

        ivSellAddImageView=view.findViewById(R.id.iv_sell_add_photo);
        ivSellAddImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow1();
            }
        });
        imageshowSell = (ImageView)view.findViewById(R.id.iv_sell_photo);



        Button btnSend=view.findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskBean=new TaskBean();

                //发布任务
                task(tabHost.getCurrentTabTag());
                //假设发布者ID为1
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo", MODE_PRIVATE);
                int userId = sharedPreferences.getInt("userId",0);
                Log.e("test",userId+"");
                taskBean.setuIdSend(userId);

                RenWuTask renwuTask=new RenWuTask(getContext(),taskBean,month,day,hour,min);
                renwuTask.execute();
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                Log.e("test","点击了发表");
            }
        });

    }
    public void task(String tabId){
        if (tabId.equals(tabHostTag[0])) {
            taskBean.setTcId(1);
            taskBean.settDesc(etMiaoshu.getText().toString());
            taskBean.settCoinCount(Integer.parseInt(spinner.getSelectedItem().toString()));
            Log.e("test","spinner="+spinner.getSelectedItem().toString());
            //状态先写待接收
            taskBean.settState("待接收");
            //TagID
            if(etSendTask.getText().toString().equals("#代取快递#")) {
                taskBean.setTagId(1);
            }else if(etSendTask.getText().toString().equals("#代买饭#")){
                taskBean.setTagId(2);
            }else if(etSendTask.getText().toString().equals("#代接水#")){
                taskBean.setTagId(3);
            }else if(etSendTask.getText().toString().equals("#约逛街#")){
                taskBean.setTagId(4);
            }else if(etSendTask.getText().toString().equals("#约运动#")){
                taskBean.setTagId(5);
            }else if(etSendTask.getText().toString().equals("#约学习#")){
                taskBean.setTagId(6);
            }else if(etSendTask.getText().toString().equals("#其他#")){
                taskBean.setTagId(7);
            }else{
                Log.e("test","设置taskBean出错");
            }
            //结束时间
            month=spinnerEndTimeMonth.getSelectedItem().toString();
            day=spinnerEndTimeDay.getSelectedItem().toString();
            hour=spinnerEndTimeHour.getSelectedItem().toString();
            min=spinnerEndTimeMin.getSelectedItem().toString();
            Log.e("test",month+" "+day+" "+hour+"  "+min);


            //先假设一个路径
            taskBean.setT_imgurl("aaaaa.jpg");

        } else if (tabId.equals(tabHostTag[1])) {
            taskBean.setTcId(2);
            taskBean.settDesc(etSellMiaoshu.getText().toString());
            taskBean.settCoinCount(Integer.parseInt(spinner.getSelectedItem().toString()));
            Log.e("test","spinner1="+spinner1.getSelectedItem().toString());
            //状态先写待接收
            taskBean.settState("待接收");
            //TagID
            if(etSendTask.getText().toString().equals("#代取快递#")) {
                taskBean.setTagId(1);
            }else if(etSendTask.getText().toString().equals("#代买饭#")){
                taskBean.setTagId(2);
            }else if(etSendTask.getText().toString().equals("#代接水#")){
                taskBean.setTagId(3);
            }else if(etSendTask.getText().toString().equals("#约逛街#")){
                taskBean.setTagId(4);
            }else if(etSendTask.getText().toString().equals("#约运动#")){
                taskBean.setTagId(5);
            }else if(etSendTask.getText().toString().equals("#约学习#")){
                taskBean.setTagId(6);
            }else if(etSendTask.getText().toString().equals("#其他#")){
                taskBean.setTagId(7);
            }else{
                Log.e("test","设置taskBean出错");
            }
            //结束时间
            month=spinnerEndTimeMonth1.getSelectedItem().toString();
            day=spinnerEndTimeDay1.getSelectedItem().toString();
            hour=spinnerEndTimeHour1.getSelectedItem().toString();
            min=spinnerEndTimeMin1.getSelectedItem().toString();
            Log.e("test",month+" "+day+" "+hour+"  "+min);
            //先假设一个路径
            taskBean.setT_imgurl("aaaaa.jpg");

        }
    }


    // 弹出PopupWindow
    private void showPopupWindow1() {
        // 1. 创建PopupWindow显示的view
        final View view = getLayoutInflater()
                .inflate(R.layout.popup_window, null);
        // 2. 创建PopupWindow
        final PopupWindow popupWindow = new PopupWindow(getContext());
        // 3. 设置PopupWindow的长宽
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 4. 设置PopupWindow显示的view
        popupWindow.setContentView(view);
        //点击空白处popupWindow消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        // 5. view中按钮添加监听器
        Button btnPhoto = view.findViewById(R.id.btn_photo);
        Button btnSelect = view.findViewById(R.id.btn_select);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 隐藏PopupWindow
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(getContext().getExternalCacheDir(), "output_image.jpg");
                try//判断图片是否存在，存在则删除在创建，不存在则直接创建
                {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }

                    outputImage.createNewFile();
                    Log.e("test","outputImage ="+outputImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24)
                    //判断安卓的版本是否高于7.0，高于则调用高于的方法，低于则调用低于的方法
                    //把文件转换成Uri对象
                    /*
                    之所以这样，是因为android7.0以后直接使用本地真实路径是不安全的，会抛出异常。
                    FileProvider是一种特殊的内容提供器，可以对数据进行保护
                     */ {
                    imageUri = FileProvider.getUriForFile(getContext(),
                            "com.cwx.timebank.android.support.v4.content.FileProvider", outputImage);
                    /*
                    第一个参数：context对象
                    第二个参数：任意唯一的字符串
                    第三个参数：文件对象
                     */

                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                //使用隐示的Intent，系统会找到与它对应的活动，即调用摄像头，并把它存储
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO1);
                //调用会返回结果的开启方式，返回成功的话，则把它显示出来
                popupWindow.dismiss();

            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setImage1();

                popupWindow.dismiss();

            }
        });
        popupWindow.showAtLocation(ivSellAddImageView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);

    }
    private void setImage1(){
        // TODO Auto-generated method stub
        // 使用intent调用系统提供的相册功能，使用startActivityForResult是为了获取用户选择的图片的地址
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType(Image_Type);
        startActivityForResult(getAlbum, ImageCode1);
    }

    // 弹出PopupWindow
    private void showPopupWindow() {
        // 1. 创建PopupWindow显示的view
        final View view = getLayoutInflater()
                .inflate(R.layout.popup_window, null);
        // 2. 创建PopupWindow
        final PopupWindow popupWindow = new PopupWindow(getContext());
        // 3. 设置PopupWindow的长宽
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 4. 设置PopupWindow显示的view
        popupWindow.setContentView(view);
        //点击空白处popupWindow消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        // 5. view中按钮添加监听器
        Button btnPhoto = view.findViewById(R.id.btn_photo);
        Button btnSelect = view.findViewById(R.id.btn_select);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 隐藏PopupWindow
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(getContext().getExternalCacheDir(), "output_image.jpg");
                try//判断图片是否存在，存在则删除在创建，不存在则直接创建
                {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }

                    outputImage.createNewFile();
                    Log.e("test","outputImage ="+outputImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24)
                    //判断安卓的版本是否高于7.0，高于则调用高于的方法，低于则调用低于的方法
                    //把文件转换成Uri对象
                    /*
                    之所以这样，是因为android7.0以后直接使用本地真实路径是不安全的，会抛出异常。
                    FileProvider是一种特殊的内容提供器，可以对数据进行保护
                     */ {
                    imageUri = FileProvider.getUriForFile(getContext(),
                            "com.cwx.timebank.android.support.v4.content.FileProvider", outputImage);
                    /*
                    第一个参数：context对象
                    第二个参数：任意唯一的字符串
                    第三个参数：文件对象
                     */

                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                //使用隐示的Intent，系统会找到与它对应的活动，即调用摄像头，并把它存储
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                //调用会返回结果的开启方式，返回成功的话，则把它显示出来
                popupWindow.dismiss();

            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setImage();

                popupWindow.dismiss();

            }
        });
        popupWindow.showAtLocation(ivAddImageView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    private void setImage(){
        // TODO Auto-generated method stub
        // 使用intent调用系统提供的相册功能，使用startActivityForResult是为了获取用户选择的图片的地址
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType(Image_Type);
        startActivityForResult(getAlbum, ImageCode);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // RESULT_OK 是系统自定义得一个常量
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult", "返回的resultCode出错");
            return;
        }

        // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
        ContentResolver resolver =getContext().getContentResolver();
        // 判断接收的Activity是不是选择图片的
        if (requestCode == ImageCode) {

            // 获得图片的地址Uri
            Uri originalUri = data.getData();
            // 新建一个字符串数组用于存储图片地址数据。
            String[] proj = {MediaStore.Images.Media.DATA};

            // android系统提供的接口，用于根据uri获取数据
            Cursor cursor =getContext().getContentResolver().query(originalUri, proj, null, null,
                    null);

            // 获得用户选择图片的索引值
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            // 将游标移至开头 ，防止引起队列越界
            cursor.moveToFirst();
            // 根据图片的URi生成bitmap
            Bitmap bm = null;

            try {
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                Log.e("test","即将执行saveBitmap");
                //saveMyBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 显得到bitmap图片
            imageshow.setImageBitmap(bm);
            imageshow.setVisibility(View.VISIBLE);
        }
        if (requestCode == ImageCode1) {

            // 获得图片的地址Uri
            Uri originalUri = data.getData();
            // 新建一个字符串数组用于存储图片地址数据。
            String[] proj = {MediaStore.Images.Media.DATA};

            // android系统提供的接口，用于根据uri获取数据
            Cursor cursor =getContext().getContentResolver().query(originalUri, proj, null, null,
                    null);

            // 获得用户选择图片的索引值
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            // 将游标移至开头 ，防止引起队列越界
            cursor.moveToFirst();
            // 根据图片的URi生成bitmap
            Bitmap bm = null;

            try {
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                Log.e("test","即将执行saveBitmap");
                //saveMyBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 显得到bitmap图片
            imageshowSell.setImageBitmap(bm);
            imageshowSell.setVisibility(View.VISIBLE);
        }

        if (requestCode == TAKE_PHOTO ) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(imageUri));
                Log.e("test","即将执行saveBitmap");
                //saveMyBitmap(bitmap);
                imageshow.setImageBitmap(bitmap);
                imageshow.setVisibility(View.VISIBLE);
                //将图片解析成Bitmap对象，并把它显现出来
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == TAKE_PHOTO1 ) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(imageUri));
                Log.e("test","即将执行saveBitmap");
                //saveMyBitmap(bitmap );
                imageshowSell.setImageBitmap(bitmap);
                imageshowSell.setVisibility(View.VISIBLE);
                //将图片解析成Bitmap对象，并把它显现出来
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    /*//已经存在bitmap将其保存到本地
    public void saveMyBitmap(Bitmap mBitmap) {
        Log.e("test","开始执行saveMyBitmap");
        File dir = new File("/sdcard/Note/");

        if (!dir.exists())
            dir.mkdirs();
        Log.e("test","dir="+dir);
        File f = new File("/sdcard/Note/" + i++ + ".jpg");
        Log.e("test","f="+f);
        Log.e("test",f.toURI().toString());//////
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("FFF", e.getMessage());
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        Log.e("test"," mBitmap.compress="+"aaa");
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("FFF", e.getMessage());
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("FFF", e.getMessage());
        }
    }*/
    private void setTabHostChanged(String tabId) {
        TextView textView1 = viewList.get(0).findViewById(R.id.tv_text);
        TextView textView2 = viewList.get(1).findViewById(R.id.tv_text);
        TextView tvLine1=viewList.get(0).findViewById(R.id.tv_line);
        TextView tvLine2=viewList.get(1).findViewById(R.id.tv_line);
        textView1.setText("买时间");
        textView2.setText("卖时间");
        if (tabId.equals(tabHostTag[0])) {
            textView1.setText(Html.fromHtml("<font color=#ffcc66>买时间</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#ffcc66"));
            tvLine2.setBackgroundColor(Color.parseColor("#aaaaaa"));

        } else if (tabId.equals(tabHostTag[1])) {
            textView2.setText(Html.fromHtml("<font color=#ffcc66>卖时间</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#aaaaaa"));
            tvLine2.setBackgroundColor(Color.parseColor("#ffcc66"));

        }
    }
    private View getTabView(String text){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.tab_view,null);
        TextView textView= view.findViewById(R.id.tv_text);
        textView.setText(text);
        viewList.add(view);
        return view;
    }


}
