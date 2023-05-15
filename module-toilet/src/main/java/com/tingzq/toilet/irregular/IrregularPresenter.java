package com.tingzq.toilet.irregular;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.tingzq.toilet.Utils;
import com.tingzq.view.tvrecyclerview.Presenter;


public class IrregularPresenter extends Presenter {

    IrregularPresenter(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        ImageView view = new ImageView(getContext());
        view.setSelected(true);
        view.setFocusable(true);
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        viewHolder.view.setBackgroundColor(Utils.getRandColor());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
