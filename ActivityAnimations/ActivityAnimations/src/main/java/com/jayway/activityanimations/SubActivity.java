package com.jayway.activityanimations;

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

public class SubActivity extends ActionBarActivity {

    public static final String BACKGROUND_COLOR = "background_color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        final int backgroundColor = getIntent().getIntExtra(BACKGROUND_COLOR, 0);
        findViewById(R.id.container).setBackgroundColor(backgroundColor);
    }
}
