package android.example.com.placesquiz.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.example.com.placesquiz.R;
import android.example.com.placesquiz.models.CustomPagerEnum;
import android.example.com.placesquiz.utils.Constants;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuizActivity extends AppCompatActivity {

    ViewPager viewPager;
    String[] answers = new String[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Set the CustomPagerAdapter
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));

        //Disable swipe option for the viewPager
        viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
    }

    //Ask the user if they want to quit the quiz
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.app_title_quit)
                    .setMessage(R.string.app_message_quit)
                    .setPositiveButton(R.string.app_message_yes, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Stop the activity
                            QuizActivity.this.finish();
                        }

                    })
                    .setNegativeButton(R.string.app_message_no, null)
                    .show();

            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }

    }

    public void saveAnswerAndMoveNext(String answer, int position) {
        answers[position] = answer;
        viewPager.setCurrentItem(position + 1);
    }

    public void submitResults(View view) {
        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
        for (String a: answers) {
            if(a != null)
                System.out.println(a.toString());
        }
        startActivity(i);
    }

    public class CustomPagerAdapter extends PagerAdapter {

        private Context mContext;

        public CustomPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, final int position) {

            Resources r = getResources();
            String[] optionsArray = null;

            //Inflates the layout with the resource id stored in customPagerEnum
            CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
            LayoutInflater inflater = LayoutInflater.from(mContext);
            final ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(), collection, false);

            //Set the image
            if(customPagerEnum.getImageResId() != 0){
                ImageView imageView = (ImageView) layout.findViewById(R.id.main_image_view);
                imageView.setImageResource(customPagerEnum.getImageResId());
            }

            //Set the options for checkBox and RadioButton
            if(customPagerEnum.getType().equals(Constants.RADIOBUTTON_CODE)
                    || customPagerEnum.getType().equals(Constants.CHECKBOX_CODE)){
                optionsArray = r.getStringArray(customPagerEnum.getQuestionOptions());
            }

            //Behaviour for each type of View
            FloatingActionButton fab = (FloatingActionButton) layout.findViewById(R.id.fab);
            if(customPagerEnum.getType().equals(Constants.RADIOBUTTON_CODE)){
                RadioButton rbOne = (RadioButton) layout.findViewById(R.id.option_one);
                RadioButton rbTwo = (RadioButton) layout.findViewById(R.id.option_two);
                RadioButton rbThree = (RadioButton) layout.findViewById(R.id.option_three);

                rbOne.setText(optionsArray[0]);
                rbTwo.setText(optionsArray[1]);
                rbThree.setText(optionsArray[2]);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RadioGroup rGroup = (RadioGroup) layout.findViewById(R.id.radio_button_group);
                        int selectedValueId = rGroup.getCheckedRadioButtonId();

                        if(selectedValueId != -1){
                            String answer = ((RadioButton)layout.findViewById(rGroup.getCheckedRadioButtonId())).getText().toString();
                            saveAnswerAndMoveNext(answer, position);
                        }
                        else{
                            Snackbar.make(v, "Select your option...", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }
                });


            }
            else if(customPagerEnum.getType().equals(Constants.CHECKBOX_CODE)){

                final CheckBox cbOne = (CheckBox) layout.findViewById(R.id.option_one_cb);
                final CheckBox cbTwo = (CheckBox) layout.findViewById(R.id.option_two_cb);
                final CheckBox cbThree = (CheckBox) layout.findViewById(R.id.option_three_cb);

                cbOne.setText(optionsArray[0]);
                cbTwo.setText(optionsArray[1]);
                cbThree.setText(optionsArray[2]);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String answer = "";
                        if((cbOne).isChecked()){
                            answer += cbOne.getText().toString();
                        }
                        if((cbTwo).isChecked()){
                            answer += cbTwo.getText().toString();

                        }
                        if((cbThree).isChecked()){
                            answer += cbThree.getText().toString();

                        }
                        if(!answer.equals(Constants.EMPTY_ANSWER)){
                            saveAnswerAndMoveNext(answer, position);
                        }else{
                            Snackbar.make(v, "Select your option...", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }
                });

            }
            else if(customPagerEnum.getType().equals(Constants.EDITTEXT_CODE)){

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editText = (EditText) layout.findViewById(R.id.etUsername);
                        String answer = editText.getText().toString();
                        if(!answer.equals(Constants.EMPTY_ANSWER)){
                            saveAnswerAndMoveNext(answer, position);
                        }
                        else{
                            Snackbar.make(v, "Write your answer...", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }
                });

            }

            collection.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return CustomPagerEnum.values().length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

}
