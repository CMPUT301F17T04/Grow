package com.grow.cmputf17team4.grow;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yizho on 2017/10/20.
 * @author Yizhou Zhao
 */

public abstract class BaseFragment extends Fragment {
    protected View mView;

    /**
     * determine if the View is initiated
     */
    protected boolean isViewInitiated;
    /**
     * Determine if the current fragment is visible to user
     */
    protected boolean isVisibleToUser;
    /**
     * Determine if the request of data is sent to server
     */
    protected boolean isDataRequested;

    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = getContext();
        mView = inflater.inflate(getLayoutId(), null);
        isViewInitiated = true;
        initView();
        prepareGetData();
        return mView;
    }

    /**
     * Abstract method, tell which layout should be used
     */
    protected abstract void initView();

    /**
     * get the layout ID for current fragment
     * @return layoutId
     */
    public abstract int getLayoutId();

    /**
     * get data from server
     */
    protected abstract void getDataFromServer();

    /**
     * is the current page showed or not.
     * @param isVisibleToUser true: visible， false: invisible
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareGetData();
    }

    /**
     * 如果只想第一次进入该页面请求数据，return prepareGetData(false)
     * 如果想每次进入该页面就请求数据，return prepareGetData(true)
     * @return 
     */
    private boolean prepareGetData(){
        return prepareGetData(false);
    }

    /**
     * 判断是否从服务器器获取数据
     * @param isforceUpdate 强制更新的标记
     * @return If the data is got from server
     */
    protected boolean prepareGetData(boolean isforceUpdate) {
        if(isVisibleToUser && isViewInitiated && (!isDataRequested || isforceUpdate)){
            getDataFromServer();
            isDataRequested = true;
            return true;
        }
        return false;
    }
}
