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

public class FragmentDogQuestionnaire4 extends Fragment {

    public static FragmentDogQuestionnaire4 newInstance() {
        return new FragmentDogQuestionnaire4();
    }

    ImageButton popup4, backBtn;
    SeekBar seekBar22, seekBar23, seekBar24, seekBar25, seekBar26;
    TextView rate22, rate23, rate24, rate25, rate26;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire4, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 4");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));

        seekBar22 = getView().findViewById(R.id.seekBar22);
        seekBar23 = getView().findViewById(R.id.seekBar23);
        seekBar24 = getView().findViewById(R.id.seekBar24);
        seekBar25 = getView().findViewById(R.id.seekBar25);
        seekBar26 = getView().findViewById(R.id.seekBar26);
        rate22 = getView().findViewById(R.id.rating22);
        rate23 = getView().findViewById(R.id.rating23);
        rate24 = getView().findViewById(R.id.rating24);
        rate25 = getView().findViewById(R.id.rating25);
        rate26 = getView().findViewById(R.id.rating26);



        // bring back previous progress if any
        if (mViewModel.getAnswer(22) != null) seekBar22.setProgress(mViewModel.getAnswer(22));
        if (mViewModel.getAnswer(23) != null) seekBar23.setProgress(mViewModel.getAnswer(23));
        if (mViewModel.getAnswer(24) != null) seekBar24.setProgress(mViewModel.getAnswer(24));
        if (mViewModel.getAnswer(25) != null) seekBar25.setProgress(mViewModel.getAnswer(25));
        if (mViewModel.getAnswer(26) != null) seekBar26.setProgress(mViewModel.getAnswer(26));

        seekBar22.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar22, int i, boolean b) {
                setSeekText(i, rate22);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar22) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar22) {

            }
        });

        seekBar23.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar23, int i, boolean b) {
                setSeekText(i, rate23);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar23) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar23) {

            }
        });

        seekBar24.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar24, int i, boolean b) {
                setSeekText(i, rate24);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar24) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar24) {

            }
        });

        seekBar25.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar25, int i, boolean b) {
                setSeekText(i, rate25);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar25) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar25) {

            }
        });

        seekBar26.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar26, int i, boolean b) {
                setSeekText(i, rate26);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar26) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar26) {

            }
        });


        popup4 = getView().findViewById(R.id.instructionsBTN4);
        popup4.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.proceed_ques4);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(22, seekBar22.getProgress());
            mViewModel.setAnswer(23, seekBar23.getProgress());
            mViewModel.setAnswer(24, seekBar24.getProgress());
            mViewModel.setAnswer(25, seekBar25.getProgress());
            mViewModel.setAnswer(26, seekBar26.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire5 dog5Fragment = new FragmentDogQuestionnaire5();
            transaction.replace(R.id.nav_host_fragment,dog5Fragment);
            transaction.addToBackStack("dogQuestionnaire5");
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