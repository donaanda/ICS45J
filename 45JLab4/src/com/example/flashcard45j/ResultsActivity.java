// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ResultsActivity extends Activity {
    TimeManager timeManager;
    List<Long> deltaTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set orientation
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.activity_land_results);
        else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("numbers");

        // Update the time taken for the last card to be answered from activity QuizPrompt.
        timeManager = (TimeManager)bundle.getSerializable("times");

        setUpActivity(intent);
    }

    //Change to portrait or landscape of the current activity if the orientation changes.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.activity_land_results);
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        setUpActivity(intent);
    }

    //We set up the activity by initializing all the TextViews to display the statistics.
    private void setUpActivity(Intent intent) {
        //Set the statistics displayed to only go to 2 decimal places, floored.
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);

        Bundle resultBundle = intent.getBundleExtra("numbers"), typeBundle = intent.getBundleExtra("type counter");

        deltaTimes = timeManager.calculateDeltaTime();

        //Get all the raw statistics from the intent bundles.
        final ArrayList<Card> cardList = (ArrayList<Card>)intent.getSerializableExtra("card list");
        double elapsedTime = timeManager.calculateElapsedTime();
        HashMap<String,Integer> totalTypeList = (HashMap<String,Integer>)typeBundle.getSerializable("total types"),
                correctTypeList = (HashMap<String,Integer>)typeBundle.getSerializable("correct types");
        int totalQuestions = cardList.size(), numCorrect = 0;

        //Create all the TextViews needed to display the statistics.
        final TextView percentView = (TextView)findViewById(R.id.percentView),
                timeView = (TextView)findViewById(R.id.timeView),
                averageTimeView = (TextView)findViewById(R.id.averageTimeView),
                stdDevView = (TextView)findViewById(R.id.stdDevView),
                averageDifficultyView = (TextView)findViewById(R.id.averageDifficultyView);
        ArrayList<TextView> typePercentViewList = new ArrayList<TextView>();

        //We dynamically instantiate extra TextViews for each type of card.
        //Each TextView displays the proportion of that type of card correct (e.g. "Percent of inheritance questions correct: 70%").
        for(String type : correctTypeList.keySet()) {
            numCorrect += correctTypeList.get(type);
            TextView typePercentView = new TextView(this);
            double percentCorrect = (double)correctTypeList.get(type)/totalTypeList.get(type) * 100;
            String formattedPercentage = df.format(percentCorrect);
            typePercentView.setText("Percent of " + type + " questions correct: " + formattedPercentage + "%");
            typePercentView.setGravity(Gravity.LEFT);
            typePercentView.setTextColor(getResources().getColor(R.color.text_color));
            typePercentViewList.add(typePercentView);
        }

        //Instantiate the restart button, which returns to the main activity along with the card list to use.
        final Button restartButton = (Button)findViewById(R.id.restartButton), reviewButton = (Button)findViewById(R.id.review_button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this,MainActivity.class);
                intent.putExtra("card list",cardList);
                startActivity(intent);
                finish();
            }
        });
        //The review button goes to the review activity to review the card answers.
        //Sends the card list in the intent to read off of and display answers.
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this,ReviewActivity.class);
                intent.putExtra("card list",cardList);
                startActivity(intent);
                finish();
            }
        });

        //Use statistics class and its static methods to calculate the final values of the statistics.
        String percentCorrect = df.format((double)numCorrect*100/totalQuestions),
                formattedTime = df.format(elapsedTime),
                formattedAverageTime = df.format(Statistics.getAverage(deltaTimes)),
                formattedStdDev = df.format(Statistics.getStandardDeviation(deltaTimes)),
                formattedAverageDifficulty = df.format(Statistics.getAverageDifficulty(cardList));

        //Display all of the statistics via the TextViews.
        percentView.setText("Your percent correct: " + percentCorrect + "%");
        percentView.setTextColor(getResources().getColor(R.color.text_color));
        timeView.setText("Time taken: " + formattedTime + " seconds");
        timeView.setTextColor(getResources().getColor(R.color.text_color));
        averageTimeView.setText("Average time per question: " + formattedAverageTime + " seconds");
        averageTimeView.setTextColor(getResources().getColor(R.color.text_color));
        stdDevView.setText("Standard deviation: " + formattedStdDev + " seconds");
        stdDevView.setTextColor(getResources().getColor(R.color.text_color));
        averageDifficultyView.setText("Average overall difficulty (1-3): " + formattedAverageDifficulty);
        averageDifficultyView.setTextColor(getResources().getColor(R.color.text_color));

        //Finally, add all the type percent correct views.
        LinearLayout ll = (LinearLayout)findViewById(R.id.typeLinearLayout);

        for(TextView tv : typePercentViewList)
            ll.addView(tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
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
            Intent intent = new Intent(ResultsActivity.this,SettingsActivity.class);
            intent.putExtra("card list",getIntent().getSerializableExtra("card list"));
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
