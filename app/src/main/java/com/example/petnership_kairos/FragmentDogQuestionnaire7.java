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

public class FragmentDogQuestionnaire7 extends Fragment {

    public static FragmentDogQuestionnaire7 newInstance() {
        return new FragmentDogQuestionnaire7();
    }
    ImageButton popup7;
    SeekBar seekBar39, seekBar40, seekBar41, seekBar42, seekBar43, seekBar44;
    TextView rate39, rate40, rate41, rate42, rate43, rate44;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire7, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 7");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getDogAnswer(1));

        seekBar39 = (SeekBar) getView().findViewById(R.id.seekBar39);
        seekBar40 = (SeekBar) getView().findViewById(R.id.seekBar40);
        seekBar41 = (SeekBar) getView().findViewById(R.id.seekBar41);
        seekBar42 = (SeekBar) getView().findViewById(R.id.seekBar42);
        seekBar43 = (SeekBar) getView().findViewById(R.id.seekBar43);
        seekBar44 = (SeekBar) getView().findViewById(R.id.seekBar44);
        rate39 = (TextView) getView().findViewById(R.id.rating39);
        rate40 = (TextView) getView().findViewById(R.id.rating40);
        rate41 = (TextView) getView().findViewById(R.id.rating41);
        rate42 = (TextView) getView().findViewById(R.id.rating42);
        rate43 = (TextView) getView().findViewById(R.id.rating43);
        rate44 = (TextView) getView().findViewById(R.id.rating44);



        // bring back previous progress if any
      //  if (mViewModel.getDogAnswer(8) != null){
        //    seekBar44.setProgress(mViewModel.getDogAnswer(8));
        //}

        seekBar39.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar39, int i, boolean b) {
                setSeekText(i, rate39);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar39) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar39) {

            }
        });

        seekBar40.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar40, int i, boolean b) {
                setSeekText(i, rate40);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar40) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar40) {

            }
        });

        seekBar41.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar41, int i, boolean b) {
                setSeekText(i, rate41);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar41) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar41) {

            }
        });

        seekBar42.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar42, int i, boolean b) {
                setSeekText(i, rate42);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar42) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar42) {

            }
        });

        seekBar43.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar43, int i, boolean b) {
                setSeekText(i, rate43);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar43) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar43) {

            }
        });
        seekBar44.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar44, int i, boolean b) {
                setSeekText(i, rate44);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar44) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar44) {

            }
        });


        popup7 = getView().findViewById(R.id.instructionsBTN7);
        popup7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.proceed_ques7);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(39, seekBar39.getProgress());
                mViewModel.setDogAnswer(40, seekBar40.getProgress());
                mViewModel.setDogAnswer(41, seekBar41.getProgress());
                mViewModel.setDogAnswer(42, seekBar42.getProgress());
                mViewModel.setDogAnswer(43, seekBar43.getProgress());
                mViewModel.setDogAnswer(44, seekBar44.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentDogQuestionnaire8 dog8Fragment = new FragmentDogQuestionnaire8();
                transaction.replace(R.id.nav_host_fragment,dog8Fragment);
                transaction.addToBackStack("dogQuestionnaire8");
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
        HelpPopup helpDialog7 = new HelpPopup();
        helpDialog7.show(getParentFragmentManager(), "Help Popup");
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