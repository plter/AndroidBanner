package com.plter.lib.ab;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * Created by plter on 12/8/15.
 */
public class AndroidBanner extends FrameLayout {

    private DisplayMetrics metrics;
    private ViewPager viewPager;
    private FrameLayout fg;
    private LinearLayout starsGroup;
    private static final Random random = new Random();
    private boolean autoRun = false;
    final Handler handler = new Handler();

    private DataSetObserver dataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();

            reAddStars();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    };
    private ViewPager.OnPageChangeListener viewPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setSelectedStarIndex(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private java.lang.Runnable changeViewPageHandler = new Runnable() {
        @Override
        public void run() {

            int pageCount = viewPager.getAdapter().getCount();
            int currentSelectedPageIndex = viewPager.getCurrentItem();

            if (pageCount > 0) {
                currentSelectedPageIndex++;
                if (currentSelectedPageIndex >= pageCount) {
                    currentSelectedPageIndex = 0;
                }

                viewPager.setCurrentItem(currentSelectedPageIndex, true);
            }

            handler.postDelayed(changeViewPageHandler, delayMillis);
        }
    };
    private long delayMillis = 8000;

    private void reAddStars() {
        starsGroup.removeAllViews();
        int length = viewPager.getAdapter().getCount();
        LinearLayout.LayoutParams starLp;

        for (int i = 0; i < length; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setPadding(10, 0, 10, 0);
            starLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            starLp.gravity = Gravity.CENTER_VERTICAL;
            starsGroup.addView(iv, starLp);
        }

        if (length > 0) {
            setSelectedStarIndex(0);
        }
    }

    public AndroidBanner(Context context) {
        super(context);

        internalInit();
    }

    public AndroidBanner(Context context, AttributeSet attrs) {
        super(context, attrs);

        internalInit();
    }

    public AndroidBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        internalInit();
    }

    private void internalInit() {
        metrics = getContext().getResources().getDisplayMetrics();

        viewPager = new ViewPager(getContext());
        viewPager.setId(random.nextInt(Integer.MAX_VALUE));
        addView(viewPager, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        viewPager.addOnPageChangeListener(viewPageChangeListener);
        setAutoRun(true);

        //add foreground layer
        fg = new FrameLayout(getContext());
        fg.setPadding(10, 0, 10, 0);
        FrameLayout.LayoutParams fglp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 60);
        fglp.gravity = Gravity.BOTTOM;
        fg.setBackgroundColor(0x77000000);
        addView(fg, fglp);

        //add stars group layout
        starsGroup = new LinearLayout(getContext());
        FrameLayout.LayoutParams starsGroupLp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        starsGroupLp.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        fg.addView(starsGroup, starsGroupLp);
    }

    public void setAdapter(PagerAdapter adapter) {
        if (viewPager.getAdapter() != null) {
            viewPager.getAdapter().unregisterDataSetObserver(dataSetObserver);
        }

        viewPager.setAdapter(adapter);
        adapter.registerDataSetObserver(dataSetObserver);

        reAddStars();
    }

    public void setCoverViewHeight(int pix) {
        ViewGroup.LayoutParams params = fg.getLayoutParams();
        params.height = pix;
        fg.setLayoutParams(params);
    }

    public void setCoverViewHeightInDp(float dp) {
        setCoverViewHeight((int) (dp * metrics.density));
    }

    public void setCoverViewColor(int color) {
        fg.setBackgroundColor(color);
    }

    private Bitmap starNormalStateFace = Bitmaps.getInstance().getStarNormalStateBitmap(), starSelectedStateFace = Bitmaps.getInstance().getStarSelectedStateBitmap();

    public void setStarNormalStateFace(Bitmap starNormalStateFace) {
        this.starNormalStateFace = starNormalStateFace;
    }

    public void setStarSelectedStateFace(Bitmap starSelectedStateFace) {
        this.starSelectedStateFace = starSelectedStateFace;
    }

    private void setSelectedStarIndex(int index) {
        View child;
        for (int i = 0; i < starsGroup.getChildCount(); i++) {
            child = starsGroup.getChildAt(i);
            ((ImageView) child).setImageBitmap(index != i ? starNormalStateFace : starSelectedStateFace);
        }
    }

    public void setAutoRun(boolean autoRun) {

        if (autoRun) {
            if (!this.autoRun) {
                handler.postDelayed(changeViewPageHandler, delayMillis);
            }
        } else {
            handler.removeCallbacks(changeViewPageHandler);
        }

        this.autoRun = autoRun;
    }

    public boolean isAutoRun() {
        return autoRun;
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public long getDelayMillis() {
        return delayMillis;
    }
}
