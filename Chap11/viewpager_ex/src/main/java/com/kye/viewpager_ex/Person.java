package com.kye.viewpager_ex;


        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

public class Person extends LinearLayout {

    Context context;
    TextView textView;
    Button button;
    ImageView imageView;
    int imageId;

    public Person(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public void init(){
        final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_pager,this,true);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String call_Num = (String)button.getTag();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel : " +call_Num));
                context.startActivity(intent);
            }
        });

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (imageId){
                    case R.drawable.jeju1 :
                        Intent intent = new Intent(context.getApplicationContext(),Jeju1Activity.class);
                        context.startActivity(intent);
                        break;
                }
            }
        });

    }

    public void setName(String name) {
        textView.setText(name);
    }

    public void setCall_Btn(String number) {
        button.setText(number);
        button.setTag(number);
    }

    public void setImage(int id){
        imageView.setImageResource(id);
        imageId = id;
    }
}
