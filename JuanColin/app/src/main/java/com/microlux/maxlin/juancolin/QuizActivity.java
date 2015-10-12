package com.microlux.maxlin.juancolin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String LOG_TAG = QuizActivity.class.getCanonicalName();
    public static final String EXTRA_ANSWER_IS_TRUE = "answerIsTrue";

    private TextView textView;
    private ImageView mAnswerView;
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private Button mCheatButton;
    private boolean mIsCheater;
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
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mCheatButton = (Button)findViewById(R.id.cheat_button);

        // keep default button's drawable
        mDefaultButton = mTrueButton.getBackground();

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
                currQV.setChoice(true);
                updateView();
                currQV.isAnswered = true;
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle when false is pressed
                currQV.setChoice(false);
                updateView();
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

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                intent.putExtra(EXTRA_ANSWER_IS_TRUE, currQV.getAnswer());
                startActivityForResult(intent, 0);
            }
        });

        updateView();
    }

    private void nextQuestion() {
        mIsCheater = false;
        if (questionIndex + 1 < quizViews.length) {
            // go to next question
            questionIndex++;
            currQV = quizViews[questionIndex];
            updateView();
            return;
        }

        //handle when quiz is over

        return;
    }

    private void prevQuestion() {
        mIsCheater = false;
        if (questionIndex - 1 >= 0) {
            // go to prev question
            questionIndex--;
            currQV = quizViews[questionIndex];
            updateView();
        }

        return;
    }

    private void updateView() {
        // set text for next question
        textView.setText(currQV.getQuestion());
        // change background of buttons to default
        mTrueButton.setBackground(mDefaultButton);
        mFalseButton.setBackground(mDefaultButton);
        if (currQV.isAnswered) {
            String output = "";
            if (currQV.getChoice() == currQV.getAnswer()) {
                // true button
                mAnswerView.setImageResource(R.drawable.technically_correct);
                getChosenButton().setBackgroundColor(Color.parseColor("#4DFF4D"));   // green
                output = "Correct!";
            } else {
                // false button
                mAnswerView.setImageResource(R.drawable.politically_incorrect);
                getChosenButton().setBackgroundColor(Color.parseColor("#FF7070"));   // red
                output = "Incorrect!";
            }

            if (mIsCheater) {
                Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            // clear correct/incorrect box
            mAnswerView.setImageResource(R.drawable.question_mark);
        }
    }

    private Button getChosenButton() {
        if (currQV.getChoice()) {
            return mTrueButton;
        }
        else {
            return mFalseButton;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
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
