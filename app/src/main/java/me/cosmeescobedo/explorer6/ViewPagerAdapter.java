package me.cosmeescobedo.explorer6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private String[] imgUrls;
    private String[] years;

    public ViewPagerAdapter(Context context, String[] imgUrls, String[] years) {
        this.context = context;
        this.imgUrls = imgUrls;
        this.years = years;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.image_page, container, false);
        ImageView imageView = v.findViewById(R.id.imagePage);
        TextView txtYear = v.findViewById(R.id.yearPage);
        Picasso.get().load(imgUrls[position]).fit().centerCrop().into(imageView);
        txtYear.setText(years[position]);
        container.addView(v);
        return v;
    }

    @Override
    public int getCount() {
        return imgUrls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
