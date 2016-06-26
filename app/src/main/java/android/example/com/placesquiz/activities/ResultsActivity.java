package android.example.com.placesquiz.activities;

import android.content.res.Resources;
import android.example.com.placesquiz.R;
import android.example.com.placesquiz.utils.Constants;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String results = "";
        Resources res = getResources();
        String[] answers = getIntent().getStringArrayExtra("answers");
        int correctAnswers = 0;

        //Calculate the correct user answers
        for(int i = 0; i < answers.length; i++) {
            System.out.println(answers[i]);
            System.out.println(Constants.correctAnswers[i]);
            if(answers[i].equalsIgnoreCase(Constants.correctAnswers[i])){
                correctAnswers ++;
            }
        }

        //Set the correct answers value
        TextView correct = (TextView) findViewById(R.id.correctAnswers);
        correct.setText(String.valueOf(correctAnswers));

        if(correctAnswers > 7){
            results += Constants.CONGRATULATIONS_MSG;
        }
        else if (correctAnswers > 4){
            results += Constants.GOOD_JOB_MSG;
        }
        else{
            results += Constants.KEEP_PRACTICING_MSG;
        }
        results += String.format(res.getString(R.string.app_message_results), correctAnswers, answers.length);
        TextView resultsTxt = (TextView) findViewById(R.id.resultsText);
        resultsTxt.setText(results);


    }
}
