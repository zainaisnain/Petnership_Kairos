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

public class FragmentCatQuestionnaire6 extends Fragment {

    public static FragmentCatQuestionnaire6 newInstance() {
        return new FragmentCatQuestionnaire6();
    }

    ImageButton cpopup6;
    SeekBar cseekBar33, cseekBar34, cseekBar35, cseekBar36, cseekBar37, cseekBar38;
    TextView crate33, crate34, crate35, crate36, crate37, crate38;
    MCDMAnswersViewModel mViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire6, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 6");

        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        cseekBar33 = getView().findViewById(R.id.cseekBar33);
        cseekBar34 = getView().findViewById(R.id.cseekBar34);
        cseekBar35 = getView().findViewById(R.id.cseekBar35);
        cseekBar36 = getView().findViewById(R.id.cseekBar36);
        cseekBar37= getView().findViewById(R.id.cseekBar37);
        cseekBar38 = getView().findViewById(R.id.cseekBar38);
        crate33 = getView().findViewById(R.id.crating33);
        crate34 = getView().findViewById(R.id.crating34);
        crate35 = getView().findViewById(R.id.crating35);
        crate36 = getView().findViewById(R.id.crating36);
        crate37 = getView().findViewById(R.id.crating37);
        crate38 = getView().findViewById(R.id.crating38);





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

        cpopup6 = getView().findViewById(R.id.cinstructionsBTN6);
        cpopup6.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.cproceed_ques6);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(33, cseekBar33.getProgress());
            mViewModel.setAnswer(34, cseekBar34.getProgress());
            mViewModel.setAnswer(35, cseekBar35.getProgress());
            mViewModel.setAnswer(36, cseekBar36.getProgress());
            mViewModel.setAnswer(37, cseekBar37.getProgress());
            mViewModel.setAnswer(38, cseekBar38.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentCatQuestionnaire7 cat7Fragment = new FragmentCatQuestionnaire7();
            transaction.replace(R.id.nav_host_fragment,cat7Fragment);
            transaction.addToBackStack("catQuestionnaire7");
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
        HelpPopup helpDialogc6 = new HelpPopup();
        helpDialogc6.show(getParentFragmentManager(), "Help Popup");
        /*
                final Dialog helpDialog = new Dialog();
        helpDialog.setContentView(R.layout.help_popup);
        helpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        helpDialog.show();
        ImageButton closeBTN = (ImageButton) helpDialog.findViewById(R.id.closeBTN);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                helpDialog.dismiss();

            }
        });
         */
    }


}