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

public class CatQuestionnaire9 extends AppCompatActivity {
    ImageButton popupc9;
    Dialog helpDialogc9;
    SeekBar cseekBar50, cseekBar51, cseekBar52, cseekBar53, cseekBar54, cseekBar55;
    TextView crate50, crate51, crate52, crate53, crate54, crate55;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_questionnaire9);

            cseekBar50 = (SeekBar) findViewById(R.id.cseekBar50);
            cseekBar51 = (SeekBar) findViewById(R.id.cseekBar51);
            cseekBar52 = (SeekBar) findViewById(R.id.cseekBar52);
            cseekBar53 = (SeekBar) findViewById(R.id.cseekBar53);
            cseekBar54= (SeekBar) findViewById(R.id.cseekBar54);
            cseekBar55 = (SeekBar) findViewById(R.id.cseekBar55);
            crate50 = (TextView) findViewById(R.id.crating44);
            crate51 = (TextView) findViewById(R.id.crating45);
            crate52 = (TextView) findViewById(R.id.crating46);
            crate53 = (TextView) findViewById(R.id.crating47);
            crate54 = (TextView) findViewById(R.id.crating48);
            crate55 = (TextView) findViewById(R.id.crating49);


            cseekBar50.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar cseekBar50, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate50.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate50.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate50.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate50.setText("Slightly Important");
                    } else {
                        crate50.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar50) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar50) {

                }
            });

            cseekBar51.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar51, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate51.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate51.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate51.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate51.setText("Slightly Important");
                    } else {
                        crate51.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar51) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar51) {

                }
            });

            cseekBar52.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar52, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate52.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate52.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate52.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate52.setText("Slightly Important");
                    } else {
                        crate52.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar52) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar52) {

                }
            });

            cseekBar53.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar53, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate53.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate53.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate53.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate53.setText("Slightly Important");
                    } else {
                        crate53.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar53) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar53) {

                }
            });

            cseekBar54.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar54, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate54.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate54.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate54.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate54.setText("Slightly Important");
                    } else {
                        crate54.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar54) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar54) {

                }
            });

            cseekBar55.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar55, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate55.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate55.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate55.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate55.setText("Slightly Important");
                    } else {
                        crate55.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar55) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar55) {

                }
            });



            popupc9 = findViewById(R.id.instructionsBTN9);
            helpDialogc9 = new Dialog(this);
            popupc9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helpDialogc9.setContentView(R.layout.help_popup);
                    helpDialogc9.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    helpDialogc9.show();
                }
            });
        }
    }