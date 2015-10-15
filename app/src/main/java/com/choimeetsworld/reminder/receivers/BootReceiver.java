package com.choimeetsworld.reminder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.choimeetsworld.reminder.enums.NotificationsType;
import com.choimeetsworld.reminder.utils.AlarmUtil;
import com.choimeetsworld.reminder.database.Database;
import com.choimeetsworld.reminder.models.Notification;
import com.choimeetsworld.reminder.utils.DateAndTimeUtil;

import java.util.Calendar;
import java.util.List;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Database database = new Database(context.getApplicationContext());
        List<Notification> notificationList = database.getNotificationList(NotificationsType.ACTIVE);
        database.close();
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);

        for (Notification notification : notificationList) {
            Calendar calendar = DateAndTimeUtil.parseDateAndTime(notification.getDateAndTime());
            calendar.set(Calendar.SECOND, 0);
            AlarmUtil.setAlarm(context, alarmIntent, notification.getId(), calendar);
        }
    }
}