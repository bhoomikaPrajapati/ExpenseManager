package com.demorjjavaandlambdaexpression.baseclasses;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by  bhoomika on 19/12/16.
 */
public abstract class BaseRecyclerAdapter<T extends BaseRecyclerAdapter.ViewHolder, M>
        extends RecyclerView.Adapter<T> {

    private List<M> data;
    private RecycleOnItemClickListener mRecycleOnItemClickListener;

    public BaseRecyclerAdapter(List<M> data) {
        this.data = data;
    }

    public interface RecycleOnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        //put here clickable views list
        public void clickableViews(View... views) {
            for (View view : views) {
                view.setOnClickListener(mOnClickListener);
            }
        }

        private View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecycleOnItemClickListener != null) {
                    mRecycleOnItemClickListener.onItemClick(v, getLayoutPosition());
                }
            }
        };
    }

    public BaseRecyclerAdapter setRecycleOnItemClickListener(
            RecycleOnItemClickListener mRecycleOnItemClickListener) {
        this.mRecycleOnItemClickListener = mRecycleOnItemClickListener;
        return this;
    }

    public M getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public final List<M> getData() {
        return data;
    }
}
