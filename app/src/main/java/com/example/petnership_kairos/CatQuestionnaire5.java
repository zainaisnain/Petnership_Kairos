package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class CatQuestionnaire5 extends AppCompatActivity {
    ImageButton popupc5;
    SeekBar cseekBarr28, cseekBar29, cseekBar30, cseekBar31;
    TextView cratee28, crate29, crate30, crate31;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_questionnaire5);


        cseekBarr28 = (SeekBar) findViewById(R.id.cseekBarr28);
        cseekBar29 = (SeekBar) findViewById(R.id.cseekBar29);
        cseekBar30 = (SeekBar) findViewById(R.id.cseekBar30);
        cseekBar31 = (SeekBar) findViewById(R.id.cseekBar31);
        cratee28 = (TextView) findViewById(R.id.cratingg28);
        crate29 = (TextView) findViewById(R.id.crating29);
        crate30 = (TextView) findViewById(R.id.crating30);
        crate31 = (TextView) findViewById(R.id.crating31);


        cseekBarr28.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar cseekBarr28, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    cratee28.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    cratee28.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    cratee28.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    cratee28.setText("Slightly Important");
                } else {
                    cratee28.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBarr28) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBarr28) {

            }
        });

        cseekBar29.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar29, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate29.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate29.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate29.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate29.setText("Slightly Important");
                } else {
                    crate29.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar29) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar29) {

            }
        });

        cseekBar30.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar30, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate30.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate30.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate30.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate30.setText("Slightly Important");
                } else {
                    crate30.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar30) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar30) {

            }
        });

        cseekBar31.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar31, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate31.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate31.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate31.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate31.setText("Slightly Important");
                } else {
                    crate31.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar31) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar31) {

            }
        });


        popupc5 = findViewById(R.id.instructionsBTNc5);
        popupc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    private void showDialog() {
        final Dialog helpDialogc5 = new Dialog(this);
        helpDialogc5.setContentView(R.layout.help_popup);
        helpDialogc5.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        helpDialogc5.show();
        ImageButton closeBTN = (ImageButton) helpDialogc5.findViewById(R.id.closeBTN);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                helpDialogc5.dismiss();

            }
        });
    }

}