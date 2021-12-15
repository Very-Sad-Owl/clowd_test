package com.boots.service.util;

import com.boots.entity.DayParts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class DayPartDefiner {

    private Calendar c = Calendar.getInstance();

    public DayParts getCurrentDayPart(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 4 && timeOfDay < 12){
            return DayParts.MORNING;
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            return DayParts.DAY;
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            return DayParts.EVENING;
        }else if(timeOfDay >= 0 && timeOfDay < 4){
            return DayParts.NIGHT;
        }
        return DayParts.DAY;
    }


}
