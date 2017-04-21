package com.health.demo.intelligentfitness.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marufei
 * time on 2017/4/21
 */

public class LvHealthAdapter extends BaseAdapter {
    private Context context;
    private List<String> listTitle=new ArrayList<>();
    public LvHealthAdapter(Context context,List<String> listTitle){
        this.context=context;
        this.listTitle=listTitle;
    }
    @Override
    public int getCount() {
        return listTitle.size();
    }

    @Override
    public Object getItem(int position) {
        return listTitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
