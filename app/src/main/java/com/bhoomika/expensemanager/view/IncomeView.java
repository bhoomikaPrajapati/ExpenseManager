package com.bhoomika.expensemanager.view;

import com.bhoomika.expensemanager.baseclasses.BaseView;

/**
 Created by bhoomika on 27/1/17.
 */

public interface IncomeView extends BaseView {
    public void onSuccessAddIncome(String message);

    public void onFailedAddIncome(String message);
}
