package com.bhoomika.expensemanager.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.baseclasses.PassCodeView;
import com.bhoomika.expensemanager.utils.Preference;


public class SplashActivity extends AppCompatActivity {
    private final String PASSCODE = "3322";
    private PassCodeView passCodeView;
    private TextView promptView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        displayDialog();
    }

    private void displayDialog() {
        if (Preference.getInstance(this).getPassword().isEmpty()) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(SplashActivity.this);
            alertDialog.setCancelable(false);
            alertDialog.setTitle("Passcode Dialog");
            alertDialog.setMessage("Your password  = " + PASSCODE);
            alertDialog.setPositiveButton(R.string.lbl_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
            Preference.getInstance(this).setPassword(PASSCODE);
        }

    }

    private void bindEvents() {
        passCodeView.setOnTextChangeListener(new PassCodeView.TextChangeListener() {
            @Override
            public void onTextChanged(String text) {
                if (text.length() == 4) {
                    if (text.equals(Preference.getInstance(SplashActivity.this).getPassword())) {
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
