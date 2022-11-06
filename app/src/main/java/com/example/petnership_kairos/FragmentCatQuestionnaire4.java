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

public class  FragmentCatQuestionnaire4 extends Fragment {

    public static FragmentCatQuestionnaire4 newInstance() {
        return new FragmentCatQuestionnaire4();
    }

    ImageButton cpopup4;
    SeekBar cseekBar21, cseekBar22, cseekBar23, cseekBar24, cseekBar25;
    TextView crate21, crate22, crate23, crate24, crate25;
    MCDMAnswersViewModel mViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire4, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 4");

        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        cseekBar21 = getView().findViewById(R.id.cseekBar21);
        cseekBar22 = getView().findViewById(R.id.cseekBar22);
        cseekBar23 = getView().findViewById(R.id.cseekBar23);
        cseekBar24 = getView().findViewById(R.id.cseekBar24);
        cseekBar25 = getView().findViewById(R.id.cseekBar25);

        crate21 = getView().findViewById(R.id.crating21);
        crate22 = getView().findViewById(R.id.crating22);
        crate23 = getView().findViewById(R.id.crating23);
        crate24 = getView().findViewById(R.id.crating24);
        crate25 = getView().findViewById(R.id.crating25);
        // bring back previous progress if any
        if (mViewModel.getAnswer(21) != null) cseekBar21.setProgress(mViewModel.getAnswer(21));
        if (mViewModel.getAnswer(22) != null) cseekBar22.setProgress(mViewModel.getAnswer(22));
        if (mViewModel.getAnswer(23) != null) cseekBar23.setProgress(mViewModel.getAnswer(23));
        if (mViewModel.getAnswer(24) != null) cseekBar24.setProgress(mViewModel.getAnswer(24));
        if (mViewModel.getAnswer(25) != null) cseekBar25.setProgress(mViewModel.getAnswer(25));


        cseekBar21.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar21, int i, boolean b) {
                setSeekText(i, crate21);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar21) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar21) {

            }
        });

        cseekBar22.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar cseekBar22, int i, boolean b) {
                setSeekText(i, crate22);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar22) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar22) {

            }
        });

        cseekBar23.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar23, int i, boolean b) {
                setSeekText(i, crate23);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar23) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar23) {

            }
        });

        cseekBar24.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar24, int i, boolean b) {
                setSeekText(i, crate24);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar24) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar24) {

            }
        });

        cseekBar25.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar25, int i, boolean b) {
                setSeekText(i, crate25);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar25) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar25) {

            }
        });

        cpopup4 = getView().findViewById(R.id.cinstructionsBTN4);
        cpopup4.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.cproceed_ques4);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(21, cseekBar21.getProgress());
            mViewModel.setAnswer(22, cseekBar22.getProgress());
            mViewModel.setAnswer(23, cseekBar23.getProgress());
            mViewModel.setAnswer(24, cseekBar24.getProgress());
            mViewModel.setAnswer(25, cseekBar25.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentCatQuestionnaire5 cat5Fragment = new FragmentCatQuestionnaire5();
            transaction.replace(R.id.nav_host_fragment,cat5Fragment);
            transaction.addToBackStack("catQuestionnaire5");
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
        HelpPopup helpDialog = new HelpPopup("Cat", "Main");
        helpDialog.show(getParentFragmentManager(), "Help Popup");

    }


}