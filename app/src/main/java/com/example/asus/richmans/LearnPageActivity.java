package com.example.asus.richmans;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LearnPageActivity extends AppCompatActivity {

    RecyclerView recTutorials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_page);

        recTutorials = (RecyclerView) findViewById(R.id.rec_tutorials);
        List<Tutorial> tutorials = new ArrayList<>();


        //sample init

        Tutorial tutorial1 = new Tutorial();
        tutorial1.id = "tt1";
        tutorial1.name = "بازی که بازی نیست";
        tutorial1.explain = getString(getResources().getIdentifier("tt1", "string", getPackageName()));
        tutorial1.type = 1;
        tutorials.add(tutorial1);


        Tutorial tutorial2 = new Tutorial();
        tutorial2.id = "tt2";
        tutorial2.name = "قواعد بازی";
        tutorial2.explain = getString(getResources().getIdentifier("tt2", "string", getPackageName()));
        tutorial2.type = 1;
        tutorials.add(tutorial2);

        Tutorial tutorialVideo = new Tutorial();
        tutorialVideo.id = "tv1";
        tutorialVideo.name = "یه فیلمی";
        tutorialVideo.explain = "یه فیلمی دیگه توضیح نمیخواد :)";
        tutorialVideo.type = 2;
        tutorials.add(tutorialVideo);

        Tutorial tutorialSound = new Tutorial();
        tutorialSound.id = "ts1";
        tutorialSound.name="یه صدایی";
        tutorialSound.explain="یه صدایی دیگه نمونست";
        tutorialSound.type=3;
        tutorials.add(tutorialSound);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recTutorials.setLayoutManager(linearLayoutManager);
        recTutorials.setHasFixedSize(true);
        recTutorials.setAdapter(new TutorialRecyclerAdapter(getApplicationContext(), tutorials));
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recTutorials.addItemDecoration(itemDecor);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
