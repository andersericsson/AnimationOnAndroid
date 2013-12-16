package com.jayway.expand;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;

public class ExpandActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);

        final ViewGroup container = (ViewGroup) findViewById(R.id.container);

        final View header1 = findViewById(R.id.header_1);
        final View header2 = findViewById(R.id.header_2);
        final View header3 = findViewById(R.id.header_3);
        final View content1 = findViewById(R.id.content_1);
        final View content2 = findViewById(R.id.content_2);
        final View content3 = findViewById(R.id.content_3);

        header1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (content1.getVisibility() == View.VISIBLE) {
                    collapseContent(header1, content1);
                } else {
                    expandContent(header1, content1);
                }
            }
        });

        header2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (content2.getVisibility() == View.VISIBLE) {
                    collapseContent(header2, content2);
                } else {
                    expandContent(header2, content2);
                }
            }
        });

        header3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (content3.getVisibility() == View.VISIBLE) {
                    collapseContent(header3, content3);
                } else {
                    expandContent(header3, content3);
                }
            }
        });
    }

    private void expandContent(final View header, final View content) {
        content.setVisibility(View.VISIBLE);
    }

    private void collapseContent(final View header, final View content) {
        content.setVisibility(View.GONE);
    }
}
