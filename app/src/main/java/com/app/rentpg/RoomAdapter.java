package com.app.rentpg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<RoomModel> roomList;

    public RoomAdapter(List<RoomModel> roomList) {
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_item_recycler, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        RoomModel room = roomList.get(position);
        holder.textRoomNumber.setText("Room No: " + room.getRoomNumber());
        holder.textHotelName.setText(room.getHotelName());
        holder.textPrice.setText("₹" + room.getPrice());
        holder.textDescription.setText(room.getDescription());
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView textRoomNumber, textHotelName, textPrice, textDescription;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            textRoomNumber = itemView.findViewById(R.id.roomNumber);
            textHotelName = itemView.findViewById(R.id.hotelName);
            textPrice = itemView.findViewById(R.id.priceValue);
            textDescription = itemView.findViewById(R.id.description);
        }
    }
}