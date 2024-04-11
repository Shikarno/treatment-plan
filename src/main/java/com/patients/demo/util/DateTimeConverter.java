package com.patients.demo.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateTimeConverter {

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZONE_ID)
                .toLocalDateTime();
    }
}
