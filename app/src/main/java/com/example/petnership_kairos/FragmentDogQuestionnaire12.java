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

public class FragmentDogQuestionnaire12 extends Fragment {

    public static FragmentDogQuestionnaire12 newInstance() {
        return new FragmentDogQuestionnaire12();
    }


    ImageButton popup12, backBtn;
    SeekBar seekBar48, seekBar49, seekBar50;
    TextView rate48, rate49, rate50;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire12, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 12");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));


        seekBar48 = getView().findViewById(R.id.seekBar48);
        seekBar49 = getView().findViewById(R.id.seekBar49);
        seekBar50 = getView().findViewById(R.id.seekBar50);
        rate48 = getView().findViewById(R.id.rating48);
        rate49 = getView().findViewById(R.id.rating49);
        rate50 = getView().findViewById(R.id.rating50);

        // bring back previous progress if any
        //  if (mViewModel.getDogAnswer(8) != null){
        //    seekBar54.setProgress(mViewModel.getDogAnswer(8));
        //}


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


        seekBar50.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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


        popup12 = getView().findViewById(R.id.instructionsBTN12);
        popup12.setOnClickListener(view1 -> showDialog());

        Button proceedBtn = getView().findViewById(R.id.proceed_ques12);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(48, seekBar48.getProgress());
            mViewModel.setAnswer(49, seekBar49.getProgress());
            mViewModel.setAnswer(50, seekBar50.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire13 dog13Fragment = new FragmentDogQuestionnaire13();
            transaction.replace(R.id.nav_host_fragment,dog13Fragment);
            transaction.addToBackStack("dogQuestionnaire13");
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