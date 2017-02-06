package com.bhoomika.expensemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.baseclasses.BaseRecyclerAdapter;
import com.bhoomika.expensemanager.database.TransactionTable;
import com.bhoomika.expensemanager.utils.AppUtils;

import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by bhoomika on 30/1/17.
 */

public class AdapterAllTransaction extends BaseRecyclerAdapter<AdapterAllTransaction.DataObjectHolder, TransactionTable> {

    private ArrayList<TransactionTable> transactionTableArrayList;

    public AdapterAllTransaction(ArrayList<TransactionTable> transactionTableArrayList, Context context) {
        super(transactionTableArrayList);
        this.transactionTableArrayList = transactionTableArrayList;

    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_all_transaction, parent, false);
        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        TransactionTable transactionTable = transactionTableArrayList.get(position);
        holder.tvDate.setText(AppUtils.getDate(transactionTable.getDate().getTime()));
        holder.tvAmount.setText(NumberFormat.getIntegerInstance().format(transactionTable.getAmount()));
        holder.tvDescription.setText(transactionTable.getDescription());
        holder.tvSign.setText(transactionTable.getSign());


    }

    @Override
    public int getItemCount() {
        return transactionTableArrayList.size();

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
}