package com.example.parktaejun.bankery;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.preference.PreferenceManager;
import android.support.test.espresso.core.deps.guava.reflect.TypeToken;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Type;
import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by parktaejun on 2016. 12. 29..
 */

public class GalleryAdapter extends BaseAdapter {

    private Context mContext;


    public GalleryAdapter(Context context) {
        mContext = context;

        pref = PreferenceManager.getDefaultSharedPreferences(context);
        loadPathArray();
        loadLocationArray();
    }

    public int getCount() {
        return pathArray.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }


    // Override this method according to your need
    public View getView(int index, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        ImageView image = new ImageView(mContext);


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bm = BitmapFactory.decodeFile(pathArray.get(index) + "_small", options);
        image.setImageBitmap(bm);

        image.setLayoutParams(new Gallery.LayoutParams(200, 200));

        image.setScaleType(ImageView.ScaleType.FIT_XY);


        return image;
    }


    private SharedPreferences pref;

    ArrayList<String> pathArray;
    ArrayList<Double> latArray;
    ArrayList<Double> lonArray;

    private void loadPathArray(){
        Type t = new TypeToken<ArrayList<String>>(){}.getType();
        pathArray = new Gson().fromJson(pref.getString("pathArray", "[]"), t);
    }

    private void loadLocationArray() {
        Type t = new TypeToken<ArrayList<Double>>() {}.getType();
        latArray = new Gson().fromJson(pref.getString("latArray", "[]"), t);
        lonArray = new Gson().fromJson(pref.getString("lonArray", "[]"), t);
    }
}
