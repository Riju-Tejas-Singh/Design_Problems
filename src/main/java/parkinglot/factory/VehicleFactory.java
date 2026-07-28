package parkinglot.factory;

import parkinglot.vehicle.*;

/**
 * Factory pattern is used for creation, whereas strategy pattern chooses behavior
 */
public class VehicleFactory {
    public static Vehicle createVehicle(VehicleSize vehicleSize, String licenseNumber) {
        switch (vehicleSize) {
            case SMALL:
                return new Bike(licenseNumber);
            case MEDIUM:
                return new Car(licenseNumber);
            case LARGE:
                return new Truck(licenseNumber);
            default:
                throw new IllegalArgumentException("Unknown vehicle size");
        }

        // java 14+

    //        return switch (vehicleSize) {
    //            case SMALL -> new Bike(licenseNumber);
    //            case MEDIUM -> new Car(licenseNumber);
    //            case LARGE -> new Truck(licenseNumber);
    //        };
    }
}
