package com.health.demo.intelligentfitness.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.health.demo.intelligentfitness.R;
import com.health.demo.intelligentfitness.util.MyUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by marufei
 * time on 2017/5/4
 */

public class LvEvaluateAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,String>>listMap=new ArrayList<>();
    public LvEvaluateAdapter(Context context,List<Map<String,String>>listMap){
        this.context=context;
        this.listMap=listMap;
    }
    @Override
    public int getCount() {
        return listMap.size();
    }

    @Override
    public Object getItem(int position) {
        return listMap.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(context, R.layout.item_evaluate,null);
            viewHolder.iv_pass= (ImageView) convertView.findViewById(R.id.iv_item_pass);
            viewHolder.tv_plan=(TextView)convertView.findViewById(R.id.tv_item_plan);
            viewHolder.tv_real=(TextView)convertView.findViewById(R.id.tv_item_real);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.tv_real.setText(listMap.get(position).get("real"));
        viewHolder.tv_plan.setText(listMap.get(position).get("plan"));
        MyUtils.Loge("aaa","real:"+listMap.get(position).get("real"));
        MyUtils.Loge("aaa","plan:"+listMap.get(position).get("plan"));
        double real=Double.valueOf(listMap.get(position).get("real"));
        double plan=Double.valueOf(listMap.get(position).get("plan"));
        if(real>=plan){
            Picasso.with(context).load(R.drawable.vector_drawable_pass).into(viewHolder.iv_pass);
        }else {
            Picasso.with(context).load(R.drawable.vector_drawable_no_pass).into(viewHolder.iv_pass);
        }
        return convertView;
    }
    class ViewHolder{
        TextView tv_plan;
        TextView tv_real;
        ImageView iv_pass;
    }
}
