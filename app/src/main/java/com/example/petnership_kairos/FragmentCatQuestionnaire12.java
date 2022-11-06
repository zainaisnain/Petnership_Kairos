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

public class FragmentCatQuestionnaire12 extends Fragment {

    public static FragmentCatQuestionnaire12 newInstance() {
        return new FragmentCatQuestionnaire12();
    }

    ImageButton cpopup12;
    SeekBar cseekBar48, cseekBar49, cseekBar50;
    TextView crate48, crate49, crate50;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire12, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 12");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));
        cseekBar48 = getView().findViewById(R.id.cseekBar48);
        cseekBar49= getView().findViewById(R.id.cseekBar49);
        cseekBar50 = getView().findViewById(R.id.cseekBar50);
        crate48 = getView().findViewById(R.id.crating48);
        crate49 = getView().findViewById(R.id.crating49);
        crate50 = getView().findViewById(R.id.crating50);



        // bring back previous progress if any
        if (mViewModel.getAnswer(48) != null) cseekBar48.setProgress(mViewModel.getAnswer(48));
        if (mViewModel.getAnswer(49) != null) cseekBar49.setProgress(mViewModel.getAnswer(49));
        if (mViewModel.getAnswer(50) != null) cseekBar50.setProgress(mViewModel.getAnswer(50));

        cseekBar48.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar48, int i, boolean b) {
                setSeekText(i, crate48);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar48) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar48) {

            }
        });

        cseekBar49.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar49, int i, boolean b) {
                setSeekText(i, crate49);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar49) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar49) {

            }
        });

        cseekBar50.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar50, int i, boolean b) {
                setSeekText(i, crate50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar50) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar50) {

            }
        });


        cpopup12 = getView().findViewById(R.id.cinstructionsBTN12);
        cpopup12.setOnClickListener(view1 -> showDialog());

        Button submitBtn = getView().findViewById(R.id.cproceed_ques12);
        submitBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(48, cseekBar48.getProgress());
            mViewModel.setAnswer(49, cseekBar49.getProgress());
            mViewModel.setAnswer(50, cseekBar50.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentCatQuestionnaire13 cat13Fragment = new FragmentCatQuestionnaire13();
            transaction.replace(R.id.nav_host_fragment,cat13Fragment);
            transaction.addToBackStack("catQuestionnaire13");
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