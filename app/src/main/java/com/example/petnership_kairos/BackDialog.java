package com.example.petnership_kairos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
public class BackDialog extends DialogFragment {
    public View view;
    Button btnNo, btnYes;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_back_dialog, container, false);

        btnNo = view.findViewById(R.id.buttonNo);
        btnNo.setOnClickListener(view -> BackDialog.this.dismiss());
        btnYes = view.findViewById(R.id.buttonYes);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                getParentFragmentManager().popBackStack();
                dismiss();
            }
        });



        return view;

    }

}