package com.project.myv.calculator;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;


public class DeprecatedMethods
{
    @TargetApi(16)
    @SuppressWarnings("deprecation")
    public void setBackground(Drawable drawable, View v)
    {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk > android.os.Build.VERSION_CODES.JELLY_BEAN)
        {
            v.setBackground(drawable);
        }
        else
        {
            v.setBackgroundDrawable(drawable);
        }
    }

    @TargetApi(21)
    @SuppressWarnings("deprecation")
    public Drawable getDrawable(Context context, int id)
    {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk >= Build.VERSION_CODES.LOLLIPOP)
        {
            return context.getResources().getDrawable(id, context.getTheme());
        }
        else
        {
            return context.getResources().getDrawable(id);
        }
    }


    @SuppressWarnings("deprecation")
    public int getColor(Context context, int colorId)
    {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk >= Build.VERSION_CODES.M)
        {
            return ContextCompat.getColor(context, colorId);
        }
        else
        {
            return context.getResources().getColor(colorId);
        }
    }
}
