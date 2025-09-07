package com.app.rentpg;

import android.annotation.SuppressLint;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RoomAdapter adapter;
    private List<RoomModel> roomList;

    public HomeFragment() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);




        recyclerView = view.findViewById(R.id.recyclerViewRooms);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dummy data
        roomList = new ArrayList<>();
        roomList.add(new RoomModel("101", "Hotel Paradise", "5000", "Sea view deluxe room"));
        roomList.add(new RoomModel("102", "Hotel Paradise", "4500", "City view room"));
        roomList.add(new RoomModel("103", "Hotel Paradise", "6000", "Luxury suite"));

        adapter = new RoomAdapter(roomList);
        recyclerView.setAdapter(adapter);




        return  view;
    }
}