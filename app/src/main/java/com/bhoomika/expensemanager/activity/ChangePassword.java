package com.bhoomika.expensemanager.activity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.baseclasses.BaseActivity;
import com.bhoomika.expensemanager.utils.AppUtils;
import com.bhoomika.expensemanager.utils.Preference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePassword extends BaseActivity {

    @BindView(R.id.etCurrantPassword)
    EditText etCurrantPassword;
    @BindView(R.id.etNewPassword)
    EditText etNewPassword;
    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;
    @BindView(R.id.tvSave)
    TextView tvSave;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        init();
    }

    @OnClick({R.id.tvSave})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tvSave:
                if (AppUtils.getText(etCurrantPassword).isEmpty()) {
                    AppUtils.showToast(this, "please enter Currant password");
                }

                if (AppUtils.getText(etNewPassword).isEmpty()) {
                    AppUtils.showToast(this, "please enter New password");
                }
                if (AppUtils.getText(etConfirmPassword).isEmpty()) {
                    AppUtils.showToast(this, "please enter Confirm password");
                }
                if (AppUtils.getText(etCurrantPassword).equalsIgnoreCase(Preference.getInstance(this).getPassword())) {
                    if (AppUtils.getText(etConfirmPassword).equals(AppUtils.getText(etNewPassword))) {
                        Preference.getInstance(this).setPassword(etNewPassword.getText().toString());
                        AppUtils.showToast(this, "Your Password change Successfully");
                        finish();
                    } else {
                        AppUtils.showToast(this, "Your new Password and Confirm Password not Match");
                    }
                } else {
                    AppUtils.showToast(this, "Your Currant password not match old Password");
                }


                break;

        }
    }

    private void init() {
        etNewPassword.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        etCurrantPassword.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        etConfirmPassword.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
    }
}
