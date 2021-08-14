package com.garbageNotification.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garbageNotification.service.LineMessageService;

import java.net.URISyntaxException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/alarm")
public class LineMessagingController {

  private final LineMessageService lineMessageService;

  @GetMapping("burnables")
  public void pushBurnablesAlarm() {
    try {
    	lineMessageService.pushBurnablesAlarm();
    } catch (URISyntaxException e) {
      log.error("", e);
    }
  }
}
