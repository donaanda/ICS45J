// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class OptionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set orientation
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.activity_land_options);
        else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_options);
    }

    //Change to portrait or landscape of the current activity if the orientation changes.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.activity_land_options);
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_options);
    }

    //This method is called when the done button is clicked.
    //Attempts to read the URL typed and adds any cards found to the card set.
    //After reading, shows a dialog box of the number of cards read and returns to main activity.
    public void optionsClick(View v) {
        final EditText urlInput = (EditText)findViewById(R.id.url_input_field);
        Editable input = urlInput.getText();
        final String sourceURL = input.toString();
        ArrayList<Card> cards = (ArrayList<Card>)getIntent().getSerializableExtra("card list"), retrievedCards = null;

        //Read the cards in - URL reading of cards requires an AsyncTask which we wait on for results and append to the card list.
        AsyncTask<Void,Void,ArrayList<Card>> onlineTask = new AsyncTask<Void, Void, ArrayList<Card>>() {
            @Override
            protected ArrayList<Card> doInBackground(Void... params) {
                return FlashcardManager.readQuestions(sourceURL);
            }
        };
        onlineTask.execute();

        try {
            retrievedCards = onlineTask.get();
        }catch(Exception e) {
            e.printStackTrace();
        }

        //Store the actual number of cards read to display after, add these cards to the card list.
        int cardsRead = retrievedCards.size();
        cards.addAll(retrievedCards);

        //Caveat of needing a final variable which "may not be initialized" due to try/catch initialization.
        //This final card list will be sent as the card list to use when we return to the main activity.
        final ArrayList<Card> finalCardList = cards;

        //Display a dialog box saying how many cards were read, with a button to return to the main activity.
        final TextView messageDialog = new TextView(this);
        messageDialog.setText(Integer.toString(cardsRead) + " cards read.");
        messageDialog.setGravity(Gravity.CENTER);
        messageDialog.setTextSize(20);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Options")
                .setView(messageDialog)
                .setCancelable(false)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
                        intent.putExtra("card list", finalCardList);
                        startActivity(intent);
                        finish();
                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    //This method is called when the back button is called.
    //It simply returns to the main activity, passing back the card list to use (which hasn't changed).
    public void backClick(View v) {
        ArrayList<Card> cards = (ArrayList<Card>)getIntent().getSerializableExtra("card list");
        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        intent.putExtra("card list", cards);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
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
            Intent intent = new Intent(OptionsActivity.this,SettingsActivity.class);
            intent.putExtra("card list",getIntent().getSerializableExtra("card list"));
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
