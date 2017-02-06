package com.bhoomika.expensemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.baseclasses.BaseActivity;
import com.bhoomika.expensemanager.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
  Created by bhoomika on 25/1/17.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.link_signup)
    TextView linkSignup;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btn_login, R.id.link_signup})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
                break;
            case R.id.link_signup:
                AppUtils.showToast(LoginActivity.this, "signUp");
                break;
            default:
                break;

        }
    }





}


