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

public class DogQuestionnaire6 extends AppCompatActivity {
    ImageButton popup6;
    SeekBar seekBar33, seekBar34, seekBar35, seekBar36, seekBar37, seekBar38;
    TextView rate33, rate34, rate35, rate36, rate37, rate38;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_questionnaire6);

            seekBar33 = (SeekBar) findViewById(R.id.seekBar33);
            seekBar34 = (SeekBar) findViewById(R.id.seekBar34);
            seekBar35 = (SeekBar) findViewById(R.id.seekBar35);
            seekBar36 = (SeekBar) findViewById(R.id.seekBar36);
            seekBar37 = (SeekBar) findViewById(R.id.seekBar37);
            seekBar38 = (SeekBar) findViewById(R.id.seekBar38);
            rate33 = (TextView) findViewById(R.id.rating33);
            rate34 = (TextView) findViewById(R.id.rating34);
            rate35 = (TextView) findViewById(R.id.rating35);
            rate36 = (TextView) findViewById(R.id.rating36);
            rate37 = (TextView) findViewById(R.id.rating37);
            rate38 = (TextView) findViewById(R.id.rating38);


            seekBar33.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar seekBar33, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate33.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate33.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate33.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate33.setText("Slightly Important");
                    } else {
                        rate33.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar33) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar33) {

                }
            });

            seekBar34.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar34, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate34.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate34.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate34.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate34.setText("Slightly Important");
                    } else {
                        rate34.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar34) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar34) {

                }
            });

            seekBar35.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar35, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate35.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate35.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate35.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate35.setText("Slightly Important");
                    } else {
                        rate35.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar35) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar35) {

                }
            });

            seekBar36.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar36, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate36.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate36.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate36.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate36.setText("Slightly Important");
                    } else {
                        rate36.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar36) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar36) {

                }
            });

            seekBar37.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar37, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate37.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate37.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate37.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate37.setText("Slightly Important");
                    } else {
                        rate37.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar37) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar37) {

                }
            });

            seekBar38.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar38, int i, boolean b) {
                    if (i == 0 || i == 16 || i == 1 || i == 15) {
                        rate38.setText("Extremely Important");
                    } else if (i == 2 || i == 14 || i == 3 || i == 13) {
                        rate38.setText("Significantly Important");
                    } else if (i == 4 || i == 12 || i == 5 || i == 11) {
                        rate38.setText("Moderately Important");
                    } else if (i == 7 || i == 9 || i == 6 || i == 10) {
                        rate38.setText("Slightly Important");
                    } else {
                        rate38.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar38) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar38) {

                }
            });


            popup6 = findViewById(R.id.instructionsBTN6);
            popup6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog();
                }
            });

    }

    private void showDialog() {
        final Dialog helpDialog6 = new Dialog(this);
        helpDialog6.setContentView(R.layout.help_popup);
        helpDialog6.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        helpDialog6.show();
        ImageButton closeBTN = (ImageButton) helpDialog6.findViewById(R.id.closeBTN);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                helpDialog6.dismiss();

            }
        });
    }

}