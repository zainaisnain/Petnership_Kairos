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

public class FragmentDogQuestionnaire9 extends Fragment {

    public static FragmentDogQuestionnaire9 newInstance() {
        return new FragmentDogQuestionnaire9();
    }
    ImageButton popup9;
    SeekBar seekBar50, seekBar51, seekBar52, seekBar53;
    TextView rate50, rate51, rate52, rate53;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire9, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 9");
                mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getDogAnswer(1));

        seekBar50 = (SeekBar) getView().findViewById(R.id.seekBar50);
        seekBar51 = (SeekBar) getView().findViewById(R.id.seekBar51);
        seekBar52 = (SeekBar) getView().findViewById(R.id.seekBar52);
        seekBar53 = (SeekBar) getView().findViewById(R.id.seekBar53);
        rate50 = (TextView) getView().findViewById(R.id.rating50);
        rate51 = (TextView) getView().findViewById(R.id.rating51);
        rate52 = (TextView) getView().findViewById(R.id.rating52);
        rate53 = (TextView) getView().findViewById(R.id.rating53);





        // bring back previous progress if any
        if (mViewModel.getDogAnswer(8) != null){
            seekBar53.setProgress(mViewModel.getDogAnswer(8));
        }

        seekBar50.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar50, int i, boolean b) {
                setSeekText(i, rate50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar50) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar50) {

            }
        });

        seekBar51.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar51, int i, boolean b) {
                setSeekText(i, rate51);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar51) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar51) {

            }
        });

        seekBar52.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar52, int i, boolean b) {
                setSeekText(i, rate52);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar52) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar52) {

            }
        });

        seekBar53.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar53, int i, boolean b) {
                setSeekText(i, rate53);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar53) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar53) {

            }
        });


        popup9 = getView().findViewById(R.id.instructionsBTN9);
        popup9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.proceed_ques9);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(50, seekBar50.getProgress());
                mViewModel.setDogAnswer(51, seekBar51.getProgress());
                mViewModel.setDogAnswer(52, seekBar52.getProgress());
                mViewModel.setDogAnswer(53, seekBar53.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentDogQuestionnaire10 dog10Fragment = new FragmentDogQuestionnaire10();
                transaction.replace(R.id.nav_host_fragment,dog10Fragment);
                transaction.addToBackStack("dogQuestionnaire10");
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