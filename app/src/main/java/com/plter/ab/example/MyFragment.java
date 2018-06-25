package com.plter.ab.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment {

    public static final String KEY_IMAGE_ID = "imageId";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ImageView iv = new ImageView(getContext());
        iv.setImageResource(getArguments().getInt(KEY_IMAGE_ID));
        return iv;
    }
}
