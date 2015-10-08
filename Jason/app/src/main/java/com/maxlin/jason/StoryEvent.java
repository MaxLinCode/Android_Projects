package com.maxlin.jason;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MaxLin on 9/30/15.
 */
public class StoryEvent {
    private String story;
    private String choiceEvent;
    private int[] leads;
    private int choice;
    private boolean isEnd;

    public StoryEvent(String storyText, String event, int[] storyLeads) {
        story = storyText;
        choiceEvent = event;
        leads = storyLeads;
    }

    public void choose(int c) {
        choice = c;
    }

    public String getStory() {
        return story;
    }

    public String getChoiceEvent() {
        return choiceEvent;
    }

    public int[] getLeads() {
        return leads;
    }

    public int getChoice() {
        return choice;
    }

    public void setEventEnd() {
        isEnd = true;
    }

}
