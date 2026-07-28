package parkinglot.entities;

import parkinglot.vehicle.Vehicle;

import java.time.Instant;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketId;
    private final ParkingSpot spot;
    private final Vehicle vehicle;
    private final long entryTimestamp;
    private long exitTimestamp;

    public ParkingTicket(ParkingSpot parkingSpot, Vehicle vehicle) {
        this.ticketId = UUID.randomUUID().toString();
        this.spot = parkingSpot;
        this.vehicle = vehicle;
        this.entryTimestamp = Instant.now().toEpochMilli();
    }

    public void setExitTimestamp() {
        this.exitTimestamp = Instant.now().toEpochMilli();
    }

    // getters
    public String getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingSpot getSpot() { return spot; }
    public long getEntryTimestamp() { return entryTimestamp; }
    public long getExitTimestamp() { return exitTimestamp; }
}