package bookmyshow.entities;

import bookmyshow.enums.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TheatreController {
    private final Map<City, List<Theatre>> cityVsTheatre;
    private final List<Theatre> allTheatre;

    public TheatreController() {
        cityVsTheatre = new HashMap<>();
        allTheatre = new ArrayList<>();
    }

    public void addTheatre(Theatre theatre, City city) {

        allTheatre.add(theatre);

        List<Theatre> theatres = cityVsTheatre.getOrDefault(city, new ArrayList<>());
        theatres.add(theatre);
        cityVsTheatre.put(city, theatres);
    }
    public Map<Theatre, List<Show>> getAllShow(Movie movie, City city) {
        //get all the theaters of this city
        Map<Theatre, List<Show>> theatreVsShows = new HashMap<>();
        List<Theatre> theatres = cityVsTheatre.get(city);

        //filter the theaters which run this movie

        theatres.forEach(theatre -> {
                    List<Show> shows = theatre.getShows().stream()
                            .filter(show -> show.getMovie().getMovieId() == movie.getMovieId())
                            .collect(Collectors.toList());

                    if (!shows.isEmpty()) {
                        theatreVsShows.put(theatre, shows);
                    }
                });
        return theatreVsShows;
    }
}
