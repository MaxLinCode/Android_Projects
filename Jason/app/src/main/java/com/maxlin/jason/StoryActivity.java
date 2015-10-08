package com.maxlin.jason;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StoryActivity extends Activity {
    public static final String STORY_EVENT = "story event";

    private StoryEvent[] storyEvents;
    private String[] choices;
    private TextView storyView;
    private TextView eventView;

    private Button choiceAView;
    private Button choiceBView;
    private Button choiceCView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyView = (TextView)findViewById(R.id.textView);
        eventView = (TextView)findViewById(R.id.textView1);

        choiceAView = (Button)findViewById(R.id.buttonA);
        choiceBView = (Button)findViewById(R.id.buttonB);
        choiceCView = (Button)findViewById(R.id.buttonC);

        Resources res = getResources();

        String[] stories = res.getStringArray(R.array.stories_array);
        String[] events = res.getStringArray(R.array.events_array);
        choices = res.getStringArray(R.array.choices_array);


        int[][] leads;

        leads = new int[][]
                {
                        {1, 2, 3}, //0
                        {4, 5, 6},//1
                        {6, 8, 9},//2
                        {10, 11, 12},//3
                        {-1},//4
                        {13, 14, 15},//5
                        {-1},//6
                        {},//7
                        {15, 17, 18},//8
                        {18, 20, 21},//9
                        {-1},//10
                        {8},//11
                        {9},//12
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
        for (int i = 0; i < events.length; i++) {
            if (leads[i].length != 0 ) {
                if (leads[i].length > 1) {
                    // initialize with the events
                    storyEvents[i] = new StoryEvent(stories[i], events[e], leads[i]);
                    e++;
                } else if (leads[i].length == 1) {
                    // no choice events
                    storyEvents[i] = new StoryEvent(stories[i], "", leads[i]);
                } else if (leads[i][0] == -1) {
                    storyEvents[i] = new StoryEvent(stories[i], "", leads[i]);
                    storyEvents[i].setEventEnd();
                }
            }
        }

        // attaching click listeners
        choiceAView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        choiceBView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        choiceCView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        start();
    }


    public void start() {
        int i = 0;
        int[] storyEventLeads = storyEvents[i].getLeads();
        storyView.setText(storyEvents[i].getStory());
        eventView.setText(storyEvents[i].getChoiceEvent());


        choiceAView.setText(choices[storyEventLeads[0]]);
        choiceBView.setText(choices[storyEventLeads[1]]);
        choiceCView.setText(choices[storyEventLeads[2]]);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
