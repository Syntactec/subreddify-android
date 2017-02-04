package com.syntactec.subreddify.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * This class defines a service which will schedule the notification service to run at the user defined interval.
 */
public class SchedulerService extends IntentService {

    public SchedulerService() {
        super("SchedulerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int syncMillis = 60000 * PreferenceManager.getDefaultSharedPreferences(this)
                .getInt("sync_frequency", -1);

        if (syncMillis == -1) {
            return;
        }

        Intent serviceIntent = new Intent(this, PollerService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, serviceIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Log.d("SchedulerService", "Creating alarm manager: Preference is " + syncMillis);
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime(),
                syncMillis,
                pendingIntent);
    }
}
