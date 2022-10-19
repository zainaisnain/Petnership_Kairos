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
    SeekBar cseekBar39, cseekBar40, cseekBar41, cseekBar42, cseekBar43, cseekBar44;
    TextView crate39, crate40, crate41, crate42, crate43, crate44;
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


        cseekBar39 = (SeekBar) getView().findViewById(R.id.cseekBar39);
        cseekBar40 = (SeekBar) getView().findViewById(R.id.cseekBar40);
        cseekBar41 = (SeekBar) getView().findViewById(R.id.cseekBar41);
        cseekBar42 = (SeekBar) getView().findViewById(R.id.cseekBar42);
        cseekBar43= (SeekBar) getView().findViewById(R.id.cseekBar43);
        cseekBar44 = (SeekBar) getView().findViewById(R.id.cseekBar44);
        crate39 = (TextView) getView().findViewById(R.id.crating39);
        crate40 = (TextView) getView().findViewById(R.id.crating40);
        crate41 = (TextView) getView().findViewById(R.id.crating41);
        crate42 = (TextView) getView().findViewById(R.id.crating42);
        crate43 = (TextView) getView().findViewById(R.id.crating43);
        crate44 = (TextView) getView().findViewById(R.id.crating44);




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

        cseekBar42.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar42, int i, boolean b) {
                setSeekText(i, crate42);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar42) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar42) {

            }
        });

        cseekBar43.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar43, int i, boolean b) {
                setSeekText(i, crate43);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar43) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar43) {

            }
        });

        cseekBar44.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar44, int i, boolean b) {
                setSeekText(i, crate44);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar44) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar44) {

            }
        });

        cpopup7 = getView().findViewById(R.id.cinstructionsBTN7);
        cpopup7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.cproceed_ques7);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(39, cseekBar39.getProgress());
                mViewModel.setDogAnswer(40, cseekBar40.getProgress());
                mViewModel.setDogAnswer(41, cseekBar41.getProgress());
                mViewModel.setDogAnswer(42, cseekBar42.getProgress());
                mViewModel.setDogAnswer(43, cseekBar43.getProgress());
                mViewModel.setDogAnswer(44, cseekBar44.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentCatQuestionnaire8 cat8Fragment = new FragmentCatQuestionnaire8();
                transaction.replace(R.id.nav_host_fragment,cat8Fragment);
                transaction.addToBackStack("catQuestionnaire8");
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