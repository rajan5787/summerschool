package com.example.rajan.popularmovie;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rajan on 22/6/16.
 */
public class CustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Item> marraylist;
    CustomAdapter(Context context, ArrayList<Item> arrayList){
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.marraylist = arrayList;

    }
    @Override
    public int getCount() {
        return marraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return marraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View grid;
        if(convertView == null)
        convertView = layoutInflater.inflate(R.layout.movie_item, null);
            TextView textView = (TextView) convertView.findViewById(R.id.name);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview);
            Item item = marraylist.get(position);
            textView.setText(item.getTitle());
            Picasso.with(context).load(item.getImage()).into(imageView);
        System.out.println("asdf");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = (Item)marraylist.get(position);
                Intent i = new Intent(context,Detail.class);
                i.putExtra("Image",  item.getImage());
                i.putExtra("Overview",item.getOverview());
                i.putExtra("releasedate",  item.getRelease_date());
                i.putExtra("Title",  item.getTitle());
                i.putExtra("Backdrop",  item.getBackdrop());
                i.putExtra("Vote_average",item.getVote_average());
                i.putExtra("Release_date",item.getRelease_date());
              //  i.putExtra("image",  item.getImage());

                context.startActivity(i);
            }
        });
        return convertView;
    }

    public void setdata(ArrayList<Item> marraylist){
        this.marraylist = marraylist;
    }
}
