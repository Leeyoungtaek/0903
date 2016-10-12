package com.naxesa.a0903.MainFragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naxesa.a0903.Connect;
import com.naxesa.a0903.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private String id;

    public MessageFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra("Id");

        // Set Data
        contents = new ArrayList<String>();
        addContents();

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

    private void addContents(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject object = new JSONObject();
                    object.put("Command", 113);
                    object.put("Id", id);
                    final JSONObject result = Connect.postData(object);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if(result.getBoolean("Command")){
                                    JSONArray array = result.getJSONArray("Data");
                                    for(int i = 0; i<array.length(); i++){
                                        contents.add(array.getString(i));
                                    }
                                    adapter.notifyDataSetChanged();
                                }else{
                                    Toast.makeText(getContext(), "메세지를 받아오지 못했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
