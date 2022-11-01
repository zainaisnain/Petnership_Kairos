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

public class FragmentDogQuestionnaire10 extends Fragment {

    public static FragmentDogQuestionnaire10 newInstance() {
        return new FragmentDogQuestionnaire10();
    }


    ImageButton popup10, backBtn;
    SeekBar seekBar42, seekBar43, seekBar44;
    TextView rate42, rate43, rate44;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire10, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 10");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));


        seekBar42 = getView().findViewById(R.id.seekBar42);
        seekBar43 = getView().findViewById(R.id.seekBar43);
        seekBar44 = getView().findViewById(R.id.seekBar44);
        rate42 = getView().findViewById(R.id.rating42);
        rate43 = getView().findViewById(R.id.rating43);
        rate44 = getView().findViewById(R.id.rating44);

        // bring back previous progress if any
      //  if (mViewModel.getDogAnswer(8) != null){
        //    seekBar54.setProgress(mViewModel.getDogAnswer(8));
        //}


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


        popup10 = getView().findViewById(R.id.instructionsBTN10);
        popup10.setOnClickListener(view1 -> showDialog());

        Button proceedBtn = getView().findViewById(R.id.proceed_ques10);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(42, seekBar42.getProgress());
            mViewModel.setAnswer(43, seekBar43.getProgress());
            mViewModel.setAnswer(44, seekBar44.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire11 dog11Fragment = new FragmentDogQuestionnaire11();
            transaction.replace(R.id.nav_host_fragment,dog11Fragment);
            transaction.addToBackStack("dogQuestionnaire11");
            transaction.commit();
        });
        ImageButton backBtn = getView().findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view12 -> {

            getParentFragmentManager().popBackStack();
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
        HelpPopup helpDialog = new HelpPopup("Dog", "Main");
        helpDialog.show(getParentFragmentManager(), "Help Popup");
    }


}