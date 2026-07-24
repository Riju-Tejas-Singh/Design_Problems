package carrentalsystem;

import carrentalsystem.entities.Car;
import carrentalsystem.entities.Customer;
import carrentalsystem.entities.Reservation;
import carrentalsystem.entities.Vehicle;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarRentalSystem carRentalSystem = CarRentalSystem.getInstance();

        // Add cars to the rental system
        carRentalSystem.addVehicle(new Car("Toyota", "Camry", 2022, "ABC123", 50.0));
        carRentalSystem.addVehicle(new Car("Honda", "Civic", 2021, "XYZ789", 45.0));
        carRentalSystem.addVehicle(new Car("Ford", "Mustang", 2023, "DEF456", 80.0));

        // Create customers
        Customer customer1 = new Customer("John Doe", "john@example.com", "DL1234");

        // Make reservations
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);
        List<Vehicle> availableCars = carRentalSystem.searchVehicles("Toyota", "Camry", startDate, endDate);
        if (!availableCars.isEmpty()) {
            Vehicle selectedCar = availableCars.get(0);
            Reservation reservation = carRentalSystem.makeReservation(customer1, selectedCar, startDate, endDate);
            if (reservation != null) {
                boolean paymentSuccess = carRentalSystem.processPayment(reservation);
                if (paymentSuccess) {
                    System.out.println("Reservation successful. Reservation ID: " + reservation.getReservationId());
                } else {
                    System.out.println("Payment failed. Reservation canceled.");
                    carRentalSystem.cancelReservation(reservation.getReservationId());
                }
            } else {
                System.out.println("Selected car is not available for the given dates.");
            }
        } else {
            System.out.println("No available cars found for the given criteria.");
        }
    }
    //    Processing  payment through credit card: $200.0
    //    Reservation successful. Reservation ID: RES183092D7
}
