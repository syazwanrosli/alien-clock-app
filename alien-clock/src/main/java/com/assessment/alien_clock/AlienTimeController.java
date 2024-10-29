package com.assessment.alien_clock;

import java.time.Duration;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/time")
@CrossOrigin(origins = "http://localhost:4200")
public class AlienTimeController {

    private static final Logger logger = LoggerFactory.getLogger(AlienTimeController.class);

    private static final LocalDateTime EARTH_REFERENCE_DATE = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
    private static final AlienTime ALIEN_REFERENCE_DATE = new AlienTime(2804, 18, 31, 2, 2, 88);

    private AlienTime alarmTime = null;

    @GetMapping("/currentAlienTime")
    public AlienTime getCurrentAlienTime(){
        LocalDateTime now = LocalDateTime.now();
        AlienTime alienTime = earthToAlien(now);
        logger.info("Current Alien Time: ", alienTime);
        
        // If an alarm is set, check if it should trigger
        if (alarmTime != null && alienTime.equals(alienTime)){
            logger.info("Alarm Triggered for Alien time: {}", alarmTime);
            alarmTime = null;
        }

        return alienTime;
    }

    // @PostMapping("/convertToEarth")
    // public LocalDateTime convertToEarth(@RequestBody AlienTime alienTime){
    //     if (alienTime == null) {
    //         logger.error("Received null  AlienTime for conversion");
    //         throw new IllegalArgumentException("Invalid AlienTime object.");
    //     }
    //     LocalDateTime earthTime = alienToEarth(alienTime);
    //     logger.info("Converted Alien Time {} to Earth Time{}", alienTime, earthTime);
    //     return earthTime;
    // }

    @PostMapping("/convertToEarth")
    public EarthTimeResponse convertToEarth(@RequestBody AlienTime alienTime) {
        if (alienTime == null) {
            logger.error("Received null AlienTime for conversion");
            throw new IllegalArgumentException("Invalid AlienTime object.");
        }
        
        LocalDateTime earthTime = alienToEarth(alienTime); // Your conversion logic here
        logger.info("Converted Alien Time {} to Earth Time {}", alienTime, earthTime);
        
        // Return EarthTimeResponse object
        return new EarthTimeResponse(earthTime);
    }

    // @PostMapping("/setAlarm")
    // public ResponseEntity<String> setAlarm(@RequestBody AlienTime alienTime) {
    //     logger.info("Attempting to set alarm with Alien Time: {}", alienTime);

    //     if (isValidAlienTime(alienTime)) {
    //         this.alarmTime = alienTime;
    //         logger.info("Alarm set for Alien Time: {}", alarmTime);
    //         return ResponseEntity.ok("{\"message\": \"Alarm set for Alien Time: " + alarmTime.toString() + "\"}");
    //     } else {
    //         logger.warn("Invalid Alien Time provided for alarm: {}", alienTime);
    //         return ResponseEntity.badRequest().body("{\"message\": \"Invalid Alien time provided.\"}");
    //     }
    // }
    @PostMapping("/setAlarm")
    public ResponseEntity<AlarmResponse> setAlarm(@RequestBody AlienTime alienTime) {
        logger.info("Attempting to set alarm with Alien Time: {}", alienTime);

        if (isValidAlienTime(alienTime)) {
            this.alarmTime = alienTime;
            logger.info("Alarm set for Alien Time: {}", alarmTime);
            return ResponseEntity.ok(new AlarmResponse("Alarm set for Alien Time: " + alarmTime.toString()));
        } else {
            logger.warn("Invalid Alien Time provided for alarm: {}", alienTime);
            return ResponseEntity.badRequest().body(new AlarmResponse("Invalid Alien time provided."));
        }
    }


    @PostMapping("/setAlienTime")
    public String setAlienTime(@RequestBody AlienTime alienTime) {
        if (isValidAlienTime(alienTime)) {
           logger.info("Alien Time set to: {}", alienTime);
           return "Alien time set to: " + alienTime.toString();
        } else {
            logger.warn("Invalid Alien time provided: {}", alienTime);
            return "Invalid Alien time provided.";
        }
    }

    private boolean isValidAlienTime(AlienTime alienTime) {
        if (alienTime.getYear() < 2804) return false; // Ensure year is not before the reference year
        if (alienTime.getMonth() < 1 || alienTime.getMonth() > 18) return false; // Month should be between 1 and 18
        if (alienTime.getDay() < 1 || alienTime.getDay() > AlienTimeUtils.ALIEN_MONTH_DAYS[alienTime.getMonth() - 1]) return false; // Validate days
        if (alienTime.getHour() < 0 || alienTime.getHour() >= AlienTimeUtils.ALIEN_DAY_HOURS) return false; // Hours check
        if (alienTime.getMinute() < 0 || alienTime.getMinute() >= AlienTimeUtils.ALIEN_HOUR_MINUTES) return false; // Minutes check
        if (alienTime.getSecond() < 0 || alienTime.getSecond() >= AlienTimeUtils.ALIEN_MINUTE_SECONDS) return false; // Seconds check

        return true;
    }

    private AlienTime earthToAlien(LocalDateTime earthDateTime) {
        Duration duration = Duration.between(EARTH_REFERENCE_DATE, earthDateTime);
        long alienTotalSeconds = (long) (duration.getSeconds() / 0.5); // 1 Alien second = 0.5 Earth second
        return AlienTimeUtils.convertSecondsToAlien(alienTotalSeconds, ALIEN_REFERENCE_DATE);
    }

    private LocalDateTime alienToEarth(AlienTime alienTime) {
        long alienTotalSeconds = AlienTimeUtils.convertAlienToSeconds(alienTime, ALIEN_REFERENCE_DATE);
        long earthSeconds = (long) (alienTotalSeconds * 0.5);
        return EARTH_REFERENCE_DATE.plusSeconds(earthSeconds);
    }

}
