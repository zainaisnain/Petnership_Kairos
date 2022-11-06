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

public class FragmentDogQuestionnaire13 extends Fragment {

    public static FragmentDogQuestionnaire13 newInstance() {
        return new FragmentDogQuestionnaire13();
    }


    ImageButton popup13, backBtn;
    SeekBar seekBar51, seekBar52, seekBar53;
    TextView rate51, rate52, rate53;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire13, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 13");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);

        seekBar51 = getView().findViewById(R.id.seekBar51);
        seekBar52 = getView().findViewById(R.id.seekBar52);
        seekBar53 = getView().findViewById(R.id.seekBar53);
        rate51 = getView().findViewById(R.id.rating51);
        rate52 = getView().findViewById(R.id.rating52);
        rate53 = getView().findViewById(R.id.rating53);



        // bring back previous progress if any
        if (mViewModel.getAnswer(51) != null) seekBar51.setProgress(mViewModel.getAnswer(51));
        if (mViewModel.getAnswer(52) != null) seekBar52.setProgress(mViewModel.getAnswer(52));
        if (mViewModel.getAnswer(53) != null) seekBar53.setProgress(mViewModel.getAnswer(53));

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


        popup13 = getView().findViewById(R.id.instructionsBTN13);
        popup13.setOnClickListener(view1 -> showDialog());

        Button proceedBtn = getView().findViewById(R.id.proceed_ques13);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(51, seekBar51.getProgress());
            mViewModel.setAnswer(52, seekBar52.getProgress());
            mViewModel.setAnswer(53, seekBar53.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire14 dog14Fragment = new FragmentDogQuestionnaire14();
            transaction.replace(R.id.nav_host_fragment,dog14Fragment);
            transaction.addToBackStack("dogQuestionnaire14");
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