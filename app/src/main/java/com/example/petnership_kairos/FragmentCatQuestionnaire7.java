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

public class FragmentCatQuestionnaire7 extends Fragment {

    public static FragmentCatQuestionnaire7 newInstance() {
        return new FragmentCatQuestionnaire7();
    }

    ImageButton cpopup7;
    SeekBar cseekBar33, cseekBar34, cseekBar35;
    TextView crate33, crate34, crate35;
    MCDMAnswersViewModel mViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire7, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 7");

        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);


        cseekBar33 = getView().findViewById(R.id.cseekBar33);
        cseekBar34 = getView().findViewById(R.id.cseekBar34);
        cseekBar35 = getView().findViewById(R.id.cseekBar35);
        crate33 = getView().findViewById(R.id.crating33);
        crate34 = getView().findViewById(R.id.crating34);
        crate35 = getView().findViewById(R.id.crating35);

        cseekBar33.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar cseekBar33, int i, boolean b) {
                setSeekText(i, crate33);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar33) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar33) {

            }
        });

        cseekBar34.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar34, int i, boolean b) {
                setSeekText(i, crate34);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar34) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar34) {

            }
        });

        cseekBar35.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar35, int i, boolean b) {
                setSeekText(i, crate35);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar35) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar35) {

            }
        });

        cpopup7 = getView().findViewById(R.id.cinstructionsBTN7);
        cpopup7.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.cproceed_ques7);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(33, cseekBar33.getProgress());
            mViewModel.setAnswer(34, cseekBar34.getProgress());
            mViewModel.setAnswer(35, cseekBar35.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentCatQuestionnaire8 cat8Fragment = new FragmentCatQuestionnaire8();
            transaction.replace(R.id.nav_host_fragment,cat8Fragment);
            transaction.addToBackStack("catQuestionnaire8");
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