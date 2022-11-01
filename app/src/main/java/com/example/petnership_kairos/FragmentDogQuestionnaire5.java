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
    ImageButton popup5, backBtn;
    SeekBar seekBar27, seekBar28, seekBar29;
    TextView rate27, rate28, rate29;
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
        System.out.println("Test: " + mViewModel.getAnswer(1));
        seekBar27 = getView().findViewById(R.id.seekBar27);
        seekBar28 = getView().findViewById(R.id.seekBar28);
        seekBar29 = getView().findViewById(R.id.seekBar29);
        rate27 = getView().findViewById(R.id.rating27);
        rate28 = getView().findViewById(R.id.rating28);
        rate29 = getView().findViewById(R.id.rating29);


        // bring back previous progress if any
     //   if (mViewModel.getDogAnswer(8) != null){
       //     seekBar32.setProgress(mViewModel.getDogAnswer(8));
        //}


        showInstructions();

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



        popup5 = getView().findViewById(R.id.instructionsBTN5);
        popup5.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.proceed_ques5);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(27, seekBar27.getProgress());
            mViewModel.setAnswer(28, seekBar28.getProgress());
            mViewModel.setAnswer(29, seekBar29.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire6 dog6Fragment = new FragmentDogQuestionnaire6();
            transaction.replace(R.id.nav_host_fragment,dog6Fragment);
            transaction.addToBackStack("dogQuestionnaire6");
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
    private void showInstructions() {
        InstructionsPopup instructionsDialog = new InstructionsPopup();
        instructionsDialog.show(getParentFragmentManager(), "Instructions Popup 2");
    }


}