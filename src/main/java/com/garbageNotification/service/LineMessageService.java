package com.garbageNotification.service;

import com.garbageNotifiction.message.NotificationMessageType;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/** Line messaging service */
@Slf4j
@Service
@RequiredArgsConstructor
public class LineMessageService {
  private static final String YES = "はい";

  private static final String NO = "いいえ";

  private final LineMessagingClient lineMessagingClient;

  private String lineBotId = "Uaa745b628bf0321fbd241d6b94087db6";

  public void pushBurnablesAlarm() throws URISyntaxException {

	// 曜日取得
	  Calendar c = Calendar.getInstance();
	  int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

	// メッセージ
	  String mes = "";
	
	  switch(dayOfWeek) {
	  case Calendar.SATURDAY:
		  mes = NotificationMessageType.INCOMBUSTIBLE.getMessage();
		  break;
	  case Calendar.MONDAY :
		  mes = NotificationMessageType.BURNABLES.getMessage();
		  break;
	  case Calendar.WEDNESDAY:
		  mes = NotificationMessageType.KANBIN.getMessage();
		  break;
	  case Calendar.THURSDAY:
		  mes = NotificationMessageType.BURNABLES.getMessage();
		  break;

	  }
	  if(dayOfWeek != Calendar.FRIDAY || dayOfWeek != Calendar.SATURDAY || dayOfWeek != Calendar.TUESDAY) {
		  // 水曜は何もないのでスキップ
		    try {
		        ConfirmTemplate confirmTemplate =
		            new ConfirmTemplate(
		          	  mes,
		                new MessageAction(YES, YES),
		                new MessageAction(NO, NO));

		        TemplateMessage templateMessage =
		            new TemplateMessage(NotificationMessageType.BURNABLES.getMessage(), confirmTemplate);

		        PushMessage pushMessage = new PushMessage(lineBotId, templateMessage);

		        BotApiResponse response = lineMessagingClient.pushMessage(pushMessage).get();
		        log.debug("Sent messages: {}", response);
		      } catch (InterruptedException | ExecutionException e) {
		        throw new RuntimeException(e);
		      }	  
	  }
  }
}
