package com.algo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UTC3 {

    private UTC3() {
    }

    public static final DateTimeFormatter ZONE_FORMAT = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    
    public static final int TIME_ZONE = 3;
    public static final ZoneId TIMEZONE = ZoneId.of("GMT+" + TIME_ZONE);
    public static final long SYSTIMEZONE_UTC_DIFF_SECONDS = TIME_ZONE * 60 * 60;

    public static ZonedDateTime nowZoneDateTime() {
        return ZonedDateTime.now(TIMEZONE);
    }

    public static LocalDate nowDate() {
        return nowZoneDateTime().toLocalDate();
    }

    public static LocalDate tmrDate() {
        return nowZoneDateTime().toLocalDate().plusDays(1);
    }

    public static LocalDate yesterdayDate() {
        return nowZoneDateTime().toLocalDate().minusDays(1);
    }

    public static LocalDate twoDatesBackDate() {
        return nowZoneDateTime().toLocalDate().minusDays(2);
    }

    public static LocalDateTime nowDateTime() {
        return nowZoneDateTime().toLocalDateTime();
    }

    public static ZonedDateTime startOfToday(LocalDate date) {
        return ZonedDateTime.of(date.atTime(LocalTime.MIN), TIMEZONE);
    }

    public static ZonedDateTime endOfToday(LocalDate date) {
        return ZonedDateTime.of(date.atTime(LocalTime.MAX), TIMEZONE);

    }

    public static ZonedDateTime get(LocalDateTime dateTime) {
        return ZonedDateTime.of(dateTime, TIMEZONE);
    }

    public static ZonedDateTime parse(String input) {
        return ZonedDateTime.parse(input, ZONE_FORMAT);
    }

    public static long utc3Milisecond(long utcMilisecond) {
        return utcMilisecond + SYSTIMEZONE_UTC_DIFF_SECONDS * 1000;
    }

    public static long utc3Second(long utcMilisecond) {
        return utc3Milisecond(utcMilisecond) / 1000;
    }

    public static ZonedDateTime fromEpochSecond(long utcSecond) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(utcSecond * 1000 + 999), TIMEZONE);
    }

    public static ZonedDateTime fromUtc3Second(long utc3Second) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli((utc3Second - SYSTIMEZONE_UTC_DIFF_SECONDS) * 1000 + 999), TIMEZONE);
    }

}
