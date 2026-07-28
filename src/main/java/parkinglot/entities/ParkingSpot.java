package parkinglot.entities;

import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleSize;

public class ParkingSpot {
    private final String spotId;
    private final VehicleSize spotSize;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    // constructors
    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        isOccupied = false;
        parkedVehicle = null;
    }

    // getters
    public String getSpotId() {
        return spotId;
    }
    public VehicleSize getSpotSize() {
        return spotSize;
    }
    public boolean isAvailable() {
        return !isOccupied;
    }

    // operations on parking spot
    public synchronized void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public synchronized void unparkVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return !isOccupied && vehicle.getSize().ordinal() <= spotSize.ordinal();
    }

}
