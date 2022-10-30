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

public class FragmentCatQuestionnaire8 extends Fragment {

    public static FragmentCatQuestionnaire8 newInstance() {
        return new FragmentCatQuestionnaire8();
    }

    ImageButton cpopup8;
    SeekBar cseekBar45, cseekBar46, cseekBar47, cseekBar48, cseekBar49, cseekBar50;
    TextView crate45, crate46, crate47, crate48, crate49, crate50;
    MCDMAnswersViewModel mViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_questionnaire8, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Questionnaire 8");

        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);

        cseekBar45 = (SeekBar) getView().findViewById(R.id.cseekBar45);
        cseekBar46 = (SeekBar) getView().findViewById(R.id.cseekBar46);
        cseekBar47 = (SeekBar) getView().findViewById(R.id.cseekBar47);
        cseekBar48 = (SeekBar) getView().findViewById(R.id.cseekBar48);
        cseekBar49= (SeekBar) getView().findViewById(R.id.cseekBar49);
        cseekBar50 = (SeekBar) getView().findViewById(R.id.cseekBar50);
        crate45 = (TextView) getView().findViewById(R.id.crating45);
        crate46 = (TextView) getView().findViewById(R.id.crating46);
        crate47 = (TextView) getView().findViewById(R.id.crating47);
        crate48 = (TextView) getView().findViewById(R.id.crating48);
        crate49 = (TextView) getView().findViewById(R.id.crating49);
        crate50 = (TextView) getView().findViewById(R.id.crating50);




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

        cseekBar48.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar48, int i, boolean b) {
                setSeekText(i, crate48);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar48) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar48) {

            }
        });

        cseekBar49.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar49, int i, boolean b) {
                setSeekText(i, crate49);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar49) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar49) {

            }
        });

        cseekBar50.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar cseekBar50, int i, boolean b) {
                setSeekText(i, crate50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar cseekBar50) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar cseekBar50) {

            }
        });

        cpopup8 = getView().findViewById(R.id.cinstructionsBTN8);
        cpopup8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        Button proceedBtn = (Button) getView().findViewById(R.id.cproceed_ques8);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save answers
                mViewModel.setAnswer(45, cseekBar45.getProgress());
                mViewModel.setAnswer(46, cseekBar46.getProgress());
                mViewModel.setAnswer(47, cseekBar47.getProgress());
                mViewModel.setAnswer(48, cseekBar48.getProgress());
                mViewModel.setAnswer(49, cseekBar49.getProgress());
                mViewModel.setAnswer(50, cseekBar50.getProgress());

                // change screen
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentCatQuestionnaire9 cat9Fragment = new FragmentCatQuestionnaire9();
                transaction.replace(R.id.nav_host_fragment,cat9Fragment);
                transaction.addToBackStack("catQuestionnaire9");
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
        HelpPopup helpDialogc8 = new HelpPopup();
        helpDialogc8.show(getParentFragmentManager(), "Help Popup");
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