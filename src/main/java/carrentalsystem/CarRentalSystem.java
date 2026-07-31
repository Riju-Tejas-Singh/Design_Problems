package carrentalsystem;

import carrentalsystem.entities.Customer;
import carrentalsystem.entities.Reservation;
import carrentalsystem.entities.Vehicle;
import carrentalsystem.payment.CreditCardPaymentProcessor;
import carrentalsystem.payment.PaymentProcessor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CarRentalSystem {
    private final Map<String, Vehicle> vehicles;
    private final Map<String, Reservation> reservations;
    private final PaymentProcessor paymentProcessor;

    private CarRentalSystem() {
        vehicles = new ConcurrentHashMap<>();
        reservations = new ConcurrentHashMap<>();
        paymentProcessor = new CreditCardPaymentProcessor();
    }

    private static class Holder {
        private static final CarRentalSystem INSTANCE = new CarRentalSystem();
    }
    public static CarRentalSystem getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * search vehicle available to rent for the input duration
     */
    public List<Vehicle> searchVehicles(String make, String model, LocalDate startDate, LocalDate endDate) {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles.values()) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model) && vehicle.isAvailable()) {
                if (isVehicleAvailable(vehicle, startDate, endDate)) {
                    availableVehicles.add(vehicle);
                }
            }
        }
        return availableVehicles;
    }

    public synchronized Reservation makeReservation(Customer customer, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        if (isVehicleAvailable(vehicle, startDate, endDate)) {
            Reservation reservation = new Reservation(customer, vehicle, startDate, endDate);
            reservations.put(reservation.getReservationId(), reservation);
            vehicle.setAvailable(false);
            return reservation;
        }
        return null;
    }

    public synchronized void completeReservationAndSubmitVehicle(String reservationId) {
        Reservation reservation = reservations.remove(reservationId);
        if (reservation != null) {
            reservation.getVehicle().setAvailable(true);
        }
    }

    /**
     * Vehicle unavailable if reserved = true for any portion requested duration (no overlap of time allowed)
     */
    private boolean isVehicleAvailable(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        for (Reservation reservation : reservations.values()) {
            if (reservation.getVehicle().equals(vehicle)) {
                if (startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean processPayment(Reservation reservation) {
        return paymentProcessor.processPayment(reservation.getTotalPrice());
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getLicensePlate(), vehicle);
    }

    public void removeVehicle(String licensePlate) {
        vehicles.remove(licensePlate);
    }
}
