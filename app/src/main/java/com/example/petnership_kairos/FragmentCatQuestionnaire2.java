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

public class FragmentCatQuestionnaire2 extends Fragment {

    public static FragmentCatQuestionnaire2 newInstance() {
        return new FragmentCatQuestionnaire2();
    }

    ImageButton cpopup2;
    SeekBar cseekBar8, cseekBar9, cseekBar10, cseekBar11, cseekBar12, cseekBar13,cseekBar14;
    TextView crate8, crate9, crate10, crate11, crate12, crate13, crate14;
    MCDMAnswersViewModel mViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire2, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 2");

        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);

        cseekBar8 = getView().findViewById(R.id.cseekBar8);
        cseekBar9 = getView().findViewById(R.id.cseekBar9);
        cseekBar10 = getView().findViewById(R.id.cseekBar10);
        cseekBar11 = getView().findViewById(R.id.cseekBar11);
        cseekBar12 = getView().findViewById(R.id.cseekBar12);
        cseekBar13 = getView().findViewById(R.id.cseekBar13);
        cseekBar14 = getView().findViewById(R.id.cseekBar14);
        crate8 = getView().findViewById(R.id.crating8);
        crate9 = getView().findViewById(R.id.crating9);
        crate10 = getView().findViewById(R.id.crating10);
        crate11 = getView().findViewById(R.id.crating11);
        crate12 = getView().findViewById(R.id.crating12);
        crate13 = getView().findViewById(R.id.crating13);
        crate14 = getView().findViewById(R.id.crating14);



        cseekBar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar cseekBar8, int i, boolean b) {
                setSeekText(i, crate8);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar8) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar8) {

            }
        });

        cseekBar9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar9, int i, boolean b) {
                setSeekText(i, crate9);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar9) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar9) {

            }
        });

        cseekBar10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar10, int i, boolean b) {
                setSeekText(i, crate10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar10) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar10) {

            }
        });

        cseekBar11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar11, int i, boolean b) {
                setSeekText(i, crate11);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar11) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar11) {

            }
        });

        cseekBar12.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar12, int i, boolean b) {
                setSeekText(i, crate12);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar12) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar12) {

            }
        });

        cseekBar13.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar13, int i, boolean b) {
                setSeekText(i, crate13);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar13) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar13) {

            }
        });

        cseekBar14.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar14, int i, boolean b) {
                setSeekText(i, crate14);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar14) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar14) {

            }
        });

        cpopup2 = getView().findViewById(R.id.cinstructionsBTN2);
        cpopup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.cproceed_ques2);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setAnswer(8, cseekBar8.getProgress());
                mViewModel.setAnswer(9, cseekBar9.getProgress());
                mViewModel.setAnswer(10, cseekBar10.getProgress());
                mViewModel.setAnswer(11, cseekBar11.getProgress());
                mViewModel.setAnswer(12, cseekBar12.getProgress());
                mViewModel.setAnswer(13, cseekBar13.getProgress());
                mViewModel.setAnswer(14, cseekBar14.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                FragmentCatQuestionnaire3 cat3Fragment = new FragmentCatQuestionnaire3();
                transaction.replace(R.id.nav_host_fragment,cat3Fragment);
                transaction.addToBackStack("catQuestionnaire3");
                transaction.commit();
            }
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