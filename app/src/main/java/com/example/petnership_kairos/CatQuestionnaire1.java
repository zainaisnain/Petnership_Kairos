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

public class CatQuestionnaire1 extends AppCompatActivity {

    ImageButton popupc1;
    SeekBar cseekBar, cseekBar2, cseekBar3, cseekBar4, cseekBar5, cseekBar6, cseekBar7;
    TextView crate1, crate2, crate3, crate4, crate5, crate6, crate7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_questionnaire1);

            cseekBar = (SeekBar)findViewById(R.id.cseekBar);
            cseekBar2 = (SeekBar) findViewById(R.id.cseekBar2);
            cseekBar3 = (SeekBar)findViewById(R.id.cseekBar3);
            cseekBar4 = (SeekBar)findViewById(R.id.cseekBar4);
            cseekBar5 = (SeekBar)findViewById(R.id.cseekBar5);
            cseekBar6 = (SeekBar)findViewById(R.id.cseekBar6);
            cseekBar7 = (SeekBar)findViewById(R.id.cseekBar7);
            crate1 = (TextView)findViewById(R.id.crating);
            crate2 = (TextView)findViewById(R.id.crating2);
            crate3 = (TextView)findViewById(R.id.crating3);
            crate4 = (TextView)findViewById(R.id.crating4);
            crate5 = (TextView)findViewById(R.id.crating5);
            crate6 = (TextView)findViewById(R.id.crating6);
            crate7 = (TextView)findViewById(R.id.crating7);



            cseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar cseekBar, int i, boolean b) {
                    if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                        crate1.setText("Extremely Important");
                    }
                    else if(i == 2 || i == 14 || i == 3 || i == 13){
                        crate1.setText("Significantly Important");
                    }
                    else if(i == 4 || i == 12 || i == 5 || i == 11){
                        crate1.setText("Moderately Important");
                    }
                    else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                        crate1.setText("Slightly Important");
                    }
                    else {
                        crate1.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar) {

                }
                @Override
                public void onStopTrackingTouch(SeekBar cseekBar) {

                }
            });

            cseekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar2, int i, boolean b) {
                    if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                        crate2.setText("Extremely Important");
                    }
                    else if(i == 2 || i == 14 || i == 3 || i == 13){
                        crate2.setText("Significantly Important");
                    }
                    else if(i == 4 || i == 12 || i == 5 || i == 11){
                        crate2.setText("Moderately Important");
                    }
                    else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                        crate2.setText("Slightly Important");
                    }
                    else {
                        crate2.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar2) {

                }
                @Override
                public void onStopTrackingTouch(SeekBar cseekBar2) {

                }
            });

            cseekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar3, int i, boolean b) {
                    if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                        crate3.setText("Extremely Important");
                    }
                    else if(i == 2 || i == 14 || i == 3 || i == 13){
                        crate3.setText("Significantly Important");
                    }
                    else if(i == 4 || i == 12 || i == 5 || i == 11){
                        crate3.setText("Moderately Important");
                    }
                    else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                        crate3.setText("Slightly Important");
                    }
                    else {
                        crate3.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar3) {

                }
                @Override
                public void onStopTrackingTouch(SeekBar cseekBar3) {

                }
            });

            cseekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar4, int i, boolean b) {
                    if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                        crate4.setText("Extremely Important");
                    }
                    else if(i == 2 || i == 14 || i == 3 || i == 13){
                        crate4.setText("Significantly Important");
                    }
                    else if(i == 4 || i == 12 || i == 5 || i == 11){
                        crate4.setText("Moderately Important");
                    }
                    else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                        crate4.setText("Slightly Important");
                    }
                    else {
                        crate4.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar4) {

                }
                @Override
                public void onStopTrackingTouch(SeekBar cseekBar4) {

                }
            });

            cseekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar5, int i, boolean b) {
                    if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                        crate5.setText("Extremely Important");
                    }
                    else if(i == 2 || i == 14 || i == 3 || i == 13){
                        crate5.setText("Significantly Important");
                    }
                    else if(i == 4 || i == 12 || i == 5 || i == 11){
                        crate5.setText("Moderately Important");
                    }
                    else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                        crate5.setText("Slightly Important");
                    }
                    else {
                        crate5.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar5) {

                }
                @Override
                public void onStopTrackingTouch(SeekBar cseekBar5) {

                }
            });

            cseekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar6, int i, boolean b) {
                    if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                        crate6.setText("Extremely Important");
                    }
                    else if(i == 2 || i == 14 || i == 3 || i == 13){
                        crate6.setText("Significantly Important");
                    }
                    else if(i == 4 || i == 12 || i == 5 || i == 11){
                        crate6.setText("Moderately Important");
                    }
                    else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                        crate6.setText("Slightly Important");
                    }
                    else {
                        crate6.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar6) {

                }
                @Override
                public void onStopTrackingTouch(SeekBar cseekBar6) {

                }
            });

            cseekBar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar cseekBar7, int i, boolean b) {
                    if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                        crate7.setText("Extremely Important");
                    }
                    else if(i == 2 || i == 14 || i == 3 || i == 13){
                        crate7.setText("Significantly Important");
                    }
                    else if(i == 4 || i == 12 || i == 5 || i == 11){
                        crate7.setText("Moderately Important");
                    }
                    else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                        crate7.setText("Slightly Important");
                    }
                    else {
                        crate7.setText("Equally Important");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar cseekBar7) {

                }
                @Override
                public void onStopTrackingTouch(SeekBar cseekBar7) {

                }
            });

            popupc1 = findViewById(R.id.instructionsBTNc1);
            popupc1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog();
                }
            });

    }

    private void showDialog() {
        final Dialog helpDialogc1 = new Dialog(this);
        helpDialogc1.setContentView(R.layout.help_popup);
        helpDialogc1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        helpDialogc1.show();
        ImageButton closeBTN = (ImageButton) helpDialogc1.findViewById(R.id.closeBTN);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                helpDialogc1.dismiss();

            }
        });
    }

}