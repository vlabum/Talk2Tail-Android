package com.talk2tail.common.ui.recyclerevents;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MarginItemDecoration extends RecyclerView.ItemDecoration {

    private int spaceHeight;

    public MarginItemDecoration(int spaceHeight) {
        super();
        this.spaceHeight = spaceHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.left = parent.getChildAdapterPosition(view) == 0 ? 0 : spaceHeight;
        outRect.right = 0;
        outRect.top = 0;
        outRect.bottom = 0;
    }
}
