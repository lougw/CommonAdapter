
package com.lougw.commonadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.RecyclerListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

import com.lougw.commonadapter.holder.BaseRecyclerViewHolder;
import com.lougw.commonadapter.interfaces.IData;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> implements
        RecyclerListener, IData<T> {
    protected Context mContext;
    protected List<T> mData;
    protected RecyclerView mRecyclerView;

    public BaseRecyclerAdapter(Context context, RecyclerView recyclerView) {
        mData = new ArrayList<T>();
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        if (null != recyclerView) {
            recyclerView.setRecyclerListener(this);
        }
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        // TODO Auto-generated method stub
        super.onViewRecycled(holder);
        if (null != holder && holder instanceof BaseRecyclerViewHolder) {
            ((BaseRecyclerViewHolder) holder).recycle();
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T data = mData.get(position);
        BaseRecyclerViewHolder<T> viewHolder = (BaseRecyclerViewHolder<T>) holder;
        viewHolder.setData(data, position);
    }


    @Override
    public abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);


    @Override
    public List<T> getData() {
        return mData;
    }

    @Override
    public void add(T item) {
        mData.add(item);
        notifyItemInserted(mData.size());
    }

    @Override
    public void addAll(List<T> list) {
        mData.addAll(list);
        notifyItemRangeInserted(mData.size() - list.size(), list.size());
    }

    @Override
    public void set(T oldItem, T newItem) {
        set(mData.indexOf(oldItem), newItem);
    }

    @Override
    public void set(int index, T item) {
        mData.set(index, item);
        notifyItemChanged(index);
    }

    @Override
    public void remove(T item) {
        final int position = mData.indexOf(item);
        mData.remove(item);
        notifyItemRemoved(position);
    }

    @Override
    public void remove(int index) {
        mData.remove(index);
        notifyItemRemoved(index);
    }


    @Override
    public void replaceAll(List<T> item) {
        mData.clear();
        mData.addAll(item);
        notifyDataSetChanged();
    }

    @Override
    public boolean contains(T item) {
        return mData.contains(item);
    }

    @Override
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener<T> {
        void onItemClick(RecyclerView.ViewHolder viewHolder, T t, int position);

        void onViewClick(RecyclerView.ViewHolder viewHolder, View view, T t, int position);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, T t, int position);

        void onViewLongClick(RecyclerView.ViewHolder viewHolder, View view, T t, int position);
    }
}
