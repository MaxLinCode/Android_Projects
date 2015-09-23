package com.microlux.maxlin.juancolin;

/**
 * Created by MaxLin on 9/21/15.
 */
public class QuizView {
    private String question;
    private boolean pchoice;
    private boolean answer;

    public boolean isAnswered = false;

    public QuizView(String q, boolean a) {
        question = q;
        answer = a;
    }

    public void setChoice(boolean b) {
        if (!isAnswered) {
            pchoice = b;
            isAnswered = true;
        }

    }

    public String getQuestion() {
        return question;
    }

    public boolean getChoice() {
        return pchoice;
    }

    public boolean getAnswer() {
        return answer;
    }


}
