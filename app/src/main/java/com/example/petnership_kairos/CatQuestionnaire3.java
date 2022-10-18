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

public class CatQuestionnaire3 extends AppCompatActivity {
    ImageButton popupc3;
    SeekBar cseekBar15, cseekBar16, cseekBar17, cseekBar18, cseekBar19, cseekBar20,cseekBar21;
    TextView crate15, crate16, crate17, crate18, crate19, crate20, crate21;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_questionnaire3);

        cseekBar15 = (SeekBar) findViewById(R.id.cseekBar15);
        cseekBar16 = (SeekBar) findViewById(R.id.cseekBar16);
        cseekBar17 = (SeekBar) findViewById(R.id.cseekBar17);
        cseekBar18 = (SeekBar) findViewById(R.id.cseekBar18);
        cseekBar19 = (SeekBar) findViewById(R.id.cseekBar19);
        cseekBar20 = (SeekBar) findViewById(R.id.cseekBar20);
        cseekBar21 = (SeekBar) findViewById(R.id.cseekBar21);
        crate15 = (TextView) findViewById(R.id.crating15);
        crate16 = (TextView) findViewById(R.id.crating16);
        crate17 = (TextView) findViewById(R.id.crating17);
        crate18 = (TextView) findViewById(R.id.crating18);
        crate19 = (TextView) findViewById(R.id.crating19);
        crate20 = (TextView) findViewById(R.id.crating20);
        crate21 = (TextView) findViewById(R.id.crating21);


        cseekBar15.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar cseekBar15, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate15.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate15.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate15.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate15.setText("Slightly Important");
                } else {
                    crate15.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar15) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar15) {

            }
        });

        cseekBar16.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar16, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate16.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate16.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate16.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate16.setText("Slightly Important");
                } else {
                    crate16.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar16) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar16) {

            }
        });

        cseekBar17.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar17, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate17.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate17.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate17.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate17.setText("Slightly Important");
                } else {
                    crate17.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar17) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar17) {

            }
        });

        cseekBar18.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar18, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate18.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate18.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate18.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate18.setText("Slightly Important");
                } else {
                    crate18.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar18) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar18) {

            }
        });

        cseekBar19.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar19, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate19.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate19.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate19.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate19.setText("Slightly Important");
                } else {
                    crate19.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar19) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar19) {

            }
        });

        cseekBar20.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar20, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate20.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate20.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate20.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate20.setText("Slightly Important");
                } else {
                    crate20.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar20) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar20) {

            }
        });

        cseekBar21.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar21, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    crate21.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    crate21.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    crate21.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    crate21.setText("Slightly Important");
                } else {
                    crate21.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar21) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar21) {

            }
        });

        popupc3 = findViewById(R.id.instructionsBTNc3);
        popupc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    private void showDialog() {
        final Dialog helpDialogc3 = new Dialog(this);
        helpDialogc3.setContentView(R.layout.help_popup);
        helpDialogc3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        helpDialogc3.show();
        ImageButton closeBTN = (ImageButton) helpDialogc3.findViewById(R.id.closeBTN);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                helpDialogc3.dismiss();

            }
        });
    }

}