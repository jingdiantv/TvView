package com.tingzq.mvvm.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tingzq.mvvm.viewmodel.BaseViewModel;
import com.trello.rxlifecycle4.components.RxFragment;


public abstract class BaseVmFragment<VM extends BaseViewModel> extends RxFragment {

    private Handler handler = new Handler();

    private Boolean isFirst = true;

    protected VM viewModel;

    protected AppCompatActivity activity;

    /** material风格对话框,避免重复初始化 */
    private MaterialDialog mMaterialDialog;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (AppCompatActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(layoutId(), container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    abstract Integer layoutId();


}
