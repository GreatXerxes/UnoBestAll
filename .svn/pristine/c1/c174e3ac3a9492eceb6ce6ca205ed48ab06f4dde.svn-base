/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author comqdhb
 */
public class CurrentTimeGenerator {

    public CurrentTimeGenerator() {

    }

    public static Time currentTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        Time t = new Time(hour, min);
        return t;
    }
}
