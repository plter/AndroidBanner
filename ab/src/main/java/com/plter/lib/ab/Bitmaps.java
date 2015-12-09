package com.plter.lib.ab;

import android.graphics.Bitmap;

/**
 * Created by plter on 12/8/15.
 */
public class Bitmaps {

    private Bitmaps() {
        initSingleColorBitmap(starNormalStateBitmap, 0xffaaaaaa);
        initSingleColorBitmap(starSelectedStateBitmap, 0xffffffff);
    }

    private static Bitmaps __ins = new Bitmaps();

    public static Bitmaps getInstance() {
        return __ins;
    }

    private Bitmap starNormalStateBitmap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888);

    public Bitmap getStarNormalStateBitmap() {
        return starNormalStateBitmap;
    }

    private Bitmap starSelectedStateBitmap = Bitmap.createBitmap(25, 25, Bitmap.Config.ARGB_8888);

    public Bitmap getStarSelectedStateBitmap() {
        return starSelectedStateBitmap;
    }

    private void initSingleColorBitmap(Bitmap bitmap, int color) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int[] colors = new int[width * height];

        for (int i = 0; i < width * height; i++) {
            colors[i] = color;
        }

        bitmap.setPixels(colors, 0, width, 0, 0, width, height);
    }
}
