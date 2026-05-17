package com.example.roomfinder.models;

public class Room {
    private String title, location, description;
    private int rent;
    private int imageResId; // drawable image

    public Room(String title, String location, String description, int rent, int imageResId) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.rent = rent;
        this.imageResId = imageResId;
    }

    // Getters
    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public int getRent() { return rent; }
    public int getImageResId() { return imageResId; }
}

