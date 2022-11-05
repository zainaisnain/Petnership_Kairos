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

public class FragmentDogQuestionnaire6 extends Fragment {

    public static FragmentDogQuestionnaire6 newInstance() {
        return new FragmentDogQuestionnaire6();
    }
    ImageButton popup6, backBtn;
    SeekBar seekBar30, seekBar31, seekBar32;
    TextView rate30, rate31, rate32;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire6, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 6");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));

        seekBar30 = getView().findViewById(R.id.seekBar30);
        seekBar31 = getView().findViewById(R.id.seekBar31);
        seekBar32 = getView().findViewById(R.id.seekBar32);
        rate30 = getView().findViewById(R.id.rating30);
        rate31 = getView().findViewById(R.id.rating31);
        rate32 = getView().findViewById(R.id.rating32);


        // bring back previous progress if any
        if (mViewModel.getAnswer(30) != null) seekBar30.setProgress(mViewModel.getAnswer(30));
        if (mViewModel.getAnswer(31) != null) seekBar31.setProgress(mViewModel.getAnswer(31));
        if (mViewModel.getAnswer(32) != null) seekBar32.setProgress(mViewModel.getAnswer(32));

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

        popup6 = getView().findViewById(R.id.instructionsBTN6);
        popup6.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.proceed_ques6);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(30, seekBar30.getProgress());
            mViewModel.setAnswer(31, seekBar31.getProgress());
            mViewModel.setAnswer(32, seekBar32.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire7 dog7Fragment = new FragmentDogQuestionnaire7();
            transaction.replace(R.id.nav_host_fragment,dog7Fragment);
            transaction.addToBackStack("dogQuestionnaire7");
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