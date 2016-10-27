package com.arirus.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whd910421 on 16/10/25.
 */

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";

    private Box mCurrentBox;
    private List<Box> mBoxes = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    public BoxDrawingView(Context context) {
        this(context,null);
    }

    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBackgroundPaint);

        for (Box box:mBoxes)
        {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left,top,right,bottom, mBoxPaint);

            canvas.drawPath();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                action = "Action_up";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_DOWN:
                action = "Action_down";
                mCurrentBox = new Box(current);
                mBoxes.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "Action_move";
                if (mCurrentBox != null)
                {
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "Action_cancel";
                mCurrentBox = null;
                break;
            default:
                break;
        }

        Log.i(TAG, "当前动作和位置"+ action + "action" + current.x +"  "+ current.y);
        return true;
    }


}
