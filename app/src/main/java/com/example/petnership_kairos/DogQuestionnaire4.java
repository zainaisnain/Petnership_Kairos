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

public class DogQuestionnaire4 extends AppCompatActivity {
    ImageButton popup4;
    Dialog helpDialog4;
    SeekBar seekBar22, seekBar23, seekBar24, seekBar25, seekBar26;
    TextView rate22, rate23, rate24, rate25, rate26;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_questionnaire4);

        seekBar22 = (SeekBar) findViewById(R.id.seekBar22);
        seekBar23 = (SeekBar) findViewById(R.id.seekBar23);
        seekBar24 = (SeekBar) findViewById(R.id.seekBar24);
        seekBar25 = (SeekBar) findViewById(R.id.seekBar25);
        seekBar26 = (SeekBar) findViewById(R.id.seekBar26);
        rate22 = (TextView) findViewById(R.id.rating22);
        rate23 = (TextView) findViewById(R.id.rating23);
        rate24 = (TextView) findViewById(R.id.rating24);
        rate25 = (TextView) findViewById(R.id.rating25);
        rate26 = (TextView) findViewById(R.id.rating26);
        
        seekBar22.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar22, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate22.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate22.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate22.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate22.setText("Slightly Important");
                } else {
                    rate22.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar22) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar22) {

            }
        });

        seekBar23.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar23, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate23.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate23.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate23.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate23.setText("Slightly Important");
                } else {
                    rate23.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar23) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar23) {

            }
        });

        seekBar24.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar24, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate24.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate24.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate24.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate24.setText("Slightly Important");
                } else {
                    rate24.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar24) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar24) {

            }
        });

        seekBar25.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar25, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate25.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate25.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate25.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate25.setText("Slightly Important");
                } else {
                    rate25.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar25) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar25) {

            }
        });

        seekBar26.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar26, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate26.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate26.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate26.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate26.setText("Slightly Important");
                } else {
                    rate26.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar26) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar26) {

            }
        });
        
        

        popup4 = findViewById(R.id.instructionsBTN4);
        helpDialog4 = new Dialog(this);
        popup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helpDialog4.setContentView(R.layout.help_popup);
                helpDialog4.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                helpDialog4.show();
            }
        });

    }
}