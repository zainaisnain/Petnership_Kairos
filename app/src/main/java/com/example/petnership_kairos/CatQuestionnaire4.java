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

public class CatQuestionnaire4 extends AppCompatActivity {
    ImageButton popupc4;
    Dialog helpDialogc4;
    SeekBar cseekBar22, cseekBar23, cseekBar24, cseekBar25, cseekBar26, cseekBar27, cseekBar28;
    TextView crate22, crate23, crate24, crate25, crate26, crate27, crate28;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_questionnaire4);
        cseekBar22 = (SeekBar) findViewById(R.id.cseekBar22);
        cseekBar23 = (SeekBar) findViewById(R.id.cseekBar23);
        cseekBar24 = (SeekBar) findViewById(R.id.cseekBar24);
        cseekBar25 = (SeekBar) findViewById(R.id.cseekBar25);
        cseekBar26= (SeekBar) findViewById(R.id.cseekBar26);
        cseekBar27 = (SeekBar) findViewById(R.id.cseekBar27);
        cseekBar28 = (SeekBar) findViewById(R.id.cseekBar28);
        crate22 = (TextView) findViewById(R.id.crating22);
        crate23 = (TextView) findViewById(R.id.crating23);
        crate24 = (TextView) findViewById(R.id.crating24);
        crate25 = (TextView) findViewById(R.id.crating25);
        crate26 = (TextView) findViewById(R.id.crating26);
        crate27 = (TextView) findViewById(R.id.crating27);
        crate28 = (TextView) findViewById(R.id.crating28);


        cseekBar22.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar cseekBar22, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate22.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate22.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate22.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate22.setText("Slightly Important");
                } else {
                    crate22.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar22) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar22) {

            }
        });

        cseekBar23.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar23, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate23.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate23.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate23.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate23.setText("Slightly Important");
                } else {
                    crate23.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar23) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar23) {

            }
        });

        cseekBar24.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar24, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate24.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate24.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate24.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate24.setText("Slightly Important");
                } else {
                    crate24.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar24) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar24) {

            }
        });

        cseekBar25.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar25, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate25.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate25.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate25.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate25.setText("Slightly Important");
                } else {
                    crate25.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar25) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar25) {

            }
        });

        cseekBar26.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar26, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate26.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate26.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate26.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate26.setText("Slightly Important");
                } else {
                    crate26.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar26) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar26) {

            }
        });

        cseekBar27.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar27, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate27.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate27.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate27.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate27.setText("Slightly Important");
                } else {
                    crate27.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar27) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar27) {

            }
        });

        cseekBar28.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar28, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate28.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate28.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate28.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate28.setText("Slightly Important");
                } else {
                    crate28.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar28) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar28) {

            }
        });

        popupc4 = findViewById(R.id.instructionsBTNc4);
        helpDialogc4 = new Dialog(this);
        popupc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helpDialogc4.setContentView(R.layout.help_popup);
                helpDialogc4.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                helpDialogc4.show();
            }
        });
    }
}