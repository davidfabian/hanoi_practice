package com.d.tornyok;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class Jatek extends AppCompatActivity {

    //vertical guidelines to divide the screen to three equal parts
    Guideline guidelineAB;
    Guideline guidelineBC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        constraintLayout.setId(R.id.jatek_constraintlayout);
        setContentView(constraintLayout);

//get the number of levels from MainActivity, and instantiate the guidelines
        Intent intent = getIntent();
        int levelCount = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 3);
        guidelineAB = new Guideline(this);
        guidelineBC = new Guideline(this);
        guidelineAB.setId(R.id.guidelineAB);
        guidelineBC.setId(R.id.guidelineBC);
//call the starting layout builder method.
        createLevels(levelCount, constraintLayout);

    }


    public void createLevels(int levelCount, ConstraintLayout constraintLayout) {
//create a sketch (ConstraintSet)of the layout changes
        ConstraintSet constraintSet = new ConstraintSet();
//add first guideline, AB, at first third of the screen
        constraintSet.create(R.id.guidelineAB, ConstraintSet.VERTICAL_GUIDELINE);
        constraintSet.setGuidelinePercent(R.id.guidelineAB, 0.33f);
//add second guideline, BC, at second third of the screen
        constraintSet.create(R.id.guidelineBC, ConstraintSet.VERTICAL_GUIDELINE);
        constraintSet.setGuidelinePercent(R.id.guidelineBC, 0.66f);
//apply the constraintSet to the layout.
        constraintSet.applyTo(constraintLayout);
//creates @levelCount number of buttons. Names them, positions them vertically
        for (int i = levelCount; i >= 1; i--) {
            Button btn = new Button(this);
            btn.setText(String.format("%d", i));
            btn.setId(i);
            final int id_ = btn.getId();
//add button to the layout
            constraintLayout.addView(btn);
            constraintSet.clone(constraintLayout);
//position it to the first third of the screen. buttons left side constrained to screen left side
            constraintSet.connect(btn.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
//position it to the first third of the screen. buttons right side constrained to AB guideline's left side
            constraintSet.connect(btn.getId(), ConstraintSet.RIGHT, guidelineAB.getId(), ConstraintSet.LEFT);
//add the button to a group, a vertical chain spread evenly in the available space
            constraintSet.setVerticalChainStyle(btn.getId(), ConstraintSet.CHAIN_PACKED);
////set button width
            constraintSet.constrainWidth(btn.getId(), 0);
            constraintSet.setMargin(btn.getId(), ConstraintSet.START, (levelCount - i) * 30);
            constraintSet.setMargin(btn.getId(), ConstraintSet.END, (levelCount - i) * 30);
//the bottom level button vertically constrained to the bottom of the screen
            if (i == levelCount) {
                constraintSet.connect(btn.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
                constraintSet.setVerticalChainStyle(id_, ConstraintSet.BOTTOM);
//all other buttons vertically constrained to the button below.
            } else {
                constraintSet.connect(btn.getId(), ConstraintSet.BOTTOM, id_ + 1, ConstraintSet.TOP);
            }
//apply the sketch (constraintSet)
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

