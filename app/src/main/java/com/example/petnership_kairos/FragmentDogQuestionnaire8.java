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

public class FragmentDogQuestionnaire8 extends Fragment {

    public static FragmentDogQuestionnaire8 newInstance() {
        return new FragmentDogQuestionnaire8();
    }
    ImageButton popup8;
    SeekBar seekBar45, seekBar46, seekBar47, seekBar48, seekBar49;
    TextView rate45, rate46, rate47, rate48, rate49;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire8, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 8");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getDogAnswer(1));



        seekBar45 = (SeekBar) getView().findViewById(R.id.seekBar45);
        seekBar46 = (SeekBar) getView().findViewById(R.id.seekBar46);
        seekBar47 = (SeekBar) getView().findViewById(R.id.seekBar47);
        seekBar48 = (SeekBar) getView().findViewById(R.id.seekBar48);
        seekBar49 = (SeekBar) getView().findViewById(R.id.seekBar49);
        rate45 = (TextView) getView().findViewById(R.id.rating45);
        rate46 = (TextView) getView().findViewById(R.id.rating46);
        rate47 = (TextView) getView().findViewById(R.id.rating47);
        rate48 = (TextView) getView().findViewById(R.id.rating48);
        rate49 = (TextView) getView().findViewById(R.id.rating49);



        // bring back previous progress if any
        if (mViewModel.getDogAnswer(8) != null){
            seekBar49.setProgress(mViewModel.getDogAnswer(8));
        }

        seekBar45.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar45, int i, boolean b) {
                setSeekText(i, rate45);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar45) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar45) {

            }
        });

        seekBar46.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar46, int i, boolean b) {
                setSeekText(i, rate46);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar46) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar46) {

            }
        });

        seekBar47.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar47, int i, boolean b) {
                setSeekText(i, rate47);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar47) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar47) {

            }
        });

        seekBar48.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar48, int i, boolean b) {
                setSeekText(i, rate48);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar48) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar48) {

            }
        });

        seekBar49.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar49, int i, boolean b) {
                setSeekText(i, rate49);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar49) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar49) {

            }
        });


        popup8 = getView().findViewById(R.id.instructionsBTN8);
        popup8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.proceed_ques8);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(45, seekBar45.getProgress());
                mViewModel.setDogAnswer(46, seekBar46.getProgress());
                mViewModel.setDogAnswer(47, seekBar47.getProgress());
                mViewModel.setDogAnswer(48, seekBar48.getProgress());
                mViewModel.setDogAnswer(49, seekBar49.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentDogQuestionnaire9 dog9Fragment = new FragmentDogQuestionnaire9();
                transaction.replace(R.id.nav_host_fragment,dog9Fragment);
                transaction.addToBackStack("dogQuestionnaire9");
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