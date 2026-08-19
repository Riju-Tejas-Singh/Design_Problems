package bookmyshow.entities;

import bookmyshow.enums.City;

import java.util.*;

public class MovieController {
    private final Map<City, List<Movie>> cityVsMovies;
    private final List<Movie> allMovies;

    public MovieController(){
        cityVsMovies = new HashMap<>();
        allMovies = new ArrayList<>();
    }

    //ADD movie to a particular city, make use of cityVsMovies map
    public void addMovie(Movie movie, City city) {

        allMovies.add(movie);
        List<Movie> movies = cityVsMovies.getOrDefault(city, new ArrayList<>());
        movies.add(movie);
        cityVsMovies.put(city, movies);
    }


    public Movie getMovieByName(String movieName) {
        return allMovies.stream()
                .filter(movie -> Objects.equals(movie.getMovieName(), movieName))
                .findFirst()
                .orElse(null);
    }


    public List<Movie> getMoviesByCity(City city) {
        return cityVsMovies.get(city);
    }

    //REMOVE movie from a particular city, make use of cityVsMovies map
    //UPDATE movie of a particular city, make use of cityVsMovies map
    //CRUD operation based on Movie ID, make use of allMovies list
}
