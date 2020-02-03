package com.kye.j_chap16;

import android.content.Context;
import android.widget.Toast;

public class Benz extends CarPrototype {

    Context context;

    public Benz(Context context) {
        super(context);

    }

    @Override
    public void doRun() {
        Toast.makeText(context,"Benz의 doRun 메소드가 호출되었습니다.",Toast.LENGTH_LONG).show();
    }

}
