package com.viteats.vitetats1.model;

public class Driver extends User {
    private String driverID;
    private String vehicleInfo;
    private boolean isAvailable;

    public Driver(String username, String password, String driverID, String vehicleInfo, boolean isAvailable) {
        super(username, password, "Driver");
        this.driverID = driverID;
        this.vehicleInfo = vehicleInfo;
        this.isAvailable = isAvailable;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(String vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
