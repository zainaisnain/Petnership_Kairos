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

public class DogQuestionnaire9 extends AppCompatActivity {
    ImageButton popup9;
    Dialog helpDialog9;
    SeekBar seekBar50, seekBar51, seekBar52, seekBar53;
    TextView rate50, rate51, rate52, rate53;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_questionnaire9);

        
            seekBar50 = (SeekBar) findViewById(R.id.seekBar50);
            seekBar51 = (SeekBar) findViewById(R.id.seekBar51);
            seekBar52 = (SeekBar) findViewById(R.id.seekBar52);
            seekBar53 = (SeekBar) findViewById(R.id.seekBar53);
            rate50 = (TextView) findViewById(R.id.rating50);
            rate51 = (TextView) findViewById(R.id.rating51);
            rate52 = (TextView) findViewById(R.id.rating52);
            rate53 = (TextView) findViewById(R.id.rating53);

            seekBar50.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar seekBar50, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate50.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate50.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate50.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate50.setText("Slightly Important");
                    } else {
                        rate50.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar50) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar50) {

                }
            });

            seekBar51.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar51, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate51.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate51.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate51.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate51.setText("Slightly Important");
                    } else {
                        rate51.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar51) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar51) {

                }
            });

            seekBar52.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar52, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate52.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate52.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate52.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate52.setText("Slightly Important");
                    } else {
                        rate52.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar52) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar52) {

                }
            });

            seekBar53.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar53, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate53.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate53.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate53.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate53.setText("Slightly Important");
                    } else {
                        rate53.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar53) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar53) {

                }
            });
            


            popup9 = findViewById(R.id.instructionsBTN9);
            helpDialog9 = new Dialog(this);
            popup9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helpDialog9.setContentView(R.layout.help_popup);
                    helpDialog9.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    helpDialog9.show();
                }
            });

        }
    }