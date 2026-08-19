package bookmyshow;

import bookmyshow.enums.City;

public class Main {
    public static void main(String[] args) {

        BookMyShow bookMyShow = BookMyShow.getInstance();
        bookMyShow.initialize();
        //user1
        bookMyShow.createBooking(City.BANGALORE, "BAAHUBALI"); // BOOKING SUCCESSFUL
        //user2
        bookMyShow.createBooking(City.BANGALORE, "BAAHUBALI"); // seat already booked, try again
    }
}
