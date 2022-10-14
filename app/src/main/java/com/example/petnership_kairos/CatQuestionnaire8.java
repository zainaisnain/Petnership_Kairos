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

public class CatQuestionnaire8 extends AppCompatActivity {
    ImageButton popupc8;
    Dialog helpDialogc8;
    SeekBar cseekBar44, cseekBar45, cseekBar46, cseekBar47, cseekBar48, cseekBar49;
    TextView crate44, crate45, crate46, crate47, crate48, crate49;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_questionnaire8);

            cseekBar44 = (SeekBar) findViewById(R.id.cseekBar44);
            cseekBar45 = (SeekBar) findViewById(R.id.cseekBar45);
            cseekBar46 = (SeekBar) findViewById(R.id.cseekBar46);
            cseekBar47 = (SeekBar) findViewById(R.id.cseekBar47);
            cseekBar48= (SeekBar) findViewById(R.id.cseekBar48);
            cseekBar49 = (SeekBar) findViewById(R.id.cseekBar49);
            crate44 = (TextView) findViewById(R.id.crating44);
            crate45 = (TextView) findViewById(R.id.crating45);
            crate46 = (TextView) findViewById(R.id.crating46);
            crate47 = (TextView) findViewById(R.id.crating47);
            crate48 = (TextView) findViewById(R.id.crating48);
            crate49 = (TextView) findViewById(R.id.crating49);


            cseekBar44.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar cseekBar44, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate44.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate44.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate44.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate44.setText("Slightly Important");
                    } else {
                        crate44.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar44) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar44) {

                }
            });

            cseekBar45.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar45, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate45.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate45.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate45.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate45.setText("Slightly Important");
                    } else {
                        crate45.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar45) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar45) {

                }
            });

            cseekBar46.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar46, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate46.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate46.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate46.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate46.setText("Slightly Important");
                    } else {
                        crate46.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar46) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar46) {

                }
            });

            cseekBar47.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar47, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate47.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate47.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate47.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate47.setText("Slightly Important");
                    } else {
                        crate47.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar47) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar47) {

                }
            });

            cseekBar48.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar48, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate48.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate48.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate48.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate48.setText("Slightly Important");
                    } else {
                        crate48.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar48) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar48) {

                }
            });

            cseekBar49.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar49, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        crate49.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        crate49.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        crate49.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        crate49.setText("Slightly Important");
                    } else {
                        crate49.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar49) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar cseekBar49) {

                }
            });



            popupc8 = findViewById(R.id.instructionsBTNc8);
            helpDialogc8 = new Dialog(this);
            popupc8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helpDialogc8.setContentView(R.layout.help_popup);
                    helpDialogc8.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    helpDialogc8.show();
                }
            });
        }
    }