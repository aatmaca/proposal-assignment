/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public static Date getCurrentDate() {
        GregorianCalendar dateTime = new GregorianCalendar();

        return new GregorianCalendar(dateTime.get(GregorianCalendar.YEAR),
                dateTime.get(GregorianCalendar.MONTH),
                dateTime.get(GregorianCalendar.DAY_OF_MONTH),0,0,0).getTime();
    }

    public static int getCurrentYear() {
        GregorianCalendar dateTime = new GregorianCalendar();
        return dateTime.get(Calendar.YEAR);
    }
    
    public static Date getDateWithoutHourDetail(Date date) {
        GregorianCalendar dateTime = new GregorianCalendar();
        dateTime.setTime(date);

        return new GregorianCalendar(dateTime.get(GregorianCalendar.YEAR),
                dateTime.get(GregorianCalendar.MONTH),
                dateTime.get(GregorianCalendar.DAY_OF_MONTH)).getTime();
    }
        
    public static int getMonthOfGivenDate(Date date) {
        GregorianCalendar dateTime = new GregorianCalendar();
        dateTime.setTime(date);
        return dateTime.get(Calendar.MONTH);
    }
    
    public static Integer getCurrentHour() {
        return new GregorianCalendar().get(GregorianCalendar.HOUR_OF_DAY);
    }
    
    public static boolean isBetweenIncludingLimits(Date myDate, Date left, Date right){
        if (myDate.before(left) == false && myDate.after(right) == false) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns the day after <code>date</code>.
     *
     * @param date Date used in calculating next day
     * @return Day after <code>date</code>.
     */
    public static Date nextDay(Date date) {
        return new Date(addDays(date.getTime(), 1));
    }

    /**
     * Adds <code>amount</code> days to <code>time</code> and returns
     * the resulting time.
     *
     * @param time Base time
     * @param amount Amount of increment.
     *
     * @return the <var>time</var> + <var>amount</var> days
     */
    public static long addDays(long time, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTimeInMillis();

    }
    
}

