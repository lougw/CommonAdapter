package com.lougw.commonadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.lougw.commonadapter.holder.BaseCommonHolder;
import com.lougw.commonadapter.interfaces.IData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lougw on 16-12-29.
 */

public abstract class BaseCommonAdapter<T> extends BaseAdapter implements AbsListView.RecyclerListener, IData<T> {
    private Context mContext;
    private List<T> mData;
    protected AbsListView mListView;

    public BaseCommonAdapter(Context context, AbsListView listView) {
        mData = new ArrayList<T>();
        this.mContext = context;
        this.mListView = listView;
        if (null != listView) {
            //设置
            listView.setRecyclerListener(this);
        }
    }

    @Override
    public List<T> getData() {
        return mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return position >= mData.size() ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseCommonHolder<T> holder;
        if (convertView != null && convertView.getTag() instanceof BaseCommonHolder) {
            holder = (BaseCommonHolder<T>) convertView.getTag();
        } else {
            holder = getHolder();
        }
        holder.setData(mData.get(position), position);
        return holder.getRootView();
    }

    protected abstract BaseCommonHolder getHolder();


    @Override
    public void add(T item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<T> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void set(T oldItem, T newItem) {
        set(mData.indexOf(oldItem), newItem);
    }

    @Override
    public void set(int index, T item) {
        mData.set(index, item);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T item) {
        mData.remove(item);
        notifyDataSetChanged();
    }

    @Override
    public void remove(int index) {
        mData.remove(index);
        notifyDataSetChanged();
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

    @Override
    public void onMovedToScrapHeap(View view) {
        if (null != view) {
            Object tag = view.getTag();
            if (tag != null && tag instanceof BaseCommonHolder) {
                BaseCommonHolder holder = (BaseCommonHolder) tag;
                holder.recycle();
            }
        }
    }
}
