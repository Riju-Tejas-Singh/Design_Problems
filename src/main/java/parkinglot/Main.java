package parkinglot;

import parkinglot.entities.ParkingFloor;
import parkinglot.entities.ParkingSpot;
import parkinglot.entities.ParkingTicket;
import parkinglot.factory.VehicleFactory;
import parkinglot.gates.EntryGate;
import parkinglot.gates.ExitGate;
import parkinglot.strategy.fee.VehicleBasedFeeStrategy;
import parkinglot.vehicle.*;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        // 1. Initialize the parking lot with floors and spots

        ParkingLot parkingLot = ParkingLot.getInstance();
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        floor1.addSpot(new ParkingSpot("F1-M1", VehicleSize.MEDIUM));
        floor1.addSpot(new ParkingSpot("F1-L1", VehicleSize.LARGE));

        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot("F2-M1", VehicleSize.MEDIUM));
        floor2.addSpot(new ParkingSpot("F2-M2", VehicleSize.MEDIUM));
        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);
        parkingLot.setFeeStrategy(new VehicleBasedFeeStrategy());

        // Create Entry and Exit gates
        EntryGate entryGate = new EntryGate(parkingLot);
        ExitGate exitGate = new ExitGate(parkingLot);

        // 2. Simulate vehicle entries

        Vehicle bike = VehicleFactory.createVehicle(VehicleSize.SMALL, "B-123");
        Vehicle car = VehicleFactory.createVehicle(VehicleSize.MEDIUM, "C-456");
        Vehicle truck = VehicleFactory.createVehicle(VehicleSize.LARGE, "T-789");

        System.out.println("\n--- Vehicle Entries ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        // 3. Park vehicles through entry gate & get tickets

        Optional<ParkingTicket> bikeTicketOpt = entryGate.processEntry(bike);
        Optional<ParkingTicket> carTicketOpt = entryGate.processEntry(car);
        Optional<ParkingTicket> truckTicketOpt = entryGate.processEntry(truck);

        System.out.println("\n--- Availability after parking ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        // 2.1 & 3.1 Simulate another car entry (should go to floor 2)
        Vehicle car2 = new Car("C-999");
        Optional<ParkingTicket> car2TicketOpt = entryGate.processEntry(car2);

        // 2.2 & 3.2 Simulate a vehicle entry that fails (no available spots)
        Vehicle truck2 = new Truck("T-000");
        Optional<ParkingTicket> failedBikeTicketOpt = entryGate.processEntry(truck2);

        // 4. Unpark and fee calculation through exit gate
        System.out.println("\n--- Vehicle Exits ---");

        if (carTicketOpt.isPresent()) {
            Optional<Double> feeOpt = exitGate.processExit(carTicketOpt.get().getTicketId());
            feeOpt.ifPresent(fee -> System.out.printf("Car C-456 unparked. Fee: $%.2f\n", fee));
        }

        System.out.println("\n--- Availability after one car leaves ---");
        floor1.displayAvailability();
        floor2.displayAvailability();
    }
    //  --- Vehicle Entries ---
    //--- Floor 1 Availability ---
    //  SMALL spots: 1
    //  MEDIUM spots: 1
    //  LARGE spots: 1
    //--- Floor 2 Availability ---
    //  SMALL spots: 0
    //  MEDIUM spots: 2
    //  LARGE spots: 0
    //B-123 parked at F1-S1. Ticket: ef6a9295-1f9a-4ff8-8820-17c76d019d89
    //C-456 parked at F1-M1. Ticket: 573fc97a-53d0-48d1-96bb-8c12603f4be6
    //T-789 parked at F1-L1. Ticket: 9fc894ba-477d-4b5e-948c-108b1b6dcecc
    //
    //--- Availability after parking ---
    //--- Floor 1 Availability ---
    //  SMALL spots: 0
    //  MEDIUM spots: 0
    //  LARGE spots: 0
    //--- Floor 2 Availability ---
    //  SMALL spots: 0
    //  MEDIUM spots: 2
    //  LARGE spots: 0
    //C-999 parked at F2-M1. Ticket: ace75957-4bd2-4979-8560-8b0d7f6489f1
    //No available spot for T-000
    //
    //--- Vehicle Exits ---
    //Car C-456 unparked. Fee: $20.00
    //
    //--- Availability after one car leaves ---
    //--- Floor 1 Availability ---
    //  SMALL spots: 0
    //  MEDIUM spots: 1
    //  LARGE spots: 0
    //--- Floor 2 Availability ---
    //  SMALL spots: 0
    //  MEDIUM spots: 1
    //  LARGE spots: 0
}
