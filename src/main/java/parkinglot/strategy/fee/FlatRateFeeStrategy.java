package parkinglot.strategy.fee;

import parkinglot.entities.ParkingTicket;

public class FlatRateFeeStrategy implements FeeStrategy {

    private static final double RATE_PER_HOUR = 10.0;

    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        long durationMs = parkingTicket.getExitTimestamp() - parkingTicket.getEntryTimestamp();
        double hours = durationMs / (1000.0 * 60 * 60);
        return Math.ceil(hours) * RATE_PER_HOUR;
    }
}
