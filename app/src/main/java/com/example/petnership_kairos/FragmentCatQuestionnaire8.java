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

public class FragmentCatQuestionnaire8 extends Fragment {

    public static FragmentCatQuestionnaire8 newInstance() {
        return new FragmentCatQuestionnaire8();
    }

    ImageButton cpopup8;
    SeekBar cseekBar36, cseekBar37, cseekBar38;
    TextView crate36, crate37, crate38;
    MCDMAnswersViewModel mViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire8, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 8");

        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);

        cseekBar36 = getView().findViewById(R.id.cseekBar36);
        cseekBar37= getView().findViewById(R.id.cseekBar37);
        cseekBar38 = getView().findViewById(R.id.cseekBar38);
        crate36 = getView().findViewById(R.id.crating36);
        crate37 = getView().findViewById(R.id.crating37);
        crate38 = getView().findViewById(R.id.crating38);



        cseekBar36.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar36, int i, boolean b) {
                setSeekText(i, crate36);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar36) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar36) {

            }
        });

        cseekBar37.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar37, int i, boolean b) {
                setSeekText(i, crate37);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar37) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar37) {

            }
        });

        cseekBar38.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar38, int i, boolean b) {
                setSeekText(i, crate38);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar38) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar38) {

            }
        });

        cpopup8 = getView().findViewById(R.id.cinstructionsBTN8);
        cpopup8.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.cproceed_ques8);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(36, cseekBar36.getProgress());
            mViewModel.setAnswer(37, cseekBar37.getProgress());
            mViewModel.setAnswer(38, cseekBar38.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentCatQuestionnaire9 cat9Fragment = new FragmentCatQuestionnaire9();
            transaction.replace(R.id.nav_host_fragment,cat9Fragment);
            transaction.addToBackStack("catQuestionnaire9");
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