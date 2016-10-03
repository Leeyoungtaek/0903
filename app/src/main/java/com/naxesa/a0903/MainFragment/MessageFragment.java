package com.naxesa.a0903.MainFragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naxesa.a0903.R;

import java.util.ArrayList;

/**
 * Created by Lee young teak on 2016-10-02.
 */

public class MessageFragment extends Fragment {

    // RecyclerView
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MessageAdapter adapter;

    // Data
    private ArrayList<String> contents;

    public MessageFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        // Set Data
        contents = new ArrayList<String>();
        contents.add("임시 메세지1");
        contents.add("임시 메세지2");
        contents.add("임시 메세지3");
        contents.add("임시 메세지4");

        // RecyclerView
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new MessageAdapter(contents);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
