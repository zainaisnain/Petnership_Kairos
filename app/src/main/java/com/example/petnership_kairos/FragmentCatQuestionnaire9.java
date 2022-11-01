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

public class FragmentCatQuestionnaire9 extends Fragment {

    public static FragmentCatQuestionnaire9 newInstance() {
        return new FragmentCatQuestionnaire9();
    }

    ImageButton cpopup9;
    SeekBar cseekBar39, cseekBar40, cseekBar41;
    TextView crate39, crate40, crate41;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire9, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 9");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));

        cseekBar39 = getView().findViewById(R.id.cseekBar39);
        cseekBar40 = getView().findViewById(R.id.cseekBar40);
        cseekBar41 = getView().findViewById(R.id.cseekBar41);
        crate39 = getView().findViewById(R.id.crating39);
        crate40 = getView().findViewById(R.id.crating40);
        crate41 = getView().findViewById(R.id.crating41);

        // bring back previous progress if any
   //     if (mViewModel.getDogAnswer(8) != null){
     //       cseekBar56.setProgress(mViewModel.getDogAnswer(8));
       // }

        cseekBar39.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar cseekBar39, int i, boolean b) {
                setSeekText(i, crate39);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar39) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar39) {

            }
        });

        cseekBar40.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar40, int i, boolean b) {
                setSeekText(i, crate40);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar40) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar40) {

            }
        });

        cseekBar41.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar41, int i, boolean b) {
                setSeekText(i, crate41);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar41) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar41) {

            }
        });


        cpopup9 = getView().findViewById(R.id.cinstructionsBTN9);
        cpopup9.setOnClickListener(view1 -> showDialog());

        Button submitBtn = getView().findViewById(R.id.cproceed_ques9);
        submitBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(39, cseekBar39.getProgress());
            mViewModel.setAnswer(40, cseekBar40.getProgress());
            mViewModel.setAnswer(41, cseekBar41.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentCatQuestionnaire10 cat10Fragment = new FragmentCatQuestionnaire10();
            transaction.replace(R.id.nav_host_fragment,cat10Fragment);
            transaction.addToBackStack("catQuestionnaire10");
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