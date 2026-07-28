package parkinglot.gates;

import parkinglot.ParkingLot;

import java.util.Optional;

public class ExitGate {
    private final ParkingLot parkingLot;

    public ExitGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Optional<Double> processExit(String ticketId) {
        System.out.println("Exit Gate: Processing ticket " + ticketId);
        return parkingLot.unparkVehicleAndCalculateFee(ticketId);
    }
}
