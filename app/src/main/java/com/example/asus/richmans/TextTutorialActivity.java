package com.example.asus.richmans;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TextTutorialActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView txtTitle, txtExplain;

    VideoView videoView;
    String path;

    SeekBar seekBar;
    Button btnPlay, btnNext, btnPreview;
    MediaPlayer player;
    Handler seekHandler = new Handler();
    int song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_tutorial);

        init();

        txtTitle.setText(getIntent().getStringExtra("name"));
        txtExplain.setText(getIntent().getStringExtra("explain"));

        //sample
        path = "android.resource://" + getPackageName() + "/" + R.raw.arrow;
        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);
        videoView.start();

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
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtExplain = (TextView) findViewById(R.id.txt_explain);
        videoView = (VideoView) findViewById(R.id.video);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        song = R.raw.helali;
        player = MediaPlayer.create(this, song);
        btnPlay = (Button) findViewById(R.id.play_button);
        btnNext = (Button) findViewById(R.id.next_button);
        btnPreview = (Button) findViewById(R.id.prev_button);
        btnPlay.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPreview.setOnClickListener(this);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setMax(player.getDuration());
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
}
