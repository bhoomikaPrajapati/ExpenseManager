package com.bhoomika.expensemanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.activity.CustomSwipeRefreshLayout;
import com.bhoomika.expensemanager.adapter.AdapterAllTransaction;
import com.bhoomika.expensemanager.adapter.AdapterSelectAmountType;
import com.bhoomika.expensemanager.baseclasses.BaseFragment;
import com.bhoomika.expensemanager.baseclasses.BaseRecyclerAdapter;
import com.bhoomika.expensemanager.baseclasses.EndlessRecyclerViewScrollListener;
import com.bhoomika.expensemanager.database.FTransaction;
import com.bhoomika.expensemanager.database.Query;
import com.bhoomika.expensemanager.database.TransactionTable;
import com.bhoomika.expensemanager.utils.AppUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bhoomika.expensemanager.database.Query.getAllCashData;

/**
 * Created by bhoomika on 30/1/17.
 */

public class AllTransactionFragment extends BaseFragment {


    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.rvRecyclerView)
    RecyclerView rvRecyclerView;
    @BindView(R.id.tvTotalBalance)
    TextView tvTotalBalance;
    @BindView(R.id.swipeRefreshLayout)
    CustomSwipeRefreshLayout swipeRefreshLayout;
    int mRecord = 5;
    int mScreen;
    int mCashRecord = 1;
    int mCardRecord = 2;
    int mFCashRecord = 3;
    int mFCardRecord = 4;


    ArrayList<TransactionTable> transactionTableArrayList = new ArrayList<>();
    private AdapterAllTransaction adapterAllTransaction;
    EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    private int totalrecord;


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
        setLitener();
        setSpinnerData();
        setDatafromDataBase();

    }


    private void setSpinnerData() {

        String[] amountType = getResources().getStringArray(R.array.type);
        if (amountType.length > 0) {
            AdapterSelectAmountType adapterSelectAmountType = new AdapterSelectAmountType(getActivity(), amountType);
            spinner.setAdapter(adapterSelectAmountType);
        }


    }


    private void setLitener() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int postion, long arg3) {

                switch (postion) {
                    //All cash Transaction
                    case 0:
                        getCashData(mRecord);

                        break;
                    //All Bank Transaction
                    case 1:
                        getCardData(mRecord);
                        break;
                    //All cash future Transaction
                    case 2:
                        getFutureCashData(mRecord);
                        break;

                    case 3:
                        getFutureBankData(mRecord);
                        break;
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {


            }
        });
    }

    private void getCardData(int mRecord) {
        if (Query.getAllBankData(mRecord).size() > 0) {
            mScreen = mCardRecord;
            totalrecord = Query.totalCardRecord();
            transactionTableArrayList.clear();
            transactionTableArrayList.addAll(Query.getAllBankData(mRecord));
            tvTotalBalance.setText(String.valueOf(Query.totalAmountCard()));
            swipeRefreshLayout.setRefreshing(false);
            adapterAllTransaction.notifyDataSetChanged();
        } else {
            transactionTableArrayList.clear();
            tvTotalBalance.setText("0");
            adapterAllTransaction.notifyDataSetChanged();
        }

    }

    private void getCashData(int mRecord) {
        if (getAllCashData(mRecord).size() > 0) {
            mScreen = mCashRecord;
            totalrecord = Query.totalCashRecord();
            transactionTableArrayList.clear();
            transactionTableArrayList.addAll(getAllCashData(mRecord));
            tvTotalBalance.setText(String.valueOf(Query.totalAmountCash()));
            swipeRefreshLayout.setRefreshing(false);
            adapterAllTransaction.notifyDataSetChanged();
        } else {
            transactionTableArrayList.clear();
            tvTotalBalance.setText("0");
            adapterAllTransaction.notifyDataSetChanged();
        }
    }

    private void getFutureCashData(int mRecord) {
        if (Query.getAllFutureCashData(mRecord).size() > 0) {

            transactionTableArrayList.clear();
            totalrecord = Query.totalFCashRecord();
            mScreen = mFCashRecord;
            int size = Query.getAllFutureCashData(mRecord).size();
            for (int i = 0; i < size; i++) {
                FTransaction fTransaction = Query.getAllFutureCashData(mRecord).get(i);
                TransactionTable transactionTable = new TransactionTable();
                transactionTable.setDate(AppUtils.stringToDate(fTransaction.getF_date()));
                transactionTable.setAmountType(fTransaction.getAmounttype());
                transactionTable.setAmount(fTransaction.getAmount());
                transactionTable.setDescription(fTransaction.getDescription());
                transactionTableArrayList.add(transactionTable);
            }

            tvTotalBalance.setText(String.valueOf(Query.totalFutureAmountCash()));
            swipeRefreshLayout.setRefreshing(false);
            adapterAllTransaction.notifyDataSetChanged();
        } else {
            transactionTableArrayList.clear();
            tvTotalBalance.setText("0");
            adapterAllTransaction.notifyDataSetChanged();
        }
    }

    private void getFutureBankData(int mRecord) {
        if (Query.getAllFutureBankData(mRecord).size() > 0) {
            mScreen = mFCardRecord;
            transactionTableArrayList.clear();
            int size = Query.getAllFutureBankData(mRecord).size();
            totalrecord = Query.totalFCardRecord();
            for (int i = 0; i < size; i++) {
                FTransaction fTransaction = Query.getAllFutureBankData(mRecord).get(i);
                TransactionTable transactionTable = new TransactionTable();
                transactionTable.setDate(AppUtils.stringToDate(fTransaction.getF_date()));
                transactionTable.setAmountType(fTransaction.getAmounttype());
                transactionTable.setAmount(fTransaction.getAmount());
                transactionTable.setDescription(fTransaction.getDescription());
                transactionTableArrayList.add(transactionTable);
            }

            tvTotalBalance.setText(String.valueOf(Query.totalFutureAmountBank()));
            swipeRefreshLayout.setRefreshing(false);
            adapterAllTransaction.notifyDataSetChanged();
        } else {
            transactionTableArrayList.clear();
            tvTotalBalance.setText("0");
            adapterAllTransaction.notifyDataSetChanged();
        }
    }

    private void init() {

        swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterAllTransaction = new AdapterAllTransaction(transactionTableArrayList, getActivity());
        adapterAllTransaction.setRecycleOnItemClickListener(mRecycleOnItemClickListener);
        ((SimpleItemAnimator) rvRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        rvRecyclerView.setAdapter(adapterAllTransaction);
        endlessRecyclerViewScrollListener =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) rvRecyclerView.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                        endlessRecyclerViewScrollListener.setLoading(true);
                        if (totalrecord>mRecord)
                            mRecord=mRecord+mRecord;
                            switch (mScreen) {
                                case 1:
                                    getCashData(mRecord);
                                    break;
                                case 2:
                                    getCardData(mRecord);
                                    break;
                                case 3:
                                    getFutureCashData(mRecord);
                                    break;
                                case 4:
                                    getFutureBankData(mRecord);
                                    break;
                                default:
                                    break;

                            }

                        //  setDatafromDataBase();
                        //  TODO get Data From Data Base
                    }
                }.setVisibleThreshold(10);
        rvRecyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);
    }


    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            endlessRecyclerViewScrollListener.setLoading(false);
            swipeRefreshLayout.setRefreshing(true);
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

    private void setDatafromDataBase() {

        if (getAllCashData(mRecord).size() > 0) {
            transactionTableArrayList.addAll(getAllCashData(mRecord));
            tvTotalBalance.setText(String.valueOf(Query.totalAmountCash()));
            swipeRefreshLayout.setRefreshing(false);
        } else {
            transactionTableArrayList.clear();
            tvTotalBalance.setText("");
        }


    }


}

