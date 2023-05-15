package com.tingzq.mvvm.activity;

import android.view.View;

import androidx.viewbinding.ViewBinding;

import com.tingzq.mvvm.utils.ViewBindUtil;
import com.tingzq.mvvm.viewmodel.BaseViewModel;

public abstract class BaseVmVbActivity<VM extends BaseViewModel, VB extends ViewBinding>
        extends BaseVmActivity<VM>{
    @Override
    protected int layoutId() {
        return 0;
    }

    protected VB viewBind;

    @Override
    protected View initDataBind() {

        viewBind = ViewBindUtil.inflateBindingWithGeneric(this, getLayoutInflater());
        return viewBind.getRoot();
    }
}
