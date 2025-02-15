package com.dsw.timing.bean;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("latitude")

    public double latitude;

    @SerializedName("timestamp")
    public long timestamp;

    public Location(double longitude, double latitude, long timestamp) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
