package com.naxesa.a0903.MainFragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naxesa.a0903.R;

import java.util.ArrayList;

/**
 * Created by Lee young teak on 2016-10-02.
 */

public class MessageAdapter extends RecyclerView.Adapter {

    private ArrayList<String> contents;

    MessageAdapter(ArrayList<String> contents){
        this.contents = contents;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MessageHolder)holder).content.setText(contents.get(position));
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public static class MessageHolder extends RecyclerView.ViewHolder{
        TextView content;
        public MessageHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
