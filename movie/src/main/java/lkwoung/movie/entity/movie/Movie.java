package lkwoung.movie.entity.movie;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private String movieId;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "movie_info")
    private String movieInfo;

    @Column(name = "movie_director")
    private String movieDirector;

    @Column(name = "movie_main_actor")
    private String movieMainActor;

    @Column(name = "movie_sub_actor")
    private String movieSubActor;

    @Column(name = "movie_images")
    private String movieImages;

    @Column(name = "movie_poster")
    private String moviePoster;

    @Column(name = "movie_grade")
    private Double movieGrade;

    @Column(name = "movie_register_date")
    private String movieRegisterDate;

    @Column(name = "movie_state")
    private String movieState;

    @Builder
    public Movie(String movieId, String movieName, String movieInfo, String movieDirector, String movieMainActor,
                 String movieSubActor, String movieImages, String moviePoster, Double movieGrade, String movieRegisterDate,
                 String movieState) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieInfo = movieInfo;
        this.movieDirector = movieDirector;
        this.movieMainActor = movieMainActor;
        this.movieSubActor = movieSubActor;
        this.movieImages = movieImages;
        this.moviePoster = moviePoster;
        this.movieGrade = movieGrade;
        this.movieRegisterDate = movieRegisterDate;
        this.movieState = movieState;
    }

    public void changeMovieName(String movieName){
        this.movieName = movieName;
    }

    public void changeMovieInfo(String movieInfo) {
        this.movieInfo = movieInfo;
    }

    public void changeMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public void changeMovieMainActor(String movieMainActor) {
        this.movieMainActor = movieMainActor;
    }

    public void changeMovieSubActor(String movieSubActor) {
        this.movieSubActor = movieSubActor;
    }

    public void changeMovieImages(String movieImages) {
        this.movieImages = movieImages;
    }

    public void changeMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;

    }

}


