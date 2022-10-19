package com.example.petnership_kairos;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.List;

public class MCDM {

    private MCDMAnswersViewModel mViewModel;
    private List<MCDMAlternative> alternatives;

    //Attributes below are all populated from calculateOptimalSolution method
    private List<MCDMCriteria> criteria;
    private int numberOfAlternatives = 0;
    private int numberOfCriteria = 0;
    private double[][] scoreMatrix;
    private double[][] normalizedDecisionMatrix;
    private double[] idealBest;
    private double[] idealWorst;
    private double[] distancesFromIdealWorst;
    private double[] distancesFromIdealBest;

    public void beginMCDM(Context context) {
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(MCDMAnswersViewModel.class);
        printSample();

        // TODO: Clear ViewModel values
    }

    private void printSample() {
        System.out.println("TESTING THISSSSSSSSSSSSS");
        System.out.println(mViewModel.getDogAnswer(59));
    }
    public List<MCDMAlternative> calculateOptimalSolutionSortedList() {
/*
        validateData();
        populateScoreMatrix();
        calculateNormalizedDecisionMatrix();
        findIdealBestAndWorst();
        calculateDistancesFromIdealBestAndWorst();
        calculatePerformanceScore();
        sortAlternativesByPerformanceScoreDesc();
        */

        return alternatives; // Sorted result from the ideal solution to the worse one.
    }



}
