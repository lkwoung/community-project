package lkwoung.movie.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lkwoung.movie.request.movieReqeust.MovieRegisterRequest;
import lkwoung.movie.request.movieReqeust.MovieUpdateRequest;
import lkwoung.movie.service.MovieService;
import lkwoung.movie.service.ReturnService;
import lkwoung.movie.util.EnumCode;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
@Api(tags = "영화 API")
public class MovieController {

    private final MovieService movieService;
    private final ReturnService returnService;

    @Operation(summary = "영화등록",description = "register: name, info, director, movieMainActor, movieSubActor, movieImages, moviePoster (admin only)")
    @PostMapping
    public JSONObject register(@Valid @RequestBody MovieRegisterRequest request, BindingResult result) {
        if (result.hasErrors()) {
            returnService.createResponse(EnumCode.VALID_ERROR,
                    result.getAllErrors().get(0).getDefaultMessage(), null);
        }
        return movieService.movieRegister(request);
    }

    @Operation(summary = "영화목록", description = "list")
    @GetMapping
    public JSONObject list(){
        return movieService.movieList();
    }

    @Operation(summary = "영화검색", description = "search")
    @GetMapping("/{keyword}")
    public JSONObject search(@PathVariable String keyword) {
        return movieService.movieSearch(keyword);
    }

    @Operation(summary = "영화수정", description = "update (admin only)")
    @PatchMapping("/{movieId}")
    public JSONObject update(@PathVariable String movieId,
                             @Valid @RequestBody MovieUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return returnService.createResponse(EnumCode.VALID_ERROR,
                    result.getAllErrors().get(0).getDefaultMessage(), null);
        }
        return movieService.movieUpdate(movieId, request);
    }

    @Operation(summary = "영화삭제", description = "delete (admin only)")
    @DeleteMapping("/{movieId}")
    public JSONObject delete(@PathVariable String movieId) {
        return movieService.movieDelete(movieId);
    }

}
