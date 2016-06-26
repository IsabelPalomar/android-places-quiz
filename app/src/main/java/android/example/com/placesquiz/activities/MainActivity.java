package android.example.com.placesquiz.activities;

import android.content.Intent;
import android.example.com.placesquiz.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Starts Quiz activity
     * @param view
     */

    public void startQuiz(View view) {
        Intent i = new Intent(MainActivity.this, QuizActivity.class);
        startActivity(i);
    }
}
