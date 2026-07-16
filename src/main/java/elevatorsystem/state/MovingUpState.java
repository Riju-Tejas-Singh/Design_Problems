package elevatorsystem.state;

import elevatorsystem.Elevator;
import elevatorsystem.enums.Direction;
import elevatorsystem.enums.RequestSource;
import elevatorsystem.models.Request;

public class MovingUpState implements ElevatorState {
    @Override
    public void move(Elevator elevator) {
        if (elevator.getUpRequests().isEmpty()) {
            elevator.setState(new IdleState());
            return;
        }

        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);

        stopIfNeeded(elevator);

        if (elevator.getUpRequests().isEmpty()) {
            // Transfer deferred upward requests
//            while (!elevator.getPendingRequests().isEmpty()) {
//                int pendingFloor = elevator.getPendingRequests().poll();
//                elevator.getUpRequests().add(pendingFloor);
//            }
            elevator.setState(new IdleState());
        }
    }

    private void stopIfNeeded(Elevator elevator) {
        Integer nextFloor = elevator.getUpRequests().first();
        if (elevator.getCurrentFloor() == nextFloor) {
            System.out.println("Elevator " + elevator.getId() + " stopped at floor " + nextFloor);
            elevator.getUpRequests().pollFirst();
        }
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        // Internal requests always get added to the appropriate queue
        if (request.getSource() == RequestSource.INTERNAL) {
            if (request.getTargetFloor() > elevator.getCurrentFloor()) {
                elevator.getUpRequests().add(request.getTargetFloor());
                // else can add to pendingRequests
            } else {
                elevator.getDownRequests().add(request.getTargetFloor());
            }
            return;
        }

        // External requests
        if (request.getDirection() == Direction.UP && request.getTargetFloor() >= elevator.getCurrentFloor()) {
            elevator.getUpRequests().add(request.getTargetFloor());
        } else if (request.getDirection() == Direction.DOWN) {
            elevator.getDownRequests().add(request.getTargetFloor());
        }
    }

    @Override
    public Direction getDirection() { return Direction.UP; }
}
