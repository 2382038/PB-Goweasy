package com.example.final_goweasyapp;

public class Rental {
    private String bikeId;
    private String startDate;
    private String duration;
    private String paymentMethod;
    private double totalAmount;

    // Constructor
    public Rental(String bikeId, String startDate, String duration, String paymentMethod, double totalAmount) {
        this.bikeId = bikeId;
        this.startDate = startDate;
        this.duration = duration;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
    }

    // Getter and Setter Methods
    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
