package com.microlux.maxlin.juancolin;

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
    private Drawable mDefaultButton;

    private int questionIndex;
    private String[] questions;
    private int[] answers;
    private boolean currQuestionAnswered;

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

        // keep default button's drawable
        mDefaultButton = mNextButton.getBackground();

        // init variables
        questions = getResources().getStringArray(R.array.questions_array);
        answers = getResources().getIntArray(R.array.answers_array);
        questionIndex = 0;
        currQuestionAnswered = false;    // for locking in answer


        // show first question
        textView.setText(questions[0]);
        // set first image
        mAnswerView.setImageResource(R.drawable.question_mark);

        // set listeners
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle when true is pressed
                checkAnswer(1, v);
                currQuestionAnswered = true;
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle when false is pressed
                checkAnswer(0, v);
                currQuestionAnswered = true;
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        // add the answer text in the future
    }

    private void checkAnswer(int in, View v) {
        if (questionIndex < questions.length && !currQuestionAnswered) {
            if (in == answers[questionIndex]) {
                // correct
                mAnswerView.setImageResource(R.drawable.technically_correct);
                v.setBackgroundColor(Color.parseColor("#4DFF4D"));   // green
                Toast.makeText(QuizActivity.this, R.string.correct_text, Toast.LENGTH_SHORT);
            } else {
                // incorrect
                mAnswerView.setImageResource(R.drawable.politically_incorrect);
                v.setBackgroundColor(Color.parseColor("#FF7070"));   // red
                Toast.makeText(QuizActivity.this, R.string.incorrect_text, Toast.LENGTH_SHORT);
            }
        }
    }

    private void nextQuestion() {
        if (questionIndex + 1 < questions.length) {
            // go to next question
            questionIndex++;
            // set text for next question
            textView.setText(questions[questionIndex]);
            // clear correct/incorrect box
            mAnswerView.setImageResource(R.drawable.question_mark);
            // change background of buttons to default
            mTrueButton.setBackground(mDefaultButton);
            mFalseButton.setBackground(mDefaultButton);
            // allow true/false choice
            currQuestionAnswered = false;
            return;
        }

        //handle when quiz is over

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
            textView.setText(questions[0]);
            // clear correct/incorrect box
            //answerView.setText("");
            mAnswerView.setImageResource(R.drawable.question_mark);
            // change background of buttons to default
            mTrueButton.setBackground(mDefaultButton);
            mFalseButton.setBackground(mDefaultButton);
            // allow true/false choice
            currQuestionAnswered = false;
            /****RESTART QUIZ****/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
