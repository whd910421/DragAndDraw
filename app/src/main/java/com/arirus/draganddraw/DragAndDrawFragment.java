package com.arirus.draganddraw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by whd910421 on 16/10/25.
 */

public class DragAndDrawFragment extends Fragment {
    private static final String TAG = "DragAndDrawFragment";
    private static DragAndDrawFragment sFragment = null;
    public static DragAndDrawFragment getInstance()
    {
        if(sFragment == null)
            sFragment = new DragAndDrawFragment();
        return sFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_drag_and_draw, container, false);
        return v;
    }
}
