package com.example.angryghost;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarAnimation extends Animation
{
    private Context mContext;
    private ProgressBar progressBar;
    private TextView textView;
    private float from;
    private float to;
    private SoundEffects soundEffects;

    public ProgressBarAnimation(Context mContext, ProgressBar progressBar, TextView textView, float from, float to) {
        this.mContext = mContext;
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from)*interpolatedTime;
        progressBar.setProgress((int)value);
        textView.setText((int)value+" %");

        if (value == to)
        {
            mContext.startActivity(new Intent(mContext, HomeActivity.class));
        }
    }
}
