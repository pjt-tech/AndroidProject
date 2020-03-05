package com.kye.chap12;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class DialogFragment extends androidx.fragment.app.DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("DialogFragment");
        builder.setMessage("Dialog Fragment 테스트중입니다.");
        builder.setPositiveButton("확인",null);
        return builder.create();

    }
}
