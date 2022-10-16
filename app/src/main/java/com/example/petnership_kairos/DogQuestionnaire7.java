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

public class DogQuestionnaire7 extends AppCompatActivity {
    ImageButton popup7;
    SeekBar seekBar39, seekBar40, seekBar41, seekBar42, seekBar43, seekBar44;
    TextView rate39, rate40, rate41, rate42, rate43, rate44;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_questionnaire7);

            seekBar39 = (SeekBar) findViewById(R.id.seekBar39);
            seekBar40 = (SeekBar) findViewById(R.id.seekBar40);
            seekBar41 = (SeekBar) findViewById(R.id.seekBar41);
            seekBar42 = (SeekBar) findViewById(R.id.seekBar42);
            seekBar43 = (SeekBar) findViewById(R.id.seekBar43);
            seekBar44 = (SeekBar) findViewById(R.id.seekBar44);
            rate39 = (TextView) findViewById(R.id.rating39);
            rate40 = (TextView) findViewById(R.id.rating40);
            rate41 = (TextView) findViewById(R.id.rating41);
            rate42 = (TextView) findViewById(R.id.rating42);
            rate43 = (TextView) findViewById(R.id.rating43);
            rate44 = (TextView) findViewById(R.id.rating44);


            seekBar39.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar seekBar39, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate39.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate39.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate39.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate39.setText("Slightly Important");
                    } else {
                        rate39.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar39) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar39) {

                }
            });

            seekBar40.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar40, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate40.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate40.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate40.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate40.setText("Slightly Important");
                    } else {
                        rate40.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar40) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar40) {

                }
            });

            seekBar41.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar41, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate41.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate41.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate41.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate41.setText("Slightly Important");
                    } else {
                        rate41.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar41) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar41) {

                }
            });

            seekBar42.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar42, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate42.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate42.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate42.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate42.setText("Slightly Important");
                    } else {
                        rate42.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar42) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar42) {

                }
            });

            seekBar43.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar43, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate43.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate43.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate43.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate43.setText("Slightly Important");
                    } else {
                        rate43.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar43) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar43) {

                }
            });

            seekBar44.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar44, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate44.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate44.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate44.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate44.setText("Slightly Important");
                    } else {
                        rate44.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar44) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar44) {

                }
            });


            popup7 = findViewById(R.id.instructionsBTN7);
            popup7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog();
                }
            });

    }

    private void showDialog() {
        final Dialog helpDialog7 = new Dialog(this);
        helpDialog7.setContentView(R.layout.help_popup);
        helpDialog7.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        helpDialog7.show();
        ImageButton closeBTN = (ImageButton) helpDialog7.findViewById(R.id.closeBTN);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                helpDialog7.dismiss();

            }
        });
    }

}