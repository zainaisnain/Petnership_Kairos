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

public class FragmentDogQuestionnaire8 extends Fragment {

    public static FragmentDogQuestionnaire8 newInstance() {
        return new FragmentDogQuestionnaire8();
    }
    ImageButton popup8, backBtn;
    SeekBar seekBar36, seekBar37, seekBar38;
    TextView rate36, rate37, rate38;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire8, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 8");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));

        seekBar36 = getView().findViewById(R.id.seekBar36);
        seekBar37 = getView().findViewById(R.id.seekBar37);
        seekBar38 = getView().findViewById(R.id.seekBar38);
        rate36 = getView().findViewById(R.id.rating36);
        rate37 = getView().findViewById(R.id.rating37);
        rate38 = getView().findViewById(R.id.rating38);



        // bring back previous progress if any
    //    if (mViewModel.getDogAnswer(8) != null){
      //      seekBar49.setProgress(mViewModel.getDogAnswer(8));
        //}

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


        popup8 = getView().findViewById(R.id.instructionsBTN8);
        popup8.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.proceed_ques8);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(36, seekBar36.getProgress());
            mViewModel.setAnswer(37, seekBar37.getProgress());
            mViewModel.setAnswer(38, seekBar38.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire9 dog9Fragment = new FragmentDogQuestionnaire9();
            transaction.replace(R.id.nav_host_fragment,dog9Fragment);
            transaction.addToBackStack("dogQuestionnaire9");
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