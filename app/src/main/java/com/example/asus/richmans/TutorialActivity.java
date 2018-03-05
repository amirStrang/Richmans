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
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

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

    RelativeLayout layoutText, layoutVoice, layoutVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        init();

        String tutorialId = getIntent().getStringExtra("id");

        switch (getIntent().getIntExtra("type", 1)) {
            //text
            case 1:
                int explainResId = getResources().getIdentifier(tutorialId, "string", getPackageName());

                txtTitle.setText(getIntent().getStringExtra("name"));
                txtExplain.setText(getString(explainResId));

                layoutText.setVisibility(View.VISIBLE);

                break;
            //video
            case 2:
                int videoResId = getResources().getIdentifier(tutorialId, "raw", getPackageName());
                path = "android.resource://" + getPackageName() + "/" + videoResId;
                Uri uri = Uri.parse(path);
                videoView.setVideoURI(uri);
                videoView.start();

                layoutVideo.setVisibility(View.VISIBLE);

                break;
            //sound
            case 3:
                int soundResId = getResources().getIdentifier(tutorialId, "raw", getPackageName());

                txtSoundName.setText(getIntent().getStringExtra("name"));
                sound = soundResId;
                player = MediaPlayer.create(this, sound);
                seekBar.setMax(player.getDuration());

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

                layoutVoice.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layoutText = (RelativeLayout) findViewById(R.id.layout_text);
        layoutVideo = (RelativeLayout) findViewById(R.id.layout_video);
        layoutVoice = (RelativeLayout) findViewById(R.id.layout_voice);

        txtTitle = (TextView) findViewById(R.id.txt_title);
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
