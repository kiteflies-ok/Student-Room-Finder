package com.example.roomfinder.adapters;

import android.content.Context;
import com.example.roomfinder.RoomDetailsActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomfinder.R;
import com.example.roomfinder.models.Room;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    Context context;
    List<Room> roomList;

    public RoomAdapter(Context context, List<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_room, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.title.setText(room.getTitle());
        holder.location.setText(room.getLocation());
        holder.rent.setText("₹" + room.getRent());
        holder.image.setImageResource(room.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RoomDetailsActivity.class);
            intent.putExtra("title", room.getTitle());
            intent.putExtra("location", room.getLocation());
            intent.putExtra("rent", room.getRent());
            intent.putExtra("desc", room.getDescription());
            intent.putExtra("image", room.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, rent;
        ImageView image;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            location = itemView.findViewById(R.id.textLocation);
            rent = itemView.findViewById(R.id.textRent);
            image = itemView.findViewById(R.id.imageRoom);
        }
    }
}
