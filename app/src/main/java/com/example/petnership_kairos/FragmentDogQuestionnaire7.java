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

public class FragmentDogQuestionnaire7 extends Fragment {

    public static FragmentDogQuestionnaire7 newInstance() {
        return new FragmentDogQuestionnaire7();
    }
    ImageButton popup7, backBtn;
    SeekBar seekBar33, seekBar34, seekBar35;
    TextView rate33, rate34, rate35;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire7, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 7");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));

        seekBar33 = getView().findViewById(R.id.seekBar33);
        seekBar34 = getView().findViewById(R.id.seekBar34);
        seekBar35 = getView().findViewById(R.id.seekBar35);
        rate33 = getView().findViewById(R.id.rating33);
        rate34 = getView().findViewById(R.id.rating34);
        rate35 = getView().findViewById(R.id.rating35);


        // bring back previous progress if any
        if (mViewModel.getAnswer(33) != null) seekBar33.setProgress(mViewModel.getAnswer(33));
        if (mViewModel.getAnswer(34) != null) seekBar34.setProgress(mViewModel.getAnswer(34));
        if (mViewModel.getAnswer(35) != null) seekBar35.setProgress(mViewModel.getAnswer(35));

        seekBar33.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar33, int i, boolean b) {
                setSeekText(i, rate33);
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
                setSeekText(i, rate34);
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
                setSeekText(i, rate35);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar35) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar35) {

            }
        });

        popup7 = getView().findViewById(R.id.instructionsBTN7);
        popup7.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.proceed_ques7);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(33, seekBar33.getProgress());
            mViewModel.setAnswer(34, seekBar34.getProgress());
            mViewModel.setAnswer(35, seekBar35.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire8 dog8Fragment = new FragmentDogQuestionnaire8();
            transaction.replace(R.id.nav_host_fragment,dog8Fragment);
            transaction.addToBackStack("dogQuestionnaire8");
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