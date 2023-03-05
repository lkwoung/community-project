package lkwoung.movie.service;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReturnService {

    public JSONObject createResponse(int status, String message, Object data) {
        log.info("ReturnService.returnToFormat");

        JSONObject lv_json = new JSONObject();
        lv_json.put("status", status);
        lv_json.put("message", message);
        lv_json.put("data", data);

        return lv_json;
    }

}
