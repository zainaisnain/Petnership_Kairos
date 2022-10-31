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

public class FragmentCatQuestionnaire5 extends Fragment {

    public static FragmentCatQuestionnaire5 newInstance() {
        return new FragmentCatQuestionnaire5();
    }

    ImageButton cpopup5;
    SeekBar cseekBar29, cseekBar30, cseekBar31, cseekBar32;
    TextView crate29, crate30, crate31, crate32;
    MCDMAnswersViewModel mViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire5, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 5");

        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);

        cseekBar29 = getView().findViewById(R.id.cseekBar29);
        cseekBar30 = getView().findViewById(R.id.cseekBar30);
        cseekBar31 = getView().findViewById(R.id.cseekBar31);
        cseekBar32 = getView().findViewById(R.id.cseekBar32);
        crate29 = getView().findViewById(R.id.crating29);
        crate30 = getView().findViewById(R.id.crating30);
        crate31 = getView().findViewById(R.id.crating31);
        crate32 = getView().findViewById(R.id.crating32);




        cseekBar29.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar cseekBar29, int i, boolean b) {
                setSeekText(i, crate29);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar29) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar29) {

            }
        });

        cseekBar30.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar30, int i, boolean b) {
                setSeekText(i, crate30);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar30) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar30) {

            }
        });

        cseekBar31.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar31, int i, boolean b) {
                setSeekText(i, crate31);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar31) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar31) {

            }
        });

        cseekBar32.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar4, int i, boolean b) {
                setSeekText(i, crate32);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar32) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar32) {

            }
        });

        cpopup5 = getView().findViewById(R.id.cinstructionsBTN5);
        cpopup5.setOnClickListener(view1 -> showDialog());
        Button proceedBtn = getView().findViewById(R.id.cproceed_ques5);
        proceedBtn.setOnClickListener(v -> {
            // save answers
            mViewModel.setAnswer(29, cseekBar29.getProgress());
            mViewModel.setAnswer(30, cseekBar30.getProgress());
            mViewModel.setAnswer(31, cseekBar31.getProgress());
            mViewModel.setAnswer(32, cseekBar32.getProgress());

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentCatQuestionnaire6 cat6Fragment = new FragmentCatQuestionnaire6();
            transaction.replace(R.id.nav_host_fragment,cat6Fragment);
            transaction.addToBackStack("catQuestionnaire6");
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
        /*
                final Dialog helpDialog = new Dialog();
        helpDialog.setContentView(R.layout.help_popup);
        helpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        helpDialog.show();
        ImageButton closeBTN = (ImageButton) helpDialog.findViewById(R.id.closeBTN);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                helpDialog.dismiss();

            }
        });
         */
    }


}