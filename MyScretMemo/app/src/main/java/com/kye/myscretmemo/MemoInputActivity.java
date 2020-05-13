package com.kye.myscretmemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoInputActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtMemo,edtName,edtNumber;
    TextView txtTitle,txtDate;
    ImageView imageView;
    Button btnPhone,btnSms,btnSave,btnCancel;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
    Intent intent;
    File curfile;
    Bitmap bitmap;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_input);

        edtMemo = findViewById(R.id.editText);
        edtName = findViewById(R.id.editText2);
        edtNumber = findViewById(R.id.editText3);

        txtTitle = findViewById(R.id.textView);
        txtDate = findViewById(R.id.txtDate);
        imageView = findViewById(R.id.imageView);

        btnPhone = findViewById(R.id.button4);
        btnSms = findViewById(R.id.button5);
        btnSave = findViewById(R.id.button6);
        btnCancel = findViewById(R.id.button7);

        btnPhone.setOnClickListener(this);
        btnSms.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        imageView.setOnClickListener(this);

        intent = getIntent();
        mode = intent.getStringExtra("mode");
        if(mode!=null && mode.equals("create")){
            txtTitle.setText("새 메모");
            Date date = new Date();
            String timeStamp = dateFormat.format(date);
            txtDate.setText(timeStamp);
        }else if(mode!=null && mode.equals("modify")){
            String contents = intent.getStringExtra("contents");
            String friendName = intent.getStringExtra("friendName");
            String friendMobile = intent.getStringExtra("friendMobile");
            String timeStamp = intent.getStringExtra("timeStamp");
            String imagePath = intent.getStringExtra("imagePath");

            txtTitle.setText("메모 수정");
            edtMemo.setText(contents);
            edtName.setText(friendName);
            edtNumber.setText(friendMobile);
            txtDate.setText(timeStamp);
            if(imagePath.equals("")){
                imageView.setImageResource(R.mipmap.ic_launcher);
            }else {
                curfile = new File(imagePath);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize=8;
                bitmap = BitmapFactory.decodeFile(curfile.getAbsolutePath(),options);
                Matrix matrix = new Matrix();
                matrix.postRotate(90);

                Bitmap rotateBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
                bitmap = rotateBitmap;
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public void onClick(View v){
        if(v==btnPhone){
            String friendMobile = edtNumber.getText().toString();
            try {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + friendMobile));
                startActivity(intent);
            }catch (SecurityException e){

            }
        }else if(v==btnSms){
            String friendMobile = edtNumber.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.putExtra("address",friendMobile);
            intent.setType("vnd.android-dir/mms-sms");
            startActivity(intent);

        }else if(v==btnSave){
            String contents = edtMemo.getText().toString();
            String friendName = edtName.getText().toString();
            String friendMobile = edtNumber.getText().toString();
            String timeStamp = txtDate.getText().toString();

            intent = new Intent();
            intent.putExtra("mode",mode);
            intent.putExtra("contents",contents);
            intent.putExtra("friendName",friendName);
            intent.putExtra("friendMobile",friendMobile);
            intent.putExtra("timeStamp",timeStamp);
            if(curfile!=null){
                intent.putExtra("imagePath",curfile.getAbsolutePath());
            }else{
                intent.putExtra("imagePath","");
            }
            setResult(RESULT_OK,intent);
            finish();

        }else if(v==btnCancel){

        }else if(v==imageView){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Date date = new Date();
            String fileStamp = fileFormat.format(date)+".jpg";
            curfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),fileStamp);
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.N){
                Uri fileURI = FileProvider.getUriForFile(getApplicationContext(),"com.kye.myscretmemo.fileprovider",curfile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,fileURI);
            }else{
                intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(curfile));
            }
            if(intent.resolveActivity(getPackageManager())!=null){
                startActivityForResult(intent,1004);
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1004){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize=8;
            bitmap = BitmapFactory.decodeFile(curfile.getAbsolutePath(),options);
            Matrix matrix = new Matrix();
            matrix.postRotate(90);

            Bitmap rotateBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            bitmap = rotateBitmap;
            imageView.setImageBitmap(bitmap);
        }
    }
}
