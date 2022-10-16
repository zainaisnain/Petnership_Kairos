package com.example.petnership_kairos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class DogQuestionnaire1 extends AppCompatActivity {
    ImageButton popup;
    SeekBar seekBar1, seekBar2, seekBar3, seekBar4, seekBar5, seekBar6, seekBar7;
    TextView rate1, rate2, rate3, rate4, rate5, rate6, rate7;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_questionnaire1);
        seekBar1 = (SeekBar)findViewById(R.id.seekBar);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar)findViewById(R.id.seekBar3);
        seekBar4 = (SeekBar)findViewById(R.id.seekBar4);
        seekBar5 = (SeekBar)findViewById(R.id.seekBar5);
        seekBar6 = (SeekBar)findViewById(R.id.seekBar6);
        seekBar7 = (SeekBar)findViewById(R.id.seekBar7);
        rate1 = (TextView)findViewById(R.id.rating1);
        rate2 = (TextView)findViewById(R.id.rating2);
        rate3 = (TextView)findViewById(R.id.rating3);
        rate4 = (TextView)findViewById(R.id.rating4);
        rate5 = (TextView)findViewById(R.id.rating5);
        rate6 = (TextView)findViewById(R.id.rating6);
        rate7 = (TextView)findViewById(R.id.rating7);



        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar1, int i, boolean b) {
                if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                    rate1.setText("Extremely Important");
                }
                else if(i == 2 || i == 14 || i == 3 || i == 13){
                    rate1.setText("Significantly Important");
                }
                else if(i == 4 || i == 12 || i == 5 || i == 11){
                    rate1.setText("Moderately Important");
                }
                else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                    rate1.setText("Slightly Important");
                }
                else {
                    rate1.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar1) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar1) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int i, boolean b) {
                if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                    rate2.setText("Extremely Important");
                }
                else if(i == 2 || i == 14 || i == 3 || i == 13){
                    rate2.setText("Significantly Important");
                }
                else if(i == 4 || i == 12 || i == 5 || i == 11){
                    rate2.setText("Moderately Important");
                }
                else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                    rate2.setText("Slightly Important");
                }
                else {
                    rate2.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar2) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar2) {

            }
        });

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar3, int i, boolean b) {
                if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                    rate3.setText("Extremely Important");
                }
                else if(i == 2 || i == 14 || i == 3 || i == 13){
                    rate3.setText("Significantly Important");
                }
                else if(i == 4 || i == 12 || i == 5 || i == 11){
                    rate3.setText("Moderately Important");
                }
                else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                    rate3.setText("Slightly Important");
                }
                else {
                    rate3.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar3) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar3) {

            }
        });

        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar4, int i, boolean b) {
                if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                    rate4.setText("Extremely Important");
                }
                else if(i == 2 || i == 14 || i == 3 || i == 13){
                    rate4.setText("Significantly Important");
                }
                else if(i == 4 || i == 12 || i == 5 || i == 11){
                    rate4.setText("Moderately Important");
                }
                else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                    rate4.setText("Slightly Important");
                }
                else {
                    rate4.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar4) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar4) {

            }
        });

        seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar5, int i, boolean b) {
                if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                    rate5.setText("Extremely Important");
                }
                else if(i == 2 || i == 14 || i == 3 || i == 13){
                    rate5.setText("Significantly Important");
                }
                else if(i == 4 || i == 12 || i == 5 || i == 11){
                    rate5.setText("Moderately Important");
                }
                else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                    rate5.setText("Slightly Important");
                }
                else {
                    rate5.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar5) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar5) {

            }
        });

        seekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar6, int i, boolean b) {
                if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                    rate6.setText("Extremely Important");
                }
                else if(i == 2 || i == 14 || i == 3 || i == 13){
                    rate6.setText("Significantly Important");
                }
                else if(i == 4 || i == 12 || i == 5 || i == 11){
                    rate6.setText("Moderately Important");
                }
                else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                    rate6.setText("Slightly Important");
                }
                else {
                    rate6.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar6) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar6) {

            }
        });

        seekBar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar7, int i, boolean b) {
                if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
                    rate7.setText("Extremely Important");
                }
                else if(i == 2 || i == 14 || i == 3 || i == 13){
                    rate7.setText("Significantly Important");
                }
                else if(i == 4 || i == 12 || i == 5 || i == 11){
                    rate7.setText("Moderately Important");
                }
                else if( i == 7 || i == 9 ||  i == 6 || i == 10){
                    rate7.setText("Slightly Important");
                }
                else {
                    rate7.setText("Equally Important");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar7) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar7) {

            }
        });

        popup = findViewById(R.id.instructionsBTN);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }
    private void showDialog() {
        final Dialog helpDialog = new Dialog(this);
        helpDialog.setContentView(R.layout.help_popup);
        helpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        helpDialog.show();
        ImageButton closeBTN = (ImageButton) helpDialog.findViewById(R.id.closeBTN);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                helpDialog.dismiss();

            }
        });
    }

}