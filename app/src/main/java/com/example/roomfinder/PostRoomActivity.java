package com.example.roomfinder;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

    public class PostRoomActivity extends AppCompatActivity {

        EditText etLocation, etRent, etDescription;
        Spinner spinnerSharingOption;
        Button btnPostRoom;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_post_room);

            // Initialize views
            etLocation = findViewById(R.id.etLocation);
            etRent = findViewById(R.id.etRent);
            etDescription = findViewById(R.id.etDescription);
            spinnerSharingOption = findViewById(R.id.spinnerSharingOption);
            btnPostRoom = findViewById(R.id.btnPostRoom);

            // Setup the spinner for sharing options
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.sharing_options, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSharingOption.setAdapter(adapter);

            // Post room button click listener
            btnPostRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postRoom();
                }
            });
        }

        private void postRoom() {
            String location = etLocation.getText().toString().trim();
            String rent = etRent.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String sharingOption = spinnerSharingOption.getSelectedItem().toString();

            // Validation
            if (location.isEmpty() || rent.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Here, you can handle saving the data, such as storing in a local database or showing a confirmation
            Toast.makeText(this, "Room posted successfully!", Toast.LENGTH_SHORT).show();

            // Optionally clear fields after posting
            etLocation.setText("");
            etRent.setText("");
            etDescription.setText("");
            spinnerSharingOption.setSelection(0);
        }
    }

