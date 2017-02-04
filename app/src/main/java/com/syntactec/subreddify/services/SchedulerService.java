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

    private AlarmManager alarmManager;

    public SchedulerService() {
        super("SchedulerService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent serviceIntent = new Intent(this, PollerService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, serviceIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Log.d("SchedulerService", "Canceling the active alarm.");
        alarmManager.cancel(pendingIntent);

        String sync_frequency = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("sync_frequency", "-1");

        if (sync_frequency.equals("-1")) {
            return;
        }

        int syncMillis = 60000 * Integer.parseInt(sync_frequency);

        Log.d("SchedulerService", "Creating alarm manager: Preference is " + syncMillis);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime(),
                syncMillis,
                pendingIntent);
    }
}
