package com.example.petnership_kairos;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class InstructionsPopup extends DialogFragment {
    public View view;
    Button btnOk;
    private String type, animal;


    public InstructionsPopup() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_instructions_popup_part2, container, false);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        btnOk = view.findViewById(R.id.popup2dismiss);
        btnOk.setOnClickListener(view -> InstructionsPopup.this.dismiss());


        Window window = getDialog().getWindow();


        return view;
    }
}