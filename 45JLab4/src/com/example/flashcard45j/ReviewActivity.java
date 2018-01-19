// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//This activity presents the cards and their answers to the user via a ListView.
//The cards are displayed vertically, each entry with the question bolded first and the answer underneath.
public class ReviewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ListView lv = (ListView)findViewById(R.id.listView);
        Intent intent = getIntent();
        final ArrayList<Card> cardList = (ArrayList<Card>)intent.getSerializableExtra("card list");

        //Define an ArrayAdapter so we can display the card question and answers via a ListView.
        //Each entry of the list has two TextViews, for the question and answer.
        class CardAdapter extends ArrayAdapter<Card> {
            public CardAdapter(Context context, ArrayList<Card> cardList) {
                super(context,0,cardList);
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Card card = getItem(position);
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_info, parent, false);
                }
                TextView questionView = (TextView)convertView.findViewById(R.id.card_question),
                        answerView = (TextView)convertView.findViewById(R.id.card_answer);
                questionView.setText(card.getQuestion());
                answerView.setText(card.getAnswer().toString());
                return convertView;
            }
        }

        //Finally, set the adapter to the list view.
        CardAdapter adapter = new CardAdapter(this,cardList);
        lv.setAdapter(adapter);

        //Instantiate the back button, which returns to the main activity with the card list to use.
        final Button backButton = (Button)findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewActivity.this,MainActivity.class);
                intent.putExtra("card list", cardList);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review, menu);
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
            Intent intent = new Intent(ReviewActivity.this,SettingsActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
