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

public class CatQuestionnaire6 extends AppCompatActivity {
    ImageButton popupc6;
    Dialog helpDialogc6;
    SeekBar cseekBar32, cseekBar33, cseekBar34, cseekBar35, cseekBar36, cseekBar37;
    TextView crate32, crate33, crate34, crate35, crate36, crate37;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_questionnaire6);

            cseekBar32 = (SeekBar) findViewById(R.id.cseekBar32);
            cseekBar33 = (SeekBar) findViewById(R.id.cseekBar33);
            cseekBar34 = (SeekBar) findViewById(R.id.cseekBar34);
            cseekBar35 = (SeekBar) findViewById(R.id.cseekBar35);
            cseekBar36= (SeekBar) findViewById(R.id.cseekBar36);
            cseekBar37 = (SeekBar) findViewById(R.id.cseekBar37);
            crate32 = (TextView) findViewById(R.id.crating32);
            crate33 = (TextView) findViewById(R.id.crating33);
            crate34 = (TextView) findViewById(R.id.crating34);
            crate35 = (TextView) findViewById(R.id.crating35);
            crate36 = (TextView) findViewById(R.id.crating36);
            crate37 = (TextView) findViewById(R.id.crating37);


            cseekBar32.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar cseekBar32, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate32.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate32.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate32.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate32.setText("Slightly Important");
                    } else {
                        crate32.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar32) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar32) {

                }
            });

            cseekBar33.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar33, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate33.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate33.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate33.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate33.setText("Slightly Important");
                    } else {
                        crate33.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar33) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar33) {

                }
            });

            cseekBar34.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar34, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate34.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate34.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate34.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate34.setText("Slightly Important");
                    } else {
                        crate34.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar34) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar34) {

                }
            });

            cseekBar35.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar35, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate35.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate35.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate35.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate35.setText("Slightly Important");
                    } else {
                        crate35.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar35) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar35) {

                }
            });

            cseekBar36.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar36, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate36.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate36.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate36.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate36.setText("Slightly Important");
                    } else {
                        crate36.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar36) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar36) {

                }
            });

            cseekBar37.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar37, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate37.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate37.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate37.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate37.setText("Slightly Important");
                    } else {
                        crate37.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar37) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar37) {

                }
            });



            popupc6 = findViewById(R.id.instructionsBTNc6);
            helpDialogc6 = new Dialog(this);
            popupc6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helpDialogc6.setContentView(R.layout.help_popup);
                    helpDialogc6.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    helpDialogc6.show();
                }
            });
        }
    }