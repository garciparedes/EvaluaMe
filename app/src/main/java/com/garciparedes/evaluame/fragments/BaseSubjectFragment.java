package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.garciparedes.evaluame.items.Subject;

/**
 * Created by garciparedes on 13/2/15.
 */
public abstract class BaseSubjectFragment extends BaseFragment {

    public static final String SUBJECT = "subject";

    protected Subject mSubject;

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSubject = getArguments().getParcelable(SUBJECT);
    }
}
