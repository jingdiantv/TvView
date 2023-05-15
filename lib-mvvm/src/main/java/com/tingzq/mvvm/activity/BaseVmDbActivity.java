package com.tingzq.mvvm.activity;

import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.tingzq.mvvm.utils.ViewBindUtil;
import com.tingzq.mvvm.viewmodel.BaseViewModel;

public abstract class BaseVmDbActivity<VM extends BaseViewModel, DB extends ViewDataBinding>
        extends BaseVmActivity<VM> {

    protected DB dataBinding;

    /** 视图模型绑定字段Id */
    private int viewModelId;

    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    protected View initDataBind() {

        dataBinding = ViewBindUtil.inflateBindingWithGeneric(this, getLayoutInflater());
        dataBinding.setLifecycleOwner(this);
        return dataBinding.getRoot();
    }

    @Override
    protected VM createViewModel() {
        viewModelId = setVariableId();
        VM viewModel = super.createViewModel();
        if(viewModelId > 0) dataBinding.setVariable(viewModelId, viewModel);
        return viewModel;
    }

    protected abstract int setVariableId();
}
