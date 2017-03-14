package com.bhoomika.expensemanager.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bhoomika.expensemanager.Balance;
import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.baseclasses.BaseActivity;
import com.bhoomika.expensemanager.customviews.BottomNavigationViewHelper;
import com.bhoomika.expensemanager.database.Query;
import com.bhoomika.expensemanager.fragment.AllTransactionFragment;
import com.bhoomika.expensemanager.fragment.ExpensesFragment;
import com.bhoomika.expensemanager.fragment.IncomeFragment;
import com.bhoomika.expensemanager.fragment.SettingFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends BaseActivity {


    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.tvBalance)
    TextView tvBalance;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    private void init() {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);
        setBalance(Query.totalAmountCash(), Query.totalAmountCard());
        //Place first fragment
        replaceFragment(new IncomeFragment(), false, null);}

    private void setListener() {
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    Fragment fragment = null;


                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_income:
                                fragment = new IncomeFragment();
                                tvBalance.setVisibility(View.VISIBLE);

                                break;

                            case R.id.action_expenses:
                                fragment = new ExpensesFragment();
                                tvBalance.setVisibility(View.VISIBLE);
                                break;

                            case R.id.action_all_transaction:
                                fragment = new AllTransactionFragment();
                                tvBalance.setVisibility(View.GONE);
                                break;

                            case R.id.action_setting:
                               fragment=new SettingFragment();
                                tvBalance.setVisibility(View.GONE);
                                break;

                        }
                        if (fragment != null) {
                            Fragment currentFragment = getCurrentFragment();
                            if (!currentFragment.getClass()
                                    .getSimpleName()
                                    .equalsIgnoreCase(fragment.getClass().getSimpleName())) {
                                getSupportFragmentManager().popBackStackImmediate(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                replaceFragment(fragment, true, null);
                            }

                            return true;

                        }
                        return false;
                    }


                });


    }

    private void setBalance(float cash, float card) {

        tvBalance.setText(getString(R.string.lable_cash_balance) + cash + getString(R.string.lable_bank_balance) + card);
    }

    @Subscribe
    public void setTvBalanceData(Balance balanceData) {
        setBalance(balanceData.getCash(), balanceData.getCard());
    }

    private Fragment  getCurrentFragment() {

        Fragment framgent = getSupportFragmentManager().findFragmentById(R.id.frameContainer);
        return framgent;
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, Bundle bundle) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.commit();
    }

    public void popFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStackImmediate();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this); // registering the bus
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this); // un-registering the bus
        super.onStop();
    }
}