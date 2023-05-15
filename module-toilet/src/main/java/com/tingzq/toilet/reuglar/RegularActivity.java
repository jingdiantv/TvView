package com.tingzq.toilet.reuglar;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tingzq.toilet.R;
import com.tingzq.toilet.Utils;
import com.tingzq.toilet.bean.Row;
import com.tingzq.toilet.bean.itemPosition;
import com.tingzq.view.tvrecyclerview.FocusHighlightHelper;
import com.tingzq.view.tvrecyclerview.GridObjectAdapter;
import com.tingzq.view.tvrecyclerview.HorizontalGridView;
import com.tingzq.view.tvrecyclerview.RowItem;


public class RegularActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular);

        HorizontalGridView gridView = findViewById(R.id.id_grid_first);
        gridView.addItemDecoration(new SpaceItemDecoration());
        gridView.setNumRows(2);
        GridObjectAdapter adapter = new GridObjectAdapter(new RegularPresenter(this));
        gridView.setFocusZoomFactor(FocusHighlightHelper.ZOOM_FACTOR_SMALL);
        gridView.setAdapter(adapter);
        for (int i = 0; i < 10; i++) {
            adapter.add(new RowItem());
        }


        HorizontalGridView gridView1 = findViewById(R.id.id_grid_second);
        String json1 = Utils.inputStreamToString(getResources().openRawResource(R.raw.horizonal_regular));
        Row row1 = new Gson().fromJson(json1, Row.class);
        GridObjectAdapter adapter1 = new GridObjectAdapter(new RegularPresenter(this),
                row1.getRowSpacing(), row1.getColumnSpacing(), row1.getColumns(),
                row1.getAspectRatio());
        gridView1.setFocusZoomFactor(FocusHighlightHelper.ZOOM_FACTOR_SMALL);
        gridView1.setAdapter(adapter1);
        for (itemPosition position : row1.getItems()) {
            RowItem rowItem = new RowItem();
            rowItem.setX(position.getX());
            rowItem.setY(position.getY());
            rowItem.setWidth(position.getWidth());
            rowItem.setHeight(position.getHeight());
            adapter1.add(rowItem);
        }
    }

    private class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.right = 30;
            outRect.bottom = 30;
        }
    }
}
