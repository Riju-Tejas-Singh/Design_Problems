package carrentalsystem.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Reservation {
    private final String reservationId;
    private final Customer customer;
    private final Vehicle car;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalPrice;

    public Reservation(Customer customer, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.reservationId = "RES" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.customer = customer;
        this.car = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        long daysRented = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return car.getRentalPricePerDay() * daysRented;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Vehicle getVehicle() {
        return car;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getReservationId() {
        return reservationId;
    }
}
