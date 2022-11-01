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
    ImageButton popup9, backBtn;
    SeekBar seekBar39, seekBar40, seekBar41;
    TextView rate39, rate40, rate41;
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
        System.out.println("Test: " + mViewModel.getAnswer(1));

        seekBar39 = getView().findViewById(R.id.seekBar39);
        seekBar40 = getView().findViewById(R.id.seekBar40);
        seekBar41 = getView().findViewById(R.id.seekBar41);
        rate39 = getView().findViewById(R.id.rating39);
        rate40 = getView().findViewById(R.id.rating40);
        rate41 = getView().findViewById(R.id.rating41);





        // bring back previous progress if any
    //    if (mViewModel.getDogAnswer(8) != null){
      //      seekBar53.setProgress(mViewModel.getDogAnswer(8));
        //}

        seekBar39.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar39, int i, boolean b) {
                setSeekText(i, rate39);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar39) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar39) {

            }
        });

        seekBar40.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar40, int i, boolean b) {
                setSeekText(i, rate40);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar40) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar40) {

            }
        });

        seekBar41.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar41, int i, boolean b) {
                setSeekText(i, rate41);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar41) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar41) {

            }
        });


        popup9 = getView().findViewById(R.id.instructionsBTN9);
        popup9.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.proceed_ques9);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(39, seekBar39.getProgress());
            mViewModel.setAnswer(40, seekBar40.getProgress());
            mViewModel.setAnswer(41, seekBar41.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire10 dog10Fragment = new FragmentDogQuestionnaire10();
            transaction.replace(R.id.nav_host_fragment,dog10Fragment);
            transaction.addToBackStack("dogQuestionnaire10");
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