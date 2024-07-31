package com.example.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class progress_bar_demo_two extends AppCompatActivity {

    private Button progress_btn;
    private ProgressBar progressBar;
    private int progressValue = 0;
    private static final int MAX_PROGRESS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_progress_bar_demo_two);

        // Handle system bar insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        progress_btn = findViewById(R.id.btn_progress);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setMax(MAX_PROGRESS); // Ensure the ProgressBar max value is set
    }

    public void startProgressbar(View view) {
        progress_btn.setVisibility(View.GONE);
        progressValue = 0;
        updateProgressBar();
    }

    private void updateProgressBar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressValue < MAX_PROGRESS) {
                    progressValue++;
                    // Update the progress bar on the UI thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressValue);
                        }
                    });
                    try {
                        Thread.sleep(100); // Adjust sleep time as needed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
