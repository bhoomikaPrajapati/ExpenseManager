package com.bhoomika.expensemanager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bhoomika.expensemanager.Balance;
import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.adapter.AdapterSelectAmountType;
import com.bhoomika.expensemanager.baseclasses.BaseActivity;
import com.bhoomika.expensemanager.baseclasses.MyEditTextDatePicker;
import com.bhoomika.expensemanager.database.Query;
import com.bhoomika.expensemanager.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddFutureIncome extends BaseActivity {

    @BindView(R.id.inputDate)
    EditText inputDate;
    @BindView(R.id.inputAmount)
    EditText inputAmount;
    @BindView(R.id.etIncomeDescription)
    EditText etIncomeDescription;
    @BindView(R.id.tvSave)
    TextView tvSave;
    @BindView(R.id.tvSetAlarm)
    TextView tvSetAlarm;
    @BindView(R.id.spinner)
    Spinner spinner;
    private AdapterSelectAmountType amountTypeAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_future_income);
        ButterKnife.bind(this);
        setSpinnerData();

    }

    public void showDatePickerDialog(View view) {
        new MyEditTextDatePicker(AddFutureIncome.this, R.id.inputDate);
    }

    private void setSpinnerData() {

        String[] amountType = getResources().getStringArray(R.array.amount_type);
        if (amountType.length > 0) {
            this.amountTypeAdapter = new AdapterSelectAmountType(this, amountType);
            spinner.setAdapter(this.amountTypeAdapter);
        }


    }


    @OnClick({R.id.tvSetAlarm, R.id.tvSave})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tvSetAlarm:

                break;
            case R.id.tvSave: {

                int type = spinner.getSelectedItemPosition();
                if (AppUtils.getText(inputAmount).isEmpty()) {
                    AppUtils.showValidation(this, getString(R.string.msg_enter_ammount));
                    return;
                }
                if (type == 0) {
                    AppUtils.showValidation(this,getString(R.string.msg_select_amount_type));
                } else {
                    boolean success = Query.futureIncomeTransaction(AppUtils.getText(inputAmount), type, AppUtils.getText(etIncomeDescription), AppUtils.getText(inputDate));
                    if (success) {
                        AppUtils.showValidation(this, getString(R.string.msg_transaction_added));
                        etIncomeDescription.setText("");
                        inputAmount.setText("");
                        spinner.setSelection(0);
                        inputDate.setText("");
                        EventBus.getDefault().postSticky(new Balance(Query.totalAmountCash(), Query.totalAmountCard()));
                    }
                }

            }
            default:
                break;

        }
    }

}
