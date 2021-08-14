package com.garbageNotification.app;

import java.net.URISyntaxException;


import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.garbageNotification.service.LineMessageService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/** Regularly executed task scheduler */
@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTask {
  public static final String TZ = "Asia/Tokyo";

  private static final transient DateTimeFormatter DATE_TIME_FORMAT =
      DateTimeFormatter.ofPattern("HH:mm:ss");

  private final LineMessageService lineMessagingService;

  @Scheduled(cron = "0 0 9-20 * * 1-7", zone = TZ)
  //@Scheduled(fixedDelay = 50000)
  public void executeBurnablesAlarm() {
    try {
      lineMessagingService.pushBurnablesAlarm();
    } catch (URISyntaxException e) {
      log.error("{}", e);
    }
    log.debug("cron executed : " + DATE_TIME_FORMAT.format(LocalDate.now(ZoneId.of(TZ))));
  }
}
