package lkwoung.movie.entity.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findAllByMovieStateOrderByMovieRegisterDate(String movieState);
    List<Movie> findAllByMovieNameOrderByMovieRegisterDate(String movieName);
    Optional<Movie> findAllByMovieIdAndMovieState(String movieId, String movieState);
}
