package com.tingzq.toilet.reuglar;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.tingzq.toilet.R;
import com.tingzq.view.tvrecyclerview.FocusHighlightHelper;
import com.tingzq.view.tvrecyclerview.GridObjectAdapter;
import com.tingzq.view.tvrecyclerview.RowItem;
import com.tingzq.view.tvrecyclerview.VerticalGridView;


public class RegularVerticalActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_vertical);
        VerticalGridView gridView = findViewById(R.id.id_grid_vertical);


        gridView.addItemDecoration(new SpaceItemDecoration());
        gridView.setNumColumns(3);
        GridObjectAdapter adapter = new GridObjectAdapter(new RegularVerticalPresenter(this));
        gridView.setFocusZoomFactor(FocusHighlightHelper.ZOOM_FACTOR_SMALL);
        gridView.setAdapter(adapter);
        for (int i = 0; i < 15; i++) {
            adapter.add(new RowItem());
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
