package com.example.parktaejun.bankery;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryActivity extends Activity {

    ImageView selectedImage;
    TextView money;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        final String m = getIntent().getStringExtra("yeartext");

        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        selectedImage = (ImageView) findViewById(R.id.imageView);

        money = (TextView)findViewById(R.id.money);

        gallery.setSpacing(1);

        final GalleryAdapter galleryImageAdapter = new GalleryAdapter(getApplicationContext());
        gallery.setAdapter(galleryImageAdapter);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // show the selected Image

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inSampleSize = 4;
                Bitmap bm = BitmapFactory.decodeFile(galleryImageAdapter.pathArray.get(position), options);
                selectedImage.setImageBitmap(bm);

                money.setText(m);

            }
        });


    }

}
