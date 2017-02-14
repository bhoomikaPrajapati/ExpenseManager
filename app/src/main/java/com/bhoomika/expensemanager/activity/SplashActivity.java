package com.bhoomika.expensemanager.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.baseclasses.PassCodeView;


public class SplashActivity extends AppCompatActivity {
    private final String PASSCODE = "3322";
    private PassCodeView passCodeView;
    private TextView promptView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void bindEvents() {
        passCodeView.setOnTextChangeListener(new PassCodeView.TextChangeListener() {
            @Override
            public void onTextChanged(String text) {
                if (text.length() == 4) {
                    if (text.equals(PASSCODE)) {
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        passCodeView.setError(true);
                    }
                }
            }
        });
    }

    private void init() {
        passCodeView = (PassCodeView) findViewById(R.id.pass_code_view);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");
        promptView = (TextView) findViewById(R.id.promptview);
        //  passCodeView.setTypeFace(typeFace);

        passCodeView.setEmptyDrawable(R.drawable.empty_dot);
        passCodeView.setFilledDrawable(R.drawable.filled_dot);
        promptView.setTypeface(typeFace);
        bindEvents();

    }
}
