package com.example.asus.richmans;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView txtTitle, txtExplain;

    VideoView videoView;
    String path;

    TextView txtSoundName;
    SeekBar seekBar;
    Button btnPlay, btnNext, btnPreview;
    MediaPlayer player;
    Handler seekHandler = new Handler();
    int sound;

    int stopPosition;

    RelativeLayout layoutText, layoutVoice, layoutVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        init();

        String tutorialId = getIntent().getStringExtra("id");

        switch (getIntent().getIntExtra("type", 1)) {
            //text
            case 3:
                int explainResId = getResources().getIdentifier(tutorialId, "string", getPackageName());

                txtTitle.setText(getIntent().getStringExtra("name"));
                txtExplain.setText(getIntent().getStringExtra("url"));

                layoutText.setVisibility(View.VISIBLE);
                break;


            //video
            case 1:
                String path = getFilesDir().getAbsolutePath() + "/.richmans/" + getIntent().getStringExtra("id") +
                        ".mp4";
                if (new File(path).exists()) {
                    Uri uri = Uri.parse(path);
                    videoView.setVideoURI(uri);
                    videoView.start();
                    layoutVideo.setVisibility(View.VISIBLE);

                } else {
                    alert();
                }
                break;

            //sound
            case 2:
                String paths = getFilesDir().getAbsolutePath() + "/.richmans/" +
                        getIntent().getStringExtra("id") +
                        ".mp3";
                if (new File(paths).exists()) {
                    Uri uri = Uri.parse(paths);
                    player = MediaPlayer.create(this, uri);
                    videoView.start();
                    txtSoundName.setText(getIntent().getStringExtra("name"));
                    seekBar.setMax(player.getDuration());
                    layoutVoice.setVisibility(View.VISIBLE);

                } else {
                    alert2();
                }
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            seekHandler.removeCallbacks(run);
                            player.seekTo(progress);
                            seekUpdation();
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });


                break;
        }
    }

    ProgressBar mpb;

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        layoutText = (RelativeLayout) findViewById(R.id.layout_text);
        layoutVideo = (RelativeLayout) findViewById(R.id.layout_video);
        layoutVoice = (RelativeLayout) findViewById(R.id.layout_voice);

        txtTitle = (TextView) findViewById(R.id.txt_title);
        mpb = (ProgressBar) findViewById(R.id.pb);
        txtExplain = (TextView) findViewById(R.id.txt_explain);
        videoView = (VideoView) findViewById(R.id.video);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        btnPlay = (Button) findViewById(R.id.play_button);
        btnNext = (Button) findViewById(R.id.next_button);
        btnPreview = (Button) findViewById(R.id.prev_button);
        btnPlay.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPreview.setOnClickListener(this);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        txtSoundName = (TextView) findViewById(R.id.txt_sound_name);
    }

    Runnable run = new Runnable() {

        @Override
        public void run() {
            seekUpdation();
        }
    };

    public void seekUpdation() {
        //change ms to MM:SS
        seekBar.setProgress(player.getCurrentPosition());
        //less for smoother
        seekHandler.postDelayed(run, 1000);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void alert() {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(TutorialActivity.this,
                    android.R.style.Theme_DeviceDefault_Light_Dialog);
        } else {
            builder = new AlertDialog.Builder(TutorialActivity.this);
        }
        builder.setTitle("Download?")
                .setMessage("فایل موجود نیست.\nآیا مایلید دانلود کنید؟")
                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //handle pos. works
                        mpb.setVisibility(View.VISIBLE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                downloadFile(getIntent().getStringExtra("url"));
                            }
                        }).start();

                    }
                })
                .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //handle neg. works
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);

        builder.show();


    }

    void alert2() {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(TutorialActivity.this,
                    android.R.style.Theme_DeviceDefault_Light_Dialog);
        } else {
            builder = new AlertDialog.Builder(TutorialActivity.this);
        }
        builder.setTitle("Download?")
                .setMessage("فایل موجود نیست.\nآیا مایلید دانلود کنید؟")
                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //handle pos. works
                        mpb.setVisibility(View.VISIBLE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                downloadFile2(getIntent().getStringExtra("url"));
                            }
                        }).start();

                    }
                })
                .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //handle neg. works
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);

        builder.show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_button:
                if (player.isPlaying()) {
                    player.pause();
                    btnPlay.setBackgroundResource(android.R.drawable.ic_media_play);
                } else {
                    player.start();
                    btnPlay.setBackgroundResource(android.R.drawable.ic_media_pause);
                }
                break;
            case R.id.next_button:
                //seekBar
                seekHandler.removeCallbacks(run);
                seekBar.setMax(player.getDuration());
                player.seekTo(player.getCurrentPosition() + 5000);
                seekUpdation();
                //start
                player.start();
                //UI Change
                btnPlay.setBackgroundResource(android.R.drawable.ic_media_pause);
                break;
            case R.id.prev_button:
                //seekBar
                seekHandler.removeCallbacks(run);
                seekBar.setMax(player.getDuration());
                player.seekTo(player.getCurrentPosition() - 5000);
                seekUpdation();
                //start
                player.start();
                //UI Change
                btnPlay.setBackgroundResource(android.R.drawable.ic_media_pause);
                break;
        }
    }

    @Override
    protected void onPause() {

        super.onPause();

        if (videoView != null && videoView.isPlaying()) {
            stopPosition = videoView.getCurrentPosition();
            videoView.pause();

        } else if (player != null && player.isPlaying()) {
            player.pause();
            btnPlay.setBackgroundResource(android.R.drawable.ic_media_play);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (videoView != null) {
            videoView.seekTo(stopPosition);
        }
    }

    void downloadFile(String dwnload_file_path) {

        try {
            URL url = new URL(dwnload_file_path);
            URLConnection urlConnection = url.openConnection();

//            urlConnection.setRequestMethod("GET");
//            urlConnection.setDoOutput(true);

            //connect
            urlConnection.connect();

            File root = getFilesDir();
            File dir = new File(root.getAbsolutePath() + "/.richmans");
            dir.mkdirs();

//            File file = new File(dir, "phn.txt");
            //set the path where we want to save the file
//            File SDCardRoot = Environment.getExternalStorageDirectory();
            //create a new file, to save the downloaded file
            File file = new File(dir, getIntent().getStringExtra("id") + ".mp4");

            FileOutputStream fileOutput = new FileOutputStream(file);

            //Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            //this is the total size of the file which we are downloading
//            totalSize = urlConnection.getContentLength();


            //create a buffer...
            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
//                downloadedSize += bufferLength;
            }
            //close the output stream when complete //
            fileOutput.close();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mpb.setVisibility(View.INVISIBLE);
                    String path = getFilesDir().getAbsolutePath() + "/.richmans/" +
                            getIntent().getStringExtra("id") + ".mp4";
                    Uri uri = Uri.parse(path);
                    videoView.setVideoURI(uri);
                    videoView.start();
                    layoutVideo.setVisibility(View.VISIBLE);
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void downloadFile2(String dwnload_file_path) {

        try {
            URL url = new URL(dwnload_file_path);
            URLConnection urlConnection = url.openConnection();

//            urlConnection.setRequestMethod("GET");
//            urlConnection.setDoOutput(true);

            //connect
            urlConnection.connect();

            File root = getFilesDir();
            File dir = new File(root.getAbsolutePath() + "/.richmans");
            dir.mkdirs();

//            File file = new File(dir, "phn.txt");
            //set the path where we want to save the file
//            File SDCardRoot = Environment.getExternalStorageDirectory();
            //create a new file, to save the downloaded file
            File file = new File(dir, getIntent().getStringExtra("id") + ".mp3");

            FileOutputStream fileOutput = new FileOutputStream(file);

            //Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            //this is the total size of the file which we are downloading
//            totalSize = urlConnection.getContentLength();


            //create a buffer...
            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
//                downloadedSize += bufferLength;
            }
            //close the output stream when complete //
            fileOutput.close();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mpb.setVisibility(View.INVISIBLE);

                    String paths = getFilesDir().getAbsolutePath() + "/.richmans/" +
                            getIntent().getStringExtra("id") + ".mp3";
                    Uri uri = Uri.parse(paths);
                    player = MediaPlayer.create(getBaseContext(), uri);
                    txtSoundName.setText(getIntent().getStringExtra("name"));
                    seekBar.setMax(player.getDuration());
                    layoutVoice.setVisibility(View.VISIBLE);

                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
