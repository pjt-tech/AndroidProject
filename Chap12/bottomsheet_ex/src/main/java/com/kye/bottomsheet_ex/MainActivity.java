package com.kye.bottomsheet_ex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomSheetBehavior<View> persistentBottomSheet;
    ArrayList<Item> items;

    public static BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        items.add(new Item(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_lab4_1,null),"Keep"));
        items.add(new Item(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_lab4_2,null),"Inbox"));
        items.add(new Item(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_lab4_3,null),"Messenger"));
        items.add(new Item(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_lab4_4,null),"google"));
        View view = findViewById(R.id.bottom_Sheet);
        persistentBottomSheet = BottomSheetBehavior.from(view);

        persistentBottomSheet.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(persistentBottomSheet.getState()==BottomSheetBehavior.STATE_HIDDEN){
                    persistentBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }


    public void onModal(View v) {

        MyAdapter adapter = new MyAdapter(items,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        View view = getLayoutInflater().inflate(R.layout.layout_recycler,null);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

    }
}
