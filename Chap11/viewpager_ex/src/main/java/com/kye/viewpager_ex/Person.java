package com.kye.viewpager_ex;


        import android.content.Context;
        import android.view.LayoutInflater;
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
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_pager,this,true);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
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
