package com.appswager.rangoliapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView
        ;
import android.widget.ImageView;

public class CustomActivity extends AppCompatActivity {

    private Animator mCurrentAnimatorEffect;
    private int mShortAnimationDurationEffect;
    GridView gv;
    Context context;
    ArrayList prgmName;
    public static String[] itemList = {"Cartoon", "Lord", "Peocock", "Free Hand", "Round", "Square"};
    public static int[] prgmImages = {R.drawable.cartoon1, R.drawable.lord1, R.drawable.peocock1, R.drawable.freehand1, R.drawable.round1, R.drawable.square1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(new CustomAdapter(CustomActivity.this, itemList, prgmImages));

    }
}