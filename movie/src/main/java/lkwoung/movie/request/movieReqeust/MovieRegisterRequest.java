package lkwoung.movie.request.movieReqeust;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MovieRegisterRequest {

    @JsonProperty("movieName")
    private String movieName;

    @JsonProperty("movieInfo")
    private String movieInfo;

    @JsonProperty("movieDirector")
    private List<String> movieDirector;

    @JsonProperty("movieMainActor")
    private List<String> movieMainActor;

    @JsonProperty("movieSubActor")
    private List<String> movieSubActor;

    @JsonProperty("movieImages")
    private List<String> movieImages;

    @JsonProperty("moviePoster")
    private String moviePoster;

}
