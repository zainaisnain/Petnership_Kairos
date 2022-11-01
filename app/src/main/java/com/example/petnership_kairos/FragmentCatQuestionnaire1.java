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

public class FragmentCatQuestionnaire1 extends Fragment {

    public static FragmentCatQuestionnaire1 newInstance() {
        return new FragmentCatQuestionnaire1();
    }

    ImageButton cpopup1;
    SeekBar cseekBar1, cseekBar2, cseekBar3, cseekBar4, cseekBar5, cseekBar6, cseekBar7;
    TextView crate1, crate2, crate3, crate4, crate5, crate6, crate7;
    MCDMAnswersViewModel mViewModel;
    View view;
    private ImageButton backBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cat_questionnaire1, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 1");

        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);


        cseekBar1 = getView().findViewById(R.id.cseekBar1);
        cseekBar2 = getView().findViewById(R.id.cseekBar2);
        cseekBar3 = getView().findViewById(R.id.cseekBar3);
        cseekBar4 = getView().findViewById(R.id.cseekBar4);
        cseekBar5 = getView().findViewById(R.id.cseekBar5);
        cseekBar6 = getView().findViewById(R.id.cseekBar6);
        cseekBar7 = getView().findViewById(R.id.cseekBar7);
        crate1 = getView().findViewById(R.id.crating1);
        crate2 = getView().findViewById(R.id.crating2);
        crate3 = getView().findViewById(R.id.crating3);
        crate4 = getView().findViewById(R.id.crating4);
        crate5 = getView().findViewById(R.id.crating5);
        crate6 = getView().findViewById(R.id.crating6);
        crate7 = getView().findViewById(R.id.crating7);


        cseekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar1, int i, boolean b) {
                setSeekText(i, crate1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar1) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar1) {

            }
        });

        cseekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int i, boolean b) {
                setSeekText(i, crate2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar2) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar2) {

            }
        });

        cseekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar3, int i, boolean b) {
                setSeekText(i, crate3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar3) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar3) {

            }
        });

        cseekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar4, int i, boolean b) {
                setSeekText(i, crate4);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar4) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar4) {

            }
        });

        cseekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar5, int i, boolean b) {
                setSeekText(i, crate5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar5) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar5) {

            }
        });

        cseekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar6, int i, boolean b) {
                setSeekText(i, crate6);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar6) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar6) {

            }
        });

        cseekBar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar7, int i, boolean b) {
                setSeekText(i, crate7);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar7) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar7) {

            }
        });

        cpopup1 = getView().findViewById(R.id.cinstructionsBTN1);
        cpopup1.setOnClickListener(view1 -> showDialog());

        Button proceedBtn = getView().findViewById(R.id.cproceed_ques1);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(1, cseekBar1.getProgress());
            mViewModel.setAnswer(2, cseekBar2.getProgress());
            mViewModel.setAnswer(3, cseekBar3.getProgress());
            mViewModel.setAnswer(4, cseekBar4.getProgress());
            mViewModel.setAnswer(5, cseekBar5.getProgress());
            mViewModel.setAnswer(6, cseekBar6.getProgress());
            mViewModel.setAnswer(7, cseekBar7.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentCatQuestionnaire2 cat2Fragment = new FragmentCatQuestionnaire2();
            transaction.replace(R.id.nav_host_fragment,cat2Fragment);
            transaction.addToBackStack("catQuestionnaire2");
            transaction.commit();
        });
        backBtn = getView().findViewById(R.id.btnBack);
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
        HelpPopup helpDialog = new HelpPopup("Cat", "Main");
        helpDialog.show(getParentFragmentManager(), "Help Popup");


    }


}