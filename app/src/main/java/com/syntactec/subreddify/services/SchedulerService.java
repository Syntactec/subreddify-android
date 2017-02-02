package com.syntactec.subreddify.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * This class defines a service which will schedule the notification service to run at the user defined interval.
 */
public class SchedulerService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int syncMillis = 60000 * PreferenceManager.getDefaultSharedPreferences(this)
                .getInt("sync_frequency", 15);

        Intent serviceIntent = new Intent(this, PollerService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, serviceIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime(),
                syncMillis,
                pendingIntent);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
