package com.example.petnership_kairos;

import java.util.ArrayList;
import java.util.List;

public class MCDMContainerQuestionnaireAnswers {
  /*  public int q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20;
    public int q21, q22, q23, q24, q25, q26, q27, q28, q29, q30, q31, q32, q33, q34, q35, q36, q37, q38, q39, q40;
    public int q41, q42, q43, q44, q45, q46, q47, q48, q49, q50, q51, q52, q53, q54, q55, q56, q57, q58, q59;
 */
    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    public List<Integer> answers = new ArrayList<>();

    public boolean finished;
    public String dateAnswered;
    public String timeAnswered;


    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getDateAnswered() {
        return dateAnswered;
    }

    public void setDateAnswered(String datAnswered) {
        this.dateAnswered = dateAnswered;
    }

    public String getTimeAnswered() {
        return timeAnswered;
    }

    public void setTimeAnswered(String timeAnswered) {
        this.timeAnswered = timeAnswered;
    }


    public MCDMContainerQuestionnaireAnswers() {

    }
    public MCDMContainerQuestionnaireAnswers(int animalType, int[] answers1, int[] answers2, int[] answers3, boolean finished, String dateAnswered, String timeAnswered) {

        this.finished = finished;
        this.dateAnswered = dateAnswered;
        this.timeAnswered = timeAnswered;

        int count = 0;

        if(animalType == 1) {
            for (int i = 0; i <60; i++) {
                answers.add(0);
            }
        }
        else {

            for (int i = 0; i <57; i++) {
                answers.add(0);
            }
        }

        for (int e : answers1) {
            answers.set(count, e);
            count++;
        }
        for (int e : answers2) {
            answers.set(count, e);
            count++;
        }
        for (int e : answers3) {
            answers.set(count, e);
            count++;
        }


    }
}
