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

    ImageButton popup10;
    public static FragmentCatQuestionnaire9 newInstance() {
        return new FragmentCatQuestionnaire9();
    }

    ImageButton cpopup9;
    SeekBar cseekBar51, cseekBar52, cseekBar53, cseekBar54, cseekBar55, cseekBar56;
    TextView crate51, crate52, crate53, crate54, crate55, crate56;
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
        System.out.println("Test: " + mViewModel.getDogAnswer(1));

        cseekBar51 = (SeekBar) getView().findViewById(R.id.cseekBar51);
        cseekBar52 = (SeekBar) getView().findViewById(R.id.cseekBar52);
        cseekBar53 = (SeekBar) getView().findViewById(R.id.cseekBar53);
        cseekBar54 = (SeekBar) getView().findViewById(R.id.cseekBar54);
        cseekBar55= (SeekBar) getView().findViewById(R.id.cseekBar55);
        cseekBar56 = (SeekBar) getView().findViewById(R.id.cseekBar56);
        crate51 = (TextView) getView().findViewById(R.id.crating51);
        crate52 = (TextView) getView().findViewById(R.id.crating52);
        crate53 = (TextView) getView().findViewById(R.id.crating53);
        crate54 = (TextView) getView().findViewById(R.id.crating54);
        crate55 = (TextView) getView().findViewById(R.id.crating55);
        crate56 = (TextView) getView().findViewById(R.id.crating56);

        // bring back previous progress if any
        if (mViewModel.getDogAnswer(8) != null){
            cseekBar56.setProgress(mViewModel.getDogAnswer(8));
        }


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

        cseekBar54.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar54, int i, boolean b) {

                setSeekText(i, crate54);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar54) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar54) {

            }
        });

        cseekBar55.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar55, int i, boolean b) {

                setSeekText(i, crate55);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar55) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar55) {

            }
        });

        cseekBar56.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar56, int i, boolean b) {

                setSeekText(i, crate56);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar56) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar cseekBar56) {

            }
        });


        cpopup9 = getView().findViewById(R.id.cinstructionsBTN9);
        cpopup9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        Button submitBtn = (Button) getView().findViewById(R.id.csubmit_ques);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(51, cseekBar51.getProgress());
                mViewModel.setDogAnswer(52, cseekBar52.getProgress());
                mViewModel.setDogAnswer(53, cseekBar53.getProgress());
                mViewModel.setDogAnswer(54, cseekBar54.getProgress());
                mViewModel.setDogAnswer(55, cseekBar55.getProgress());
                mViewModel.setDogAnswer(56, cseekBar56.getProgress());

                for (int i = 1; i < 57; i++) {
                    System.out.println("SEEKBAR #" + i + ": " + mViewModel.getDogAnswer(i));
                }

                MCDM mainAlgo = new MCDM();
                mainAlgo.beginMCDM(getContext());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentRecommendedPets recPets = new FragmentRecommendedPets();
                transaction.replace(R.id.nav_host_fragment,recPets);
                transaction.addToBackStack("recommendedPets");
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
        final Dialog helpDialog10 = new Dialog(this);
        helpDialog10.setContentView(R.layout.help_popup);
        helpDialog10.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        helpDialog10.show();
        ImageButton closeBTN = (ImageButton) helpDialog10.findViewById(R.id.closeBTN);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                helpDialog10.dismiss();

            }
        });*/
    }


}