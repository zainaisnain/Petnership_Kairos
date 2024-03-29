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

public class FragmentDogQuestionnaire2 extends Fragment {

    public static FragmentDogQuestionnaire2 newInstance() {
        return new FragmentDogQuestionnaire2();
    }

    ImageButton popup2, backBtn;
    SeekBar seekBar8, seekBar9, seekBar10, seekBar11, seekBar12, seekBar13,seekBar14;
    TextView rate8, rate9, rate10, rate11, rate12, rate13, rate14;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire2, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 2");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));

        seekBar8 = getView().findViewById(R.id.seekBar8);
        seekBar9 = getView().findViewById(R.id.seekBar9);
        seekBar10 = getView().findViewById(R.id.seekBar10);
        seekBar11 = getView().findViewById(R.id.seekBar11);
        seekBar12 = getView().findViewById(R.id.seekBar12);
        seekBar13 = getView().findViewById(R.id.seekBar13);
        seekBar14 = getView().findViewById(R.id.seekBar14);
        rate8 = getView().findViewById(R.id.rating8);
        rate9 = getView().findViewById(R.id.rating9);
        rate10 = getView().findViewById(R.id.rating10);
        rate11 = getView().findViewById(R.id.rating11);
        rate12 = getView().findViewById(R.id.rating12);
        rate13 = getView().findViewById(R.id.rating13);
        rate14 = getView().findViewById(R.id.rating14);


        // bring back previous progress if any
        if (mViewModel.getAnswer(8) != null) seekBar8.setProgress(mViewModel.getAnswer(8));
        if (mViewModel.getAnswer(9) != null) seekBar9.setProgress(mViewModel.getAnswer(9));
        if (mViewModel.getAnswer(10) != null) seekBar10.setProgress(mViewModel.getAnswer(10));
        if (mViewModel.getAnswer(11) != null) seekBar11.setProgress(mViewModel.getAnswer(11));
        if (mViewModel.getAnswer(12) != null) seekBar12.setProgress(mViewModel.getAnswer(12));
        if (mViewModel.getAnswer(13) != null) seekBar13.setProgress(mViewModel.getAnswer(13));
        if (mViewModel.getAnswer(14) != null) seekBar14.setProgress(mViewModel.getAnswer(14));

        seekBar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar8, int i, boolean b) {
                setSeekText(i, rate8);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar8) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar8) {

            }
        });

        seekBar9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar9, int i, boolean b) {
                setSeekText(i, rate9);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar9) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar9) {

            }
        });

        seekBar10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar10, int i, boolean b) {
                setSeekText(i, rate10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar10) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar10) {

            }
        });

        seekBar11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar11, int i, boolean b) {
                setSeekText(i, rate11);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar11) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar11) {

            }
        });

        seekBar12.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar12, int i, boolean b) {
                setSeekText(i, rate12);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar12) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar12) {

            }
        });

        seekBar13.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar13, int i, boolean b) {
                setSeekText(i, rate13);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar13) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar13) {

            }
        });

        seekBar14.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar14, int i, boolean b) {
                setSeekText(i, rate14);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar14) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar14) {

            }
        });

        popup2 = getView().findViewById(R.id.instructionsBTN2);
        popup2.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.proceed_ques2);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(8, seekBar8.getProgress());
            mViewModel.setAnswer(9, seekBar9.getProgress());
            mViewModel.setAnswer(10, seekBar10.getProgress());
            mViewModel.setAnswer(11, seekBar11.getProgress());
            mViewModel.setAnswer(12, seekBar12.getProgress());
            mViewModel.setAnswer(13, seekBar13.getProgress());
            mViewModel.setAnswer(14, seekBar14.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire3 dog3Fragment = new FragmentDogQuestionnaire3();
            transaction.replace(R.id.nav_host_fragment,dog3Fragment);
            transaction.addToBackStack("dogQuestionnaire3");
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