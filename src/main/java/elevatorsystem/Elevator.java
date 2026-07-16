package elevatorsystem;

import elevatorsystem.enums.Direction;
import elevatorsystem.models.Request;
import elevatorsystem.observer.ElevatorObserver;
import elevatorsystem.state.ElevatorState;
import elevatorsystem.state.IdleState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator implements Runnable{
    private final int id;
    private final AtomicInteger currentFloor;
    private ElevatorState state;
    private volatile boolean isRunning = true;

    private final TreeSet<Integer> upRequests;
    private final TreeSet<Integer> downRequests;

    // Observer Pattern: List of observers
    private final List<ElevatorObserver> observers = new ArrayList<>();

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = new AtomicInteger(1);
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>(Collections.reverseOrder());
        this.state = new IdleState();
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (ElevatorObserver observer : observers)
            observer.update(this);
    }

    // --- State Pattern Methods ---
    public void setState(ElevatorState state) {
        this.state = state;
        notifyObservers(); // Notify observers on direction change (direction stored in state)
    }

    public void move() {
        state.move(this);
    }

    public synchronized void addRequest(Request request) {
        System.out.println("Elevator " + id + " processing: " + request);
        state.addRequest(this, request);
    }

    // getters and setters
    public Direction getDirection() {
        return state.getDirection();
    }
    public int getId() {
        return id;
    }
    public int getCurrentFloor() {
        return currentFloor.get();
    }
    public void setCurrentFloor(int floor) {
        this.currentFloor.set(floor);
        notifyObservers(); // Notify observers on floor change
    }

    public TreeSet<Integer> getUpRequests() { return upRequests; }
    public TreeSet<Integer> getDownRequests() { return downRequests; }
    public void stopElevator() { this.isRunning = false;}

    @Override
    public void run() {
        while (isRunning) {
            if (state.getDirection() != Direction.IDLE) {
                System.out.println("elevator " + id + " direction is: " + state.getDirection());
            }
            move();
//            try {
//                Thread.sleep(5000); // Simulate movement time
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                isRunning = false;
//            }
        }
    }
}
