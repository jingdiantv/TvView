package com.tingzq.mvvm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tingzq.mvvm.R;
import com.tingzq.mvvm.constant.AppConstant;
import com.tingzq.mvvm.viewmodel.BaseViewModel;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseVmActivity<VM extends BaseViewModel> extends RxAppCompatActivity {


    /**
     * 视图模型
     */
    protected VM viewModel;


    /**
     * material风格对话框,避免重复初始化
     */
    private MaterialDialog materialDialog;


    protected abstract int layoutId();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void createObserver();

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
        viewModel = createViewModel();
        registerUiObservable();
        initView(savedInstanceState);
        createObserver();
    }


    protected VM createViewModel() {
        Class modelClass;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            // 获取当前类泛型中的第二个参数(VM)
            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            // 如果没有指定泛型参数,则默认使用BaseViewModel
            modelClass = BaseViewModel.class;
        }
        VM viewModel =(VM) new ViewModelProvider(this).get(modelClass);
        // 让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        return viewModel;

    }




    protected void registerUiObservable(){
        /** 显示加载弹出框 */
        viewModel.getUiObservable().getShowDialogEvent().observe(this, title -> {
            if (materialDialog != null) {
                materialDialog = materialDialog.getBuilder().title(title).build();
                materialDialog.show();
            } else {
                MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                        .title(title)
                        .progress(true, 0)
                        .progressIndeterminateStyle(true)
                        .canceledOnTouchOutside(false)
                        .backgroundColorRes(R.color.white)
                        .keyListener((dialog, keyCode, event) -> false);
                materialDialog = builder.show();
            }
        });

        /** 关闭加载弹出框 */
        viewModel.getUiObservable().getCloseDialogEvent().observe(this, v -> {
            if (materialDialog != null && materialDialog.isShowing()) materialDialog.dismiss();
        });

        /** 进入活动 */
        viewModel.getUiObservable().getStartActivityEvent().observe(this, params -> {
            Class<?> clz = (Class<?>) params.get(AppConstant.ACTIVITY_CLASS);
            Bundle bundle = (Bundle) params.get(AppConstant.BUNDLE);
            startActivity(clz, bundle);
        });

        /** 进入碎片容器活动 */
        viewModel.getUiObservable().getStartFragmentContainerActivityEvent().observe(this, params -> {
            String containerFragmentName = (String) params.get(AppConstant.CONTAINER_FRAGMENT_NAME);
            Bundle bundle = (Bundle) params.get(AppConstant.BUNDLE);
            startFragmentContainerActivity(containerFragmentName, bundle);
        });

        /** 关闭界面 */
        viewModel.getUiObservable().getFinishEvent().observe(this, v -> finish());

        /** 返回上一层 */
        viewModel.getUiObservable().getOnBackPressedEvent().observe(this, v -> onBackPressed());

    }


    /**
     * 进入活动
     * @param clz 进入活动类
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 进入活动
     * @param clz 进入活动类
     * @param bundle 进入活动所携带的数据
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 进入碎片容器活动
     * @param containerFragmentName 所跳转的目的Fragment名称
     */
    public void startFragmentContainerActivity(String containerFragmentName) {
        startFragmentContainerActivity(containerFragmentName, null);
    }

    /**
     * 进入碎片容器活动
     * @param containerFragmentName 所跳转的目的Fragment名称
     * @param bundle 进入碎片容器活动所携带的数据
     */
    public void startFragmentContainerActivity(String containerFragmentName, Bundle bundle) {
        Intent intent = new Intent(this, FragmentContainerActivity.class);
        intent.putExtra(AppConstant.CONTAINER_FRAGMENT_NAME, containerFragmentName);
        if (bundle != null) {
            intent.putExtra(AppConstant.BUNDLE, bundle);
        }
        startActivity(intent);
    }


    protected View initDataBind() {
        return null;
    }

}
