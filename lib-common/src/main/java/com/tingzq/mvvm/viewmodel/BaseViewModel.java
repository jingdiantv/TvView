package com.tingzq.mvvm.viewmodel;

import android.os.Bundle;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.tingzq.mvvm.callback.UiObservable;
import com.tingzq.mvvm.callback.livedata.event.SingleLiveEvent;
import com.tingzq.mvvm.constant.AppConstant;
import com.tingzq.mvvm.model.BaseModel;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public abstract class BaseViewModel<M extends BaseModel> extends ViewModel
        implements IBaseViewModel, Consumer<Disposable> {

    /**ui处理订阅发布监听处理 */
    private UiObservable uiObservable;

    private CompositeDisposable compositeDisposable;


    protected M model;

    public BaseViewModel() {

        model = createModel();

    }

    private M createModel() {
        try {
            Type superClass = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            Class<?> clazz = getRawType(type);
            return (M) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    public UiObservable getUiObservable(){
        if(uiObservable == null){
            uiObservable = new UiObservable();
        }
        return uiObservable;
    }

    public void showDialog(){
        showDialog("加载中");
    }

    public void showDialog(String title){
        uiObservable.getShowDialogEvent().postValue(title);
    }

    public void closeDialog(){
        uiObservable.getCloseDialogEvent().call();
    }

    /**
     * 跳转页面
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz){
        startActivity(clz, null);
    }

    /**
     * 跳转页面
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle){
        Map<String, Object> params = new HashMap<>();
        params.put(AppConstant.ACTIVITY_CLASS, clz);
        if(bundle != null){
            params.put(AppConstant.BUNDLE, bundle);
        }
        uiObservable.getStartActivityEvent().postValue(params);
    }


    /**
     * 跳转容器活动页面
     * @param containerFragmentName 所跳转的目的Fragment名称
     */
    public void startFragmentContainerActivity(String containerFragmentName) {
        startFragmentContainerActivity(containerFragmentName, null);
    }

    /**
     * 跳转容器页面
     * @param containerFragmentName 所跳转的目的Fragment名称
     * @param bundle 跳转所携带的信息
     */
    public void startFragmentContainerActivity(String containerFragmentName, Bundle bundle) {
        Map<String, Object> params = new HashMap();
        params.put(AppConstant.CONTAINER_FRAGMENT_NAME, containerFragmentName);
        if (bundle != null) {
            params.put(AppConstant.BUNDLE, bundle);
        }
        uiObservable.getStartFragmentContainerActivityEvent().postValue(params);
    }


    /** 关闭界面 */
    public void finish() {
        uiObservable.getFinishEvent().call();
    }


    /** 返回上一层 */
    public void onBackPressed() {
        uiObservable.getOnBackPressedEvent().call();
    }




    protected void addDisposable(Disposable disposable) {
        if (this.compositeDisposable == null) {
            this.compositeDisposable = new CompositeDisposable();
        }
        this.compositeDisposable.add(disposable);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
        if (model != null) {
            model.onCleared();
        }
    }

    @Override
    public void accept(Disposable disposable) throws Exception {
        addDisposable(disposable);
    }


    // type不能直接实例化对象，通过type获取class的类型，然后实例化对象
    private Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            return Array.newInstance(getRawType(componentType), 0).getClass();
        } else if (type instanceof TypeVariable) {
            return Object.class;
        } else if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + className);
        }
    }


}
