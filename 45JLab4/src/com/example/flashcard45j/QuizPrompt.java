// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//This activity will be instantiated once per new card - once we finish one card via QuizPrompt, we reinstatiate QuizPrompt with the next card.
public class QuizPrompt extends Activity {
    public static int buttonGapPX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_prompt);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        buttonGapPX = (int)Math.ceil(5*metrics.density);

        Intent intent = getIntent();
        setUpActivity(intent);
    }

    //Sets up the activity by retrieving the card list and statistics so far, then creating the components needed for the card to be represented.
    //We set the question textView with ID's 100 and more (100-101 for now) to be above all the answers in the RelativeLayout.
    private void setUpActivity(Intent intent) {
        //Retrieve the current card we're on, its answer, and its type.
        Bundle numberBundle = intent.getBundleExtra("numbers"), typeBundle = intent.getBundleExtra("type counter");
        ArrayList<Card> cardList = (ArrayList<Card>)intent.getSerializableExtra("card list");
        int index = numberBundle.getInt("index");
        Card card = cardList.get(index);
        Answer answer = card.getAnswer();
        String cardType = card.getType();

        //Display the type of question in the bar above.
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.quizlayout);
        setTitle("Quiz: " + card.getType() + " Question");
        final TextView questionDisplay = new TextView(this);
        questionDisplay.setText(card.getQuestion());
        questionDisplay.setTextSize(20);
        questionDisplay.setId(100);

        //We call the respective method which displays the answer format we want for the card question.
        if(answer instanceof MultipleChoiceAnswer) {
            defineMCQuestion(rl, questionDisplay, cardList, answer, numberBundle, typeBundle, cardType);
        } else if(answer instanceof ShortAnswer) {
            defineSAQuestion(rl, questionDisplay, cardList, answer, numberBundle, typeBundle, cardType);
        } else {
            defineTFQuestion(rl, questionDisplay, cardList, answer, numberBundle, typeBundle, cardType);
        }
    }

    //This method is called if the displayed card is multiple choice.
    private void defineMCQuestion(final RelativeLayout rl, final TextView aboveView, final ArrayList<Card> cardList,final Answer a,
                                  final Bundle numberBundle, final Bundle typeBundle, final String cardType) {
        //Retrieve the possible answers as a list and shuffle them for display.
        final MultipleChoiceAnswer mca = (MultipleChoiceAnswer)a;
        final List<String> answerList = new ArrayList(Arrays.asList(mca.getAnswerChoices()));
        Collections.shuffle(answerList);

        //Display the answers. When the confirmation button is clicked, we verify the answer.
        final ListView listView = new ListView(this);
        listView.setId(101);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, answerList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String answer = answerList.get(position);
                boolean correct = mca.checkAnswer(answer.toString());
                processNextIntent(numberBundle, typeBundle, cardList, cardType, correct);
            }
        });
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, 100);
        rl.addView(listView, params);

        RelativeLayout.LayoutParams questionParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        aboveView.setGravity(Gravity.CENTER_HORIZONTAL);
        rl.addView(aboveView,questionParams);
    }

    //This method is called if the displayed card is short answer.
    private void defineSAQuestion(final RelativeLayout rl, final TextView aboveView, final ArrayList<Card> cardList, final Answer a,
                                  final Bundle numberBundle, final Bundle typeBundle, final String cardType) {
        //The short answer is represented with an EditText field and a confirmation button.
        //Once the confirm button is clicked, we verify the answer.
        final EditText answerInput = new EditText(this);
        answerInput.setSingleLine(true);
        answerInput.setEms(10);
        answerInput.setId(101);
        final Button confirmButton = new Button(this);
        confirmButton.setId(102);
        confirmButton.setText("Confirm Answer");
        confirmButton.setBackground(getResources().getDrawable(R.drawable.button_effect));
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable input = answerInput.getText();
                int inputLength = input.length();

                //If the answer length is 0, then no answer is typed and we won't continue until there is an answer.
                if (inputLength > 0) {
                    boolean correct = a.checkAnswer(input.toString());
                    processNextIntent(numberBundle, typeBundle, cardList, cardType, correct);
                }
            }
        });
        RelativeLayout.LayoutParams answerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        answerParams.setMargins(0,buttonGapPX,0,0);
        answerParams.addRule(RelativeLayout.BELOW, 100);
        answerParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        RelativeLayout.LayoutParams confirmParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        confirmParams.setMargins(0,buttonGapPX,0,0);
        confirmParams.addRule(RelativeLayout.BELOW, 101);
        confirmParams.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        rl.addView(answerInput, answerParams);
        rl.addView(confirmButton, confirmParams);

        RelativeLayout.LayoutParams questionParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        questionParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        aboveView.setGravity(Gravity.CENTER_HORIZONTAL);
        rl.addView(aboveView,questionParams);
    }

    //This method is called if the displayed card is true/false.
    private void defineTFQuestion(final RelativeLayout rl, final TextView aboveView, final ArrayList<Card> cardList, final Answer a,
                                  final Bundle numberBundle, final Bundle typeBundle, final String cardType) {
        //The true/false answer is represented with true/false buttons.
        //Once one of the buttons is clicked, we verify whether the one clicked is correct.
        final Button trueButton = new Button(this), falseButton = new Button(this);
        trueButton.setId(101);
        falseButton.setId(102);
        trueButton.setText("True");
        falseButton.setText("False");
        trueButton.setBackground(getResources().getDrawable(R.drawable.button_effect));
        falseButton.setBackground(getResources().getDrawable(R.drawable.button_effect));
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean correct = a.checkAnswer(trueButton.getText().toString().toLowerCase());
                processNextIntent(numberBundle, typeBundle, cardList, cardType, correct);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean correct = a.checkAnswer(falseButton.getText().toString().toLowerCase());
                processNextIntent(numberBundle,typeBundle,cardList,cardType,correct);
            }
        });
        RelativeLayout.LayoutParams trueParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        trueParams.setMargins(0,buttonGapPX,0,0);
        trueParams.addRule(RelativeLayout.BELOW, 100);
        trueParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        RelativeLayout.LayoutParams falseParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        falseParams.setMargins(0,buttonGapPX,0,0);
        falseParams.addRule(RelativeLayout.BELOW, 101);
        falseParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        rl.addView(trueButton, trueParams);
        rl.addView(falseButton, falseParams);

        RelativeLayout.LayoutParams questionParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        questionParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        aboveView.setGravity(Gravity.CENTER_HORIZONTAL);
        rl.addView(aboveView,questionParams);
    }

    //Updates the bundle's map of types of correct questions, indexing by the type correct and incrementing.
    //Puts the updated map back into the bundle under the same key.
    private Bundle incrementTypeBundle(Bundle b, String type) {
        HashMap<String,Integer> correctTypesMap = (HashMap<String,Integer>)b.getSerializable("correct types");
        correctTypesMap.put(type,correctTypesMap.get(type)+1);
        b.putSerializable("correct types",correctTypesMap);
        return b;
    }

    //Called when an answer is given, handles the transition to the next activity, packing the statistics and objects into bundles.
    private void processNextIntent(final Bundle numberBundle, final Bundle typeBundle, final ArrayList<Card> cardList,
                                    final String cardType, boolean correct) {
        final int index = numberBundle.getInt("index");
        final Bundle finalTypeBundle;
        //Increments the number correct under the types held for later statistics - updates the number correct under types.
        finalTypeBundle = correct ? QuizPrompt.this.incrementTypeBundle(typeBundle,cardType) : typeBundle;

        // Update the time taken for this card to be answered.
        TimeManager timeManager = (TimeManager)numberBundle.getSerializable("times");
        timeManager.insertTime(SystemClock.elapsedRealtime());
        numberBundle.putSerializable("times",timeManager);

        numberBundle.putInt("index",index+1);
        //If this card was the last card, we move to the results activity, otherwise continue traversing the card list.
        Intent intent = index < cardList.size() - 1 ?
                new Intent(QuizPrompt.this, QuizPrompt.class) : new Intent(QuizPrompt.this, ResultsActivity.class);

        intent.putExtra("card list",cardList);
        intent.putExtra("numbers",numberBundle);
        intent.putExtra("type counter",finalTypeBundle);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_prompt, menu);
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
            Intent intent = new Intent(QuizPrompt.this,SettingsActivity.class);
            intent.putExtra("card list",getIntent().getSerializableExtra("card list"));
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
