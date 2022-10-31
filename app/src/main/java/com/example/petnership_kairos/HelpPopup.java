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
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class HelpPopup extends DialogFragment {
    public View view;
    ImageButton btnOk;
    private String type, animal;


    public HelpPopup() {
    }
    public HelpPopup(String animal, String type) {
        this.animal = animal;
        this.type = type;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (animal.equals("Cat") && type.equals("Main")) {
            view = inflater.inflate(R.layout.fragment_help_popup_cat_main, container, false);
        }
        else if (animal.equals("Dog") && type.equals("Main")) {
            view = inflater.inflate(R.layout.fragment_help_popup_dog_main, container, false);
        }
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        btnOk = view.findViewById(R.id.closeBTN);
        btnOk.setOnClickListener(view -> HelpPopup.this.dismiss());

        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(true);

        Window window = getDialog().getWindow();

        // set "origin" to top left corner, so to speak
        window.setGravity(Gravity.TOP|Gravity.LEFT);

        // after that, setting values for x and y works "naturally"
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 100;
        params.y = 350;
        window.setAttributes(params);




        return view;
    }
}