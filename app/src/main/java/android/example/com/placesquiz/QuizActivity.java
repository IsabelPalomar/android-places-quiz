package android.example.com.placesquiz;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.example.com.placesquiz.utils.Constants;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
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

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void submitResults(View view) {
        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
        for (String a: answers) {
            if(a != null)
                System.out.println(a.toString());
        }
        //startActivity(i);
    }

    public void onRadioButtonClicked(View view) {

        RadioGroup rGroup = (RadioGroup) findViewById(R.id.radio_button_group);
        int selectedValueId = rGroup.getCheckedRadioButtonId();
        String answer = ((RadioButton)findViewById(rGroup.getCheckedRadioButtonId())).getText().toString();

        if(answer!= null){
            answers[viewPager.getCurrentItem()] = answer;
            moveNextPage();
        }
    }

    public void onCheckBoxClicked(View view) {

        String answer = "";
        CheckBox option1 = (CheckBox) findViewById(R.id.option_one_cb);
        CheckBox option2 = (CheckBox) findViewById(R.id.option_two_cb);
        CheckBox option3 = (CheckBox) findViewById(R.id.option_three_cb);


        if((option1).isChecked()){
            answer += option1.getText().toString();
        }

        if((option2).isChecked()){
            answer += option2.getText().toString();

        }

        if((option3).isChecked()){
            answer += option3.getText().toString();

        }

        if(answer!= null){
            answers[viewPager.getCurrentItem()] = answer;
            moveNextPage();
        }

    }


    public void moveNextPage() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    public void onEditText(View view) {
        EditText editText = (EditText) findViewById(R.id.etUsername);
        String answer = editText.getText().toString();
        System.out.println(answer);

        if(answer!= null){
            answers[viewPager.getCurrentItem()] = answer;
            moveNextPage();
        }
    }


    public class CustomPagerAdapter extends PagerAdapter {

        private Context mContext;

        public CustomPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {

            Resources r = getResources();
            String[] optionsArray = null;

            CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
            LayoutInflater inflater = LayoutInflater.from(mContext);
            ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(), collection, false);

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


            if(customPagerEnum.getType().equals(Constants.RADIOBUTTON_CODE)){
                RadioButton rbOne = (RadioButton) layout.findViewById(R.id.option_one);
                RadioButton rbTwo = (RadioButton) layout.findViewById(R.id.option_two);
                RadioButton rbThree = (RadioButton) layout.findViewById(R.id.option_three);

                rbOne.setText(optionsArray[0]);
                rbTwo.setText(optionsArray[1]);
                rbThree.setText(optionsArray[2]);
            }
            else if(customPagerEnum.getType().equals(Constants.CHECKBOX_CODE)){

                CheckBox cbOne = (CheckBox) layout.findViewById(R.id.option_one_cb);
                CheckBox cbTwo = (CheckBox) layout.findViewById(R.id.option_two_cb);
                CheckBox cbThree = (CheckBox) layout.findViewById(R.id.option_three_cb);

                cbOne.setText(optionsArray[0]);
                cbTwo.setText(optionsArray[1]);
                cbThree.setText(optionsArray[2]);

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

    public enum CustomPagerEnum {

        QUESTION1(Constants.RADIOBUTTON_CODE, R.layout.content_quiz_radiobutton, R.drawable.london, R.array.question1_options),
        QUESTION2(Constants.CHECKBOX_CODE, R.layout.content_quiz_checkbox, R.drawable.amsterdam, R.array.question2_options),
        QUESTION3(Constants.EDITTEXT_CODE, R.layout.content_quiz_edittext, R.drawable.paris, Constants.NO_VALUE),
        QUESTION4(Constants.RADIOBUTTON_CODE, R.layout.content_quiz_radiobutton, R.drawable.hawaii, R.array.question4_options),
        QUESTION5(Constants.RADIOBUTTON_CODE, R.layout.content_quiz_radiobutton, R.drawable.machu_pichu, R.array.question5_options),
        QUESTION6(Constants.EDITTEXT_CODE, R.layout.content_quiz_edittext, R.drawable.new_york, Constants.NO_VALUE),
        QUESTION7(Constants.RADIOBUTTON_CODE, R.layout.content_quiz_radiobutton, R.drawable.sydney, R.array.question7_options),
        QUESTION8(Constants.RADIOBUTTON_CODE, R.layout.content_quiz_radiobutton, R.drawable.venice, R.array.question8_options),
        QUESTION9(Constants.RADIOBUTTON_CODE, R.layout.content_quiz_radiobutton, R.drawable.chichen_itza, R.array.question9_options),
        SUBMIT(Constants.NO_CODE, R.layout.content_quiz_submit, Constants.NO_VALUE, Constants.NO_VALUE);

        private String mType;
        private int mLayoutResId;
        private int mImageResId;
        private int mQuestionOptions;

        CustomPagerEnum(String type, int layoutResId, int imageResId, int questionOptions) {
            mType = type;
            mLayoutResId = layoutResId;
            mImageResId = imageResId;
            mQuestionOptions = questionOptions;
        }

        public String getType() {
            return mType;
        }
        public int getLayoutResId() {
            return mLayoutResId;
        }
        public int getImageResId() {
            return mImageResId;
        }
        public int getQuestionOptions() {
            return mQuestionOptions;
        }


    }

}
