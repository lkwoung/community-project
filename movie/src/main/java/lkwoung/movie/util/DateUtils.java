package lkwoung.movie.util;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateUtils {

    /**
     * 0000-00-00 00:00:00 포멧으로 날짜정보를 넘겨준다.
     * @return
     */
    public String getNow() {
        SimpleDateFormat lv_dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return lv_dateFormat.format(new Date());
    }

}
