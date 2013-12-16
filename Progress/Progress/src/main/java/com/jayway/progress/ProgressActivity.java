package com.jayway.progress;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressActivity extends ActionBarActivity {

    private static final int TOTAL_SIZE = 2582109;

    private class DownloadRunnable implements Runnable {

        private int mProgress = 0;

        @Override
        public void run() {
            mProgress += Math.random() * 800000;
            if (mProgress >= TOTAL_SIZE) {
                mProgress = TOTAL_SIZE;
            } else {
                mProgressBar.postDelayed(this, (long) (250 + 500 * Math.random()));
            }

            updateProgress(mProgress);
        }

        public void resetProgress() {
            mProgress = 0;
        }
    }

    private TextView mProgressText;
    private ProgressBar mProgressBar;
    private DownloadRunnable mDownloadRunnable = new DownloadRunnable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        mProgressText = (TextView) findViewById(R.id.progress_text);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setMax(TOTAL_SIZE);

        findViewById(R.id.restart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                restartDownload();
            }
        });

        updateProgress(0);
    }

    private void updateProgress(int progress) {
        mProgressBar.setProgress(progress);
        mProgressText.setText("Downloaded " + humanReadableByteCount(progress) + " of " + humanReadableByteCount(TOTAL_SIZE));
    }

    private void restartDownload() {
        mProgressBar.removeCallbacks(mDownloadRunnable);
        mDownloadRunnable.resetProgress();
        mProgressBar.post(mDownloadRunnable);
    }

    // Adapted from http://stackoverflow.com/questions/3758606/how-to-convert-byte-size-into-human-readable-format-in-java
    public static String humanReadableByteCount(long bytes) {
        int unit = 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = ("KMGTPE").charAt(exp - 1) + "";
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
