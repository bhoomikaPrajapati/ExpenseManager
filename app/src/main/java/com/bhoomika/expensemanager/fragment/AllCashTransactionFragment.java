package com.bhoomika.expensemanager.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.adapter.AdapterAllTransaction;
import com.bhoomika.expensemanager.baseclasses.BaseFragment;
import com.bhoomika.expensemanager.baseclasses.BaseRecyclerAdapter;
import com.bhoomika.expensemanager.database.TransactionTable;
import com.bhoomika.expensemanager.view.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bhoomika.expensemanager.database.Query.getAllCashData;
import static com.bhoomika.expensemanager.database.Query.totalAmountCash;


/**
 * Created by bhoomika on 23/6/17.
 */


public class AllCashTransactionFragment extends BaseFragment {

    @BindView(R.id.rvRecyclerView)
    RecyclerView rvRecyclerView;
    @BindView(R.id.tvTotalBalance)
    TextView tvTotalBalance;
    private static final int TOTAL_VIEW_RECORD = 10;


    ArrayList<TransactionTable> transactionTableArrayList = new ArrayList<>();
    ArrayList<TransactionTable> allCashArrayList = new ArrayList<>();
    private AdapterAllTransaction adapterAllTransaction;

    private int totalrecord;
    private int displayRecord;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_cash_transaction, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getAllCashRecord();
        init();
        setLoadMore();

    }

    private void init() {
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        transactionTableArrayList.addAll(getCashRecord(0, TOTAL_VIEW_RECORD));
        adapterAllTransaction = new AdapterAllTransaction(rvRecyclerView, transactionTableArrayList, getActivity());
        adapterAllTransaction.setRecycleOnItemClickListener(mRecycleOnItemClickListener);
        rvRecyclerView.setAdapter(adapterAllTransaction);

    }


    private void setLoadMore() {
        totalrecord=getAllCashData().size();
        adapterAllTransaction.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                if (transactionTableArrayList.size() < totalrecord) {
                /*    transactionTableArrayList.add(null);
                    adapterAllTransaction.notifyItemInserted(transactionTableArrayList.size() - 1);*/
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           /* transactionTableArrayList.remove(transactionTableArrayList.size() - 1);
                            adapterAllTransaction.notifyItemRemoved(transactionTableArrayList.size());*/

                            //Generating more data
                            int index = transactionTableArrayList.size();
                            int end = index + TOTAL_VIEW_RECORD;

                            transactionTableArrayList.addAll(getCashRecord(index, end));
                            adapterAllTransaction.notifyDataSetChanged();
                            adapterAllTransaction.setLoaded();
                        }
                    }, 1000);
                } else {
                    Toast.makeText(getContext(), "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
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


    private void getAllCashRecord() {

        if (getAllCashData().size() > 0) {
            allCashArrayList.clear();
            allCashArrayList.addAll(getAllCashData());
            tvTotalBalance.setText(""+totalAmountCash());

        } else {
            transactionTableArrayList.clear();
            tvTotalBalance.setText("0");
        }

    }



    public List<TransactionTable> getCashRecord(int start, int end) {


       totalrecord =getAllCashData().size() ;
        if (totalrecord < end) {
            end = totalrecord;
        }

        if (allCashArrayList.size() > 0)
        {
            return allCashArrayList.subList(start, end);
        }


            return allCashArrayList;




    }
}
