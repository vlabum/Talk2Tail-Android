package com.talk2tail.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class CircleImageView extends AppCompatImageView {

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final float halfWith = getWidth() / 2f;
        final float halfHeight = getHeight() / 2f;
        final float radius = Math.max(halfHeight, halfWith);
        final Path path = new Path();
        path.addCircle(halfWith, halfHeight, radius, Path.Direction.CCW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
