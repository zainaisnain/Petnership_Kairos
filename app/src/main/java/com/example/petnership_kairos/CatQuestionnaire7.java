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

public class CatQuestionnaire7 extends AppCompatActivity {
    ImageButton popupc7;
    Dialog helpDialogc7;
    SeekBar cseekBar38, cseekBar39, cseekBar40, cseekBar41, cseekBar42, cseekBar43;
    TextView crate38, crate39, crate40, crate41, crate42, crate43;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_questionnaire7);

            cseekBar38 = (SeekBar) findViewById(R.id.cseekBar38);
            cseekBar39 = (SeekBar) findViewById(R.id.cseekBar39);
            cseekBar40 = (SeekBar) findViewById(R.id.cseekBar40);
            cseekBar41 = (SeekBar) findViewById(R.id.cseekBar41);
            cseekBar42= (SeekBar) findViewById(R.id.cseekBar42);
            cseekBar43 = (SeekBar) findViewById(R.id.cseekBar43);
            crate38 = (TextView) findViewById(R.id.crating38);
            crate39 = (TextView) findViewById(R.id.crating39);
            crate40 = (TextView) findViewById(R.id.crating40);
            crate41 = (TextView) findViewById(R.id.crating41);
            crate42 = (TextView) findViewById(R.id.crating42);
            crate43 = (TextView) findViewById(R.id.crating43);


            cseekBar38.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar cseekBar38, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate38.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate38.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate38.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate38.setText("Slightly Important");
                    } else {
                        crate38.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar38) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar38) {

                }
            });

            cseekBar39.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar39, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate39.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate39.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate39.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate39.setText("Slightly Important");
                    } else {
                        crate39.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar39) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar39) {

                }
            });

            cseekBar40.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar40, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate40.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate40.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate40.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate40.setText("Slightly Important");
                    } else {
                        crate40.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar40) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar40) {

                }
            });

            cseekBar41.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar41, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate41.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate41.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate41.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate41.setText("Slightly Important");
                    } else {
                        crate41.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar41) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar41) {

                }
            });

            cseekBar42.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar42, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate42.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate42.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate42.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate42.setText("Slightly Important");
                    } else {
                        crate42.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar42) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar42) {

                }
            });

        cseekBar43.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar43, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate43.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate43.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate43.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate43.setText("Slightly Important");
                    } else {
                        crate43.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar43) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar43) {

                }
            });



            popupc7 = findViewById(R.id.instructionsBTNc7);
            helpDialogc7 = new Dialog(this);
            popupc7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helpDialogc7.setContentView(R.layout.help_popup);
                    helpDialogc7.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    helpDialogc7.show();
                }
            });
        }
    }