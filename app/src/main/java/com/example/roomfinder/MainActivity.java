package com.example.roomfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.SearchView;
import com.example.roomfinder.adapters.RoomAdapter;
import com.example.roomfinder.models.Room;
import java.util.ArrayList;
import java.util.List;
import android.content.SharedPreferences;
import android.view.View;
import android.content.Intent;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    Button btnPostRoom;
    List<Room> roomList;
    RoomAdapter adapter;
    SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "user_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        btnPostRoom = findViewById(R.id.btnPostRoom);
        roomList = new ArrayList<>();

        roomList = new ArrayList<>();
        loadDummyData();

        adapter = new RoomAdapter(this, roomList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Room> filtered = new ArrayList<>();
                for (Room room : roomList) {
                    if (room.getLocation().toLowerCase().contains(newText.toLowerCase())) {
                        filtered.add(room);
                    }
                }
                adapter = new RoomAdapter(MainActivity.this, filtered);
                recyclerView.setAdapter(adapter);
                return true;
            }
        });


    // ✅ Button click now inside onCreate
    btnPostRoom.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, PostRoomActivity.class);
            startActivity(intent);
        }
    });
}

    private void loadDummyData() {
        roomList.add(new Room("Sharing Room in Akurdi", "Akurdi", "Spacious Sharing room near college", 4000, R.drawable.room1));
        roomList.add(new Room("Sharing Room in Nigdi", "Nigdi", "2 Sharing with balcony", 3500, R.drawable.room2));
        roomList.add(new Room("Paying Guest Room in Chinchwad", "Chinchwad", "Pg with kitchen", 7000, R.drawable.room3));
        roomList.add(new Room("Paying Guest Room in Pimpri", "Pimpri", "Fully furnished PG", 5500, R.drawable.room4));
        roomList.add(new Room("Single room in Nigdi", "Pimpri", "Fully furnished PG", 6000, R.drawable.room5));
        roomList.add(new Room("Room with attached balcony in Pimpri", "Pimpri", "Fully furnished PG", 8000, R.drawable.room6));

    }
}
