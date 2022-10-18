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

public class DogQuestionnaire10 extends AppCompatActivity {
    ImageButton popup10;
    SeekBar seekBar54, seekBar55, seekBar56, seekBar57, seekBar58, seekBar59;
    TextView rate54, rate55, rate56, rate57, rate58, rate59;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_questionnaire10);
        
            seekBar54 = (SeekBar) findViewById(R.id.seekBar54);
            seekBar55 = (SeekBar) findViewById(R.id.seekBar55);
            seekBar56 = (SeekBar) findViewById(R.id.seekBar56);
            seekBar57 = (SeekBar) findViewById(R.id.seekBar57);
            seekBar58 = (SeekBar) findViewById(R.id.seekBar58);
            seekBar59 = (SeekBar) findViewById(R.id.seekBar59);
            rate54 = (TextView) findViewById(R.id.rating54);
            rate55 = (TextView) findViewById(R.id.rating55);
            rate56 = (TextView) findViewById(R.id.rating56);
            rate57 = (TextView) findViewById(R.id.rating57);
            rate58 = (TextView) findViewById(R.id.rating58);
            rate59 = (TextView) findViewById(R.id.rating59);


            seekBar54.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar seekBar54, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate54.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate54.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate54.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate54.setText("Slightly Important");
                    } else {
                        rate54.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar54) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar54) {

                }
            });

            seekBar55.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar55, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate55.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate55.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate55.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate55.setText("Slightly Important");
                    } else {
                        rate55.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar55) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar55) {

                }
            });

            seekBar56.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar56, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate56.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate56.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate56.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate56.setText("Slightly Important");
                    } else {
                        rate56.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar56) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar56) {

                }
            });

            seekBar57.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar57, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate57.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate57.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate57.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate57.setText("Slightly Important");
                    } else {
                        rate57.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar57) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar57) {

                }
            });

            seekBar58.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar58, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate58.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate58.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate58.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate58.setText("Slightly Important");
                    } else {
                        rate58.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar58) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar58) {

                }
            });

            seekBar59.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar59, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate59.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate59.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate59.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate59.setText("Slightly Important");
                    } else {
                        rate59.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar59) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar59) {

                }
            });


            popup10 = findViewById(R.id.instructionsBTN10);
            popup10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog();
                }
            });

    }

    private void showDialog() {
        final Dialog helpDialog10 = new Dialog(this);
        helpDialog10.setContentView(R.layout.help_popup);
        helpDialog10.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        helpDialog10.show();
        ImageButton closeBTN = (ImageButton) helpDialog10.findViewById(R.id.closeBTN);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                helpDialog10.dismiss();

            }
        });
    }

}