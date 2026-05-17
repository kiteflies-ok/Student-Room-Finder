package com.example.roomfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;


import androidx.appcompat.app.AppCompatActivity;

public class RoomDetailsActivity extends AppCompatActivity {

    TextView title, location, rent, description;
    ImageView image;
    Button btnCall, btnBookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);
        // Make sure your XML filename is room_details_activity.xml

        // Link all the views
        title = findViewById(R.id.detailTitle);
        location = findViewById(R.id.detailLocation);
        rent = findViewById(R.id.detailRent);
        description = findViewById(R.id.detailDescription);
        image = findViewById(R.id.detailImage);

        btnCall = findViewById(R.id.btnCall);
        btnBookNow = findViewById(R.id.btnBookNow);

        // Get data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            title.setText(intent.getStringExtra("title"));
            location.setText(intent.getStringExtra("location"));
            rent.setText(intent.getStringExtra("rent"));
            description.setText(intent.getStringExtra("description"));
            image.setImageResource(R.drawable.room1);
            image.setImageResource(R.drawable.room2);
            image.setImageResource(R.drawable.room3);
            image.setImageResource(R.drawable.room4);
            image.setImageResource(R.drawable.room5);
            image.setImageResource(R.drawable.room6);;// Replace with dynamic image later if needed
        }

        // Dummy phone number for call button (can be passed via intent too)
        String phoneNumber = "9876543210";
        Button btnWhatsapp = findViewById(R.id.btnWhatsapp);
        String whatsappNumber = "91" + phoneNumber; // Add country code
        Button btnDirection = findViewById(R.id.btnDirection);


        // Call Owner Button
        btnCall.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            String url = "https://wa.me/" + whatsappNumber + "?text=" + Uri.encode("Hi, I'm interested in the room you listed.");
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            startActivity(callIntent);
        });

        // Book Now Button
        btnBookNow.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(RoomDetailsActivity.this);
            builder.setTitle("Confirm Booking")
                    .setMessage("Do you want to book this room?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Toast.makeText(RoomDetailsActivity.this, "Room booked successfully!", Toast.LENGTH_LONG).show();
                        // You can add Firebase logic here
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });


        btnDirection.setOnClickListener(v -> {
            String locationQuery = location.getText().toString(); // get from textview
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(locationQuery));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });


    }
}
