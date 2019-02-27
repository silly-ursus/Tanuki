package io.github.umbrafox.listapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent in = getIntent();
        int index = in.getIntExtra("io.github.umbrafox.listapp.ITEM_INDEX", -1);
        //why wouldn't negative index give out of bounds exception?

        if(index > -1){
            int pic = getImg(index);
            ImageView  img = (ImageView) findViewById(R.id.imageView);
            scaleImg(img, pic);
        }

    }

    private int getImg(int index){
        switch(index){
            case 0: return R.drawable.peach;
            case 1: return R.drawable.pear;
            case 2: return R.drawable.apple;
            case 3: return R.drawable.orange;
            case 4: return R.drawable.cherry;
            default: return -1;
        }
    }

    private void scaleImg(ImageView img, int pic){
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        //boundaries on
        BitmapFactory.decodeResource(getResources(), pic, options);
        //look at resources w/o drawing it

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if(imgWidth > screenWidth){
            int ratio = Math.round((float)imgWidth / (float)screenWidth);
            options.inSampleSize = ratio;
        }
        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);
    }

}
