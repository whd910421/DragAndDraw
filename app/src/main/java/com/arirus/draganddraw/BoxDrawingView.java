package com.arirus.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
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
    private static final String PARENT_STATE_KEY = "xxx";
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
    protected void onRestoreInstanceState(Parcelable state) {
//        super.onRestoreInstanceState(state);
        if (state != null)
        {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable(PARENT_STATE_KEY));
            String prefixName = "box";
            int boxCount = 1;

            // While we found a key we will create boxes.
            while (bundle.containsKey(prefixName + boxCount)) {
                // Get the x and y values from the bundle.
                float[] pointsArray = bundle.getFloatArray(prefixName + boxCount);

                // Create the boxes from the saved array.
                PointF origin = new PointF(pointsArray[0], pointsArray[1]);
                PointF current = new PointF(pointsArray[2], pointsArray[3]);
                Box box = new Box(origin);
                box.setCurrent(current);

                mBoxes.add(box);
                boxCount++;
            }
            Log.i(TAG, "读取了有"+ boxCount);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable parentState = super.onSaveInstanceState();

        // Bundle used to save the state.
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARENT_STATE_KEY, parentState);

        int boxNumber = 1;

        // Saved the state of the draw boxes
        // the order of the points are origin.x, origin.y,
        // current.x, current,y.
        for(Box box : mBoxes) {

            float[] pointsArray = { box.getOrigin().x, box.getOrigin().y,
                    box.getCurrent().x, box.getCurrent().y
            };

            // Save all the boxes.
            bundle.putFloatArray("box" + boxNumber,
                    pointsArray);
            boxNumber ++;
        }
        Log.i(TAG,"存了有"+ boxNumber);

        return bundle;
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

//        Log.i(TAG, "当前动作和位置"+ action + "action" + current.x +"  "+ current.y);
        return true;
    }


}
