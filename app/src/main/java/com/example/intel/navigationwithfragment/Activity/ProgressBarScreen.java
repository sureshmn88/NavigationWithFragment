package com.example.intel.navigationwithfragment.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.intel.navigationwithfragment.R;

public class ProgressBarScreen extends AppCompatActivity {
    LinearLayout mOneLayout, mTwoLayout, mThreeLayout, mFourLayout;
    ProgressDialog mProgressDialog;
    Dialog mediaDialog;

    ProgressDialog dialog;
    private int progressStatus = 0;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_screen);

        mOneLayout = findViewById(R.id.one);
        mTwoLayout = findViewById(R.id.two);
        mThreeLayout = findViewById(R.id.three);
        mFourLayout = findViewById(R.id.four);
        final Button btn = (Button) findViewById(R.id.btn);
        final TextView tv = (TextView) findViewById(R.id.tv);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.pb);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the progress status zero on each button click
                progressStatus = 0;

                /*
                    A Thread is a concurrent unit of execution. It has its own call stack for
                    methods being invoked, their arguments and local variables. Each application
                    has at least one thread running when it is started, the main thread,
                    in the main ThreadGroup. The runtime keeps its own threads
                    in the system thread group.
                */
                // Start the lengthy operation in a background thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progressStatus < 100){
                            // Update the progress status
                            progressStatus +=1;

                            // Try to sleep the thread for 20 milliseconds
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }

                            // Update the progress bar
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    pb.setProgress(progressStatus);
                                    // Show the progress on TextView
                                    tv.setText(progressStatus+"");
                                }
                            });
                        }
                    }
                }).start(); // Start the operation
            }
        });

        mOneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // showDDialog(1);
                //showNew();

                progressStatus = 0;

                /*
                    A Thread is a concurrent unit of execution. It has its own call stack for
                    methods being invoked, their arguments and local variables. Each application
                    has at least one thread running when it is started, the main thread,
                    in the main ThreadGroup. The runtime keeps its own threads
                    in the system thread group.
                */
                // Start the lengthy operation in a background thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progressStatus < 100){
                            // Update the progress status
                            progressStatus +=1;

                            // Try to sleep the thread for 20 milliseconds
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }

                            // Update the progress bar
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    pb.setProgress(progressStatus);
                                    // Show the progress on TextView
                                    tv.setText(progressStatus+"");
                                }
                            });
                        }
                    }
                }).start(); // Start the operation
            }

        });

        mTwoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // showDDialog(2);
            }
        });

        mThreeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  showDDialog(3);
            }
        });

        mFourLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // showDDialog(4);
            }
        });

    }


    /*void showNew() {

        mediaDialog = new Dialog(this);
        mediaDialog.setContentView(R.layout.dialog_custom);
        mediaDialog.setTitle("Add Media");
        mediaDialog.setCancelable(false);

        mCircularProgressView=mediaDialog.findViewById(R.id.progress_color);
        mCircularProgressView.setMax(100);

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 100; i++) {
                            final int value = i;
                            doFakeWork();
                            mCircularProgressView.setProgress(value);
                        }
                        if (mCircularProgressView.getProgress() == 100) {
                            mediaDialog.dismiss();
                        }
                    }
                });


            }
        };
        new Thread(runnable).start();

        mediaDialog.show();


    }

    void showDDialog(int type) {

        String title = "One";
        int progressStyle = R.style.MyAlertDialogStyle;
        if (type == 1) {
            title = "One";
            progressStyle = R.style.MyAlertDialogStyle;
        } else if (type == 2) {
            title = "Two";
            progressStyle = R.style.MyAlertDialogStyle1;
        } else if (type == 3) {
            title = "Three";
            progressStyle = R.style.MyAlertDialogStyle2;
        } else if (type == 4) {
            title = "Four";
            progressStyle = R.style.MyAlertDialogStyle3;
        }

        dialog=null;
        dialog = new ProgressDialog(ProgressBarScreen.this, progressStyle);
        dialog.setTitle(title);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    final int value = i;
                    doFakeWork();
                    dialog.setProgress(value);
                }
                if (dialog.getProgress() == 100) {
                    dialog.dismiss();
                }


            }
        };
        new Thread(runnable).start();

    }

    void showDialog() {

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMax(100);
        mProgressDialog.setTitle("Video Uploading....");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    final int value = i;
                    doFakeWork();
                    mProgressDialog.setProgress(value);

                    if (i > 25) {
                        *//*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mProgressDialog.setProgressDrawable(getDrawable(R.drawable.progressbar_states));
                            mProgressDialog.setIndeterminate(false);
                        }*//*
                        mProgressDialog.setProgressStyle(R.style.AppCompatAlertDialogStyle);
                    }
                }
                if (mProgressDialog.getProgress() == 100) {
                    mProgressDialog.dismiss();
                }
                Log.d("TAGZ", "Running :" + mProgressDialog.getProgress());

            }
        };
        new Thread(runnable).start();

    }

    private void doFakeWork() {
        SystemClock.sleep(50);
    }*/
}
