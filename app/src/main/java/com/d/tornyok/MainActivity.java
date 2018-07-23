package com.d.tornyok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
//start screen. showing the number of levels, increase, decrease and start the game

public class MainActivity extends AppCompatActivity {
    //    intent extra id's
    public static final String EXTRA_MESSAGE = "NUMBER_OF_LEVELS";
    public static final String EXTRA_X = "X_DIMENSION";
    public static final String EXTRA_Y = "Y_DIMENSION";
    //    store number of levels and screen size
    int levelCount;
    int x_size = 12;
    int y_size = 13;
    //    shows a number, the number of levels
    TextView TVLevelCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TVLevelCounter = findViewById(R.id.tv_levelcounter);
        //default level count
        levelCount = 3;
        //update levelcount
        updateCounter(levelCount);
    }

    // Starts @Jatek.java, and send number of levels @levelCount in the intent
//    called when the Start button (b_start) clicked.
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Jatek.class);
        intent.putExtra(EXTRA_MESSAGE, levelCount);

        x_size = view.getRootView().getWidth();
        y_size = view.getRootView().getHeight();

        intent.putExtra(EXTRA_X, x_size);
        intent.putExtra(EXTRA_Y, y_size);
        startActivity(intent);
    }

    //increase number of levels by one, to a maximum of 10, then updates the levelCount variable
//    called when plus button (b_increase) clicked.
    public void increaseLevelCount(View v) {
        if (levelCount < 10) {
            levelCount += 1;
        }
        updateCounter(levelCount);
    }

    //decrease the number of levels, to a minimum of 3, then updates the levelCount variable
//    called when minus button (b_decrease) clicked.
    public void decreaseLevelCount(View v) {
        if (levelCount > 3) {
            levelCount -= 1;
        }

        updateCounter(levelCount);
    }

    public void updateCounter(int levelcount) {
        String levelString = String.format(getResources().getString(R.string.levels) + " %d", levelcount);
        TVLevelCounter.setText(levelString);


    }
}
