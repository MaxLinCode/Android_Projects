package com.microlux.maxlin.juancolin;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String LOG_TAG = QuizActivity.class.getCanonicalName();

    private TextView textView;
    private ImageView mAnswerView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Drawable mDefaultButton;

    private QuizView[] quizViews;
    private QuizView currQV;
    private int questionIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // get textviews
        textView = (TextView)findViewById(R.id.textView);

        // get answer view
        mAnswerView = (ImageView)findViewById(R.id.answerView);

        // get buttons
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mNextButton = (Button)findViewById(R.id.next_button);
        mPrevButton = (Button)findViewById(R.id.prev_button);

        // keep default button's drawable
        mDefaultButton = mNextButton.getBackground();

        // init variables
        String[] questions = getResources().getStringArray(R.array.questions_array);
        int[] answers = getResources().getIntArray(R.array.answers_array);
        quizViews = new QuizView[answers.length];

        // load in questions and answers in to quizview
        for (int i = 0; i < quizViews.length; i++) {
            quizViews[i] = new QuizView(questions[i], answers[i]==1);
        }

        // dispose of unneeded arrays
        questions = null;
        answers = null;
        questionIndex = 0;
        currQV = quizViews[questionIndex];

        // show first question
        textView.setText(currQV.getQuestion());
        // set first image
        mAnswerView.setImageResource(R.drawable.question_mark);

        // set listeners
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle when true is pressed
                checkAnswer(1, v);
                currQV.isAnswered = true;
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle when false is pressed
                checkAnswer(0, v);
                currQV.isAnswered = true;
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevQuestion();
            }
        });
    }

    private void checkAnswer(int in, View v) {
        if (questionIndex < quizViews.length && !currQV.isAnswered) {
            if ((in == 1) == currQV.getAnswer()) {
                // correct
                mAnswerView.setImageResource(R.drawable.technically_correct);
                v.setBackgroundColor(Color.parseColor("#4DFF4D"));   // green
            } else {
                // incorrect
                mAnswerView.setImageResource(R.drawable.politically_incorrect);
                v.setBackgroundColor(Color.parseColor("#FF7070"));   // red
            }
        }
    }

    private void nextQuestion() {
        if (questionIndex + 1 < quizViews.length) {
            // go to next question
            questionIndex++;
            currQV = quizViews[questionIndex];
            // set text for next question
            textView.setText(currQV.getQuestion());
            // clear correct/incorrect box
            mAnswerView.setImageResource(R.drawable.question_mark);
            // change background of buttons to default
            mTrueButton.setBackground(mDefaultButton);
            mFalseButton.setBackground(mDefaultButton);
            return;
        }

        //handle when quiz is over

        return;
    }

    private void prevQuestion() {
        if (questionIndex - 1 >= 0) {
            // go to prev question
            questionIndex--;
            currQV = quizViews[questionIndex];
            // set screen for prev question
            textView.setText(currQV.getQuestion());
            if (currQV.isAnswered) {
                if (currQV.getAnswer()) {
                    // true
                    mAnswerView.setImageResource(R.drawable.technically_correct);
                    // change background of buttons to default
                    mTrueButton.setBackground(mDefaultButton);
                    mFalseButton.setBackground(mDefaultButton);
                }
                else {
                    // false
                    mAnswerView.setImageResource(R.drawable.politically_incorrect);
                    // change background of buttons to default
                    mTrueButton.setBackground(mDefaultButton);
                    mFalseButton.setBackground(mDefaultButton);
                }
            }
            else {
                // clear correct/incorrect box
                mAnswerView.setImageResource(R.drawable.question_mark);
                // change background of buttons to default
                mTrueButton.setBackground(mDefaultButton);
                mFalseButton.setBackground(mDefaultButton);
            }
        }

        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
            // um this will just be a restart for debugging :)
            /****RESTART QUIZ****/
            questionIndex = 0;
            currQV = quizViews[0];
            textView.setText(currQV.getQuestion());
            // clear correct/incorrect box
            //answerView.setText("");
            mAnswerView.setImageResource(R.drawable.question_mark);
            // change background of buttons to default
            mTrueButton.setBackground(mDefaultButton);
            mFalseButton.setBackground(mDefaultButton);
            // allow true/false choice
            for (int i = 0; i < quizViews.length; i++) {
                quizViews[i].isAnswered = false;
            }
            /****RESTART QUIZ****/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
