package com.example.latoris.recyclerviewpractise;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Latoris on 2016/8/31.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MviewHolder>{

    private List<Photo> Photos;
    private Context mContext;
    private LayoutInflater inflater;

    public RecyclerAdapter(Context context, List<Photo> photoes){
        this.mContext = context;
        this.Photos = photoes;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount(){
        return Photos.size();
    }

    @Override
    public MviewHolder onCreateViewHolder(ViewGroup viewgroup, int i){
        View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.fuli,viewgroup, false);
        return new MviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MviewHolder holder, final int position){
        holder.imageView.setImageResource(Photos.get(position).getImg());
        holder.tv.setText(Photos.get(position).getTitle());
    }


    class MviewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tv;
        public MviewHolder(View view){
            super(view);
            imageView = (ImageView)view.findViewById(R.id.fuliimage);
            tv = (TextView)view.findViewById(R.id.fulititle);
        }
    }
}
