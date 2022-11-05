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

public class FragmentDogQuestionnaire1 extends Fragment {

    public static FragmentDogQuestionnaire1 newInstance() {
        return new FragmentDogQuestionnaire1();
    }

    ImageButton popup;
    SeekBar seekBar1, seekBar2, seekBar3, seekBar4, seekBar5, seekBar6, seekBar7;
    TextView rate1, rate2, rate3, rate4, rate5, rate6, rate7;
    MCDMAnswersViewModel mViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire1, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 1");

        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        mViewModel.setAnimalType("Dog");


        seekBar1 = getView().findViewById(R.id.seekBar);
        seekBar2 = getView().findViewById(R.id.seekBar2);
        seekBar3 = getView().findViewById(R.id.seekBar3);
        seekBar4 = getView().findViewById(R.id.seekBar4);
        seekBar5 = getView().findViewById(R.id.seekBar5);
        seekBar6 = getView().findViewById(R.id.seekBar6);
        seekBar7 = getView().findViewById(R.id.seekBar7);
        rate1 = getView().findViewById(R.id.rating1);
        rate2 = getView().findViewById(R.id.rating2);
        rate3 = getView().findViewById(R.id.rating3);
        rate4 = getView().findViewById(R.id.rating4);
        rate5 = getView().findViewById(R.id.rating5);
        rate6 = getView().findViewById(R.id.rating6);
        rate7 = getView().findViewById(R.id.rating7);



        // bring back previous progress if any
        if (mViewModel.getAnswer(1) != null) seekBar1.setProgress(mViewModel.getAnswer(1));
        if (mViewModel.getAnswer(2) != null) seekBar2.setProgress(mViewModel.getAnswer(2));
        if (mViewModel.getAnswer(3) != null) seekBar3.setProgress(mViewModel.getAnswer(3));
        if (mViewModel.getAnswer(4) != null) seekBar4.setProgress(mViewModel.getAnswer(4));
        if (mViewModel.getAnswer(5) != null) seekBar5.setProgress(mViewModel.getAnswer(5));
        if (mViewModel.getAnswer(6) != null) seekBar6.setProgress(mViewModel.getAnswer(6));
        if (mViewModel.getAnswer(7) != null) seekBar7.setProgress(mViewModel.getAnswer(7));


        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar1, int i, boolean b) {
                setSeekText(i, rate1);
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
                setSeekText(i, rate2);
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
                setSeekText(i, rate3);
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
                setSeekText(i, rate4);
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
                setSeekText(i, rate5);
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
                setSeekText(i, rate6);
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
                setSeekText(i, rate7);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar7) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar7) {

            }
        });

        popup = getView().findViewById(R.id.instructionsBTN1);
        popup.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.proceed_ques1);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(1, seekBar1.getProgress());
            mViewModel.setAnswer(2, seekBar2.getProgress());
            mViewModel.setAnswer(3, seekBar3.getProgress());
            mViewModel.setAnswer(4, seekBar4.getProgress());
            mViewModel.setAnswer(5, seekBar5.getProgress());
            mViewModel.setAnswer(6, seekBar6.getProgress());
            mViewModel.setAnswer(7, seekBar7.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire2 dog2Fragment = new FragmentDogQuestionnaire2();
            transaction.replace(R.id.nav_host_fragment,dog2Fragment);
            transaction.addToBackStack("dogQuestionnaire2");
            transaction.commit();
        });
        ImageButton backBtn = getView().findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view12 -> {

            getParentFragmentManager().popBackStack();
        });


    }

    private void setSeekText(int i, TextView j) {
        if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
            j.setText(R.string.seekTextExtremely);
        }
        else if(i == 2 || i == 14 || i == 3 || i == 13){
            j.setText(R.string.seekTextSignificantly);
        }
        else if(i == 4 || i == 12 || i == 5 || i == 11){
            j.setText(R.string.seekTextModerately);
        }
        else if( i == 7 || i == 9 ||  i == 6 || i == 10){
            j.setText(R.string.seekTextSlightly);
        }
        else {
            j.setText(R.string.seekTextEqually);
        }
    }

    private void showDialog() {
        HelpPopup helpDialog = new HelpPopup("Dog", "Main");
        helpDialog.show(getParentFragmentManager(), "Help Popup");

    }


}