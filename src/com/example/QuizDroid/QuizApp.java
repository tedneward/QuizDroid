package com.example.QuizDroid;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
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

//        new Thread(new Runnable() {
//            public void run() {
//                try
//                {
//                    URL downloadUrl = new URL("http://tednewardsandbox.site44.com/questions.json");
//
//                    BufferedReader in = null;
//                    PrintWriter out = null;
//                    try
//                    {
//                        in = new BufferedReader(new InputStreamReader(downloadUrl.openStream()));
//                        out = new PrintWriter(new FileOutputStream(getFilesDir() + "/questions.json"));
//
//                        String str;
//                        while ((str = in.readLine()) != null) {
//                            Log.v("QuizApp", "Writing downloaded " + str);
//                            out.println(str);
//                        }
//                    }
//                    finally
//                    {
//                        if (in != null)
//                            in.close();
//                        if (out != null)
//                            out.close();
//                    }
//                }
//                catch (MalformedURLException malURLEx)
//                {
//                    Log.wtf("QuizApp", malURLEx);
//                }
//                catch (IOException ioEx)
//                {
//                    Log.wtf("QuizApp", ioEx);
//                }
//            }
//        }).start();

        Log.d("QuizApp", "getFilesDir() = " + getFilesDir().getAbsolutePath());
        FileInputStream fis = null;
        try
        {
            fis = openFileInput("questions.json");
            String json = readJSONFile(fis);
            JSONArray jsonTopics = new JSONArray(json);

            topics = new ArrayList<Topic>();
            for (int i=0; i<jsonTopics.length(); i++)
            {
                JSONObject topic = jsonTopics.getJSONObject(i);
                topics.add(loadTopic(topic));
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

        // Now kick off the background service to download the new (?) file
        //Intent downloadIntent = new Intent(this, DownloadService.class);
        //startService(downloadIntent);
        DownloadService.setServiceAlarm(this, true);
    }

    private String readJSONFile(FileInputStream fis)
            throws IOException
    {
        int size = fis.available();
        byte[] buffer = new byte[size];
        fis.read(buffer);

        return new String(buffer, "UTF-8");
    }

    private Topic loadTopic(JSONObject topic)
            throws JSONException
    {
        JSONArray qs = topic.getJSONArray("questions");
        Log.d("QuizApp", "Topic " + topic.getString("title") + " has " + qs.length() + " questions.");
        List<Question> questions = new ArrayList<Question>();
        for (int j=0; j< qs.length(); j++)
        {
            Log.d("QuizApp", "Adding " + qs.getJSONObject(j).getString("text"));
            questions.add(loadQuestion(qs.getJSONObject(j)));
        }

        return new Topic(topic.getString("title"), topic.getString("desc"), questions);
    }

    private Question loadQuestion(JSONObject q)
            throws JSONException
    {
        return new Question(q.getString("text"),
                q.getJSONArray("answers").getString(0),
                q.getJSONArray("answers").getString(1),
                q.getJSONArray("answers").getString(2),
                q.getJSONArray("answers").getString(3),
                q.getInt("answer"));
    }


    @Override
    public void onTerminate() {
        super.onTerminate();

        if (DownloadService.isServiceAlarmOn(this))
            DownloadService.setServiceAlarm(this, false);
    }

    public List<Topic> getTopics() {
        return topics;
    }

    private List<Topic> topics;
}
