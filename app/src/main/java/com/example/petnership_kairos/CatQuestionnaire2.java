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

public class CatQuestionnaire2 extends AppCompatActivity {
    ImageButton popupc2;
    Dialog helpDialogc2;
    SeekBar cseekBar8, cseekBar9, cseekBar10, cseekBar11, cseekBar12, cseekBar13,cseekBar14;
    TextView crate8, crate9, crate10, crate11, crate12, crate13, crate14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_questionnaire2);

            cseekBar8 = (SeekBar) findViewById(R.id.cseekBar8);
            cseekBar9 = (SeekBar) findViewById(R.id.cseekBar9);
            cseekBar10 = (SeekBar) findViewById(R.id.cseekBar10);
            cseekBar11 = (SeekBar) findViewById(R.id.cseekBar11);
            cseekBar12 = (SeekBar) findViewById(R.id.cseekBar12);
            cseekBar13 = (SeekBar) findViewById(R.id.cseekBar13);
            cseekBar14 = (SeekBar) findViewById(R.id.cseekBar14);
            crate8 = (TextView) findViewById(R.id.crating8);
            crate9 = (TextView) findViewById(R.id.crating9);
            crate10 = (TextView) findViewById(R.id.crating10);
            crate11 = (TextView) findViewById(R.id.crating11);
            crate12 = (TextView) findViewById(R.id.crating12);
            crate13 = (TextView) findViewById(R.id.crating13);
            crate14 = (TextView) findViewById(R.id.crating14);


            cseekBar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar cseekBar8, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate8.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate8.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate8.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate8.setText("Slightly Important");
                    } else {
                        crate8.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar8) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar8) {

                }
            });

            cseekBar9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar9, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate9.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate9.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate9.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate9.setText("Slightly Important");
                    } else {
                        crate9.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar9) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar9) {

                }
            });

            cseekBar10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar10, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate10.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate10.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate10.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate10.setText("Slightly Important");
                    } else {
                        crate10.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar10) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar10) {

                }
            });

            cseekBar11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar11, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate11.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate11.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate11.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate11.setText("Slightly Important");
                    } else {
                        crate11.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar11) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar11) {

                }
            });

            cseekBar12.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar12, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate12.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate12.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate12.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate12.setText("Slightly Important");
                    } else {
                        crate12.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar12) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar12) {

                }
            });

            cseekBar13.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar13, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate13.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate13.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate13.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate13.setText("Slightly Important");
                    } else {
                        crate13.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar13) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar13) {

                }
            });

            cseekBar14.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar14, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate14.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate14.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate14.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate14.setText("Slightly Important");
                    } else {
                        crate14.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar14) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar14) {

                }
            });

            popupc2 = findViewById(R.id.instructionsBTNc2);
            helpDialogc2 = new Dialog(this);
            popupc2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helpDialogc2.setContentView(R.layout.help_popup);
                    helpDialogc2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    helpDialogc2.show();
                }
            });

    }
}