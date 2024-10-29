package com.assessment.alien_clock;

public class AlienTimeUtils {

    public static final int ALIEN_MINUTE_SECONDS = 90; // 1 Alien minute = 90 Earth seconds
    public static final int ALIEN_HOUR_MINUTES = 90;   // 1 Alien hour = 90 Earth minutes
    public static final int ALIEN_DAY_HOURS = 36;      // 1 Alien day = 36 Earth hours
    public static final int[] ALIEN_MONTH_DAYS = {44, 42, 48, 40, 48, 44, 40, 44, 42, 40, 40, 42, 44, 48, 42, 40, 44, 38};

    // public static AlienTime convertSecondsToAlien(long totalAlienSeconds, AlienTime reference) {
    //     // Initialize alien time with reference values
    //     AlienTime alienTime = new AlienTime(reference.getYear(), reference.getMonth(), reference.getDay(), 0, 0, 0);
        
    //     // Conversion logic
    //     long totalMinutes = totalAlienSeconds / ALIEN_MINUTE_SECONDS; // Convert total seconds to total Alien minutes
    //     alienTime.setMinute((int)(totalMinutes % ALIEN_HOUR_MINUTES));
        
    //     long totalHours = totalMinutes / ALIEN_HOUR_MINUTES; // Convert total minutes to total Alien hours
    //     alienTime.setHour((int)(totalHours % ALIEN_DAY_HOURS));
        
    //     long totalDays = totalHours / ALIEN_DAY_HOURS; // Convert total hours to total Alien days
    //     alienTime.setDay((int)(totalDays % ALIEN_MONTH_DAYS[alienTime.getMonth() - 1])); // Adjust for month
        
    //     alienTime.setYear(reference.getYear() + (int)(totalDays / ALIEN_MONTH_DAYS[alienTime.getMonth() - 1])); // Adjust year if necessary
        
    //     // Remaining logic to set month and adjust for months
    //     return alienTime;
        
    // }
    public static AlienTime convertSecondsToAlien(long totalAlienSeconds, AlienTime reference) {
        // Initialize alien time with reference values
        AlienTime alienTime = new AlienTime(reference.getYear(), reference.getMonth(), reference.getDay(), 0, 0, 0);
        
        // Calculate total minutes and seconds
        long totalMinutes = totalAlienSeconds / ALIEN_MINUTE_SECONDS;
        long remainingSeconds = totalAlienSeconds % ALIEN_MINUTE_SECONDS;
    
        // Set seconds
        alienTime.setSecond((int)remainingSeconds);
    
        // Calculate total hours
        long totalHours = totalMinutes / ALIEN_HOUR_MINUTES;
        alienTime.setMinute((int)(totalMinutes % ALIEN_HOUR_MINUTES));
    
        // Calculate total days
        long totalDays = totalHours / ALIEN_DAY_HOURS;
        alienTime.setHour((int)(totalHours % ALIEN_DAY_HOURS));
    
        // Adjust year and month
        while (totalDays >= ALIEN_MONTH_DAYS[alienTime.getMonth() - 1]) {
            totalDays -= ALIEN_MONTH_DAYS[alienTime.getMonth() - 1];
            alienTime.setMonth(alienTime.getMonth() % 18 + 1); // Move to next month
            if (alienTime.getMonth() == 1) { // If January, increment year
                alienTime.setYear(alienTime.getYear() + 1);
            }
        }
    
        alienTime.setDay((int)(totalDays + 1)); // Set day (1-based)
    
        return alienTime;
    }
    
    

    public static long convertAlienToSeconds(AlienTime alienTime, AlienTime reference) {
        long totalSeconds = 0;
    
        // Calculate total seconds from years
        totalSeconds += (alienTime.getYear() - reference.getYear()) * 
                        ALIEN_MONTH_DAYS.length * ALIEN_DAY_HOURS * ALIEN_HOUR_MINUTES * ALIEN_MINUTE_SECONDS;
    
        // Calculate total seconds from months
        for (int month = 0; month < alienTime.getMonth() - 1; month++) {
            totalSeconds += ALIEN_MONTH_DAYS[month] * ALIEN_DAY_HOURS * ALIEN_HOUR_MINUTES * ALIEN_MINUTE_SECONDS;
        }
    
        // Calculate total seconds from days
        totalSeconds += (alienTime.getDay() - 1) * ALIEN_DAY_HOURS * ALIEN_HOUR_MINUTES * ALIEN_MINUTE_SECONDS;
    
        // Add seconds from hours, minutes, and seconds
        totalSeconds += (alienTime.getHour() * ALIEN_HOUR_MINUTES * ALIEN_MINUTE_SECONDS) + 
                        (alienTime.getMinute() * ALIEN_MINUTE_SECONDS) + 
                        alienTime.getSecond();
    
        return totalSeconds; 
    }    
    

}
