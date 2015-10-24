package com.must.ponnex.dsp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by EmmanuelFrancis on 10/20/2015.
 */

public class Scope extends View {
    private int width;
    private int height;

    private Path path;
    private Canvas cb;
    private Paint paint;
    private Bitmap bitmap;
    private Bitmap graticule;

    protected boolean storage;
    protected boolean clear;

    protected float scale;
    protected float start;

    protected MainActivity main;
    protected MainActivity.Audio audio;

    // Scope

    public Scope(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        // Create path and paint

        path = new Path();
        paint = new Paint();
    }


    // On size changed

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        // Get dimensions

        width = w;
        height = h;

        // Create a bitmap for trace storage

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        cb = new Canvas(bitmap);

        // Create a bitmap for the graticule

        graticule = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(graticule);

        // Black background

        canvas.drawColor(Color.BLACK);

        // Set up paint

        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.argb(255, 0, 63, 0));

        // Draw graticule

        for (int i = 0; i < width; i += MainActivity.SIZE)
            canvas.drawLine(i, 0, i, height, paint);

        canvas.translate(0, height / 2);

        for (int i = 0; i < height / 2; i += MainActivity.SIZE)
        {
            canvas.drawLine(0, i, width, i, paint);
            canvas.drawLine(0, -i, width, -i, paint);
        }

        // Draw the graticule on the bitmap

        cb.drawBitmap(graticule, 0, 0, null);

        cb.translate(0, height / 2);
    }

    private int max;

    // On draw

    @SuppressLint("DefaultLocale")
    @Override
    protected void onDraw(Canvas canvas)
    {
        // Check for data

        if ((audio == null) || (audio.data == null))
        {
            canvas.drawBitmap(graticule, 0, 0, null);
            return;
        }

        // Draw the graticule on the bitmap

        if (!storage || clear)
        {
            cb.drawBitmap(graticule, 0, -height / 2, null);
            clear = false;
        }

        // Calculate x scale etc

        float xscale = (float)(2.0 / ((audio.sample / 100000.0) * scale));
        int xstart = Math.round(start);
        int xstep = Math.round((float)1.0 / xscale);
        int xstop = Math.round(xstart + ((float)width / xscale));

        if (xstop > audio.length)
            xstop = (int)audio.length;

        // Calculate y scale

        if (max < 4096)
            max = 4096;

        float yscale = (float)(max / (height / 2.0));

        max = 0;

        // Draw the trace

        path.rewind();
        path.moveTo(0, 0);

        if (xscale < 1.0)
        {
            for (int i = 0; i < xstop - xstart; i += xstep)
            {
                if (max < Math.abs(audio.data[i + xstart]))
                    max = Math.abs(audio.data[i + xstart]);

                float x = (float)i * xscale;
                float y = -(float)audio.data[i + xstart] / yscale;
                path.lineTo(x, y);
            }
        }

        else
        {
            for (int i = 0; i < xstop - xstart; i++)
            {
                if (max < Math.abs(audio.data[i + xstart]))
                    max = Math.abs(audio.data[i + xstart]);

                float x = (float)i * xscale;
                float y = -(float)audio.data[i + xstart] / yscale;
                path.lineTo(x, y);

                // Draw points at max resolution

                if (main.timebase == 0)
                {
                    path.addRect(x - 2, y - 2, x + 2, y + 2, Path.Direction.CW);
                    path.moveTo(x, y);
                }
            }
        }

        // Green trace

        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        cb.drawPath(path, paint);

        canvas.drawBitmap(bitmap, 0, 0, null);
    }
}
