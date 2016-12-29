package com.example.parktaejun.bankery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // create Intent to take a picture and return control to the calling application
        // 기본 내장 되어 있는 IMAGE CAPTURE 기능을 해당 app.에서 intent로 선언한다.
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 아래 정의한 capture한 사진의 저장 method를 실행 한 후
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        // 먼저 선언한 intent에 해당 file 명의 값을 추가로 저장한다.
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

        // start the image capture Intent
        // 해당 intent를 시작한다.
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }

/** Create a file Uri for saving an image or video */
    /** 저장할 image file의 이름(URI)을 값을 반환. onCreate()에서 fileUri 값에 반영되는 값을 반환하도록 설계되어 있음 */
    private static Uri getOutputMediaFileUri(int type){
        // 아래 capture한 사진이 저장될 file 공간을 생성하는 method를 통해 반환되는 File의 URI를 반환
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        // 외부 저장소에 이 App을 통해 촬영된 사진만 저장할 directory 경로와 File을 연결
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){ // 해당 directory가 아직 생성되지 않았을 경우 mkdirs(). 즉 directory를 생성한다.
            if (! mediaStorageDir.mkdirs()){ // 만약 mkdirs()가 제대로 동작하지 않을 경우, 오류 Log를 출력한 뒤, 해당 method 종료
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        // File 명으로 file의 생성 시간을 활용하도록 DateFormat 기능을 활용
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;

        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        }else {
            return null;
        }
        return mediaFile; // 생성된 File valuable을 반환
    }

}
