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

public class FragmentDogQuestionnaire15 extends Fragment {

    public static FragmentDogQuestionnaire15 newInstance() {
        return new FragmentDogQuestionnaire15();
    }

    ImageButton popup15, backBtn;
    SeekBar seekBar57, seekBar58, seekBar59;
    TextView rate57, rate58, rate59;
    MCDMAnswersViewModel mViewModel;
    MCDM mainAlgo;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire15, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 15");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);


        // begin data fetch
        mainAlgo = new MCDM();
        mainAlgo.fetchAlternatives(getContext(),1);


        seekBar57 = getView().findViewById(R.id.seekBar57);
        seekBar58 = getView().findViewById(R.id.seekBar58);
        seekBar59 = getView().findViewById(R.id.seekBar59);
        rate57 = getView().findViewById(R.id.rating57);
        rate58 = getView().findViewById(R.id.rating58);
        rate59 = getView().findViewById(R.id.rating59);

        // bring back previous progress if any
        //  if (mViewModel.getDogAnswer(8) != null){
        //    seekBar54.setProgress(mViewModel.getDogAnswer(8));
        //}

        seekBar57.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar57, int i, boolean b) {

                setSeekText(i, rate57);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar57) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar57) {

            }
        });

        seekBar58.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar58, int i, boolean b) {

                setSeekText(i, rate58);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar58) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar58) {

            }
        });

        seekBar59.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar59, int i, boolean b) {

                setSeekText(i, rate59);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar59) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar59) {

            }
        });


        popup15 = getView().findViewById(R.id.instructionsBTN15);
        popup15.setOnClickListener(view1 -> showDialog());

        Button submitBtn = getView().findViewById(R.id.submit_ques15);
        submitBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(57, seekBar57.getProgress());
            mViewModel.setAnswer(58, seekBar58.getProgress());
            mViewModel.setAnswer(59, seekBar59.getProgress());

            for (int i = 1; i < 60; i++) {
                System.out.println("SEEKBAR #" + i + ": " + mViewModel.getAnswer(i));
            }

            mainAlgo.beginMCDM();

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentRecommendedPets recPets = new FragmentRecommendedPets();
            transaction.replace(R.id.nav_host_fragment,recPets);
            transaction.addToBackStack("recommendedPets");
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