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

public class FragmentDogQuestionnaire4 extends Fragment {

    public static FragmentDogQuestionnaire4 newInstance() {
        return new FragmentDogQuestionnaire4();
    }

    ImageButton popup4;
    SeekBar seekBar22, seekBar23, seekBar24, seekBar25, seekBar26;
    TextView rate22, rate23, rate24, rate25, rate26;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire4, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 4");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getDogAnswer(1));

        seekBar22 = (SeekBar) getView().findViewById(R.id.seekBar22);
        seekBar23 = (SeekBar) getView().findViewById(R.id.seekBar23);
        seekBar24 = (SeekBar) getView().findViewById(R.id.seekBar24);
        seekBar25 = (SeekBar) getView().findViewById(R.id.seekBar25);
        seekBar26 = (SeekBar) getView().findViewById(R.id.seekBar26);
        rate22 = (TextView) getView().findViewById(R.id.rating22);
        rate23 = (TextView) getView().findViewById(R.id.rating23);
        rate24 = (TextView) getView().findViewById(R.id.rating24);
        rate25 = (TextView) getView().findViewById(R.id.rating25);
        rate26 = (TextView) getView().findViewById(R.id.rating26);

        // bring back previous progress if any
   //     if (mViewModel.getDogAnswer(8) != null){
     //       seekBar26.setProgress(mViewModel.getDogAnswer(8));
       // }

        seekBar22.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar22, int i, boolean b) {
                setSeekText(i, rate22);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar22) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar22) {

            }
        });

        seekBar23.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar23, int i, boolean b) {
                setSeekText(i, rate23);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar23) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar23) {

            }
        });

        seekBar24.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar24, int i, boolean b) {
                setSeekText(i, rate24);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar24) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar24) {

            }
        });

        seekBar25.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar25, int i, boolean b) {
                setSeekText(i, rate25);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar25) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar25) {

            }
        });

        seekBar26.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar26, int i, boolean b) {
                setSeekText(i, rate26);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar26) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar26) {

            }
        });


        popup4 = getView().findViewById(R.id.instructionsBTN4);
        popup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.proceed_ques4);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(22, seekBar22.getProgress());
                mViewModel.setDogAnswer(23, seekBar23.getProgress());
                mViewModel.setDogAnswer(24, seekBar24.getProgress());
                mViewModel.setDogAnswer(25, seekBar25.getProgress());
                mViewModel.setDogAnswer(26, seekBar26.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentDogQuestionnaire5 dog5Fragment = new FragmentDogQuestionnaire5();
                transaction.replace(R.id.nav_host_fragment,dog5Fragment);
                transaction.addToBackStack("dogQuestionnaire5");
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
        HelpPopup helpDialog4 = new HelpPopup();
        helpDialog4.show(getParentFragmentManager(), "Help Popup");
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