package com.kye.cameraapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener, ImageView.OnClickListener {

    TextureView textureView;
    ImageView imageView;
    Camera camera;
    List<Camera.Size> supportedPreviewSizes;
    Camera.Size previewSize;

    MediaRecorder mediaRecorder;
    boolean isRecoding;
    Drawable normalDr;
    Drawable activeDr;
    int result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO},200);
        }else{
            setContentView(R.layout.activity_main);
            textureView = findViewById(R.id.textureView);
            imageView = findViewById(R.id.btnShutter);
            textureView.setSurfaceTextureListener(this);
            imageView.setOnClickListener(this);
        }

        normalDr = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_normal,null);
        activeDr = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_pressed,null);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        camera = Camera.open(); //하드웨어 카메라를 이용할 수 있도록 점유
        Camera.Parameters parameters = camera.getParameters(); //카메라 설정 작업
        supportedPreviewSizes = parameters.getSupportedPreviewSizes(); //스마트폰의 카메라 지원 PreView 목록
        if(supportedPreviewSizes!=null){
            previewSize = CameraUtil.getOptimalPreviewSize(supportedPreviewSizes, width,height);
            parameters.setPreviewSize(previewSize.width, previewSize.height); //카메라가 뷰에 맞는 PreViewSize를 갖게 됨
        }
        result = CameraUtil.setCameraDisplayOrientation(this,0); //사진촬영시 획득되는 데이터의 방향 ,액티비티 방향에 맞게 설정됨
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE); //사진촬영시 계속 포커스를 맞춰 달라는 설정
        parameters.setRotation(result);
        camera.setParameters(parameters);
        camera.setDisplayOrientation(result);
        try {
            camera.setPreviewTexture(surface); //카메라 설정이 완료되면 카메라로부터 넘어오는 영상을 TextureView에게 전달해 줌
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        camera.stopPreview();
        camera.release();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==200 && grantResults.length>0){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED &&
                    grantResults[1]==PackageManager.PERMISSION_GRANTED &&
                    grantResults[2]==PackageManager.PERMISSION_GRANTED){
                setContentView(R.layout.activity_main);
                textureView = findViewById(R.id.textureView);
                imageView = findViewById(R.id.btnShutter);
                textureView.setSurfaceTextureListener(this);
                imageView.setOnClickListener(this);

            }
        }
    }

    @Override
    public void onClick(View v) {
        // 카메라로 사진찍기


        if(camera!=null){
            Toast.makeText(getApplicationContext(),"사진을 찍었습니다",Toast.LENGTH_SHORT).show();
            camera.takePicture(null, null, new Camera.PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data, Camera camera) {
                    FileOutputStream fos;

                    try {
                        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/myApp");
                        if(!dir.exists()){
                            dir.mkdir();
                        }
                        File file = File.createTempFile("IMG-",".jpg", dir);
                        if(!file.exists()){
                            file.createNewFile();
                        }
                        fos = new FileOutputStream(file);
                        fos.write(data);
                        fos.flush();
                        fos.close();
                        SystemClock.sleep(3000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    camera.startPreview();
                }
            });
        }
/*
        if(camera!=null){
            if(isRecoding){
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder=null;
                isRecoding=false;
                imageView.setImageDrawable(normalDr);
            }else{
                try {
                    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/myApp");
                    if(!dir.exists()){
                        dir.mkdir();
                    }
                    File file = File.createTempFile("VIDEO-",".mp4", dir);
                    mediaRecorder = new MediaRecorder();
                    camera.unlock();
                    mediaRecorder.setCamera(camera);
                    mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
                    mediaRecorder.setOutputFile(file.getAbsolutePath());
                    mediaRecorder.setOrientationHint(result);
                    mediaRecorder.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaRecorder.start();
                isRecoding=true;
                imageView.setImageDrawable(activeDr);
            }
        }
        */
    }
}
