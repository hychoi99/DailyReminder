package com.choimeetsworld.reminder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.choimeetsworld.reminder.database.Database;
import com.choimeetsworld.reminder.models.Notification;
import com.choimeetsworld.reminder.utils.NotificationUtil;

public class SnoozeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Database database = new Database(context.getApplicationContext());
        Notification notification = database.getNotification(intent.getIntExtra("NOTIFICATION_ID", 0));
        NotificationUtil.createNotification(context, notification, intent);
        database.close();
    }
}