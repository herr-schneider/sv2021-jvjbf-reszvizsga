package cinema;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class CinemaService {

    private AtomicLong id = new AtomicLong();
    private List<Movie> movieList = new ArrayList<>();

    private ModelMapper modelMapper;

    public CinemaService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDTO> getMovies(Optional<String> title) {
       return movieList.stream()
                .filter(movie -> title.isEmpty() || movie.getTitle().equalsIgnoreCase(title.get()))
                .map(i -> modelMapper.map(i, MovieDTO.class))
                .collect(Collectors.toList());
    }

    public MovieDTO getMovieByID(long id) {
        Movie movie = getMovie(id);

        return modelMapper.map(movie, MovieDTO.class);
    }

    private Movie getMovie(long id) {
        Movie movie = movieList.stream()
                .filter(i -> i.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
        return movie;
    }

    public MovieDTO addMovie(CreateMovieCommand command) {
        Movie newMovie = new Movie(id.incrementAndGet(), command.getTitle(), command.getDate(),
                command.getMaxSpaces(), command.getMaxSpaces());

        movieList.add(newMovie);
        return modelMapper.map(newMovie, MovieDTO.class);
    }

    public MovieDTO reserve(long id, CreateReservationCommand command) {
        Movie movie = getMovie(id);
        movie.reserve(command.getReserveSpaces());

        return modelMapper.map(movie, MovieDTO.class);
    }

    public MovieDTO updateDate(long id, UpdateDateCommand command) {
        Movie movie = getMovie(id);
        movie.setDate(command.getDate());

        return modelMapper.map(movie, MovieDTO.class);
    }

    public void deleteAll() {
        movieList.clear();
        id.set(0);
    }
}
