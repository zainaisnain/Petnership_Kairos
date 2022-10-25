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

public class FragmentDogQuestionnaire2 extends Fragment {

    public static FragmentDogQuestionnaire2 newInstance() {
        return new FragmentDogQuestionnaire2();
    }

    ImageButton popup2;
    SeekBar seekBar8, seekBar9, seekBar10, seekBar11, seekBar12, seekBar13,seekBar14;
    TextView rate8, rate9, rate10, rate11, rate12, rate13, rate14;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire2, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 2");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getDogAnswer(1));

        seekBar8 = (SeekBar) getView().findViewById(R.id.seekBar8);
        seekBar9 = (SeekBar) getView().findViewById(R.id.seekBar9);
        seekBar10 = (SeekBar) getView().findViewById(R.id.seekBar10);
        seekBar11 = (SeekBar) getView().findViewById(R.id.seekBar11);
        seekBar12 = (SeekBar) getView().findViewById(R.id.seekBar12);
        seekBar13 = (SeekBar) getView().findViewById(R.id.seekBar13);
        seekBar14 = (SeekBar) getView().findViewById(R.id.seekBar14);
        rate8 = (TextView) getView().findViewById(R.id.rating8);
        rate9 = (TextView) getView().findViewById(R.id.rating9);
        rate10 = (TextView) getView().findViewById(R.id.rating10);
        rate11 = (TextView) getView().findViewById(R.id.rating11);
        rate12 = (TextView) getView().findViewById(R.id.rating12);
        rate13 = (TextView) getView().findViewById(R.id.rating13);
        rate14 = (TextView) getView().findViewById(R.id.rating14);

        // bring back previous progress if any
      //  if (mViewModel.getDogAnswer(8) != null){
        //    seekBar14.setProgress(mViewModel.getDogAnswer(8));
        //}

        seekBar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar8, int i, boolean b) {
                setSeekText(i, rate8);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar8) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar8) {

            }
        });

        seekBar9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar9, int i, boolean b) {
                setSeekText(i, rate9);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar9) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar9) {

            }
        });

        seekBar10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar10, int i, boolean b) {
                setSeekText(i, rate10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar10) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar10) {

            }
        });

        seekBar11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar11, int i, boolean b) {
                setSeekText(i, rate11);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar11) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar11) {

            }
        });

        seekBar12.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar12, int i, boolean b) {
                setSeekText(i, rate12);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar12) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar12) {

            }
        });

        seekBar13.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar13, int i, boolean b) {
                setSeekText(i, rate13);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar13) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar13) {

            }
        });

        seekBar14.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar14, int i, boolean b) {
                setSeekText(i, rate14);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar14) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar14) {

            }
        });

        popup2 = getView().findViewById(R.id.instructionsBTN2);
        popup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.proceed_ques2);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setDogAnswer(8, seekBar8.getProgress());
                mViewModel.setDogAnswer(9, seekBar9.getProgress());
                mViewModel.setDogAnswer(10, seekBar10.getProgress());
                mViewModel.setDogAnswer(11, seekBar11.getProgress());
                mViewModel.setDogAnswer(12, seekBar12.getProgress());
                mViewModel.setDogAnswer(13, seekBar13.getProgress());
                mViewModel.setDogAnswer(14, seekBar14.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentDogQuestionnaire3 dog3Fragment = new FragmentDogQuestionnaire3();
                transaction.replace(R.id.nav_host_fragment,dog3Fragment);
                transaction.addToBackStack("dogQuestionnaire3");
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
        HelpPopup helpDialog2 = new HelpPopup();
        helpDialog2.show(getParentFragmentManager(), "Help Popup");
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