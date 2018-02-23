package com.example.asus.richmans;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class StoreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    RecyclerView recyclerViewSubCats;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    //category btns
    Button btnCat1, btnCat2, btnCat3, btnCat4, btnCat5, btnCat6, btnCat7, btnCat8, btnCat9;

    //samples
    String[] cat_mobile_names = {
            "موبایل",
            "تبلت و کتابخوان",
            "لپ تاپ",
            "دوربین",
            "کامپیوتر و تجهیزات جانبی",
            "ماشین های اداری",
            "لوازم جانبی کالای دیجیتال"
    };

    int[] cat_mobile_images = {
            R.drawable.mobile,
            R.drawable.tablet_ebook_reader,
            R.drawable.laptop,
            R.drawable.camera,
            R.drawable.computer_parts,
            R.drawable.office_machines,
            R.drawable.accessories_main,
    };

    String[] cat_cloth_names = {
            "مردانه",
            "زنانه",
            "بچگانه",
            "ورزشی",
            "عطر",
            "ساعت",
            "اکسسوری لوازم شخصی"
    };

    int[] cat_cloth_images = {
            R.drawable.men,
            R.drawable.wemen,
            R.drawable.kids_apparel,
            R.drawable.sports,
            R.drawable.atr,
            R.drawable.watch_clock,
            R.drawable.pesonal_appliance_accessories,
    };


    String[] cat_home_names = {
            "صوتی و تصویری",
            "لوازم خانگی برقی",
            "آشپزخانه",
            "سرو و پذیرایی",
            "دکوراتیو",
            "خواب حمام",
            "شستشو و نظافت",
            "ابزار غیر برقی",
            "ابزار برقی",
            "باغبانی",
            "نور و روشنایی",
            "خوراکی و آشامیدنی"
    };

    int[] cat_home_images = {
            R.drawable.video_audio_entertainment,
            R.drawable.home_appliance,
            R.drawable.home_kitchen,
            R.drawable.serving,
            R.drawable.decorative,
            R.drawable.towel,
            R.drawable.cleaning,
            R.drawable.non_electrical_tools,
            R.drawable.power_tools,
            R.drawable.gardening,
            R.drawable.lighting,
            R.drawable.food
    };

    String[] cat_cosmetic_names = {
            "لوازم آرایشی",
            "لوازم بهداشتی",
            "لوازم شخصی برقی",
            "عینک آفتابی",
            "زیورآلات",
            "ابزار سلامت"
    };

    int[] cat_cosmetic_images = {
            R.drawable.beauty,
            R.drawable.hair_clipper,
            R.drawable.electrical_personal_care,
            R.drawable.sunglasses,
            R.drawable.jewelery,
            R.drawable.health_care,
    };

    String[] cat_culture_names = {
            "کتاب و مجلات",
            "لوازم التحریر",
            "صنایع دستی",
            "فرش",
            "آلات موسیسقی",
            "موسیقی",
            "فیلم",
            "نرم افزار و بازی",
            "محتوای آموزشی"
    };

    int[] cat_culture_images = {
            R.drawable.publication,
            R.drawable.stationery,
            R.drawable.handicraft,
            R.drawable.carpet,
            R.drawable.musicalinstruments,
            R.drawable.music_audio_content,
            R.drawable.film_video_content,
            R.drawable.software_games,
            R.drawable.multimedia_training_pack,
    };

    String[] cat_sport_names = {
            "پوشاک ورزشی",
            "کفش ورزشی",
            "لوازم ورزشی",
            "دوچرخه و لوازم جانبی",
            "تجهیزات سفر",
            "اسباب بازی",
            "حیوانات خانگی"
    };

    int[] cat_sport_images = {

            R.drawable.sports_wear,
            R.drawable.sportshoes,
            R.drawable.sports,
            R.drawable.bicycle,
            R.drawable.traveling_equipment,
            R.drawable.toys,
            R.drawable.pet,
    };

    String[] cat_kids_names = {
            "ایمنی و مراقبت",
            "غذاخوری",
            "لوازم شخصی",
            "بهداشت و حمام",
            "گردش و سفر",
            "سرگرمی و آموزشی",
            "خواب کودک"
    };

    int[] cat_kids_images = {
            R.drawable.safety_and_care,
            R.drawable.dining_accessories,
            R.drawable.personal,
            R.drawable.health_and_bathroom_yools,
            R.drawable.travel,
            R.drawable.entertainment_and_games_equipment,
            R.drawable.baby_bedding,
    };

    String[] cat_car_names = {
            "خودرو",
            "لوازم جانبی خودرو",
            "لوازم مصرفی خودرو",
            "موتور سیکلت",
            "لوازم جانبی موتور سیکلت",
            "لوازم مصرفی موتور سیکلت",
    };

    int[] cat_car_images = {
            R.drawable.cars,
            R.drawable.car_accessory_parts,
            R.drawable.consumable_parts,
            R.drawable.motorbike,
            R.drawable.helmets,
            R.drawable.motor_tools,
    };

    String[] cat_service_names = {
            "دندانپزشکی",
            "باشگاه",
            "کلاس های آزاد",
            "هتل",
            "تور مسافرتی",
            "کنسرت"
    };

    int[] cat_service_images = {
            R.drawable.dentist,
            R.drawable.gym,
            R.drawable.edu_class,
            R.drawable.hotel,
            R.drawable.tor_travel,
            R.drawable.consert,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_cart:
                Intent i = new Intent(this, MyShopActivity.class);
                startActivity(i);
                break;
            case R.id.item_search:
                toast("search");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerViewSubCats = (RecyclerView) findViewById(R.id.rec_subCats);
        List<SubCategory> subCats = new ArrayList<>();
        //initialize list
        for (int i = 0; i < cat_mobile_images.length; i++) {
            SubCategory subCategory = new SubCategory();
            subCategory.image = cat_mobile_images[i];
            subCategory.name = cat_mobile_names[i];
            subCats.add(subCategory);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSubCats.setLayoutManager(linearLayoutManager);
        recyclerViewSubCats.setHasFixedSize(true);
        recyclerViewSubCats.setAdapter(new SubCategoryRecyclerAdapter(getApplicationContext(), subCats));
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, VERTICAL);
        recyclerViewSubCats.addItemDecoration(itemDecor);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    public void GoToCat(View view) {
        int id = view.getId();
        List<SubCategory> subCats = new ArrayList<>();
        switch (id) {
            case R.id.btn_cat1:
                for (int i = 0; i < cat_mobile_images.length; i++) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.image = cat_mobile_images[i];
                    subCategory.name = cat_mobile_names[i];
                    subCats.add(subCategory);
                }
                recyclerViewSubCats.setAdapter(new SubCategoryRecyclerAdapter(getApplicationContext(), subCats));
                break;
            case R.id.btn_cat2:
                for (int i = 0; i < cat_cloth_names.length; i++) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.image = cat_cloth_images[i];
                    subCategory.name = cat_cloth_names[i];
                    subCats.add(subCategory);
                }
                recyclerViewSubCats.setAdapter(new SubCategoryRecyclerAdapter(getApplicationContext(), subCats));
                break;
            case R.id.btn_cat3:
                for (int i = 0; i < cat_home_names.length; i++) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.image = cat_home_images[i];
                    subCategory.name = cat_home_names[i];
                    subCats.add(subCategory);
                }
                recyclerViewSubCats.setAdapter(new SubCategoryRecyclerAdapter(getApplicationContext(), subCats));
                break;
            case R.id.btn_cat4:
                for (int i = 0; i < cat_cosmetic_names.length; i++) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.image = cat_cosmetic_images[i];
                    subCategory.name = cat_cosmetic_names[i];
                    subCats.add(subCategory);
                }
                recyclerViewSubCats.setAdapter(new SubCategoryRecyclerAdapter(getApplicationContext(), subCats));
                break;
            case R.id.btn_cat5:
                for (int i = 0; i < cat_culture_names.length; i++) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.image = cat_culture_images[i];
                    subCategory.name = cat_culture_names[i];
                    subCats.add(subCategory);
                }
                recyclerViewSubCats.setAdapter(new SubCategoryRecyclerAdapter(getApplicationContext(), subCats));
                break;
            case R.id.btn_cat6:
                for (int i = 0; i < cat_sport_names.length; i++) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.image = cat_sport_images[i];
                    subCategory.name = cat_sport_names[i];
                    subCats.add(subCategory);
                }
                recyclerViewSubCats.setAdapter(new SubCategoryRecyclerAdapter(getApplicationContext(), subCats));
                break;
            case R.id.btn_cat7:
                for (int i = 0; i < cat_kids_names.length; i++) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.image = cat_kids_images[i];
                    subCategory.name = cat_kids_names[i];
                    subCats.add(subCategory);
                }
                recyclerViewSubCats.setAdapter(new SubCategoryRecyclerAdapter(getApplicationContext(), subCats));
                break;
            case R.id.btn_cat8:
                for (int i = 0; i < cat_car_names.length; i++) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.image = cat_car_images[i];
                    subCategory.name = cat_car_names[i];
                    subCats.add(subCategory);
                }
                recyclerViewSubCats.setAdapter(new SubCategoryRecyclerAdapter(getApplicationContext(), subCats));
                break;
            case R.id.btn_cat9:
                for (int i = 0; i < cat_service_names.length; i++) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.image = cat_service_images[i];
                    subCategory.name = cat_service_names[i];
                    subCats.add(subCategory);
                }
                recyclerViewSubCats.setAdapter(new SubCategoryRecyclerAdapter(getApplicationContext(), subCats));
                break;

        }

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent i;
        switch (id) {
            case R.id.item_home:
                i = new Intent(getApplicationContext(), StoreActivity.class);
                startActivity(i);
                this.finish();
                break;
            case R.id.item_list:

                break;
            case R.id.item_my_shop:
                i = new Intent(getApplicationContext(), MyShopActivity.class);
                startActivity(i);
                this.finish();
                break;
        }
        return true;
    }

    void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
