package com.demorjjavaandlambdaexpression.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demorjjavaandlambdaexpression.R;
import com.demorjjavaandlambdaexpression.baseclasses.BaseRecyclerAdapter;
import com.demorjjavaandlambdaexpression.database.TransactionTable;
import com.demorjjavaandlambdaexpression.utils.AppUtils;

import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by bhoomika on 30/1/17.
 */

public class AdapterAllTransaction extends BaseRecyclerAdapter<AdapterAllTransaction.DataObjectHolder, TransactionTable> {


    private Context context;

    private ArrayList<TransactionTable> transactionTableArrayList;

    public AdapterAllTransaction(ArrayList<TransactionTable> transactionTableArrayList, Context context) {
        super(transactionTableArrayList);
        this.transactionTableArrayList = transactionTableArrayList;
        this.context = context;

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