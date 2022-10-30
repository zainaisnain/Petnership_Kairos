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

public class FragmentDogQuestionnaire3 extends Fragment {

    public static FragmentDogQuestionnaire3 newInstance() {
        return new FragmentDogQuestionnaire3();
    }

    ImageButton popup3;
    SeekBar seekBar15, seekBar16, seekBar17, seekBar18, seekBar19, seekBar20,seekBar21;
    TextView rate15, rate16, rate17, rate18, rate19, rate20, rate21;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire3, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 3");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));

        seekBar15 = (SeekBar) getView().findViewById(R.id.seekBar15);
        seekBar16 = (SeekBar) getView().findViewById(R.id.seekBar16);
        seekBar17 = (SeekBar) getView().findViewById(R.id.seekBar17);
        seekBar18 = (SeekBar) getView().findViewById(R.id.seekBar18);
        seekBar19 = (SeekBar) getView().findViewById(R.id.seekBar19);
        seekBar20 = (SeekBar) getView().findViewById(R.id.seekBar20);
        seekBar21 = (SeekBar) getView().findViewById(R.id.seekBar21);
        rate15 = (TextView) getView().findViewById(R.id.rating15);
        rate16 = (TextView) getView().findViewById(R.id.rating16);
        rate17 = (TextView) getView().findViewById(R.id.rating17);
        rate18 = (TextView) getView().findViewById(R.id.rating18);
        rate19 = (TextView) getView().findViewById(R.id.rating19);
        rate20 = (TextView) getView().findViewById(R.id.rating20);
        rate21 = (TextView) getView().findViewById(R.id.rating21);

        // bring back previous progress if any
      //  if (mViewModel.getDogAnswer(8) != null){
        //    seekBar21.setProgress(mViewModel.getDogAnswer(8));
        //}

        seekBar15.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar15, int i, boolean b) {
                setSeekText(i, rate15);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar15) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar15) {

            }
        });

        seekBar16.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar16, int i, boolean b) {
                setSeekText(i, rate16);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar16) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar16) {

            }
        });

        seekBar17.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar17, int i, boolean b) {
                setSeekText(i, rate17);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar17) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar17) {

            }
        });

        seekBar18.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar18, int i, boolean b) {
                setSeekText(i, rate18);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar18) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar18) {

            }
        });

        seekBar19.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar19, int i, boolean b) {
                setSeekText(i, rate19);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar19) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar19) {

            }
        });

        seekBar20.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar20, int i, boolean b) {
                setSeekText(i, rate20);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar20) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar20) {

            }
        });

        seekBar21.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar21, int i, boolean b) {
                setSeekText(i, rate21);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar21) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar21) {

            }
        });

        popup3 = getView().findViewById(R.id.instructionsBTN3);
        popup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        Button proceedBtn = (Button) getView().findViewById(R.id.proceed_ques3);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setAnswer(15, seekBar15.getProgress());
                mViewModel.setAnswer(16, seekBar16.getProgress());
                mViewModel.setAnswer(17, seekBar17.getProgress());
                mViewModel.setAnswer(18, seekBar18.getProgress());
                mViewModel.setAnswer(19, seekBar19.getProgress());
                mViewModel.setAnswer(20, seekBar20.getProgress());
                mViewModel.setAnswer(21, seekBar21.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentDogQuestionnaire4 dog4Fragment = new FragmentDogQuestionnaire4();
                transaction.replace(R.id.nav_host_fragment,dog4Fragment);
                transaction.addToBackStack("dogQuestionnaire4");
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
        HelpPopup helpDialog3 = new HelpPopup();
        helpDialog3.show(getParentFragmentManager(), "Help Popup");
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