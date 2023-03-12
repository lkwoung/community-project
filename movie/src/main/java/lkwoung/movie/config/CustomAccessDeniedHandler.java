package lkwoung.movie.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lkwoung.movie.service.ReturnService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static lkwoung.movie.util.EnumCode.ACCESS_ERROR;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ReturnService gv_returnService;

    public CustomAccessDeniedHandler(ReturnService gv_returnService) {
        this.gv_returnService = gv_returnService;
    }


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ObjectMapper lv_mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(200);
        response.getWriter().write(lv_mapper.writeValueAsString(
                gv_returnService.createResponse(ACCESS_ERROR, "권한이 없습니다.", null)));
    }
}
