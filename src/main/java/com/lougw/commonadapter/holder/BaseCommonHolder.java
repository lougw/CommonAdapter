
package com.lougw.commonadapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;


public abstract class BaseCommonHolder<T> {
    protected View mRootView;
    protected Context mContext;
    protected T mData;

    public BaseCommonHolder(Context context) {
        this.mContext = context;
        mRootView = initView();
        mRootView.setTag(this);
    }


    public View getRootView() {
        return mRootView;
    }

    public void setData(T data, int position) {
        mData = data;
        refreshView(position);
    }

    public T getData() {
        return mData;
    }


    public void recycleImageView(ImageView view) {
        if (null != view) {
            view.setImageDrawable(null);
        }
    }

    /**
     * 子类必须覆盖用于实现UI初始化
     */
    protected abstract View initView();

    /**
     * 子类必须覆盖用于实现UI刷新
     */
    public abstract void refreshView(int position);

    /**
     * 用于回收
     */
    public void recycle() {

    }

    @Override
    public int hashCode() {
        if (null != mData) {
            return mData.hashCode();
        }

        return super.hashCode();
    }
}
