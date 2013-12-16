package com.jayway.activityanimations;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static int ROWS = 6;
    private static int COLS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout container = (LinearLayout) findViewById(R.id.container);

        for (int row = 0; row < ROWS; row++) {
            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
            rowParams.weight = 1;
            container.addView(linearLayout, rowParams);
            for (int col = 0; col < COLS; col++) {
                View view = new View(this);
                final int color = Color.rgb(0xFF / (COLS - 1) * col, 0xFF / (ROWS - 1) * row, 0);
                view.setBackgroundColor(color);
                view.setTag(color);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                params.weight = 1;
                params.setMargins(8, 8, 8, 8);
                linearLayout.addView(view, params);
                view.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(final View v) {
        final Intent intent = new Intent(this, SubActivity.class);
        intent.putExtra(SubActivity.BACKGROUND_COLOR, (Integer) v.getTag());
        startActivity(intent);
    }
}
