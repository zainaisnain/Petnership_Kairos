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

public class DogQuestionnaire5 extends AppCompatActivity {
    ImageButton popup5;
    SeekBar seekBar27, seekBar28, seekBar29, seekBar30, seekBar31, seekBar32;
    TextView rate27, rate28, rate29, rate30, rate31, rate32;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_questionnaire5);
        
            seekBar27 = (SeekBar) findViewById(R.id.seekBar27);
            seekBar28 = (SeekBar) findViewById(R.id.seekBar28);
            seekBar29 = (SeekBar) findViewById(R.id.seekBar29);
            seekBar30 = (SeekBar) findViewById(R.id.seekBar30);
            seekBar31 = (SeekBar) findViewById(R.id.seekBar31);
            seekBar32 = (SeekBar) findViewById(R.id.seekBar32);
            rate27 = (TextView) findViewById(R.id.rating27);
            rate28 = (TextView) findViewById(R.id.rating28);
            rate29 = (TextView) findViewById(R.id.rating29);
            rate30 = (TextView) findViewById(R.id.rating30);
            rate31 = (TextView) findViewById(R.id.rating31);
            rate32 = (TextView) findViewById(R.id.rating32);


            seekBar27.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar seekBar27, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate27.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate27.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate27.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate27.setText("Slightly Important");
                    } else {
                        rate27.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar27) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar27) {

                }
            });

            seekBar28.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar28, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate28.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate28.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate28.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate28.setText("Slightly Important");
                    } else {
                        rate28.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar28) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar28) {

                }
            });

            seekBar29.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar29, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate29.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate29.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate29.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate29.setText("Slightly Important");
                    } else {
                        rate29.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar29) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar29) {

                }
            });

            seekBar30.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar30, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate30.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate30.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate30.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate30.setText("Slightly Important");
                    } else {
                        rate30.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar30) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar30) {

                }
            });

            seekBar31.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar31, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate31.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate31.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate31.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate31.setText("Slightly Important");
                    } else {
                        rate31.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar31) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar31) {

                }
            });

            seekBar32.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar32, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate32.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate32.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate32.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate32.setText("Slightly Important");
                    } else {
                        rate32.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar32) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar32) {

                }
            });


            popup5 = findViewById(R.id.instructionsBTN5);
            popup5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog();
                }
            });

    }

    private void showDialog() {
        final Dialog helpDialog5 = new Dialog(this);
        helpDialog5.setContentView(R.layout.help_popup);
        helpDialog5.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        helpDialog5.show();
        ImageButton closeBTN = (ImageButton) helpDialog5.findViewById(R.id.closeBTN);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                helpDialog5.dismiss();

            }
        });
    }

}