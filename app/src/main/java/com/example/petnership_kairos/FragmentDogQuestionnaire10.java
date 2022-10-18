package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragmentDogQuestionnaire10 extends Fragment {

    ImageButton popup10;
    public static FragmentDogQuestionnaire10 newInstance() {
        return new FragmentDogQuestionnaire10();
    }

    ImageButton popup;
    SeekBar seekBar54, seekBar55, seekBar56, seekBar57, seekBar58, seekBar59;
    TextView rate54, rate55, rate56, rate57, rate58, rate59;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire10, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 10");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getDogAnswer(1));

        seekBar54 = (SeekBar) getView().findViewById(R.id.seekBar54);
        seekBar55 = (SeekBar) getView().findViewById(R.id.seekBar55);
        seekBar56 = (SeekBar) getView().findViewById(R.id.seekBar56);
        seekBar57 = (SeekBar) getView().findViewById(R.id.seekBar57);
        seekBar58 = (SeekBar) getView().findViewById(R.id.seekBar58);
        seekBar59 = (SeekBar) getView().findViewById(R.id.seekBar59);
        rate54 = (TextView) getView().findViewById(R.id.rating54);
        rate55 = (TextView) getView().findViewById(R.id.rating55);
        rate56 = (TextView) getView().findViewById(R.id.rating56);
        rate57 = (TextView) getView().findViewById(R.id.rating57);
        rate58 = (TextView) getView().findViewById(R.id.rating58);
        rate59 = (TextView) getView().findViewById(R.id.rating59);

        // bring back previous progress if any
        if (mViewModel.getDogAnswer(8) != null){
            seekBar54.setProgress(mViewModel.getDogAnswer(8));
        }


        seekBar54.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar54, int i, boolean b) {

                setSeekText(i, rate54);
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

                setSeekText(i, rate55);
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

                setSeekText(i, rate56);
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

                setSeekText(i, rate57);
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

                setSeekText(i, rate58);
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

                setSeekText(i, rate59);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar59) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar59) {

            }
        });


        popup10 = getView().findViewById(R.id.instructionsBTN10);
        popup10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        Button submitBtn = (Button) getView().findViewById(R.id.cproceed_ques5);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(54, seekBar54.getProgress());
                mViewModel.setDogAnswer(55, seekBar55.getProgress());
                mViewModel.setDogAnswer(56, seekBar56.getProgress());
                mViewModel.setDogAnswer(57, seekBar57.getProgress());
                mViewModel.setDogAnswer(58, seekBar58.getProgress());
                mViewModel.setDogAnswer(59, seekBar59.getProgress());

                for (int i = 1; i < 60; i++) {
                    System.out.println("SEEKBAR #" + i + ": " + mViewModel.getDogAnswer(i));
                }

                MCDM mainAlgo = new MCDM();
                mainAlgo.beginMCDM(getContext());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentRecommendedPets recPets = new FragmentRecommendedPets();
                transaction.replace(R.id.nav_host_fragment,recPets);
                transaction.addToBackStack("recommendedPets");
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