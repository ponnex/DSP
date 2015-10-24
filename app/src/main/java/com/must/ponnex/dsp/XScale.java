package com.must.ponnex.dsp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by EmmanuelFrancis on 10/20/2015.
 */
public class XScale extends View {
    private static final int HEIGHT_FRACTION = 24;

    protected float step;
    protected float scale;
    protected float start;

    private int width;
    private int height;

    private Paint paint;

    public XScale(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        // Create paint

        paint = new Paint();

        // Set initial values

        start = 0;
        scale = 1;
        step = 10;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Get offered dimension

        int w = MeasureSpec.getSize(widthMeasureSpec);

        // Set wanted dimensions

        setMeasuredDimension(w, w / HEIGHT_FRACTION);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        // Get actual dimensions

        width = w;
        height = h;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onDraw(Canvas canvas)
    {
        // Set up paint

        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);

        // Draw ticks

        for (int i = 0; i < width; i += MainActivity.SIZE)
            canvas.drawLine(i, 0, i, height / 4, paint);

        for (int i = 0; i < width; i += MainActivity.SIZE * 5)
            canvas.drawLine(i, 0, i, height / 3, paint);

        // Set up paint

        paint.setAntiAlias(true);
        paint.setTextSize(height * 2 / 3);
        paint.setTextAlign(Paint.Align.CENTER);

        // Draw scale

        if (scale < 100.0)
        {
            canvas.drawText("ms", 10, height - (height / 6), paint);

            for (int i = MainActivity.SIZE * 10; i < width;
                 i += MainActivity.SIZE * 10)
            {
                String s = String.format("%1.1f", (start + (i * scale)) /
                        MainActivity.SMALL_SCALE);
                canvas.drawText(s, i, height - (height / 8), paint);
            }
        }
    }
}
