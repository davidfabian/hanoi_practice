package com.d.tornyok;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class Jatek extends AppCompatActivity {


    Guideline guidelineAB;
    Guideline guidelineBC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        constraintLayout.setId(R.id.jatek_constraintlayout);
        setContentView(constraintLayout);

        Intent intent = getIntent();
        int levelCount = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 3);
        guidelineAB = new Guideline(this);
        guidelineBC = new Guideline(this);

        guidelineAB.setId(R.id.guidelineAB);
        guidelineBC.setId(R.id.guidelineBC);

        createLevels(levelCount, constraintLayout);

    }


    public void createLevels(int levelCount, ConstraintLayout constraintLayout) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.create(R.id.guidelineAB, ConstraintSet.VERTICAL_GUIDELINE);
        constraintSet.setGuidelinePercent(R.id.guidelineAB, 0.33f);

        constraintSet.create(R.id.guidelineBC, ConstraintSet.VERTICAL_GUIDELINE);
        constraintSet.setGuidelinePercent(R.id.guidelineBC, 0.66f);
        constraintSet.applyTo(constraintLayout);

        for (int i = levelCount; i >= 1; i--) {
            Button btn = new Button(this);
            btn.setText(String.format("%d", i));
            btn.setId(i);
            final int id_ = btn.getId();
//            btn.setLayoutParams(new ConstraintLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            constraintLayout.addView(btn);
            constraintSet.clone(constraintLayout);
            constraintSet.connect(btn.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
            constraintSet.connect(btn.getId(), ConstraintSet.RIGHT, guidelineAB.getId(), ConstraintSet.LEFT);
            constraintSet.setVerticalChainStyle(btn.getId(), ConstraintSet.CHAIN_SPREAD_INSIDE);
            if (i == levelCount) {
                constraintSet.connect(btn.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
                constraintSet.setVerticalChainStyle(id_, ConstraintSet.BOTTOM);
            } else {
                constraintSet.connect(btn.getId(), ConstraintSet.BOTTOM, id_ + 1, ConstraintSet.TOP);
            }

            constraintSet.applyTo(constraintLayout);
        }
    }
//    left column:
//    constraintSet.connect(btn.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
//    constraintSet.connect(btn.getId(), ConstraintSet.RIGHT, guidelineAB.getId(), ConstraintSet.LEFT);
//    constraintSet.setVerticalChainStyle(btn.getId(), ConstraintSet.CHAIN_SPREAD_INSIDE);
//
//    middle column:
//    constraintSet.connect(btn.getId(), ConstraintSet.LEFT, guidelineAB.getId(), ConstraintSet.RIGHT);
//    constraintSet.connect(btn.getId(), ConstraintSet.RIGHT, guidelineBC.getId(), ConstraintSet.LEFT);
//    constraintSet.setVerticalChainStyle(btn.getId(), ConstraintSet.CHAIN_SPREAD_INSIDE);
//
//    right column:
//    constraintSet.connect(btn.getId(), ConstraintSet.LEFT, guidelineBC.getId(), ConstraintSet.RIGHT);
//    constraintSet.connect(btn.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);
//    constraintSet.setVerticalChainStyle(btn.getId(), ConstraintSet.CHAIN_SPREAD_INSIDE);


}

