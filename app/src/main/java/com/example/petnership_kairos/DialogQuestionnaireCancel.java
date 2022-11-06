package com.example.petnership_kairos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

public class DialogQuestionnaireCancel extends DialogFragment{

    Button returnBtn, exitBtn;
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_questionnaire_cancel, container, false);

        exitBtn = view.findViewById(R.id.buttonExit);
        exitBtn.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            if (getTag().equalsIgnoreCase("Cancel Home")) {

                Intent intent = new Intent(getActivity(), AdopterDashboard.class);
                intent.putExtra("com.example.petnership_kairos.fragment", "Home");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
            }
            else if (getTag().equalsIgnoreCase("Cancel History")) {

                Intent intent = new Intent(getActivity(), AdopterDashboard.class);
                intent.putExtra("com.example.petnership_kairos.fragment", "History");
                startActivity(intent);
                /*transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                ApplicationHistoryFragment applicationHistory = new ApplicationHistoryFragment();
                transaction.replace(R.id.nav_host_fragment,applicationHistory);
                transaction.commit();*/
            }
            else if (getTag().equalsIgnoreCase("Cancel Browse")) {
                Intent intent = new Intent(getActivity(), AdopterDashboard.class);
                intent.putExtra("com.example.petnership_kairos.fragment", "Browse");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
            }
            else if (getTag().equalsIgnoreCase("Cancel Out")) {
                Intent intent = new Intent(getActivity(), AdopterDashboard.class);
                intent.putExtra("com.example.petnership_kairos.fragment", "Logout");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
            }
            else if (getTag().equalsIgnoreCase("Cancel Update Email")) {
                Intent intent = new Intent(getActivity(), UpdateEmail.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
            }
            else if (getTag().equalsIgnoreCase("Cancel Change Password")) {
                Intent intent = new Intent(getActivity(), AdopterDashboard.class);
                intent.putExtra("com.example.petnership_kairos.fragment", "Password");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);


            }
            else {

                Intent intent = new Intent(getActivity(), AdopterDashboard.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
            }
            dismiss();
//
        });

        returnBtn = view.findViewById(R.id.buttonReturn);
        returnBtn.setOnClickListener(v -> dismiss());
        return view;
    }
}


