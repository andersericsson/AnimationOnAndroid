package com.jayway.propertyanimations;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

public class PropertyAnimation extends ActionBarActivity {

    private ImageView mContactImageView;
    private View mDimLayer;
    private View mEditLayer;
    private View mButtonContainer;
    private ImageView mLargeContactImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        mButtonContainer = findViewById(R.id.button_container);
        mDimLayer = findViewById(R.id.dim_layer);
        mEditLayer = findViewById(R.id.edit_layer);
        mLargeContactImageView = (ImageView) findViewById(R.id.large_contact_image);
        mContactImageView = (ImageView) findViewById(R.id.contact_image);

        // Set up listeners to start and end edit mode
        mContactImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startEditMode();
            }
        });
        findViewById(R.id.done_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                endEditMode();
            }
        });
    }

    private void startEditMode() {
        enterEditMode();
    }

    private void endEditMode() {
        exitEditMode();
    }

    private void enterEditMode() {
        mDimLayer.setVisibility(View.VISIBLE);
        mEditLayer.setVisibility(View.VISIBLE);
    }

    private void exitEditMode() {
        mDimLayer.setVisibility(View.INVISIBLE);
        mEditLayer.setVisibility(View.INVISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void animateTargetFromSource(View target, View source) {
        // get source and target location
        int[] sourceLocation = new int[2];
        source.getLocationOnScreen(sourceLocation);
        int[] targetLocation = new int[2];
        target.getLocationOnScreen(targetLocation);

        // calculate center point, compensating for translation
        float sourceCenterX = (sourceLocation[0] - source.getTranslationX()) + source.getScaleX() * source.getWidth() / 2;
        float sourceCenterY = (sourceLocation[1] - source.getTranslationY()) + source.getScaleY() * source.getHeight() / 2;
        float targetCenterX = (targetLocation[0] - target.getTranslationX()) + target.getScaleX() * target.getWidth() / 2;
        float targetCenterY = (targetLocation[1] - target.getTranslationY()) + target.getScaleY() * target.getHeight() / 2;

        // set translation and scale on target to match source
        target.setTranslationX(sourceCenterX - targetCenterX);
        target.setTranslationY(sourceCenterY - targetCenterY);
        target.setScaleX((float) source.getWidth() / (float) target.getWidth());
        target.setScaleY((float) source.getHeight() / (float) target.getHeight());

        // animate the target to it's normal position
        target.animate().translationX(0).translationY(0).scaleX(1).scaleY(1);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void animateTargetToSource(View target, View source) {
        int[] sourceLocation = new int[2];
        source.getLocationOnScreen(sourceLocation);
        int[] targetLocation = new int[2];
        target.getLocationOnScreen(targetLocation);

        // calculate center point, compensating for translation
        float sourceCenterX = (sourceLocation[0] - source.getTranslationX()) + source.getScaleX() * source.getWidth() / 2;
        float sourceCenterY = (sourceLocation[1] - source.getTranslationY()) + source.getScaleY() * source.getHeight() / 2;
        float targetCenterX = (targetLocation[0] - target.getTranslationX()) + target.getScaleX() * target.getWidth() / 2;
        float targetCenterY = (targetLocation[1] - target.getTranslationY()) + target.getScaleY() * target.getHeight() / 2;

        // calculate translation and scale to match source
        int translateX = (int) (sourceCenterX - targetCenterX);
        int translateY = (int) (sourceCenterY - targetCenterY);
        float scaleX = (float) source.getWidth() / (float) target.getWidth();
        float scaleY = (float) source.getHeight() / (float) target.getHeight();

        // animate target to the source values
        target.animate().translationX(translateX).translationY(translateY).scaleX(scaleX).scaleY(scaleY);
    }
}
