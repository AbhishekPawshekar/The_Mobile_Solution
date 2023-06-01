package com.example.the_mobile_solution;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends PagerAdapter  {

 public String productimageurl,productmoreimageurl1,productmoreimageurl2,productmoreimageurl3;
    private Context mContext;
    private Bitmap[]mis_fotos;

    public ImageAdapter(Context mContext,String productimageurl,String productmoreimageurl1,String productmoreimageurl2,String productmoreimageurl3) {
        this.mContext = mContext;
        this.productimageurl=productimageurl;
        this.productmoreimageurl1=productmoreimageurl1;
        this.productmoreimageurl2=productmoreimageurl2;
        this.productmoreimageurl3=productmoreimageurl3;
    }

    public int getCount() {
        get_images();
        return mis_fotos.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    public Object getItem(int position) {
        return null;    }

    public long getItemId(int position) {
        return 0;    }

    public Object instantiateItem(@NonNull ViewGroup container,int position)     {
        ImageView imageView;
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        imageView.setImageBitmap(mis_fotos[position]);
        return imageView;
    }

    private void get_images(){

        mis_fotos= new Bitmap[Integer.parseInt(productimageurl)];


    }}