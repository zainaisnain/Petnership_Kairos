package com.example.petnership_kairos;

import java.util.Hashtable;
import androidx.lifecycle.ViewModel;

public class MCDMAnswersViewModel extends ViewModel {
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


    private Hashtable<String, Integer> dogAnswersDict = new Hashtable<String, Integer>();
    private Hashtable<String, Integer> catAnswersDict = new Hashtable<String, Integer>();


    public Integer getDogAnswer(int label) {
        return this.dogAnswersDict.get(dogCodes[label]);
    }

    public void setDogAnswer(int label, int answer) {
        this.dogAnswersDict.put(dogCodes[label], answer);
    }
    public Integer getCatAnswer(int label) {
        return this.catAnswersDict.get(catCodes[label]);
    }

    public void setCatAnswer(int label, int answer) {
        this.catAnswersDict.put(catCodes[label], answer);
    }
}
