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

public class FragmentDogQuestionnaire6 extends Fragment {

    public static FragmentDogQuestionnaire6 newInstance() {
        return new FragmentDogQuestionnaire6();
    }
    ImageButton popup6;
    SeekBar seekBar33, seekBar34, seekBar35, seekBar36, seekBar37, seekBar38;
    TextView rate33, rate34, rate35, rate36, rate37, rate38;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire6, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 6");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getDogAnswer(1));

        seekBar33 = (SeekBar) getView().findViewById(R.id.seekBar33);
        seekBar34 = (SeekBar) getView().findViewById(R.id.seekBar34);
        seekBar35 = (SeekBar) getView().findViewById(R.id.seekBar35);
        seekBar36 = (SeekBar) getView().findViewById(R.id.seekBar36);
        seekBar37 = (SeekBar) getView().findViewById(R.id.seekBar37);
        seekBar38 = (SeekBar) getView().findViewById(R.id.seekBar38);
        rate33 = (TextView) getView().findViewById(R.id.rating33);
        rate34 = (TextView) getView().findViewById(R.id.rating34);
        rate35 = (TextView) getView().findViewById(R.id.rating35);
        rate36 = (TextView) getView().findViewById(R.id.rating36);
        rate37 = (TextView) getView().findViewById(R.id.rating37);
        rate38 = (TextView) getView().findViewById(R.id.rating38);


        // bring back previous progress if any
        if (mViewModel.getDogAnswer(8) != null){
            seekBar38.setProgress(mViewModel.getDogAnswer(8));
        }

        seekBar33.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar33, int i, boolean b) {
                setSeekText(i, rate33);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar33) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar33) {

            }
        });

        seekBar34.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar34, int i, boolean b) {
                setSeekText(i, rate34);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar34) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar34) {

            }
        });

        seekBar35.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar35, int i, boolean b) {
                setSeekText(i, rate35);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar35) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar35) {

            }
        });

        seekBar36.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar36, int i, boolean b) {
                setSeekText(i, rate36);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar36) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar36) {

            }
        });

        seekBar37.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar37, int i, boolean b) {
                setSeekText(i, rate37);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar37) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar37) {

            }
        });
        seekBar38.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar38, int i, boolean b) {
                setSeekText(i, rate38);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar38) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar38) {

            }
        });


        popup6 = getView().findViewById(R.id.instructionsBTN6);
        popup6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.proceed_ques6);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(33, seekBar33.getProgress());
                mViewModel.setDogAnswer(34, seekBar34.getProgress());
                mViewModel.setDogAnswer(35, seekBar35.getProgress());
                mViewModel.setDogAnswer(36, seekBar36.getProgress());
                mViewModel.setDogAnswer(37, seekBar37.getProgress());
                mViewModel.setDogAnswer(38, seekBar38.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentDogQuestionnaire7 dog7Fragment = new FragmentDogQuestionnaire7();
                transaction.replace(R.id.nav_host_fragment,dog7Fragment);
                transaction.addToBackStack("dogQuestionnaire7");
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
        HelpPopup helpDialog6 = new HelpPopup();
        helpDialog6.show(getParentFragmentManager(), "Help Popup");
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