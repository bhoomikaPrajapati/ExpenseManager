package com.bhoomika.expensemanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.baseclasses.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by bhoomika on 30/1/17.
 */

public class AllTransactionFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        AllCashTransactionFragment allCashTransactionFragment = new AllCashTransactionFragment();
        replaceFragment(allCashTransactionFragment, true, null);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, Bundle bundle) {

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rlContainer, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.commit();
    }




 /*   private void getCardData() {
        if (Query.getAllBankData().size() > 0) {
            mScreen = mCardRecord;
            totalrecord = Query.totalCardRecord();
            transactionTableArrayList.clear();
            transactionTableArrayList.addAll(Query.getAllBankData());
            tvTotalBalance.setText(String.valueOf(Query.totalAmountCard()));

            adapterAllTransaction.notifyDataSetChanged();
        } else {
            transactionTableArrayList.clear();
            tvTotalBalance.setText("0");
            adapterAllTransaction.notifyDataSetChanged();
        }

    }



    private void getFutureCashData() {
        if (Query.getAllFutureCashData().size() > 0) {

            transactionTableArrayList.clear();
            totalrecord = Query.totalFCashRecord();
            mScreen = mFCashRecord;
            int size = Query.getAllFutureCashData().size();
            for (int i = 0; i < size; i++) {
                FTransaction fTransaction = Query.getAllFutureCashData().get(i);
                TransactionTable transactionTable = new TransactionTable();
                transactionTable.setDate(AppUtils.stringToDate(fTransaction.getF_date()));
                transactionTable.setAmountType(fTransaction.getAmounttype());
                transactionTable.setAmount(fTransaction.getAmount());
                transactionTable.setDescription(fTransaction.getDescription());
                transactionTableArrayList.add(transactionTable);
            }

            tvTotalBalance.setText(String.valueOf(Query.totalFutureAmountCash()));

            adapterAllTransaction.notifyDataSetChanged();
        } else {
            transactionTableArrayList.clear();
            tvTotalBalance.setText("0");
            adapterAllTransaction.notifyDataSetChanged();
        }
    }

    private void getFutureBankData() {
        if (Query.getAllFutureBankData().size() > 0) {
            mScreen = mFCardRecord;
            transactionTableArrayList.clear();
            int size = Query.getAllFutureBankData().size();
            totalrecord = Query.totalFCardRecord();
            for (int i = 0; i < size; i++) {
                FTransaction fTransaction = Query.getAllFutureBankData().get(i);
                TransactionTable transactionTable = new TransactionTable();
                transactionTable.setDate(AppUtils.stringToDate(fTransaction.getF_date()));
                transactionTable.setAmountType(fTransaction.getAmounttype());
                transactionTable.setAmount(fTransaction.getAmount());
                transactionTable.setDescription(fTransaction.getDescription());
                transactionTableArrayList.add(transactionTable);
            }

            tvTotalBalance.setText(String.valueOf(Query.totalFutureAmountBank()));

            adapterAllTransaction.notifyDataSetChanged();
        } else {
            transactionTableArrayList.clear();
            tvTotalBalance.setText("0");
            adapterAllTransaction.notifyDataSetChanged();
        }
    }

    private void init() {

        rvRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterAllTransaction = new AdapterAllTransaction(rvRecyclerView, transactionTableArrayList, getActivity());
        adapterAllTransaction.setRecycleOnItemClickListener(mRecycleOnItemClickListener);
        ((SimpleItemAnimator) rvRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        rvRecyclerView.setAdapter(adapterAllTransaction);

    }


    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            //  setDatafromDataBase();
            //  TODO get Data From Data Base
        }
    };

    private BaseRecyclerAdapter.RecycleOnItemClickListener mRecycleOnItemClickListener =
            new BaseRecyclerAdapter.RecycleOnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                }
            };


*/

}

