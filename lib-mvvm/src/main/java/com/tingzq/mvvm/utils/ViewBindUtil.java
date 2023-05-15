package com.tingzq.mvvm.utils;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Consumer;
import java.util.function.Function;

public class ViewBindUtil {
    private static final String TAG = ViewBindUtil.class.getSimpleName();


    public static <VB extends ViewBinding> VB inflateBindingWithGeneric(AppCompatActivity activity, LayoutInflater layoutInflater) {
        VB binding = withGenericBindingClass(activity, clazz -> {
            try {
                Method inflateMethod = clazz.getMethod("inflate", LayoutInflater.class);
                return (VB) inflateMethod.invoke(null, layoutInflater);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Failed to inflate binding with generic type", e);
            }
        });
        if(binding instanceof ViewDataBinding){
             ((ViewDataBinding) binding).setLifecycleOwner(activity);
        }
        return binding;

    }

    public static <VB extends ViewBinding> VB inflateBindingWithGeneric(Fragment fragment, LayoutInflater layoutInflater, ViewGroup parent, boolean attachToParent) {
        VB binding = withGenericBindingClass(fragment, clazz -> {
            try {
                Method inflateMethod = clazz.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
                return (VB) inflateMethod.invoke(null, layoutInflater, parent, attachToParent);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Failed to inflate binding with generic type", e);
            }
        });
        if(binding instanceof ViewDataBinding){
            ((ViewDataBinding) binding).setLifecycleOwner(fragment.getViewLifecycleOwner());
        }
        return binding;

    }

    private static <VB extends ViewBinding> VB withGenericBindingClass(Object object, Block<Class<VB>, VB> block) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            java.lang.reflect.Type genericSuperclass = clazz.getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                java.lang.reflect.Type type = parameterizedType.getActualTypeArguments()[1];
                if (type instanceof Class) {
                    try {
                        return block.invoke((Class<VB>) type);
                    } catch (NoSuchMethodException | ClassCastException | InvocationTargetException e) {
                        throw new RuntimeException("Cannot inflate ViewBinding");
                    } catch (Exception e) {
                        QLog.e(TAG, e.getLocalizedMessage());
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        throw new IllegalArgumentException("There is no generic of ViewBinding.");
    }

    public interface Block<T, R> {
        R invoke(T t) throws Exception;
    }

}
