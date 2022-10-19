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

public class FragmentCatQuestionnaire3 extends Fragment {

    public static FragmentCatQuestionnaire3 newInstance() {
        return new FragmentCatQuestionnaire3();
    }

    ImageButton cpopup3;
    SeekBar cseekBar15, cseekBar16, cseekBar17, cseekBar18, cseekBar19, cseekBar20,cseekBar21;
    TextView crate15, crate16, crate17, crate18, crate19, crate20, crate21;
    MCDMAnswersViewModel mViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire3, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 3");

        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);

        cseekBar15 = (SeekBar) getView().findViewById(R.id.cseekBar15);
        cseekBar16 = (SeekBar) getView().findViewById(R.id.cseekBar16);
        cseekBar17 = (SeekBar) getView().findViewById(R.id.cseekBar17);
        cseekBar18 = (SeekBar) getView().findViewById(R.id.cseekBar18);
        cseekBar19 = (SeekBar) getView().findViewById(R.id.cseekBar19);
        cseekBar20 = (SeekBar) getView().findViewById(R.id.cseekBar20);
        cseekBar21 = (SeekBar) getView().findViewById(R.id.cseekBar21);
        crate15 = (TextView) getView().findViewById(R.id.crating15);
        crate16 = (TextView) getView().findViewById(R.id.crating16);
        crate17 = (TextView) getView().findViewById(R.id.crating17);
        crate18 = (TextView) getView().findViewById(R.id.crating18);
        crate19 = (TextView) getView().findViewById(R.id.crating19);
        crate20 = (TextView) getView().findViewById(R.id.crating20);
        crate21 = (TextView) getView().findViewById(R.id.crating21);



        cseekBar15.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar cseekBar15, int i, boolean b) {
                setSeekText(i, crate15);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar15) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar15) {

            }
        });

        cseekBar16.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar16, int i, boolean b) {
                setSeekText(i, crate16);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar16) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar16) {

            }
        });

        cseekBar17.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar17, int i, boolean b) {
                setSeekText(i, crate17);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar17) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar17) {

            }
        });

        cseekBar18.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar18, int i, boolean b) {
                setSeekText(i, crate18);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar18) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar18) {

            }
        });

        cseekBar19.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar19, int i, boolean b) {
                setSeekText(i, crate19);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar19) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar19) {

            }
        });

        cseekBar20.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar20, int i, boolean b) {
                setSeekText(i, crate20);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar20) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar20) {

            }
        });

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

        cpopup3 = getView().findViewById(R.id.cinstructionsBTN3);
        cpopup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.cproceed_ques3);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(15, cseekBar15.getProgress());
                mViewModel.setDogAnswer(16, cseekBar16.getProgress());
                mViewModel.setDogAnswer(17, cseekBar17.getProgress());
                mViewModel.setDogAnswer(18, cseekBar18.getProgress());
                mViewModel.setDogAnswer(19, cseekBar19.getProgress());
                mViewModel.setDogAnswer(20, cseekBar20.getProgress());
                mViewModel.setDogAnswer(21, cseekBar21.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentCatQuestionnaire4 cat4Fragment = new FragmentCatQuestionnaire4();
                transaction.replace(R.id.nav_host_fragment,cat4Fragment);
                transaction.addToBackStack("catQuestionnaire4");
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