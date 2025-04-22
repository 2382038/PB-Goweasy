package com.example.final_goweasyapp;

public class Bike {
    private String bikeId;
    private String name;
    private String location;
    private String image;

    // Constructor
    public Bike(String bikeId, String name, String location, String image) {
        this.bikeId = bikeId;
        this.name = name;
        this.location = location;
        this.image = image;
    }

    // Getter and Setter Methods
    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
