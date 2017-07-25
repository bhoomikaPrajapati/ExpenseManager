package com.bhoomika.expensemanager.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.baseclasses.BaseRecyclerAdapter;
import com.bhoomika.expensemanager.database.TransactionTable;
import com.bhoomika.expensemanager.utils.AppUtils;
import com.bhoomika.expensemanager.view.OnLoadMoreListener;

import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by bhoomika on 30/1/17.
 */

public class AdapterAllTransaction extends BaseRecyclerAdapter<BaseRecyclerAdapter.ViewHolder, TransactionTable> {

    private ArrayList<TransactionTable> transactionTableArrayList;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public AdapterAllTransaction(RecyclerView recyclerView, ArrayList<TransactionTable> transactionTableArrayList, Context context) {
        super(transactionTableArrayList);
        this.transactionTableArrayList = transactionTableArrayList;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount < (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });

    }



    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }



    @Override
    public int getItemViewType(int position) {
        return transactionTableArrayList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    @Override
    public BaseRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_all_transaction, parent, false);
            return new DataObjectHolder(view);

        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }




   //** @Override
    public void onBindViewHolder(BaseRecyclerAdapter.ViewHolder holderGroup, int position) {

        if (holderGroup instanceof DataObjectHolder) {
            DataObjectHolder holder = (DataObjectHolder) holderGroup;
            TransactionTable transactionTable = transactionTableArrayList.get(position);
            holder.tvDate.setText(AppUtils.getDate(transactionTable.getDate().getTime()));
            holder.tvAmount.setText(NumberFormat.getIntegerInstance().format(transactionTable.getAmount()));
            holder.tvDescription.setText(transactionTable.getDescription());
            holder.tvSign.setText(transactionTable.getSign());

        } else if (holderGroup instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holderGroup;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return transactionTableArrayList == null ? 0 : transactionTableArrayList.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    class DataObjectHolder extends BaseRecyclerAdapter.ViewHolder {
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvSign)
        TextView tvSign;
        @BindView(R.id.tvAmount)
        TextView tvAmount;

        DataObjectHolder(View itemView) {
            super(itemView);
            clickableViews(itemView);

        }
    }


    private class LoadingViewHolder extends BaseRecyclerAdapter.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }
}