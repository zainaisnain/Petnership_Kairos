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

public class FragmentCatQuestionnaire14 extends Fragment {

    public static FragmentCatQuestionnaire14 newInstance() {
        return new FragmentCatQuestionnaire14();
    }

    MCDM mainAlgo;
    ImageButton cpopup14;
    SeekBar cseekBar54, cseekBar55, cseekBar56;
    TextView crate54, crate55, crate56;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire14, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 14");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));

        // begin data fetch
        mainAlgo = new MCDM();
        mainAlgo.fetchAlternatives(getContext(),2);

        cseekBar54 = getView().findViewById(R.id.cseekBar54);
        cseekBar55= getView().findViewById(R.id.cseekBar55);
        cseekBar56 = getView().findViewById(R.id.cseekBar56);
        crate54 = getView().findViewById(R.id.crating54);
        crate55 = getView().findViewById(R.id.crating55);
        crate56 = getView().findViewById(R.id.crating56);



        // bring back previous progress if any
        if (mViewModel.getAnswer(54) != null) cseekBar54.setProgress(mViewModel.getAnswer(54));
        if (mViewModel.getAnswer(55) != null) cseekBar55.setProgress(mViewModel.getAnswer(55));
        if (mViewModel.getAnswer(56) != null) cseekBar56.setProgress(mViewModel.getAnswer(56));

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


        cpopup14 = getView().findViewById(R.id.cinstructionsBTN14);
        cpopup14.setOnClickListener(view1 -> showDialog());

        Button submitBtn = getView().findViewById(R.id.csubmit_ques);
        submitBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(54, cseekBar54.getProgress());
            mViewModel.setAnswer(55, cseekBar55.getProgress());
            mViewModel.setAnswer(56, cseekBar56.getProgress());

            for (int i = 1; i < 57; i++) {
                System.out.println("SEEKBAR #" + i + ": " + mViewModel.getAnswer(i));
            }

            mainAlgo.beginMCDM();

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentRecommendedPets recPets = new FragmentRecommendedPets();
            transaction.replace(R.id.nav_host_fragment,recPets);
            transaction.addToBackStack("recommendedPets");
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
        HelpPopup helpDialog = new HelpPopup("Cat", "Main");
        helpDialog.show(getParentFragmentManager(), "Help Popup");
    }


}