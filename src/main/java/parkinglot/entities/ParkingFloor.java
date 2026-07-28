package parkinglot.entities;

import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleSize;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ParkingFloor {
    private final int floorNumber;
    private final Map<String, ParkingSpot> spots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new ConcurrentHashMap<>();
    }

    public Optional<ParkingSpot> findAvailableSpot(Vehicle vehicle) {
        return spots.values().stream().filter(spot -> spot.canFitVehicle(vehicle)).
                min(Comparator.comparing(ParkingSpot::getSpotSize));
    }

    public void displayAvailability() {
        System.out.printf("--- Floor %d Availability ---\n", floorNumber);
        Map<VehicleSize, Long> availableCounts = spots.values().stream()
                .filter(ParkingSpot::isAvailable)
                .collect(Collectors.groupingBy(ParkingSpot::getSpotSize, Collectors.counting()));

        for (VehicleSize size : VehicleSize.values()) {
            System.out.printf("  %s spots: %d\n", size, availableCounts.getOrDefault(size, 0L));
        }
    }

    // getter & setter
    public int getFloorNumber() {
        return floorNumber;
    }

    public void addSpot(ParkingSpot parkingSpot) {
        spots.put(parkingSpot.getSpotId(), parkingSpot);
    }
}
