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

public class FragmentCatQuestionnaire10 extends Fragment {

    public static FragmentCatQuestionnaire10 newInstance() {
        return new FragmentCatQuestionnaire10();
    }

    ImageButton cpopup10;
    SeekBar cseekBar42, cseekBar43, cseekBar44;
    TextView crate42, crate43, crate44;
    MCDMAnswersViewModel mViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire10, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 10");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        System.out.println("Test: " + mViewModel.getAnswer(1));


        cseekBar42 = getView().findViewById(R.id.cseekBar42);
        cseekBar43= getView().findViewById(R.id.cseekBar43);
        cseekBar44 = getView().findViewById(R.id.cseekBar44);
        crate42 = getView().findViewById(R.id.crating42);
        crate43 = getView().findViewById(R.id.crating43);
        crate44 = getView().findViewById(R.id.crating44);

        // bring back previous progress if any
        //     if (mViewModel.getDogAnswer(8) != null){
        //       cseekBar56.setProgress(mViewModel.getDogAnswer(8));
        // }

        cseekBar42.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar42, int i, boolean b) {
                setSeekText(i, crate42);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar42) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar42) {

            }
        });

        cseekBar43.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar43, int i, boolean b) {
                setSeekText(i, crate43);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar43) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar43) {

            }
        });

        cseekBar44.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar44, int i, boolean b) {
                setSeekText(i, crate44);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar44) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar44) {

            }
        });


        cpopup10 = getView().findViewById(R.id.cinstructionsBTN10);
        cpopup10.setOnClickListener(view1 -> showDialog());

        Button submitBtn = getView().findViewById(R.id.cproceed_ques10);
        submitBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(42, cseekBar42.getProgress());
            mViewModel.setAnswer(43, cseekBar43.getProgress());
            mViewModel.setAnswer(44, cseekBar44.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentCatQuestionnaire11 cat11Fragment = new FragmentCatQuestionnaire11();
            transaction.replace(R.id.nav_host_fragment,cat11Fragment);
            transaction.addToBackStack("catQuestionnaire11");
            transaction.commit();
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