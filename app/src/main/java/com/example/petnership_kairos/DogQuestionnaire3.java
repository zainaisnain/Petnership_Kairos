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

public class DogQuestionnaire3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton popup3;
        Dialog helpDialog3;
        SeekBar seekBar15, seekBar16, seekBar17, seekBar18, seekBar19, seekBar20,seekBar21;
        TextView rate15, rate16, rate17, rate18, rate19, rate20, rate21;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_questionnaire3);


            seekBar15 = (SeekBar) findViewById(R.id.seekBar15);
            seekBar16 = (SeekBar) findViewById(R.id.seekBar16);
            seekBar17 = (SeekBar) findViewById(R.id.seekBar17);
            seekBar18 = (SeekBar) findViewById(R.id.seekBar18);
            seekBar19 = (SeekBar) findViewById(R.id.seekBar19);
            seekBar20 = (SeekBar) findViewById(R.id.seekBar20);
            seekBar21 = (SeekBar) findViewById(R.id.seekBar21);
            rate15 = (TextView) findViewById(R.id.rating15);
            rate16 = (TextView) findViewById(R.id.rating16);
            rate17 = (TextView) findViewById(R.id.rating17);
            rate18 = (TextView) findViewById(R.id.rating18);
            rate19 = (TextView) findViewById(R.id.rating19);
            rate20 = (TextView) findViewById(R.id.rating20);
            rate21 = (TextView) findViewById(R.id.rating21);


            seekBar15.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar seekBar15, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate15.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate15.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate15.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate15.setText("Slightly Important");
                    } else {
                        rate15.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar15) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar15) {

                }
            });

            seekBar16.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar16, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate16.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate16.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate16.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate16.setText("Slightly Important");
                    } else {
                        rate16.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar16) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar16) {

                }
            });

            seekBar17.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar17, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate17.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate17.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate17.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate17.setText("Slightly Important");
                    } else {
                        rate17.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar17) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar17) {

                }
            });

            seekBar18.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar18, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate18.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate18.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate18.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate18.setText("Slightly Important");
                    } else {
                        rate18.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar18) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar18) {

                }
            });

            seekBar19.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar19, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate19.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate19.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate19.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate19.setText("Slightly Important");
                    } else {
                        rate19.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar19) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar19) {

                }
            });

            seekBar20.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar20, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate20.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate20.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate20.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate20.setText("Slightly Important");
                    } else {
                        rate20.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar20) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar20) {

                }
            });

            seekBar21.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar21, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate21.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate21.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate21.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate21.setText("Slightly Important");
                    } else {
                        rate21.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar21) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar21) {

                }
            });

            popup3 = findViewById(R.id.instructionsBTN3);
            helpDialog3 = new Dialog(this);
            popup3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helpDialog3.setContentView(R.layout.help_popup);
                    helpDialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    helpDialog3.show();
                }
            });

        }
}