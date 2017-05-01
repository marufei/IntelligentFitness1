package com.health.demo.intelligentfitness.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.health.demo.intelligentfitness.R;
import com.health.demo.intelligentfitness.bean.HomeFragmentListBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marufei
 * time on 2017/4/21
 */

public class LvHealthAdapter extends BaseAdapter {
    private Context context;
    private List<HomeFragmentListBean.DataBean> list;
    public LvHealthAdapter(Context context,List<HomeFragmentListBean.DataBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            convertView=View.inflate(context, R.layout.item_list,null);
            viewHolder.iv_item_pic= (ImageView) convertView.findViewById(R.id.iv_item_pic);
            viewHolder.tv_item_title=(TextView)convertView.findViewById(R.id.tv_item_title);
            viewHolder.tv_item_content=(TextView)convertView.findViewById(R.id.tv_item_content);
            viewHolder.tv_item_time=(TextView)convertView.findViewById(R.id.tv_item_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.tv_item_title.setText(list.get(position).getTitle());
        viewHolder.tv_item_content.setText(list.get(position).getDescribe());
        viewHolder.tv_item_time.setText(list.get(position).getFcreate_time());
        Picasso.with(context).load(list.get(position).getCover()).into(viewHolder.iv_item_pic);
        return convertView;
    }
    class ViewHolder{
        TextView tv_item_time;
        TextView tv_item_title;
        TextView tv_item_content;
        ImageView iv_item_pic;
    }
}
