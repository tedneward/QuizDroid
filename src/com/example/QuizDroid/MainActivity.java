package com.example.QuizDroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TopicAdapter adapter = new TopicAdapter(this, R.id.listView, QuizApp.getInstance().getTopics());

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MainActivity", "User selected item " + position + " == " + QuizApp.getInstance().getTopics().get(position).getName());
                Intent questionIntent = new Intent(MainActivity.this, QuestionActivity.class);
                questionIntent.putExtra("TOPIC", position);
                startActivity(questionIntent);
            }
        });
    }
}
