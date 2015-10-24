package com.must.ponnex.dsp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by EmmanuelFrancis on 10/20/2015.
 */

public class YScale extends View {
    private static final int WIDTH_FRACTION = 24;

    private int width;
    private int height;

    private Paint paint;

    public YScale(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        // Create paint

        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Get offered dimension

        int h = MeasureSpec.getSize(heightMeasureSpec);

        // Set wanted dimensions

        setMeasuredDimension(h / WIDTH_FRACTION, h);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        // Get actual dimensions

        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        paint.setStrokeWidth(2);

        canvas.translate(0, height / 2);

        // Draw scale ticks

        for (int i = 0; i < height / 2; i += MainActivity.SIZE)
        {
            canvas.drawLine(width * 2 / 3, i, width, i, paint);
            canvas.drawLine(width * 2 / 3, -i, width, -i, paint);
        }

        for (int i = 0; i < height / 2; i += MainActivity.SIZE * 5)
        {
            canvas.drawLine(width / 3, i, width, i, paint);
            canvas.drawLine(width / 3, -i, width, -i, paint);
        }
    }
}
