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

public class FragmentCatQuestionnaire4 extends Fragment {

    public static FragmentCatQuestionnaire4 newInstance() {
        return new FragmentCatQuestionnaire4();
    }

    ImageButton cpopup4;
    SeekBar cseekBar22, cseekBar23, cseekBar24, cseekBar25, cseekBar26, cseekBar27, cseekBar28;
    TextView crate22, crate23, crate24, crate25, crate26, crate27, crate28;
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
        cseekBar22 = (SeekBar) getView().findViewById(R.id.cseekBar22);
        cseekBar23 = (SeekBar) getView().findViewById(R.id.cseekBar23);
        cseekBar24 = (SeekBar) getView().findViewById(R.id.cseekBar24);
        cseekBar25 = (SeekBar) getView().findViewById(R.id.cseekBar25);
        cseekBar26= (SeekBar) getView().findViewById(R.id.cseekBar26);
        cseekBar27 = (SeekBar) getView().findViewById(R.id.cseekBar27);
        cseekBar28 = (SeekBar) getView().findViewById(R.id.cseekBar28);
        crate22 = (TextView) getView().findViewById(R.id.crating22);
        crate23 = (TextView) getView().findViewById(R.id.crating23);
        crate24 = (TextView) getView().findViewById(R.id.crating24);
        crate25 = (TextView) getView().findViewById(R.id.crating25);
        crate26 = (TextView) getView().findViewById(R.id.crating26);
        crate27 = (TextView) getView().findViewById(R.id.crating27);
        crate28 = (TextView) getView().findViewById(R.id.crating28);



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

        cseekBar26.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar26, int i, boolean b) {
                setSeekText(i, crate26);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar26) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar26) {

            }
        });

        cseekBar27.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar27, int i, boolean b) {
                setSeekText(i, crate27);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar27) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar27) {

            }
        });

        cseekBar28.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar28, int i, boolean b) {
                setSeekText(i, crate28);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar28) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar28) {

            }
        });

        cpopup4 = getView().findViewById(R.id.cinstructionsBTN4);
        cpopup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.cproceed_ques4);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(22, cseekBar22.getProgress());
                mViewModel.setDogAnswer(23, cseekBar23.getProgress());
                mViewModel.setDogAnswer(24, cseekBar24.getProgress());
                mViewModel.setDogAnswer(25, cseekBar25.getProgress());
                mViewModel.setDogAnswer(26, cseekBar26.getProgress());
                mViewModel.setDogAnswer(27, cseekBar27.getProgress());
                mViewModel.setDogAnswer(28, cseekBar28.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentCatQuestionnaire5 cat5Fragment = new FragmentCatQuestionnaire5();
                transaction.replace(R.id.nav_host_fragment,cat5Fragment);
                transaction.addToBackStack("catQuestionnaire5");
                transaction.commit();
            }
        });


    }

    private void setSeekText(int i, TextView j) {
        if(i == 0 ||  i == 16 ||  i == 1 || i == 15 ){
            j.setText("Extremely Important");
        }
        else if(i == 2 || i == 14 || i == 3 || i == 13){
            j.setText("Significantly Important");
        }
        else if(i == 4 || i == 12 || i == 5 || i == 11){
            j.setText("Moderately Important");
        }
        else if( i == 7 || i == 9 ||  i == 6 || i == 10){
            j.setText("Slightly Important");
        }
        else {
            j.setText("Equally Important");
        }
    }

    private void showDialog() {
        HelpPopup helpDialogc4 = new HelpPopup();
        helpDialogc4.show(getParentFragmentManager(), "Help Popup");
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