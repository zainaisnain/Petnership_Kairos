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

public class FragmentDogQuestionnaire11 extends Fragment {

    public static FragmentDogQuestionnaire11 newInstance() {
        return new FragmentDogQuestionnaire11();
    }


    ImageButton popup11, backBtn;
    SeekBar seekBar45, seekBar46, seekBar47;
    TextView rate45, rate46, rate47;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire11, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 11");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));


        seekBar45 = getView().findViewById(R.id.seekBar45);
        seekBar46 = getView().findViewById(R.id.seekBar46);
        seekBar47 = getView().findViewById(R.id.seekBar47);
        rate45 = getView().findViewById(R.id.rating45);
        rate46 = getView().findViewById(R.id.rating46);
        rate47 = getView().findViewById(R.id.rating47);


        // bring back previous progress if any
        if (mViewModel.getAnswer(45) != null) seekBar45.setProgress(mViewModel.getAnswer(45));
        if (mViewModel.getAnswer(46) != null) seekBar46.setProgress(mViewModel.getAnswer(46));
        if (mViewModel.getAnswer(47) != null) seekBar47.setProgress(mViewModel.getAnswer(47));


        seekBar45.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar45, int i, boolean b) {
                setSeekText(i, rate45);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar45) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar45) {

            }
        });

        seekBar46.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar46, int i, boolean b) {
                setSeekText(i, rate46);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar46) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar46) {

            }
        });

        seekBar47.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar47, int i, boolean b) {
                setSeekText(i, rate47);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar47) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar47) {

            }
        });


        popup11 = getView().findViewById(R.id.instructionsBTN11);
        popup11.setOnClickListener(view1 -> showDialog());

        Button proceedBtn = getView().findViewById(R.id.proceed_ques11);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(45, seekBar45.getProgress());
            mViewModel.setAnswer(46, seekBar46.getProgress());
            mViewModel.setAnswer(47, seekBar47.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire12 dog12Fragment = new FragmentDogQuestionnaire12();
            transaction.replace(R.id.nav_host_fragment,dog12Fragment);
            transaction.addToBackStack("dogQuestionnaire12");
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