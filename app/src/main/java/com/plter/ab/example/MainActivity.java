package com.plter.ab.example;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.plter.lib.ab.AndroidBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private AndroidBanner ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ab = (AndroidBanner) findViewById(R.id.ab);
//        ab.setDelayMillis(2000);
        ab.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private final List<Integer> imgIds = new ArrayList<Integer>() {
                {
                    add(R.mipmap.img1);
                    add(R.mipmap.img2);
                    add(R.mipmap.img3);
                    add(R.mipmap.img4);
                    add(R.mipmap.img5);
                }
            };


            @Override
            public Fragment getItem(final int position) {
                return new Fragment() {
                    @Nullable
                    @Override
                    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                        ImageView iv = new ImageView(getContext());
                        iv.setImageResource(imgIds.get(position));
                        return iv;
                    }
                };
            }

            @Override
            public int getCount() {
                return imgIds.size();
            }
        });
    }
}
