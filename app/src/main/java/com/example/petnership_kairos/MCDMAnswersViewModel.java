package com.example.petnership_kairos;

import java.util.Hashtable;
import androidx.lifecycle.ViewModel;

public class MCDMAnswersViewModel extends ViewModel {

    // final values
    private final int INTENSITY_COUNT = 3;
    private final int DOG_MAIN_MATRICES_SIZE = 7;
    private final int[] DOG_SUBCRITERIA_MATRICES_COUNT = {3, 2, 2, 3};
    private final int DOG_INTENSITY_MATRICES_COUNT = 11;
    private final int CAT_MAIN_MATRICES_SIZE = 8;
    private final int[] CAT_SUBCRITERIA_MATRICES_COUNT = {1, 2};
    private final int CAT_INTENSITY_MATRICES_COUNT = 9;
    private final int DOG_MAIN_ANSWERS = 21;
    private final int DOG_SUBCRITERIA_ANSWERS = 5;
    private final int DOG_INTENSITY_ANSWERS = 33;
    private final int CAT_MAIN_ANSWERS = 28;
    private final int CAT_SUBCRITERIA_ANSWERS = 1;
    private final int CAT_INTENSITY_ANSWERS = 27;

    private boolean finishedAnswering = false;
    private double consistencyRatio = -1;

    private int currentResultView = 0;

    public static String[] dogCodes = {
            "",
            "DA0101", "DA0102", "DA0103", "DA0104", "DA0105", "DA0106",
            "DA0201", "DA0202", "DA0203", "DA0204", "DA0205",
            "DA0301", "DA0302", "DA0303", "DA0304",
            "DA0401", "DA0402", "DA0403",
            "DA0501", "DA0502",
            "DA0601",
            "DB0101", "DB0201", "DB0301", "DB0302", "DB0303",
            "DC0101", "DC0102", "DC0103",
            "DC0201", "DC0202", "DC0203",
            "DC0301", "DC0302", "DC0303",
            "DC0401", "DC0402", "DC0403",
            "DC0501", "DC0502", "DC0503",
            "DC0601", "DC0602", "DC0603",
            "DC0701", "DC0702", "DC0703",
            "DC0801", "DC0802", "DC0803",
            "DC0901", "DC0902", "DC0903",
            "DC1001", "DC1002", "DC1003",
            "DC1101", "DC1102", "DC1103"
    };
    public static String[] catCodes = {
            "",
            "CA0101", "CA0102", "CA0103", "CA0104", "CA0105", "CA0106", "CA0107",
            "CA0201", "CA0202", "CA0203", "CA0204", "CA0205", "CA0206",
            "CA0301", "CA0302", "CA0303", "CA0304", "CA0305",
            "CA0401", "CA0402", "CA0403", "CA0404",
            "CA0501", "CA0502", "CA0503",
            "CA0601", "CA0602",
            "CA0701",
            "CB0101",
            "CC0101", "CC0102", "CC0103",
            "CC0201", "CC0202", "CC0203",
            "CC0301", "CC0302", "CC0303",
            "CC0401", "CC0402", "CC0403",
            "CC0501", "CC0502", "CC0503",
            "CC0601", "CC0602", "CC0603",
            "CC0701", "CC0702", "CC0703",
            "CC0801", "CC0802", "CC0803",
            "CC0901", "CC0902", "CC0903",
    };



    private String animalType;

    private Hashtable<String, Integer> answersDict = new Hashtable<String, Integer>();
    //private Hashtable<String, Integer> catAnswersDict = new Hashtable<String, Integer>();

    public int[] getMainAnswers() {
        return mainAnswers;
    }

    public void setMainAnswers(int[] mainAnswers) {
        this.mainAnswers = mainAnswers;
    }

    public int[] getSubcriteriaAnswers() {
        return subcriteriaAnswers;
    }

    public void setSubcriteriaAnswers(int[] subcriteriaAnswers) {
        this.subcriteriaAnswers = subcriteriaAnswers;
    }

    public int[] getIntensityAnswers() {
        return intensityAnswers;
    }

