package com.garbageNotifiction.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Notification messages */
@Getter
@RequiredArgsConstructor
public enum NotificationMessageType {
  STICKER("Thank you for sending stamp ：）"),
  IMAGE("Thank you for sending image ：D"),
  VIDEO("Thank you for sending video XD"),
  AUDIO("Thank you for sending audio ；）"),
  BURNABLES("明日は燃えるゴミの日です。もう出しましたか？"),
  INCOMBUSTIBLE("明日は燃えないゴミの日です。もう出しましたか？"),
  KANBIN("明日はかん、ビン、ペットボトルの日です。もう出しましたか？"),
  CONFIRM("Did you take out the garbage?");


  private final String message;
}