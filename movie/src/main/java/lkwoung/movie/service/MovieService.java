package lkwoung.movie.service;

import lkwoung.movie.entity.movie.Movie;
import lkwoung.movie.entity.movie.MovieRepository;
import lkwoung.movie.request.movieReqeust.MovieRegisterRequest;
import lkwoung.movie.request.movieReqeust.MovieUpdateRequest;
import lkwoung.movie.util.DateUtils;
import lkwoung.movie.util.EnumCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static lkwoung.movie.util.EnumCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final ReturnService gv_returnService;
    private final MovieRepository gv_movieRepository;
    private final DateUtils gv_dateUtils;

    @Transactional
    public JSONObject movieRegister(MovieRegisterRequest request) {
        try {
            log.info("MovieService.movieRegister");

            String lv_now = gv_dateUtils.getNow();
            String lv_directors = String.join(",", request.getMovieDirector());
            String lv_mainActors = String.join(",", request.getMovieMainActor());
            String lv_subActors = String.join(",", request.getMovieSubActor());
            String lv_images = String.join(",", request.getMovieImages());

            gv_movieRepository.save(
                    Movie.builder()
                            .movieName(request.getMovieName())
                            .movieInfo(request.getMovieInfo())
                            .movieDirector(lv_directors)
                            .movieMainActor(lv_mainActors)
                            .movieSubActor(lv_subActors)
                            .moviePoster(request.getMoviePoster())
                            .movieImages(lv_images)
                            .movieGrade(0.0)
                            .movieRegisterDate(lv_now)
                            .movieState("active")
                            .build()
            );

            Thread.sleep(1000);
            return gv_returnService.createResponse(SUCCESS, "success", 200);
        } catch (Exception e) {
            log.error("ERROR MovieService.movieRegister");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return gv_returnService.createResponse(SERVER_ERROR, "서버 에러입니다", null);
        }
    }


    public JSONObject movieList() {
        try {
            log.info("MovieService.movieList");
            List<Movie> lv_movieList = gv_movieRepository.findAllByMovieStateOrderByMovieRegisterDate("active");

            JSONObject lv_response = new JSONObject();
            JSONArray lv_movieJsonArray = new JSONArray();
            for (Movie movie : lv_movieList) {
                JSONObject lv_movieJsonObject = new JSONObject();
                lv_movieJsonObject.put("movieName", movie.getMovieName());
                lv_movieJsonObject.put("movieDirector", movie.getMovieDirector());
                lv_movieJsonObject.put("movieMainActor", movie.getMovieMainActor());
                lv_movieJsonObject.put("movieSubActor", movie.getMovieSubActor());
                lv_movieJsonObject.put("moviePoster", movie.getMoviePoster());
                lv_movieJsonArray.add(lv_movieJsonObject);
            }
            lv_response.put("row", lv_movieJsonArray);

            return gv_returnService.createResponse(SUCCESS, "success", lv_response);
        } catch (Exception e) {
            log.error("ERROR MovieService.movieList");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return gv_returnService.createResponse(SERVER_ERROR, "서버 에러입니다", null);
        }
    }

    public JSONObject movieSearch(String keyword) {
        try {
            log.info("MovieService.movieSearch");
            List<Movie> findMoviesList = gv_movieRepository.findAllByMovieNameOrderByMovieRegisterDate(keyword);


            JSONObject lv_response = new JSONObject();
            JSONArray lv_movieJsonArray = new JSONArray();
            for (Movie movie : findMoviesList) {
                JSONObject lv_movieJsonObject = new JSONObject();
                lv_movieJsonObject.put("movieName", movie.getMovieName());
                lv_movieJsonObject.put("movieDirector", movie.getMovieDirector());
                lv_movieJsonObject.put("movieMainActor", movie.getMovieMainActor());
                lv_movieJsonObject.put("movieSubActor", movie.getMovieSubActor());
                lv_movieJsonObject.put("moviePoster", movie.getMoviePoster());
                lv_movieJsonArray.add(lv_movieJsonObject);
            }
            lv_response.put("row", lv_movieJsonArray);

            return gv_returnService.createResponse(SUCCESS, "success", lv_response);
        } catch (Exception e) {
            log.error("ERROR MovieService.movieSearch");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return gv_returnService.createResponse(SERVER_ERROR, "서버 에러입니다", null);
        }
    }
    @Transactional
    public JSONObject movieUpdate(String movieId, MovieUpdateRequest request) {
        try {
            log.info("MovieService.movieUpdate");
            Optional<Movie> lv_findMovie = gv_movieRepository.findAllByMovieIdAndMovieState(movieId, "active");
            if (lv_findMovie.isEmpty()) {
                return gv_returnService.createResponse(NO_VALUE, "no value", null);
            }

            String lv_directors = String.join(",", request.getMovieDirector());
            String lv_mainActors = String.join(",", request.getMovieMainActor());
            String lv_subActors = String.join(",", request.getMovieSubActor());
            String lv_images = String.join(",", request.getMovieImages());

            Movie lv_movie = lv_findMovie.get();
            lv_movie.changeMovieName(request.getMovieName());
            lv_movie.changeMovieInfo(request.getMovieInfo());
            lv_movie.changeMovieDirector(lv_directors);
            lv_movie.changeMovieMainActor(lv_mainActors);
            lv_movie.changeMovieSubActor(lv_subActors);
            lv_movie.changeMovieImages(lv_images);
            lv_movie.changeMoviePoster(request.getMoviePoster());

            Thread.sleep(1000);
            return gv_returnService.createResponse(SUCCESS, "success", null);
        } catch (Exception e) {
            log.error("ERROR MovieService.movieUpdate");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return gv_returnService.createResponse(SERVER_ERROR, "서버 에러입니다", null);
        }
    }

    @Transactional
    public JSONObject movieDelete(String movieId) {
        try {
            log.info("MovieService.movieDelete");
            Optional<Movie> lv_findMovie = gv_movieRepository.findAllByMovieIdAndMovieState(movieId, "active");
            if (lv_findMovie.isEmpty()) {
                return gv_returnService.createResponse(NO_VALUE, "no value", null);
            }
            gv_movieRepository.deleteById(movieId);

            return gv_returnService.createResponse(SUCCESS, "success", null);
        } catch (Exception e) {
            log.error("ERROR MovieService.movieDelete");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return gv_returnService.createResponse(SERVER_ERROR, "서버 에러입니다", null);
        }
    }
}
