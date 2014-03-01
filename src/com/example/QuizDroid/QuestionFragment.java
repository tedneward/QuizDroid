package com.example.QuizDroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ted on 2/21/14.
 */
public class QuestionFragment extends Fragment {

    private Question question;
    private QuestionActivity hostActivity;


    public QuestionFragment(Question question)
    {
        this.question = question;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        QuestionActivity qa = (QuestionActivity)activity;
        hostActivity = qa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("QuestionFragment", "onCreateView");
        View view = inflater.inflate(R.layout.question_fragment, container, false);

        TextView txtText = (TextView) view.findViewById(R.id.txtQuestionText);
        txtText.setText(question.getQuestionText());

        final RadioButton radAnswer1 = (RadioButton) view.findViewById(R.id.radAnswer1);
        radAnswer1.setText(question.getAnswer1());

        final RadioButton radAnswer2 = (RadioButton) view.findViewById(R.id.radAnswer2);
        radAnswer2.setText(question.getAnswer2());

        final RadioButton radAnswer3 = (RadioButton) view.findViewById(R.id.radAnswer3);
        radAnswer3.setText(question.getAnswer3());

        final RadioButton radAnswer4 = (RadioButton) view.findViewById(R.id.radAnswer4);
        radAnswer4.setText(question.getAnswer4());

        Button submit = (Button) view.findViewById(R.id.btnQuestionSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(hostActivity, "Checking answer...", Toast.LENGTH_SHORT).show();

                switch (question.getCorrectAnswer())
                {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }

                hostActivity.nextQuestion();
            }
        });

        Log.d("QuestionFragment", "Exiting onCreateView");
        return view;
    }
}