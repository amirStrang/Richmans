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
        tutorial1.name = "بازی که بازی نیست";
        tutorial1.explain = "    اگر همه مردم دنیا را در یک جا جمع کنیم و به آنها بگوییی هر کس قبول دارد که ثروتمند شدن یک مساله ذهنی است برود و در سمت راست بایستد و هر کس قبول ندارد در سمت چپ، فکر می کنید چند درصد از مردم در سمت چپ می ایستند؟ شاید درست حدس زده باشید: بیش از 90 درصد مردم باور نمی کنند و قبول ندارند که فرایند ثروتمند شدن از ذهن انسانها و باورهای آنها آغاز بشود و خیلی زود می روند و در سمت چپ می ایستند!!.نکته جالب اینکه 100 درصد از این 90 درصد که در سمت چپ ایستاده اند آدمهای فقیر و یا متوسط به پایین هستند و جالب تر اینکه اغلب آن 10 درصدی که در سمت راست ایستاده اند انسانهای بسیار ثروتمندی اند. تقریبا می توانیم ادعا کنیم تمام اساتیدی که در دنیا در زمینه موضوع کسب ثروت آموزش می دهند و تجربیات خودشان و دیگر ثروتمندان را مورد بررسی قرار داده اند، در این نکته اشتراک نظر دارند که شخصی که ذهن ثروتمندی دارد، بخش اعظمی از راه رسیدن به ثروت زیاد را پیموده است.این ادعا در مورد اساتیدی که بر مبنای قانون جذب آموزش می دهند با قاطعیت صد در صد است. بازی ثروتمندان (یا همان بازی فراوانی) با تلاش شبانه روزی یک تیم 4 نفره طراحی گردیده است و تمام اصول آموزش به ذهن در آن رعایت شده است و اگر به درستی و با انگیزه خوب از آن استفاده کنید در عرض چند هفته نشانه های بسیار خوبی را از ثروتمندتر شدن به شما خواهد داد و بعد از چند ماه می تواند ذهن شما را ثروتمند کند و شما شاهد جریان ثروت بیشتر به سمت خودتان باشید. بازی ثروتمندان را نصب کنید و با این کار مهم ترین گام را برای ساختن ذهن ثروتمندتان بردارید";
        tutorial1.image = R.drawable.sample1;
        tutorials.add(tutorial1);


        Tutorial tutorial2 = new Tutorial();
        tutorial2.name = "قواعد بازی";
        tutorial2.explain = "آموزشآموزشآموزشآموزشآموزشآموزشآموزشآموزشآموزشآموزشآموزشآموزشآموزشآموزشآموزش";
        tutorial2.image = R.drawable.sample2;
        tutorials.add(tutorial2);

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
