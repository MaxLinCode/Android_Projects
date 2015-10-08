package com.maxlin.jason;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StoryActivity extends Activity {
    private static final String LOG_TAG = StoryActivity.class.getCanonicalName();
    public static final String STORY_EVENT = "story event";

    private StoryEvent[] storyEvents;
    private String[] choices;

    private ViewGroup buttonLayout;

    private TextView storyView;
    private TextView eventView;

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        buttonLayout = (ViewGroup)findViewById(R.id.button_layout);

        storyView = (TextView)findViewById(R.id.textView);
        eventView = (TextView)findViewById(R.id.textView1);

        Resources res = getResources();

        String[] stories = res.getStringArray(R.array.stories_array);
        String[] events = res.getStringArray(R.array.events_array);
        choices = res.getStringArray(R.array.choices_array);


        int[][] leads;

        leads = new int[][]
                {
                        {1, 2, 3}, //0
                        {4, 5, 6},//1
                        {7, 8, 9},//2
                        {10, 11, 12},//3
                        {-1},//4
                        {13, 14, 15},//5
                        {7},//6
                        {-1},//7
                        {15, 17, 18},//8
                        {18, 20, 21},//9
                        {-1},//10
                        {9},//11
                        {8},//12
                        {-1},//13
                        {-1},//14
                        {-1},//15
                        {},//16
                        {-1},//17
                        {-1},//18
                        {},//19
                        {-1},//20
                        {3},//21
                };

        // initializing the story events array
        storyEvents = new StoryEvent[22];
        int e = 0; // events index
        for (int i = 0; i < storyEvents.length; i++) {
            if (leads[i].length != 0 ) {
                if (leads[i].length > 1) {
                    // initialize with the events
                    storyEvents[i] = new StoryEvent(stories[i], events[e], leads[i]);
                    e++;
                } else if (leads[i].length == 1) {
                    // no choice events
                    Log.d(LOG_TAG, "Continue event at: " + i);
                    storyEvents[i] = new StoryEvent(stories[i], "", leads[i]);
                } else if (leads[i][0] == -1) {
                    storyEvents[i] = new StoryEvent(stories[i], "", leads[i]);
                    storyEvents[i].setEventEnd();
                }
            }
        }

        // initial view
        updateView();
    }

    public void updateView() {
        Log.d(LOG_TAG, "The current view is: " + index);
        buttonLayout.removeAllViews();
        StoryEvent event = storyEvents[index];
        int[] storyEventLeads = event.getLeads();
        int numChoices = storyEventLeads.length;


        storyView.setText(event.getStory());
        eventView.setText(event.getChoiceEvent());


        if (numChoices == 1) {
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            int nextEvent = storyEventLeads[0];
            if (nextEvent == -1) {
                // this an end
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // update view when continue is pressed
                        index = 0;
                        updateView();
                    }
                });
                button.setText("Restart?");
                buttonLayout.addView(button);
            }
            else {
                Log.d(LOG_TAG, "Continue Choice to: " + nextEvent);
                // this is a redirect
                index = nextEvent;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // update view when continue is pressed
                        updateView();
                    }
                });
                button.setText("Continue");
                buttonLayout.addView(button);
            }
        }
        else {
            for (int b = 0; b < numChoices; b++) {
                Button button = new Button(this);

                button.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //choose index
                        index = storyEvents[index].getLeads()[buttonLayout.indexOfChild(v)];
                        updateView();
                    }
                });
                button.setText(choices[storyEventLeads[b]]);
                buttonLayout.addView(button);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_story, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //lol secret reset button
            index = 0;
            updateView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
