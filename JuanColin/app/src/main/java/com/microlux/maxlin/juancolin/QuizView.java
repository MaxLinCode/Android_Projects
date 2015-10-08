package com.microlux.maxlin.juancolin;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MaxLin on 9/21/15.
 */
public class QuizView implements Parcelable{
    private String question;
    private boolean pchoice;
    private boolean answer;

    public boolean isAnswered = false;

    public QuizView(String q, boolean a) {
        question = q;
        answer = a;
    }

    protected QuizView(Parcel in) {
        question = in.readString();
        pchoice = in.readInt()==1;
        answer = in.readInt()==1;
    }

    public static final Creator<QuizView> CREATOR = new Creator<QuizView>() {
        @Override
        public QuizView createFromParcel(Parcel in) {
            return new QuizView(in);
        }

        @Override
        public QuizView[] newArray(int size) {
            return new QuizView[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeInt(pchoice?1:0);
        dest.writeInt(answer?1:0);
    }
}
