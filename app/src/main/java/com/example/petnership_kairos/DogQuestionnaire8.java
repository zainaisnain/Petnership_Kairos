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

public class DogQuestionnaire8 extends AppCompatActivity {
    ImageButton popup8;
    Dialog helpDialog8;
    SeekBar seekBar45, seekBar46, seekBar47, seekBar48, seekBar49;
    TextView rate45, rate46, rate47, rate48, rate49;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_questionnaire8);


            seekBar45 = (SeekBar) findViewById(R.id.seekBar45);
            seekBar46 = (SeekBar) findViewById(R.id.seekBar46);
            seekBar47 = (SeekBar) findViewById(R.id.seekBar47);
            seekBar48 = (SeekBar) findViewById(R.id.seekBar48);
            seekBar49 = (SeekBar) findViewById(R.id.seekBar49);
            rate45 = (TextView) findViewById(R.id.rating45);
            rate46 = (TextView) findViewById(R.id.rating46);
            rate47 = (TextView) findViewById(R.id.rating47);
            rate48 = (TextView) findViewById(R.id.rating48);
            rate49 = (TextView) findViewById(R.id.rating49);

            seekBar45.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar seekBar45, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate45.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate45.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate45.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate45.setText("Slightly Important");
                    } else {
                        rate45.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar45) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar45) {

                }
            });

            seekBar46.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar46, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate46.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate46.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate46.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate46.setText("Slightly Important");
                    } else {
                        rate46.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar46) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar46) {

                }
            });

            seekBar47.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar47, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate47.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate47.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate47.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate47.setText("Slightly Important");
                    } else {
                        rate47.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar47) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar47) {

                }
            });

            seekBar48.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar48, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate48.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate48.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate48.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate48.setText("Slightly Important");
                    } else {
                        rate48.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar48) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar48) {

                }
            });

            seekBar49.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar49, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate49.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate49.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate49.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate49.setText("Slightly Important");
                    } else {
                        rate49.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar49) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar49) {

                }
            });



            popup8 = findViewById(R.id.instructionsBTN8);
            helpDialog8 = new Dialog(this);
            popup8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helpDialog8.setContentView(R.layout.help_popup);
                    helpDialog8.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    helpDialog8.show();
                }
            });

        }
    }