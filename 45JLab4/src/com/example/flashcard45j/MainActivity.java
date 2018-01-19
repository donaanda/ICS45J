// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {
    //Default initialization for the default settings and the backing flashcard manager object.
    //The settings are default initialized because we'll need them when changing orientation.
    private int sortFlag = 0, selectFlag = 0;
    private boolean typeSortFlag = false;
    private static FlashcardManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set orientation
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.activity_land_main);
        else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_main);

        //Call the main method which starts the activity given the retrieved settings parameters.
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sortFlag = sharedPreferences.getInt("sort_settings",0);
        selectFlag = sharedPreferences.getInt("select_settings",0);
        typeSortFlag = sharedPreferences.getBoolean("type_settings",false);

        Intent intent = getIntent();
        setUpActivity(intent,sortFlag,selectFlag,typeSortFlag);
    }

    //Change to portrait or landscape of the current activity if the orientation changes.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.activity_land_main);
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        setUpActivity(intent,sortFlag,selectFlag,typeSortFlag);
    }

    //"Sets up the activity," utilizing the settings flags and the passed card list to use.
    private void setUpActivity(Intent intent, int sortFlag, int selectFlag, boolean typeFlag) {
        ArrayList<Card> retrievedCards = (ArrayList<Card>)intent.getSerializableExtra("card list");

        // Create the appropriate flashcard manager object, depending on whether there are already cards (there should always be cards at least from cards.txt).
        //If the card list is empty, we populate it with the text file once (all subsequent executions here won't be populated again).
        if(retrievedCards == null || retrievedCards.size() == 0) {
            //Read from file, if reading failed then show dialog box indicating a fatal error.
            //cards.txt should come as the default questions with the application untouched, otherwise the program shouldn't continue.
            InputStream is = null;
            try {
                is = getAssets().open(String.format("cards.txt"));
            } catch (IOException ioe) {
                final TextView messageDialog = new TextView(this);
                messageDialog.setText("Reading from internal file failed, please redownload the app.");
                messageDialog.setGravity(Gravity.CENTER_HORIZONTAL);
                messageDialog.setTextSize(14);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder
                        .setTitle("Fatal error")
                        .setView(messageDialog)
                        .setCancelable(false)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create()
                        .show();
            }

            manager = new FlashcardManager(is);
        } else {
            manager = new FlashcardManager();
            manager.addCardList(retrievedCards);
        }

        //Now that the card list is fully populated, we can sort and reduce to a subset by difficulty.
        manager.settingsSort(sortFlag,typeFlag);
        manager.difficultySelectSubSet(selectFlag);
    }

    //This method is called when the "start" button is pressed on the main screen.
    //It sends all of the information needed for the game to the next activity, the quiz itself (QuizPrompt).
    public void startClick(View v) {
        //We pass an intent with extras <cardList,<total questions, questions correct, begin time>>
        Intent intent = new Intent(MainActivity.this,QuizPrompt.class);
        Bundle numberBundle = new Bundle(), typeCountBundle = new Bundle();
        TimeManager timeManager = new TimeManager();
        timeManager.insertTime(SystemClock.elapsedRealtime());
        HashMap<String,Integer> totalTypeList = manager.countCardTypes(), correctTypeList = new HashMap<String, Integer>();

        //Initialize a new map for the number of questions correct
        for(String type : totalTypeList.keySet())
            correctTypeList.put(type,0);

        //Send all the game information to the next activity via bundles/intent extras.
        intent.putExtra("card list", manager.getCardList());
        numberBundle.putSerializable("times",timeManager);
        numberBundle.putInt("index",0);

        typeCountBundle.putSerializable("correct types", correctTypeList);
        typeCountBundle.putSerializable("total types", totalTypeList);

        intent.putExtra("numbers", numberBundle);
        intent.putExtra("type counter",typeCountBundle);

        //Start the activity
        startActivity(intent);
        finish();
    }

    //This method is called when the "review" button is pressed.
    //Sends the current card list to the next activity (SettingsActivity) to be read for review.
    public void reviewClick(View v) {
        Intent intent = new Intent(MainActivity.this,ReviewActivity.class);
        intent.putExtra("card list",manager.getCardList());
        startActivity(intent);
        finish();
    }

    //This method is called when the "options" button is pressed.
    //Sends the current card list to the next activity (OptionsActivity) for additional options to mutate the card list (e.g. URL reading).
    public void optionsClick(View v) {
        Intent intent = new Intent(MainActivity.this,OptionsActivity.class);
        intent.putExtra("card list",manager.getCardList());
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //This method serves to handle the transition between this main activity and the settings activity.
    //This sends the card list to the settings activity to be mutated according to the setting changes.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
            intent.putExtra("card list",getIntent().getSerializableExtra("card list"));
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
