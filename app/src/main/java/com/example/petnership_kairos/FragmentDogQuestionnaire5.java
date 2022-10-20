package com.example.petnership_kairos;

import androidx.lifecycle.ViewModelProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragmentDogQuestionnaire5 extends Fragment {

    public static FragmentDogQuestionnaire5 newInstance() {
        return new FragmentDogQuestionnaire5();
    }
    ImageButton popup5;
    SeekBar seekBar27, seekBar28, seekBar29, seekBar30, seekBar31, seekBar32;
    TextView rate27, rate28, rate29, rate30, rate31, rate32;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire5, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 5");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getDogAnswer(1));
        seekBar27 = (SeekBar) getView().findViewById(R.id.seekBar27);
        seekBar28 = (SeekBar) getView().findViewById(R.id.seekBar28);
        seekBar29 = (SeekBar) getView().findViewById(R.id.seekBar29);
        seekBar30 = (SeekBar) getView().findViewById(R.id.seekBar30);
        seekBar31 = (SeekBar) getView().findViewById(R.id.seekBar31);
        seekBar32 = (SeekBar) getView().findViewById(R.id.seekBar32);
        rate27 = (TextView) getView().findViewById(R.id.rating27);
        rate28 = (TextView) getView().findViewById(R.id.rating28);
        rate29 = (TextView) getView().findViewById(R.id.rating29);
        rate30 = (TextView) getView().findViewById(R.id.rating30);
        rate31 = (TextView) getView().findViewById(R.id.rating31);
        rate32 = (TextView) getView().findViewById(R.id.rating32);


        // bring back previous progress if any
        if (mViewModel.getDogAnswer(8) != null){
            seekBar32.setProgress(mViewModel.getDogAnswer(8));
        }

        seekBar27.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar27, int i, boolean b) {
                setSeekText(i, rate27);
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
                setSeekText(i, rate28);
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
                setSeekText(i, rate29);
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
                setSeekText(i, rate30);
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
                setSeekText(i, rate31);
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
                setSeekText(i, rate32);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar32) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar32) {

            }
        });


        popup5 = getView().findViewById(R.id.instructionsBTN5);
        popup5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.proceed_ques5);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(27, seekBar27.getProgress());
                mViewModel.setDogAnswer(28, seekBar28.getProgress());
                mViewModel.setDogAnswer(29, seekBar29.getProgress());
                mViewModel.setDogAnswer(30, seekBar30.getProgress());
                mViewModel.setDogAnswer(31, seekBar31.getProgress());
                mViewModel.setDogAnswer(32, seekBar32.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentDogQuestionnaire6 dog6Fragment = new FragmentDogQuestionnaire6();
                transaction.replace(R.id.nav_host_fragment,dog6Fragment);
                transaction.addToBackStack("dogQuestionnaire6");
                transaction.commit();
            }
        });


    }

    private void setSeekText(int i, TextView j) {
        if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
            j.setText("Extremely Important");
        }
        else if(i == 2 || i == 14 || i == 3 || i == 13){
            j.setText("Significantly Important");
        }
        else if(i == 4 || i == 12 || i == 5 || i == 11){
            j.setText("Moderately Important");
        }
        else if( i == 7 || i == 9 ||  i == 6 || i == 10){
            j.setText("Slightly Important");
        }
        else {
            j.setText("Equally Important");
        }
    }
    private void showDialog() {
        /*
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
        });*/
    }


}