package com.tingzq.toilet.irregular;

import android.app.Activity;
import android.os.Bundle;


import com.google.gson.Gson;
import com.tingzq.toilet.R;
import com.tingzq.toilet.Utils;
import com.tingzq.toilet.bean.Row;
import com.tingzq.toilet.bean.itemPosition;
import com.tingzq.toilet.reuglar.RegularVerticalPresenter;
import com.tingzq.view.tvrecyclerview.FocusHighlightHelper;
import com.tingzq.view.tvrecyclerview.GridObjectAdapter;
import com.tingzq.view.tvrecyclerview.RowItem;
import com.tingzq.view.tvrecyclerview.VerticalGridView;


public class IrregularVerticalActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irregular_vertical);
        VerticalGridView gridView = findViewById(R.id.id_grid_vertical_ir);
        String json = Utils.inputStreamToString(getResources().openRawResource(R.raw.vertical_irregular));
        Row row = new Gson().fromJson(json, Row.class);

        GridObjectAdapter<RowItem> adapter = new GridObjectAdapter<>(new RegularVerticalPresenter(this),
                row.getRowSpacing(), row.getColumnSpacing(), row.getColumns(),
                row.getAspectRatio());
        gridView.setFocusZoomFactor(FocusHighlightHelper.ZOOM_FACTOR_SMALL);
        gridView.setAdapter(adapter);

        for (itemPosition position : row.getItems()) {
            RowItem rowItem = new RowItem();
            rowItem.setX(position.getX());
            rowItem.setY(position.getY());
            rowItem.setWidth(position.getWidth());
            rowItem.setHeight(position.getHeight());
            adapter.add(rowItem);
        }
    }
}
