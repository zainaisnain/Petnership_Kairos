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

public class FragmentDogQuestionnaire14 extends Fragment {

    public static FragmentDogQuestionnaire14 newInstance() {
        return new FragmentDogQuestionnaire14();
    }


    ImageButton popup14, backBtn;
    SeekBar seekBar54, seekBar55, seekBar56;
    TextView rate54, rate55, rate56;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_questionnaire14, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Questionnaire 14");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);

        seekBar54 = getView().findViewById(R.id.seekBar54);
        seekBar55 = getView().findViewById(R.id.seekBar55);
        seekBar56 = getView().findViewById(R.id.seekBar56);
        rate54 = getView().findViewById(R.id.rating54);
        rate55 = getView().findViewById(R.id.rating55);
        rate56 = getView().findViewById(R.id.rating56);


        // bring back previous progress if any
        if (mViewModel.getAnswer(54) != null) seekBar54.setProgress(mViewModel.getAnswer(54));
        if (mViewModel.getAnswer(55) != null) seekBar55.setProgress(mViewModel.getAnswer(55));
        if (mViewModel.getAnswer(56) != null) seekBar56.setProgress(mViewModel.getAnswer(56));

        seekBar54.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar54, int i, boolean b) {

                setSeekText(i, rate54);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar54) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar54) {

            }
        });

        seekBar55.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar55, int i, boolean b) {

                setSeekText(i, rate55);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar55) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar55) {

            }
        });

        seekBar56.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar56, int i, boolean b) {

                setSeekText(i, rate56);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar56) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar56) {

            }
        });


        popup14 = getView().findViewById(R.id.instructionsBTN14);
        popup14.setOnClickListener(view1 -> showDialog());

        Button proceedBtn = getView().findViewById(R.id.proceed_ques14);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(54, seekBar54.getProgress());
            mViewModel.setAnswer(55, seekBar55.getProgress());
            mViewModel.setAnswer(56, seekBar56.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentDogQuestionnaire15 dog15Fragment = new FragmentDogQuestionnaire15();
            transaction.replace(R.id.nav_host_fragment,dog15Fragment);
            transaction.addToBackStack("dogQuestionnaire15");
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