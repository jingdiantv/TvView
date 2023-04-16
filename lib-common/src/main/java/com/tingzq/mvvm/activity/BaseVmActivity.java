package com.tingzq.mvvm.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tingzq.mvvm.viewmodel.BaseViewModel;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseVmActivity<VM extends BaseViewModel> extends RxAppCompatActivity {


    /**
     * 视图模型
     */
    protected VM viewModel;

    protected abstract int layoutId();

    protected abstract void initView(Bundle savedInstanceState);

    /**
     * material风格对话框,避免重复初始化
     */
    private MaterialDialog materialDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view;
        if ((view = initDataBind()) != null) {
            setContentView(view);
        } else {
            setContentView(layoutId());
        }

        init(savedInstanceState);

    }

    private void init(Bundle savedInstanceState) {
        createViewModel();

    }


    private void createViewModel() {
        Class modelClass;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            // 获取当前类泛型中的第二个参数(VM)
            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            // 如果没有指定泛型参数,则默认使用BaseViewModel
            modelClass = BaseViewModel.class;
        }
        viewModel =(VM) new ViewModelProvider(this).get(modelClass);
        // 让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);

    }


    protected void registerUiObservable(){

    }


    protected View initDataBind() {
        return null;
    }

}
