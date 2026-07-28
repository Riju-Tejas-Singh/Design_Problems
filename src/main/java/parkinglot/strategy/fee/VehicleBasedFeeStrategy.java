package parkinglot.strategy.fee;

import parkinglot.entities.ParkingTicket;
import parkinglot.vehicle.VehicleSize;

import java.util.Map;

public class VehicleBasedFeeStrategy implements FeeStrategy {

    private static final Map<VehicleSize, Double> HOURLY_RATES =
            Map.of(VehicleSize.SMALL, 10.0, VehicleSize.MEDIUM, 20.0, VehicleSize.LARGE, 30.0);

    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        long durationMs = parkingTicket.getExitTimestamp() - parkingTicket.getEntryTimestamp();
        double hours = (durationMs / (1000.0 * 60 * 60));
        return Math.ceil(hours) * HOURLY_RATES.get(parkingTicket.getVehicle().getSize());
    }
}
