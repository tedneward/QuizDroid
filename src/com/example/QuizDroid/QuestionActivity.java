package com.example.QuizDroid;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ted on 2/21/14.
 */
public class QuestionActivity extends Activity {

    private Topic topic;
    private int questionNumber = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent launchingIntent = getIntent();
        int whichTopic = launchingIntent.getIntExtra("TOPIC", -1);
        if (whichTopic == -1)
        {
            Log.e("QuestionActivity", "Somehow we got a non-value for TOPIC");
            finish();
        }
        topic = QuizApp.getInstance().getTopics().get(whichTopic);
        Question question = topic.getQuestions().get(questionNumber);

        Log.d("QuestionActivity", "Trying to find and launch fragment");

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        QuestionFragment fragment = new QuestionFragment(question);
        ft.add(R.id.fragQuestionFragmentPlaceholder, fragment);
        ft.commit();

        setContentView(R.layout.question_activity);

        TextView txtTitle = (TextView) findViewById(R.id.txtQuestionTitle);
        txtTitle.setText(topic.getName());

        Log.d("QuestionActivity", "Exiting onCreate");
    }

    public void nextQuestion() {
        Log.d("QuestionActivity", "Time to flip from " + questionNumber + " to next question");

        if (questionNumber < topic.getQuestions().size() - 1)
        {
            questionNumber++;
            Log.d("QuestionActivity", "Advancing to question #" + questionNumber);

            Question question = topic.getQuestions().get(questionNumber);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            QuestionFragment fragment = new QuestionFragment(question);
            ft.setCustomAnimations(
                    R.animator.fragment_slide_left_enter,
                    R.animator.fragment_slide_left_exit,
                    R.animator.fragment_slide_right_enter,
                    R.animator.fragment_slide_right_exit);
            ft.replace(R.id.fragQuestionFragmentPlaceholder, fragment);
            ft.commit();
        }
        else
        {
            Log.d("QuestionActivity", "All done with the topic!");
        }
    }
}