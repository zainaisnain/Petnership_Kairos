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

public class FragmentCatQuestionnaire11 extends Fragment {

    public static FragmentCatQuestionnaire11 newInstance() {
        return new FragmentCatQuestionnaire11();
    }

    ImageButton cpopup11;
    SeekBar cseekBar45, cseekBar46, cseekBar47;
    TextView crate45, crate46, crate47;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire11, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 11");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));


        cseekBar45 = getView().findViewById(R.id.cseekBar45);
        cseekBar46 = getView().findViewById(R.id.cseekBar46);
        cseekBar47 = getView().findViewById(R.id.cseekBar47);
        crate45 = getView().findViewById(R.id.crating45);
        crate46 = getView().findViewById(R.id.crating46);
        crate47 = getView().findViewById(R.id.crating47);

        // bring back previous progress if any
        //     if (mViewModel.getDogAnswer(8) != null){
        //       cseekBar56.setProgress(mViewModel.getDogAnswer(8));
        // }

        cseekBar45.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar cseekBar45, int i, boolean b) {
                setSeekText(i, crate45);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar45) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar45) {

            }
        });

        cseekBar46.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar46, int i, boolean b) {
                setSeekText(i, crate46);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar46) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar46) {

            }
        });

        cseekBar47.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar47, int i, boolean b) {
                setSeekText(i, crate47);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar47) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar47) {

            }
        });


        cpopup11 = getView().findViewById(R.id.cinstructionsBTN11);
        cpopup11.setOnClickListener(view1 -> showDialog());

        Button submitBtn = getView().findViewById(R.id.cproceed_ques11);
        submitBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(45, cseekBar45.getProgress());
            mViewModel.setAnswer(46, cseekBar46.getProgress());
            mViewModel.setAnswer(47, cseekBar47.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentCatQuestionnaire12 cat12Fragment = new FragmentCatQuestionnaire12();
            transaction.replace(R.id.nav_host_fragment,cat12Fragment);
            transaction.addToBackStack("catQuestionnaire12");
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