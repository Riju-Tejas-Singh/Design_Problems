package parkinglot;

import parkinglot.entities.ParkingFloor;
import parkinglot.entities.ParkingSpot;
import parkinglot.entities.ParkingTicket;
import parkinglot.strategy.fee.FeeStrategy;
import parkinglot.strategy.fee.FlatRateFeeStrategy;
import parkinglot.strategy.parking.BestFitStrategy;
import parkinglot.strategy.parking.ParkingStrategy;
import parkinglot.vehicle.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingLot {

    private final Map<String, ParkingTicket> activeTickets;
    private final List<ParkingFloor> floors;
    private ParkingStrategy parkingStrategy;
    private FeeStrategy feeStrategy;

    private ParkingLot() {
        this.parkingStrategy = new BestFitStrategy();
        this.feeStrategy = new FlatRateFeeStrategy();
        activeTickets = new ConcurrentHashMap<>();
        floors = new CopyOnWriteArrayList<>();
    }

    private static class Holder {
        private static final ParkingLot INSTANCE = new ParkingLot();
    }
    public static ParkingLot getInstance() {
        return Holder.INSTANCE;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> availableSpot = parkingStrategy.findSpot(floors, vehicle);
        if (availableSpot.isPresent()) {
            ParkingSpot spot = availableSpot.get();
            spot.parkVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(spot, vehicle);
            activeTickets.put(vehicle.getLicenseNumber(), ticket);
            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getLicenseNumber(), spot.getSpotId(), ticket.getTicketId());
            return Optional.of(ticket);
        }
        System.out.println("No available spot for " + vehicle.getLicenseNumber());
        return Optional.empty();
    }

    public Optional<Double> unparkVehicle(String licenseNumber) {
        ParkingTicket ticket = activeTickets.remove(licenseNumber);
        if (ticket == null) {
            System.out.println("No ticket found for " + licenseNumber);
            return Optional.empty();
        }
        ticket.setExitTimestamp();
        ticket.getSpot().unparkVehicle();
        Double fee = feeStrategy.calculateFee(ticket);
        return Optional.of(fee);
    }


    // setters
    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }
    public void setFeeStrategy (FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }
    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }
}
