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

public class FragmentCatQuestionnaire13 extends Fragment {

    public static FragmentCatQuestionnaire13 newInstance() {
        return new FragmentCatQuestionnaire13();
    }

    ImageButton cpopup13;
    SeekBar cseekBar51, cseekBar52, cseekBar53;
    TextView crate51, crate52, crate53;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire13, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 13");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));

        cseekBar51 = getView().findViewById(R.id.cseekBar51);
        cseekBar52 = getView().findViewById(R.id.cseekBar52);
        cseekBar53 = getView().findViewById(R.id.cseekBar53);
        crate51 = getView().findViewById(R.id.crating51);
        crate52 = getView().findViewById(R.id.crating52);
        crate53 = getView().findViewById(R.id.crating53);
        // bring back previous progress if any
        //     if (mViewModel.getDogAnswer(8) != null){
        //       cseekBar56.setProgress(mViewModel.getDogAnswer(8));
        // }

        cseekBar51.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar cseekBar51, int i, boolean b) {

                setSeekText(i, crate51);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar51) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar51) {

            }
        });

        cseekBar52.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar52, int i, boolean b) {

                setSeekText(i, crate52);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar52) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar52) {

            }
        });

        cseekBar53.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar53, int i, boolean b) {

                setSeekText(i, crate53);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar53) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar53) {

            }
        });


        cpopup13 = getView().findViewById(R.id.cinstructionsBTN13);
        cpopup13.setOnClickListener(view1 -> showDialog());

        Button submitBtn = getView().findViewById(R.id.cproceed_ques13);
        submitBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(51, cseekBar51.getProgress());
            mViewModel.setAnswer(52, cseekBar52.getProgress());
            mViewModel.setAnswer(53, cseekBar53.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentCatQuestionnaire14 cat14Fragment = new FragmentCatQuestionnaire14();
            transaction.replace(R.id.nav_host_fragment,cat14Fragment);
            transaction.addToBackStack("catQuestionnaire14");
            transaction.commit();
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