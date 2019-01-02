package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwx.timebank.task.ShowHeadImg;

import static android.content.Context.MODE_PRIVATE;


public class MyInfoActivity extends Fragment{
    private SharedPreferences sp;
    //当创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mine,container,false);

        sp = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);


        return view;
    }

    //当View创建完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(sp.getInt("userId",0)!=0){//若该用户已登录，显示该用户的头像等相关信息
            CircleImageView headImg = view.findViewById(R.id.civ_head_img);
            TextView nickname = view.findViewById(R.id.tv_nickname);
            TextView id = view.findViewById(R.id.tv_id);

            String imgUrl = sp.getString("uImage",null);
            if (imgUrl!=null){
                new ShowHeadImg(headImg,imgUrl).execute();
            }

            nickname.setText(sp.getString("uNickname",""));
            String userId = sp.getInt("userId",0)+"";
            userId = String.format("%8s", userId).replaceAll(" ", "0");
            id.setText("ID:"+userId);

        //用户已登录，最后一栏显示 退出登录
            TextView tvIsLogin = view.findViewById(R.id.tv_is_login);
            tvIsLogin.setText("退出登录");
        }


        LinearLayout signIn = view.findViewById(R.id.ll_sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SignInActivity.class);
                startActivity(intent);
            }
        });


        LinearLayout set = view.findViewById(R.id.ll_myinfo);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = sp.getInt("userId",0);
                if(userId != 0){//说明该用户已经登录，跳转到用户信息页面

                    Intent intent = new Intent(getContext(),UpdateMyInfo.class);
                    startActivity(intent);
                }else{//用户还没有登陆，跳转到登陆页面
                    Intent intent = new Intent(getContext(),LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        LinearLayout mySend = view.findViewById(R.id.ll_my_send);
        mySend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sp.getInt("userId",0)!=0) {//若该用户已登录
                    Intent intent = new Intent(getContext(),SendActivity.class);
                    startActivity(intent);
                }else{//用户还没有登陆，跳转到登陆页面
                    Intent intent = new Intent(getContext(),LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        LinearLayout myReceived = view.findViewById(R.id.ll_my_received);
        myReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sp.getInt("userId",0)!=0) {//若该用户已登录
                    Intent intent = new Intent(getContext(),ReceiveActivity.class);
                    startActivity(intent);
                }else{//用户还没有登陆，跳转到登陆页面
                    Intent intent = new Intent(getContext(),LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        LinearLayout myCoin=view.findViewById(R.id.ll_my_coin);
        myCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sp.getInt("userId",0)!=0) {//若该用户已登录
                    Intent intent=new Intent(getContext(),CoinActivity.class);
                    startActivity(intent);
                }else{//用户还没有登陆，跳转到登陆页面
                    Intent intent = new Intent(getContext(),LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        LinearLayout myCancle=view.findViewById(R.id.ll_my_cancle);
        myCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = sp.getInt("userId",0);
                if(userId != 0) {//如果用户已经登录，退出登录，删除相关用户信息，跳到首页
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove("userId");
                    editor.remove("uName");
                    editor.remove("uSex");
                    editor.remove("uArea");
                    editor.remove("uNickname");
//                    editor.remove("uImage");不删除头像信息，以便下次登录时显示用户上次的头像
                    //如果该次登录时勾选了记住密码，则不删除手机号和密码信息，用于下次登录显示在界面上
                    if(sp.getBoolean("isRememberPwd",false)==false){//没有记住密码则删除相关信息
                        editor.remove("uPhone");
                        editor.remove("uPassword");
//                        editor.remove("isRememberPwd");
                    }
                    editor.remove("uIdCard");
                    editor.remove("uCoin");
                    editor.commit();

                    SharedPreferences sp = getContext().getSharedPreferences("isFromMy",MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sp.edit();
                    editor1.putBoolean("fromMy",true);
                    editor1.commit();
                    Intent intent=new Intent(getContext(),MainActivity.class);
                    startActivity(intent);
                }else{//如果用户还没有登录，跳转到登录页面
                    Intent intent=new Intent(getContext(),LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        ImageView ivSet = view.findViewById(R.id.iv_set);
        ivSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SetActivity.class);
                startActivity(intent);
            }
        });

        TextView tvUpdatePassword = view.findViewById(R.id.tv_update_password);
        tvUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sp.getInt("userId",0)!=0) {//若该用户已登录
                    Intent intent=new Intent(getContext(),UpdatePasswordActivity.class);
                    startActivity(intent);
                }else{//用户还没有登陆，跳转到登陆页面
                    Intent intent = new Intent(getContext(),LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }





}
