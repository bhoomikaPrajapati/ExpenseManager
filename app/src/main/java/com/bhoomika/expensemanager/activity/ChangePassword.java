package com.bhoomika.expensemanager.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.baseclasses.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

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

    private void init() {

    }
}
