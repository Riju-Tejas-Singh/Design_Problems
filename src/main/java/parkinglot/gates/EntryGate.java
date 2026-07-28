package parkinglot.gates;

import parkinglot.ParkingLot;
import parkinglot.entities.ParkingTicket;
import parkinglot.vehicle.Vehicle;

import java.util.Optional;

public class EntryGate {
    private final ParkingLot parkingLot;

    public EntryGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Optional<ParkingTicket> processEntry(Vehicle vehicle) {
        System.out.println("Entry Gate: Processing vehicle " + vehicle.getLicenseNumber());
        return parkingLot.parkVehicleAndGenerateTicket(vehicle);
    }
}
