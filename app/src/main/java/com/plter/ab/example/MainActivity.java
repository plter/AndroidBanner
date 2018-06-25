package com.plter.ab.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

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
                MyFragment f = new MyFragment();
                Bundle data = new Bundle();
                data.putInt(MyFragment.KEY_IMAGE_ID, imgIds.get(position));
                f.setArguments(data);
                return f;
            }

            @Override
            public int getCount() {
                return imgIds.size();
            }
        });
    }
}
