package com.bhoomika.expensemanager.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bhoomika.expensemanager.R;

import in.arjsna.passcodeview.PassCodeView;


public class SplashActivity extends AppCompatActivity {
    PassCodeView passCodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        passCodeView  = (PassCodeView) findViewById(R.id.pass_code_view);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");

/**
 *Set TypeFace for the font in keys of keypad
 */
        passCodeView.setTypeFace(typeFace);

/**
 * Set color for the keypad text
 * @param color - Resource id of the color to be set
 */
        //passCodeView.setKeyTextColor(getColor(R.color.black));

/**
 * Set size of keypad text
 * @param size - Text size value to be set
 */
        passCodeView.setKeyTextSize(30);

/**
 * Set passcode digit lenght
 * @param length - digit length to be set
 */
        passCodeView.setDigitLength(4);


/**
 * Set current passcode text
 * @param code - {@code String} passcode string to be set
 */



/**
 * Reset the code to empty
 */
      //  passCodeView.reset();

/**
 * Set drawable for empty digits programmatically
 */
      //  passCodeView.setEmptyDrawable(R.drawable.empty_dot);

/**
 * Set drawable for filled digits programmatically
 */
       // passCodeView.setFilledDrawable(R.drawable.filled_dot);

/**
 * Attach {@code TextChangeListener} to get notified on text changes
 * @param listener - {@Code TextChangeListener} object to be attached and notified
 */
       /* passCodeView.setOnTextChangeListener(new PassCodeView.TextChangeListener() {
            @Override
            public void onTextChanged(String text) {
                Log.i("Passcode", "text");
            }
        });*/
    }
}
