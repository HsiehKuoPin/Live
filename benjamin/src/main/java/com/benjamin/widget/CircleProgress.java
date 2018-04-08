package com.benjamin.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CircleProgress extends View {
    private int angle = 0;

    public CircleProgress(Context context) {
        super(context);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#90dab26a"));
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3.0F);
        paint.setAntiAlias(true);
        RectF oval = new RectF(0.0F, 0.0F, (float)this.getWidth(), (float)this.getHeight());
        canvas.drawArc(oval, -90.0F, (float)this.angle, true, paint);
    }

    public void draw(int progress) {
        this.angle = (int)(360.0F * ((float)progress / 100.0F));
        this.invalidate();
    }
}