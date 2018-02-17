package com.example.asus.richmans;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;


public class SubCategoryRecyclerAdapter extends RecyclerView.Adapter<SubCategoryViewHolder> {

    private List<SubCategory> subCats;
    private Context context;
    private String[] allSubCats = {
            "موبایل",
            "تبلت و کتابخوان",
            "لپ تاپ",
            "دوربین",
            "کامپیوتر و تجهیزات جانبی",
            "ماشین های اداری",
            "لوازم جانبی کالای دیجیتال",
            "مردانه",
            "زنانه",
            "بچگانه",
            "ورزشی",
            "عطر",
            "ساعت",
            "اکسسوری لوازم شخصی", "صوتی و تصویری",
            "لوازم خانگی برقی",
            "آشپزخانه",
            "سرو و پذیرایی",
            "دکوراتیو",
            "خواب حمام",
            "شستشو و نظافت",
            "ابزار غیر برقی",
            "ابزار برقی",
            "باغبانی",
            "نور و روشنایی", "لوازم آرایشی",
            "لوازم بهداشتی",
            "لوازم شخصی برقی",
            "عینک آفتابی",
            "زیورآلات",
            "ابزار سلامت", "کتاب و مجلات",
            "لوازم التحریر",
            "صنایع دستی",
            "فرش",
            "آلات موسیسقی",
            "فیلم",
            "نرم افزار و بازی",
            "محتوای آموزشی", "پوشاک ورزشی",
            "کفش ورزشی",
            "لوازم ورزشی",
            "دوچرخه و لوازم جانبی",
            "تجهیزات سفر",
            "اسباب سفر",
            "حیوانات خانگی", "ایمنی و مراقبت",
            "غذاخوری",
            "لوازم شخصی",
            "بهداشت و حمام",
            "گردش و سفر",
            "سرگرمی و آموزشی",
            "خواب کودک", "خودرو",
            "لوازم جانبی خودرو",
            "لوازم مصرفی خودرو",
            "موتور سیکلت",
            "لوازم جانبی موتور سیکلت",
            "لوازم مصرفی موتور سیکلت",
            "انبارداری صنعتی", "دندانپزشکی",
            "باشگاه",
            "کلاس های آزاد",
            "هتل",
            "تور مسافرتی",
            "کنسرت"
    };

    public SubCategoryRecyclerAdapter(Context context, List<SubCategory> subCats) {
        this.subCats = subCats;
        this.context = context;
    }

    @Override
    public SubCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcat_recycler_item, parent, false);
        return new SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SubCategoryViewHolder holder, int position) {
        SubCategory subCat = subCats.get(position);

        holder.name.setText(subCat.name);
        Glide.with(context).load(subCat.image).into(holder.image);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to Product list
//                String subCatName = holder.name.toString();
//                switch (subCatName) {
//                    case allSubCats[0]:
//                        Toast.makeText(context, "ssss", Toast.LENGTH_SHORT).show();
//                        break;
//                ....
//                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return subCats.size();
    }
}
