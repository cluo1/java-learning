package cn.mariojd.service;

import cn.mariojd.base.BaseService;
import cn.mariojd.entity.Message;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mario
 */
@Service
@Transactional(readOnly = true)
public class MessageService extends BaseService {

    @Cacheable(value = "message_report")
    public int[] report() {
        List<Message> messageList = messageRepository.findAll();
        int today = 0, yesterday = 0, before_yesterday = 0, _3DaysAgo = 0, _7DaysAgo = 0, _15DaysAgo = 0;
        Date nowTime = new Date();
        for (Message message : messageList) {
            int time = checkDay(nowTime, message.getPostTime());
            if (time == 0) {
                today++;
            } else if (time == 1) {
                yesterday++;
            } else if (time == 2) {
                before_yesterday++;
            } else if (time >= 3 && time < 7) {
                _3DaysAgo++;
            } else if (time >= 7 && time < 15) {
                _7DaysAgo++;
            } else if (time >= 15) {
                _15DaysAgo++;
            }
        }
        return new int[]{today, yesterday, before_yesterday, _3DaysAgo, _7DaysAgo, _15DaysAgo};
    }

    public static int checkDay(Date nowTime, Date postTime) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(nowTime);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(postTime);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 == year2) {
            //同一年
            return day1 - day2;
        } else {
            //不同年
            int timeDistance = 0;
            for (int i = year2; i < year1; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    //闰年
                    timeDistance += 366;
                } else {
                    //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day1 - day2);
        }
    }
}
