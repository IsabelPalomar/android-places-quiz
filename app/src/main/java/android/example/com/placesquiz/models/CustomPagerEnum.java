package android.example.com.placesquiz.models;

import android.example.com.placesquiz.R;
import android.example.com.placesquiz.utils.Constants;

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

