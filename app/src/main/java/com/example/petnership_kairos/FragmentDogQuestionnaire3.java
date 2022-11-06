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

public class FragmentDogQuestionnaire3 extends Fragment {

    public static FragmentDogQuestionnaire3 newInstance() {
        return new FragmentDogQuestionnaire3();
    }

    ImageButton popup3, backBtn;
    SeekBar seekBar15, seekBar16, seekBar17, seekBar18, seekBar19, seekBar20,seekBar21;
    TextView rate15, rate16, rate17, rate18, rate19, rate20, rate21;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire3, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 3");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));

        seekBar15 = getView().findViewById(R.id.seekBar15);
        seekBar16 = getView().findViewById(R.id.seekBar16);
        seekBar17 = getView().findViewById(R.id.seekBar17);
        seekBar18 = getView().findViewById(R.id.seekBar18);
        seekBar19 = getView().findViewById(R.id.seekBar19);
        seekBar20 = getView().findViewById(R.id.seekBar20);
        seekBar21 = getView().findViewById(R.id.seekBar21);
        rate15 = getView().findViewById(R.id.rating15);
        rate16 = getView().findViewById(R.id.rating16);
        rate17 = getView().findViewById(R.id.rating17);
        rate18 = getView().findViewById(R.id.rating18);
        rate19 = getView().findViewById(R.id.rating19);
        rate20 = getView().findViewById(R.id.rating20);
        rate21 = getView().findViewById(R.id.rating21);


        // bring back previous progress if any
        if (mViewModel.getAnswer(15) != null) seekBar15.setProgress(mViewModel.getAnswer(15));
        if (mViewModel.getAnswer(16) != null) seekBar16.setProgress(mViewModel.getAnswer(16));
        if (mViewModel.getAnswer(17) != null) seekBar17.setProgress(mViewModel.getAnswer(17));
        if (mViewModel.getAnswer(18) != null) seekBar18.setProgress(mViewModel.getAnswer(18));
        if (mViewModel.getAnswer(19) != null) seekBar19.setProgress(mViewModel.getAnswer(19));
        if (mViewModel.getAnswer(20) != null) seekBar20.setProgress(mViewModel.getAnswer(20));
        if (mViewModel.getAnswer(21) != null) seekBar21.setProgress(mViewModel.getAnswer(21));

        seekBar15.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar15, int i, boolean b) {
                setSeekText(i, rate15);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar15) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar15) {

            }
        });

        seekBar16.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar16, int i, boolean b) {
                setSeekText(i, rate16);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar16) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar16) {

            }
        });

        seekBar17.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar17, int i, boolean b) {
                setSeekText(i, rate17);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar17) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar17) {

            }
        });

        seekBar18.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar18, int i, boolean b) {
                setSeekText(i, rate18);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar18) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar18) {

            }
        });

        seekBar19.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar19, int i, boolean b) {
                setSeekText(i, rate19);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar19) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar19) {

            }
        });

        seekBar20.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar20, int i, boolean b) {
                setSeekText(i, rate20);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar20) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar20) {

            }
        });

        seekBar21.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar21, int i, boolean b) {
                setSeekText(i, rate21);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar21) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar21) {

            }
        });

        popup3 = getView().findViewById(R.id.instructionsBTN3);
        popup3.setOnClickListener(view1 -> showDialog());

        Button proceedBtn = getView().findViewById(R.id.proceed_ques3);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(15, seekBar15.getProgress());
            mViewModel.setAnswer(16, seekBar16.getProgress());
            mViewModel.setAnswer(17, seekBar17.getProgress());
            mViewModel.setAnswer(18, seekBar18.getProgress());
            mViewModel.setAnswer(19, seekBar19.getProgress());
            mViewModel.setAnswer(20, seekBar20.getProgress());
            mViewModel.setAnswer(21, seekBar21.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire4 dog4Fragment = new FragmentDogQuestionnaire4();
            transaction.replace(R.id.nav_host_fragment,dog4Fragment);
            transaction.addToBackStack("dogQuestionnaire4");
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