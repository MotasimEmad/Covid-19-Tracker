package com.example.covid19.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.covid19.R;
import com.example.covid19.models.Model;

import java.util.List;

public class AboutAdapter extends PagerAdapter {

    private List<Model> models;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public AboutAdapter(List<Model> models , Context mContext){
        this.models = models;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.instructions_container , container , false);
        ImageView mImageView;
        TextView title;

        mImageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);

        mImageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());

        container.addView(view , 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
