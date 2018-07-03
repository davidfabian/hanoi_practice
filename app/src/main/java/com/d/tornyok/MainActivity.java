package com.d.tornyok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "NUMBER_OF_LEVELS";
    int levelCount;
    TextView TVLevelCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TVLevelCounter = findViewById(R.id.tv_levelcounter);
        levelCount = 3;
        updateCounter(levelCount);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, Jatek.class);
        intent.putExtra(EXTRA_MESSAGE, levelCount);
        startActivity(intent);
    }

    public void increaseLevelCount(View v) {
        if (levelCount < 10) {
            levelCount += 1;
        }
        updateCounter(levelCount);
    }

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
