package bookmyshow.entities;

import bookmyshow.enums.City;

import java.util.ArrayList;
import java.util.List;

public class Theatre {

    private int theatreId;
    private String address;
    private City city;
    private List<Screen> screen = new ArrayList<>();
    private List<Show> shows = new ArrayList<>();

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Screen> getScreen() {
        return screen;
    }

    public void setScreen(List<Screen> screen) {
        this.screen = screen;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
