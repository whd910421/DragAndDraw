package com.arirus.draganddraw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by whd910421 on 16/10/25.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    private static final String TAG = "SingleFragmentActivity";
    private Fragment mFragment ;

    protected int getLayoutResID()
    {
        return R.layout.activity_fragment;
    }

    protected abstract Fragment newFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());

        FragmentManager fm = getSupportFragmentManager();
        mFragment = fm.findFragmentById(R.id.fragment_container);

        if (mFragment == null)
        {
            mFragment= newFragment();
            fm.beginTransaction().add(R.id.fragment_container, mFragment).commit();
        }
    }
}
