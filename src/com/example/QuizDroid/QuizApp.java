package com.example.QuizDroid;

import android.app.Application;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by ted on 2/21/14.
 */
public class QuizApp extends Application
{
    public QuizApp()
    {
        if (instance == null)
            instance = this;
        else {
            Log.e("QuizApp", "*** MORE THAN ONE CONSTRUCTED!");
            throw new RuntimeException("Multiple app exception");
        }
    }

    private static QuizApp instance;
    public static QuizApp getInstance() {
        return instance;
    }

    @Override public void onCreate()
    {
        super.onCreate();

        Log.d("QuizApp", "getFilesDir() = " + getFilesDir().getAbsolutePath());
        FileInputStream fis = null;
        try
        {
            fis = openFileInput("questions.json");
            String json = readJSONFile(fis);
            JSONArray jsonTopics = new JSONArray(json);

            topics = new ArrayList<Topic>();

            // Convert JSON array into List<Topic>, and nested objects into List<Question>
            for (int i=0; i<jsonTopics.length(); i++)
            {
                JSONObject topic = jsonTopics.getJSONObject(i);

                JSONArray qs = topic.getJSONArray("questions");
                Log.d("QuizApp", "Topic " + topic.getString("title") + " has " + qs.length() + " questions.");

                List<Question> questions = new ArrayList<Question>();
                for (int j=0; j< qs.length(); j++)
                {
                    Log.d("QuizApp", "Adding " + qs.getJSONObject(j).getString("text"));
                    questions.add(new Question(qs.getJSONObject(j).getString("text"),
                            qs.getJSONObject(j).getJSONArray("answers").getString(0),
                            qs.getJSONObject(j).getJSONArray("answers").getString(1),
                            qs.getJSONObject(j).getJSONArray("answers").getString(2),
                            qs.getJSONObject(j).getJSONArray("answers").getString(3),
                            qs.getJSONObject(j).getInt("answer")));
                }
                topics.add(new Topic(topic.getString("title"), topic.getString("desc"), questions));
            }
        }
        catch (JSONException jsonEx)
        {
            Log.e("QuizApp", "Exception in reading JSON file: " + jsonEx.getMessage());
            Log.wtf("QuizApp", jsonEx);
        }
        catch (IOException ioEx)
        {
            Log.e("QuizApp", "Exception in reading JSON file: " + ioEx.getMessage());
            Log.wtf("QuizApp", ioEx);
        }
        finally
        {
            try
            {
                if (fis != null)
                    fis.close();
            }
            catch (IOException ioEx)
            {
                // Not much we can do here....
            }
        }
    }

    private String readJSONFile(FileInputStream fis)
            throws IOException
    {
        int size = fis.available();
        byte[] buffer = new byte[size];
        fis.read(buffer);

        return new String(buffer, "UTF-8");
    }

    public List<Topic> getTopics() {
        return topics;
    }

    private List<Topic> topics;
}
