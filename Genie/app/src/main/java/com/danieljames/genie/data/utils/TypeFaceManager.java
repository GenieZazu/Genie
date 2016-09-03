package com.danieljames.genie.data.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Switch;
import android.widget.TextView;

/**
 * A Class used to manage typeface related activities
 */
public class TypeFaceManager {

    /**
     * Set a custom {@Typeface} to a {@View}
     * @param context the context of the activity
     * @param view the view
     * @param typeFace the {@TypeFace}
     */
    public static void setTypeface(Context context, TextView view, TypeFace typeFace) {
        Typeface tFace =  Typeface.createFromAsset(context.getAssets(), typeFace.getPath());
        view.setTypeface(tFace);
    }

    public static void setTypeface(Context context, Switch view, TypeFace typeFace) {
        Typeface tFace =  Typeface.createFromAsset(context.getAssets(), typeFace.getPath());
        view.setTypeface(tFace);
    }
}
