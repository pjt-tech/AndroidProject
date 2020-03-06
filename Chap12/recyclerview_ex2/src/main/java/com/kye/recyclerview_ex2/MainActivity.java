package com.kye.recyclerview_ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.aa,"#1"));
        items.add(new Item(R.drawable.bb,"#2"));
        items.add(new Item(R.drawable.cc,"#3"));
        items.add(new Item(R.drawable.dd,"#4"));
        items.add(new Item(R.drawable.ee,"#5"));

        MyAdapter adapter = new MyAdapter(items,this);
        recyclerView.setAdapter(adapter);
    }
}
