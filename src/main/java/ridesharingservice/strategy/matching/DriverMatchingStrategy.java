package ridesharingservice.strategy.matching;

import ridesharingservice.entities.Driver;
import ridesharingservice.entities.Location;
import ridesharingservice.enums.RideType;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findDrivers(List<Driver> allDrivers, Location pickupLocation, RideType rideType);
}