    public void setIntensityAnswers(int[] intensityAnswers) {
        this.intensityAnswers = intensityAnswers;
    }

    private int[] mainAnswers;
    private int[] subcriteriaAnswers;
    private int[] intensityAnswers;

    private MCDMAlternative[] topThree;

    public String getAnimalType() {
        return animalType;
    }
    public void setAnimalType(String animalType) {
        this.animalType = animalType;
        if (animalType.equals("Dog")) {
            mainAnswers = new int[DOG_MAIN_ANSWERS];
            subcriteriaAnswers = new int[DOG_SUBCRITERIA_ANSWERS];
            intensityAnswers = new int[DOG_INTENSITY_ANSWERS];
        }
        else {
            mainAnswers = new int[CAT_MAIN_ANSWERS];
            subcriteriaAnswers = new int[CAT_SUBCRITERIA_ANSWERS];
            intensityAnswers = new int[CAT_INTENSITY_ANSWERS];
        }
    }

    public MCDMAlternative[] getTopThree() {
        return topThree;
    }
    public void setTopThree(MCDMAlternative[] topThree) {
        this.topThree = topThree;
    }

    public Integer getAnswer(int label) {
        return this.answersDict.get((animalType.equals("Dog") ? dogCodes:catCodes)[label]);
    }
    public void setAnswer(int label, int answer) {
        this.answersDict.put((animalType.equals("Dog") ? dogCodes:catCodes)[label], answer);
        if (label < (animalType.equals("Dog") ? DOG_MAIN_ANSWERS : CAT_MAIN_ANSWERS)+1) {
            mainAnswers[label-1] = normalize(answer);
        }
        else if (label < (animalType.equals("Dog") ? DOG_MAIN_ANSWERS+DOG_SUBCRITERIA_ANSWERS : CAT_MAIN_ANSWERS+CAT_SUBCRITERIA_ANSWERS)+1) {
            subcriteriaAnswers[label-((animalType.equals("Dog") ? DOG_MAIN_ANSWERS : CAT_MAIN_ANSWERS)+1)] = normalize(answer);
        }
        else {
            intensityAnswers[label-((animalType.equals("Dog") ? DOG_MAIN_ANSWERS+DOG_SUBCRITERIA_ANSWERS : CAT_MAIN_ANSWERS+CAT_SUBCRITERIA_ANSWERS)+1)] = normalize(answer);
        }

    }

    public int getCurrentResultView() {
        return currentResultView;
    }

    public void setCurrentResultView(int currentResultView) {
        this.currentResultView = currentResultView;
    }
    public MCDMAlternative getCurrentAlternative() {
        return topThree[getCurrentResultView()];
    }

    public void setConsistencyRatio(double consistencyRatio) {
        this.consistencyRatio = consistencyRatio;
    }
    public double getConsistencyRatio() {
        return consistencyRatio;
    }

    public int getMainAnswer(int val) {
        return this.mainAnswers[val];
    }
    public int getSubcriteriaAnswer(int val) {
        return this.subcriteriaAnswers[val];
    }
    public int getIntensityAnswer(int val) {
        return this.intensityAnswers[val];
    }

    public int normalize(int val) {
        int temp = 0;
        switch(val) {
            case 0: temp = 9; break;
            case 1: temp = 8; break;
            case 2: temp = 7; break;
            case 3: temp = 6; break;
            case 4: temp = 5; break;
            case 5: temp = 4; break;
            case 6: temp = 3; break;
            case 7: temp = 2; break;
            case 8: temp = 1; break;
            case 9: temp = -2; break;
            case 10: temp = -3; break;
            case 11: temp = -4; break;
            case 12: temp = -5; break;
            case 13: temp = -6; break;
            case 14: temp = -7; break;
            case 15: temp = -8; break;
            case 16: temp = -9; break;
        }
        return temp;
    }
    public boolean getFinishedAnswering() {
        return finishedAnswering;
    }
    public void setFinishedAnswering(boolean finishedAnswering) {
        this.finishedAnswering = finishedAnswering;
    }

}
