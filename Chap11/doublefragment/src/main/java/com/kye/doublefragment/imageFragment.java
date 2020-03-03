package com.kye.doublefragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class imageFragment extends Fragment {

    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_image,container,false);
        imageView = viewGroup.findViewById(R.id.imageView);


        return viewGroup;
    }
    public void setImage(int id){
        imageView.setImageResource(id);
    }


}
