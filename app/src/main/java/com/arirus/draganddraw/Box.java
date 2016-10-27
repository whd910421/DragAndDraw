package com.arirus.draganddraw;

import android.graphics.PointF;

/**
 * Created by whd910421 on 16/10/25.
 */

public class Box {
    private PointF mOrigin;
    private PointF mCurrent;

    public Box(PointF pointF) {
        mOrigin = pointF;
        mCurrent = pointF;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }

    public PointF getOrigin() {
        return mOrigin;
    }
}
