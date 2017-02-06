package com.bhoomika.expensemanager.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bhoomika.expensemanager.R;


/**
 * Created by bhoomika on 27/1/17.
 */

public class AdapterSelectAmountType extends BaseAdapter {
    private LayoutInflater inflater;
    private String[] amountType;

    public AdapterSelectAmountType(Context context, String[] amountType) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.amountType = amountType;
    }

    @Override
    public int getCount() {
        return amountType.length;
    }

    @Override
    public Object getItem(int position) {
        return amountType[position];
    }

    public int getPositionForSelection(String value) {
        if (!TextUtils.isEmpty(value)) {
            int i = 0;
            for (String s : amountType) {
                if (s.equalsIgnoreCase(value)) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_spinner, parent, false);
        TextView tvAmountType = (TextView) view.findViewById(R.id.textView);
        tvAmountType.setText(amountType[position]);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_spinner_data, parent, false);
        TextView tvAmountType = (TextView) view.findViewById(R.id.textView);
        tvAmountType.setText(amountType[position]);
        return view;
    }
}

