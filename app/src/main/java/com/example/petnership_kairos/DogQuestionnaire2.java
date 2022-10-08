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

public class DogQuestionnaire2 extends AppCompatActivity {
    ImageButton popup2;
    Dialog helpDialog2;
    SeekBar seekBar8, seekBar9, seekBar10, seekBar11, seekBar12, seekBar13,seekBar14;
    TextView rate8, rate9, rate10, rate11, rate12, rate13, rate14;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_questionnaire2);

        seekBar8 = (SeekBar) findViewById(R.id.seekBar8);
        seekBar9 = (SeekBar) findViewById(R.id.seekBar9);
        seekBar10 = (SeekBar) findViewById(R.id.seekBar10);
        seekBar11 = (SeekBar) findViewById(R.id.seekBar11);
        seekBar12 = (SeekBar) findViewById(R.id.seekBar12);
        seekBar13 = (SeekBar) findViewById(R.id.seekBar13);
        seekBar14 = (SeekBar) findViewById(R.id.seekBar14);
        rate8 = (TextView) findViewById(R.id.rating8);
        rate9 = (TextView) findViewById(R.id.rating9);
        rate10 = (TextView) findViewById(R.id.rating10);
        rate11 = (TextView) findViewById(R.id.rating11);
        rate12 = (TextView) findViewById(R.id.rating12);
        rate13 = (TextView) findViewById(R.id.rating13);
        rate14 = (TextView) findViewById(R.id.rating14);


        seekBar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar8, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate8.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate8.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate8.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate8.setText("Slightly Important");
                } else {
                    rate8.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar8) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar8) {

            }
        });

        seekBar9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar9, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate9.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate9.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate9.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate9.setText("Slightly Important");
                } else {
                    rate9.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar9) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar9) {

            }
        });

        seekBar10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar10, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate10.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate10.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate10.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate10.setText("Slightly Important");
                } else {
                    rate10.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar10) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar10) {

            }
        });

        seekBar11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar11, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate11.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate11.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate11.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate11.setText("Slightly Important");
                } else {
                    rate11.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar11) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar11) {

            }
        });

        seekBar12.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar12, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate12.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate12.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate12.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate12.setText("Slightly Important");
                } else {
                    rate12.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar12) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar12) {

            }
        });

        seekBar13.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar13, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate13.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate13.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate13.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate13.setText("Slightly Important");
                } else {
                    rate13.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar13) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar13) {

            }
        });

        seekBar14.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar14, int i, boolean b) {
                if (i == 0 || i == 16 || i == 1 || i == 15) {
                    rate14.setText("Extremely Important");
                } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                    rate14.setText("Significantly Important");
                } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                    rate14.setText("Moderately Important");
                } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                    rate14.setText("Slightly Important");
                } else {
                    rate14.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar14) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar14) {

            }
        });

        popup2 = findViewById(R.id.instructionsBTN2);
        helpDialog2 = new Dialog(this);
        popup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helpDialog2.setContentView(R.layout.help_popup);
                helpDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                helpDialog2.show();
            }
        });

    }
    }