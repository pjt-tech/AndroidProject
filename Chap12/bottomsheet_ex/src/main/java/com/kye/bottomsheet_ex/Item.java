package com.kye.bottomsheet_ex;

import android.graphics.drawable.Drawable;

public class Item {
    Drawable drawable;
    String title;

    public Item(Drawable drawable, String title) {
        this.drawable = drawable;
        this.title = title;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public String getTitle() {
        return title;
    }
}
