package com.cwx.timebank.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ShowHeadImg extends AsyncTask<Object,Object,Bitmap>{
    private ImageView headImg;
    private String imgUrl;
    public ShowHeadImg(ImageView headImg,String imgUrl){
        this.headImg = headImg;
        this.imgUrl = imgUrl;
    }

    @Override
    protected Bitmap doInBackground(Object[] objects) {
        Bitmap bitmap = returnBitMap(imgUrl);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap!=null && headImg!=null){
            headImg.setImageBitmap(bitmap);
        }

    }

    public Bitmap returnBitMap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        if(url!=null){
            try {
                myFileUrl = new URL(url);
            } catch (MalformedURLException e){
                e.printStackTrace();
            }
            try {
                HttpURLConnection conn = null;
                if(myFileUrl!=null){
                    conn = (HttpURLConnection) myFileUrl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }
}
